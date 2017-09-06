package com.mcoding.base.dst.utils;

public class DstUtils {
	
	public static final String PREFIX_FOR_UNLIMIT = "UNLIMIT_DST_PROMOTE";
	public static final String PRODUCT_PROMOTION_URL = "/distributor/front/toProductPromotion";
	public static final String PRODUCT_INFO_URL = "/mMall/product_detail.html?productId=";
	
	/**
	 * 创建个人的推广二维码的key
	 * @param accountId
	 * @param distributorId
	 * @return
	 */
	public static String createQrcodeKey(int accountId, int distributorId){
		return PREFIX_FOR_UNLIMIT + "_" + accountId + "_" + distributorId;
	}
	
	/**
	 * 从二维码key值中，获取分销商id
	 * @param qrcodeKey
	 * @return
	 */
	public static Integer getDistributorIdFromKey(String qrcodeKey){
		qrcodeKey = qrcodeKey.replaceAll(PREFIX_FOR_UNLIMIT + "_", "");
		String memberIdStr = qrcodeKey.split("_")[1];
		return Integer.valueOf(memberIdStr);
	}

	/**
	 * 创建产品推广海报的二维码的内容
	 * @param productId
	 * @param distributorId
	 * @return
	 */
	public static String createProductPromotionUrl(int productId, int distributorId){
		return new StringBuffer(PRODUCT_PROMOTION_URL).append("/").append(productId).append("/").append(distributorId).toString();
	}
	
	/**
	 * 创建产品信息页面的地址
	 * @param productId
	 * @return
	 */
	public static String createProductInfoUrl(int productId){
		return PRODUCT_INFO_URL + productId;
	}
	
}
