package com.mcoding.base.utils.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QrcodeUtils {
	
	public static final int MAX_SIZE = 2331;
	public static final int MIN_UNIT = 1;
	public static final int PIXOFF = 2; // 设置偏移量 不设置可能导致解析出错

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content 注入二维码的内容
	 * @param width 图片的大小
	 * @param charsetName 内容的编码
	 * @return 生成图片
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static BufferedImage writeQrcode(String content, String charsetName, int width)
			throws UnsupportedEncodingException {
		
		byte[] contentBytes = content.getBytes(charsetName);
		int contentLength = contentBytes.length;
		
		if (contentLength > MAX_SIZE) {
			throw new IllegalArgumentException("内容长度超过二维码存储上限 2331 byte，无法生成二维码。");
		}

		int version = getVersionFormLength(contentLength); 

		Qrcode qrcodeHandler = new Qrcode();
		qrcodeHandler.setQrcodeErrorCorrect('M');
		qrcodeHandler.setQrcodeEncodeMode('B');
		qrcodeHandler.setQrcodeVersion(version);
		
		boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
		int codeLength = codeOut.length;
		
		int minWidth = MIN_UNIT * codeLength + PIXOFF;
		if (width < minWidth) {
			throw new IllegalArgumentException("宽度过少，请重新设置。当前内容下最小宽度是"+minWidth+"，179的宽度，能满足所有的设置。");
		}

		double unitSizeDouble = ((double)(width - PIXOFF)) / codeLength;
		int unitSize = (int) Math.floor(unitSizeDouble);
		int height = width;
		
		BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufImg.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.clearRect(0, 0, width, height);
		gs.setColor(Color.BLACK); // 设定图像颜色> BLACK

		// 输出内容> 二维码
//		if (contentBytes.length <= 0 || contentBytes.length >= 120) {
//			throw new RuntimeException("QRCode content bytes length = " + contentBytes.length + " not in [ 0,120 ]. ");
//		}
		
		for (int i = 0; i < codeOut.length; i++) {
			for (int j = 0; j < codeOut.length; j++) {
				if (!codeOut[j][i]) {
					continue;
				}
				gs.fillRect(j * unitSize + PIXOFF, i * unitSize + PIXOFF, unitSize, unitSize);
			}
			
		}

		gs.dispose();
		bufImg.flush();

		return bufImg;
	}
	
	/**
	 * 各个版本下的最大存储量，参考 http://www.qrcode.com/zh/about/version.html
	 * @param version
	 * @return
	 */
	private static int getVersionFormLength(int length){
		if (length> 0 && length <=14) {
			return 1;
		}else if (length> 14 && length <=26) {
			return 2;
		}else if (length >26 && length <=42) {
			return 3;
		}else if (length >42 && length <=62) {
			return 4;
		}else if (length >62 && length <=84) {
			return 5;
		}else if (length >84 && length <=106) {
			return 6;
		}else if (length >106 && length <=122) {
			return 7;
		}else if (length >122 && length <=152) {
			return 8;
		}else if (length >152 && length <=180) {
			return 9;
		}else if (length >180 && length <=213) {
			return 10;
		}else if (length >213 && length <=251) {
			return 11;
		}else if (length >251 && length <=287) {
			return 12;
		}else if (length >287 && length <=331) {
			return 13;
		}else if (length >331 && length <=362) {
			return 14;
		}else if (length >362 && length <=412) {
			return 15;
		}else if (length >412 && length <=450) {
			return 16;
		}else if (length >450 && length <=504) {
			return 17;
		}else if (length >504 && length <=560) {
			return 18;
		}else if (length >560 && length <=624) {
			return 19;
		}else if (length >624 && length <=666) {
			return 20;
		}else if (length >666 && length <=711) {
			return 21;
		}else if (length >711 && length <=779) {
			return 22;
		}else if (length >779 && length <=857) {
			return 23;
		}else if (length >857 && length <=911) {
			return 24;
		}else if (length >911 && length <=997) {
			return 25;
		}else if (length >997 && length <=1059) {
			return 26;
		}else if (length >1059 && length <=1125) {
			return 27;
		}else if (length >1125 && length <=1190) {
			return 28;
		}else if (length >1190 && length <=1264) {
			return 29;
		}else if (length >1264 && length <=1370) {
			return 30;
		}else if (length >1370 && length <=1452) {
			return 31;
		}else if (length >1452 && length <=1538) {
			return 32;
		}else if (length >1538 && length <=1628) {
			return 33;
		}else if (length >1628 && length <=1722) {
			return 34;
		}else if (length >1722 && length <=1809) {
			return 35;
		}else if (length >1809 && length <=1911) {
			return 36;
		}else if (length >1911 && length <=1989) {
			return 37;
		}else if (length >1989 && length <=2099) {
			return 38;
		}else if (length >2099 && length <=2213) {
			return 39;
		}else if (length >2213 && length <=2331) {
			return 40;
		}
        
		throw new IllegalArgumentException("信息长度超长，无法转成二维码");
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content 注入二维码的内容
	 * @param charsetName 内容的编码
	 * @param width 图片的大小
	 * @param out 图片的输出流
	 * @return
	 * @throws IOException
	 */
	public static boolean writeQrcode(String content, String charsetName, int width, OutputStream out)
			throws IOException {
		BufferedImage bufImg = writeQrcode(content, charsetName, width);
		return ImageIO.write(bufImg, "png", out);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content 注入二维码的内容
	 * @param charsetName 内容的编码
	 * @param width 图片的大小
	 * @param out 图片的输出流
	 * @return
	 * @throws IOException
	 */
	public static boolean writeQrcode(String content, String charsetName, int width, File out) throws IOException {
		BufferedImage bufImg = writeQrcode(content, charsetName, width);
		return ImageIO.write(bufImg, "png", out);
	}

	public static void main(String[] args) throws IOException {
		 File file = new File("C:\\Users\\hzy\\Desktop\\abc.jpg");
//		 String content = "1234567890123456";
		 String content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4cd8d60cd66ac9e3&redirect_uri=http%3A%2F%2Fhzywx.wicp.net%2FEMIS%2FWechatAPI%2Foauth2Openid%2FITBwITHtTX-z7nHz7W6z7nIqUuiW2d7sC38QAYyOAY21iYOaiYXpB3XrK6y5ITHtITIRITHt2O1z7nHz7W6z7nIDBdyrITi_ITIcITIcB9NeC3XGvU8zA9NnPYWz7oKeT3OFPw1tye_tPYyXvZylvYbVBc2VBwNDBcXFITicAdIqKd2nB6zoITi67uoz7nHz7o7z7nIwft1t791ufk1t7oXkT1Dz7nHziWf&response_type=code&scope=snsapi_base#wechat_redirect";
		 BufferedImage image = writeQrcode(content, "UTF-8", 83);
		
		 ImageIO.write(image, "jpg", file);
//		byte[] contentBytes = "".getBytes("UTF-8");
//		System.out.println(contentBytes.length);

	}

}
