package com.mcoding.base.dst.service.promotion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mcoding.base.dst.bean.promotion.DstPromotProductPoster;
import com.mcoding.base.dst.utils.DstUtils;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.ui.bean.attachment.Attachment;
import com.mcoding.base.ui.service.attachment.AttachmentService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.utils.image.ImageUtils;
import com.mcoding.base.utils.qrcode.QrcodeUtils;

@Component
public class ProductPromoter {

	private static final String qrcodebgPath = "promotion/qrcodebg.png";

	@Value("#{sysConfig['resource.location']}")
	protected String resourcePath;

	@Resource
	protected AttachmentService attachmentService;

	public void createQrcode(Member distributor, DstPromotProductPoster poster, OutputStream outputStream)
			throws IOException {
		if (poster == null || distributor == null || outputStream == null) {
			throw new NullPointerException("制作产品推广二维码失败，因为传入参数为空,member[" + distributor + "],poster[" + poster
					+ "],outputstream[" + outputStream + "]");
		}

		if (poster.getTemplateImgId() == null) {
			throw new NullPointerException("找不到产品的推广海报");
		}

		Attachment attchment = this.attachmentService.queryObjById(poster.getTemplateImgId());
		if (attchment == null || StringUtils.isBlank(attchment.getFilePath())) {
			throw new NullPointerException("找不到产品的推广海报");
		}
		
		this.resourcePath = this.resourcePath.replaceAll("\\/$", "") + File.separator;
		String filePath = this.resourcePath + attchment.getFilePath() + attchment.getFileRename();

		File posterTemplate = new File(filePath);
		if (!posterTemplate.exists() || !posterTemplate.isFile()) {
			throw new FileNotFoundException("产品的推广海报文件不存在");
		}

		// 生成二维码
		String domain = StoreUtils.getStoreFromThreadLocal().getStoreDomain().split(";")[0];
		domain = domain.replaceAll("/$", "");
//		String qrcodeContent = domain + productUrl + "/"+poster.getProductId()+"/" + distributor.getId();
		String qrcodeContent = domain + DstUtils.createProductPromotionUrl(poster.getProductId(), distributor.getId());
		int qrcodeWidth = 257;
		BufferedImage qrcode = QrcodeUtils.writeQrcode(qrcodeContent, "UTF-8", qrcodeWidth);

		// 生成模板下面的二维码栏
		File blankBarFile = new File(this.resourcePath + qrcodebgPath);
		BufferedImage blankBar = ImageIO.read(blankBarFile);
		
		blankBar = ImageUtils.contactCenter(blankBar, qrcode, 46, 72);

		// 合并成新的图片

		BufferedImage templateImage = ImageIO.read(posterTemplate);
		BufferedImage newImage = ImageUtils.contactVertical(templateImage, blankBar);

		ImageIO.write(newImage, "jpg", outputStream);
	}

}
