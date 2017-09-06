
package com.mcoding.comp.wechat.account.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.security.KeyStore;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ssl.SSLContexts;

import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.account.utils.WechatTokenOrTicketManager;
import com.mcoding.comp.wechat.common.WxConstant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.ApacheHttpClientBuilder;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;

@ApiModel(value="微信账号表")
public class AccountConfig implements Serializable, WxMpConfigStorage {
    /**###################以下是非自动生成的###############################**/
    protected volatile String oauth2redirectUri;
	private String tradeType;

	protected volatile SSLContext sslContext;
	protected volatile ApacheHttpClientBuilder apacheHttpClientBuilder;
	
	protected Lock accessTokenLock = new ReentrantLock();
	protected Lock jsapiTicketLock = new ReentrantLock();
	protected Lock cardApiTicketLock = new ReentrantLock();
	
	@Override
	public String getAccessToken() {
		return WechatTokenOrTicketManager.getInstance().getAccessToken(this.id);
	}

	@Override
	public boolean isAccessTokenExpired() {
	    return System.currentTimeMillis() > WechatTokenOrTicketManager.getInstance().getAccessTokenExpiresTime(this.id);
	}

	@Override
	public void expireAccessToken() {
		WechatTokenOrTicketManager.getInstance().expireAccessToken(this.id);
	}

	@Override
	public synchronized void updateAccessToken(WxAccessToken accessToken) {
	    updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
	}

	@Override
	public synchronized void updateAccessToken(String accessToken, int expiresIn) {
		WechatTokenOrTicketManager.getInstance().updateAccessToken(this.id, accessToken, System.currentTimeMillis() + (expiresIn - 200) * 1000l);
	}

	@Override
	public String getJsapiTicket() {
		return WechatTokenOrTicketManager.getInstance().getJsapiTicket(this.id);
	}

	@Override
	public boolean isJsapiTicketExpired() {
	    return System.currentTimeMillis() > WechatTokenOrTicketManager.getInstance().getJsapiTicketExpiresTime(this.id);
	}

	@Override
	public void expireJsapiTicket() {
		WechatTokenOrTicketManager.getInstance().expireJsapiTicket(this.id);;
	}

	@Override
	public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
		WechatTokenOrTicketManager.getInstance().updateJsapiTicket(this.id, jsapiTicket, System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l);
	}
	
	public String getCardApiTicket(){
		return WechatTokenOrTicketManager.getInstance().getCardApiTicket(this.id);
	}
	
	public boolean isCardApiTicketExpired(){
		return System.currentTimeMillis() > WechatTokenOrTicketManager.getInstance().getCardApiTicketExpiresTime(this.id);
	}
	
	public void expireCardApiTicket(){
		WechatTokenOrTicketManager.getInstance().expireCardApiTicket(this.id);
	}
	
	public void updateCardApiTicket(String cardApiTicket, int expiresInSeconds){
		WechatTokenOrTicketManager.getInstance().updateCardApiTicket(this.id, cardApiTicket, System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l);
	}

	@Override
	public String getSecret() {
		return this.appSecret;
	}

	@Override
	public String getPartnerId() {
		return this.mchId;
	}

	@Override
	public String getPartnerKey() {
		return this.mchKey;
	}

	@Override
	public long getExpiresTime() {
		return WechatTokenOrTicketManager.getInstance().getAccessTokenExpiresTime(this.id);
	}

	@Override
	public String getOauth2redirectUri() {
		return this.oauth2redirectUri;
	}

	@Override
	public File getTmpDirFile() {
		Properties properties = SpringContextHolder.getBean("sysConfig");
		String filePath = properties.getProperty("resource.location");
		if(StringUtils.isNotBlank(filePath)){
			return new File(filePath);
		}else{
			return null;
		}
	}

	@Override
	public Lock getAccessTokenLock() {
		return this.accessTokenLock;
	}

	@Override
	public Lock getJsapiTicketLock() {
		return this.jsapiTicketLock;
	}

	@Override
	public Lock getCardApiTicketLock() {
		return this.cardApiTicketLock;
	}

	@Override
	public String getNotifyURL() {
		return this.getDomain() + WxConstant.URL_PAID_NOTIFY;
	}
	
    public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	@Override
	public String getTradeType() {
		return this.tradeType;
	}

	@Override
	public String getHttpProxyHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHttpProxyPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getHttpProxyUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHttpProxyPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
		return this.apacheHttpClientBuilder;
	}

	@Override
	public boolean autoRefreshToken() {
		return true;
	}
	
	@Override
	public SSLContext getSslContext() {
		if (this.sslContext != null) {
			return this.sslContext;
		}
		
		if (StringUtils.isBlank(this.getPartnerId()) || StringUtils.isBlank(this.certPath)) {
			return null;
		}
	
		File file = new File(this.certPath);
	    if (!file.exists()) {
	        throw new RuntimeException("证书文件：【" + file.getPath() + "】不存在！");
	    }

	    try {
	        FileInputStream inputStream = new FileInputStream(file);
	        KeyStore keystore = KeyStore.getInstance("PKCS12");
	        char[] partnerId2charArray = this.getPartnerId().toCharArray();
	        keystore.load(inputStream, partnerId2charArray);
	        this.sslContext = SSLContexts.custom().loadKeyMaterial(keystore, partnerId2charArray).build();
	    } catch (Exception e) {
	      throw new RuntimeException("证书文件有问题，请核实！", e);
	    }
		    
		return this.sslContext;
	}

	@Override
	public void setSslContext(SSLContext sslContext) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setSslContextFilePath(String filePath) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean useSandboxForWxPay() {
		return false;
	}
    /**###################以下是非自动生成的， 结束###############################**/


    private Integer id;

    @ApiModelProperty("公众号 名字")
    private String name;

    @ApiModelProperty("公众号类型，1服务号，2订阅号，3企业号")
    private Integer accountType;

    @ApiModelProperty("微信号")
    private String code;

    @ApiModelProperty("公众号 原始id")
    private String originId;

    private String appId;

    private String appSecret;

    private String token;

    private String aesKey;

    @ApiModelProperty("加密模式，1明文模式，2兼容模式，3安全模式")
    private Integer encryptType;

    @ApiModelProperty("是否支持微信支付，1支持，0不支持")
    private Integer isPayEnable;

    @ApiModelProperty("微信支付，商户id")
    private String mchId;

    @ApiModelProperty("微信支付,商户key")
    private String mchKey;

    @ApiModelProperty("公众号服务器域名")
    private String domain;

    @ApiModelProperty("微信支付后的回调url，不带servername，path，默认wechatPay/notifyUrl")
    private String wxpayNotifyUrl;

    @ApiModelProperty("商户证书的存储路径")
    private String certPath;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId == null ? null : originId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey == null ? null : aesKey.trim();
    }

    public Integer getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(Integer encryptType) {
        this.encryptType = encryptType;
    }

    public Integer getIsPayEnable() {
        return isPayEnable;
    }

    public void setIsPayEnable(Integer isPayEnable) {
        this.isPayEnable = isPayEnable;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey == null ? null : mchKey.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getWxpayNotifyUrl() {
        return wxpayNotifyUrl;
    }

    public void setWxpayNotifyUrl(String wxpayNotifyUrl) {
        this.wxpayNotifyUrl = wxpayNotifyUrl == null ? null : wxpayNotifyUrl.trim();
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath == null ? null : certPath.trim();
    }

}