/**
 * Copyright (c) 2014-2016 www.nryuncang.com. All Rights Reserved.
 */
package com.zwz;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * 
 *
 * @author <a href=mailto:zhangweize@nryuncang.com>zhangweize</a>
 * @version 1.0.0
 */
public class QRCodeUtilSimple {
	/**
	 * ���ɶ�ά��
	 * 
	 *
	 * @param content
	 * @param imgPath
	 * @throws IOException [����˵��]
	 * @author <a href=mailto:zhangweize@nryuncang.com>zhangweize</a>
	 * @version 1.0.0
	 */
	public static void encoderQRCode(String content, String imgPath) throws IOException {
		Qrcode qrcodeHandler = new Qrcode();
		// ���ö�ά���Ŵ��ʣ���ѡL(7%)��M(15%)��Q(25%)��H(30%)���Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС
		qrcodeHandler.setQrcodeErrorCorrect('H');
		qrcodeHandler.setQrcodeEncodeMode('B');
		qrcodeHandler.setQrcodeVersion(5);
		// int imgSize = 67 + 12 * (size - 1);
		
		byte[] contentBytes = content.getBytes("UTF-8");
		BufferedImage bufImg = new BufferedImage(115, 115, BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufImg.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.clearRect(0, 0, 115, 115);
		// �趨ͼ����ɫ> BLACK
		gs.setColor(Color.BLACK);
		// ����ƫ���� �����ÿ��ܵ��½�������
		int pixoff = 2;
		// �������> ��ά��
		if (contentBytes.length > 0 && contentBytes.length < 800) {
			boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
			for (int i = 0; i < codeOut.length; i++) {
				for (int j = 0; j < codeOut.length; j++) {
					if (codeOut[j][i]) {
						gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
					}
				}
			}
		} else {
			System.err.println("QRCode content bytes length = " + contentBytes.length + " not in [ 0,120 ]. ");
		}
		gs.dispose();
		bufImg.flush();
		File imgFile = new File(imgPath);
		// ���ɶ�ά��QRCodeͼƬ
		ImageIO.write(bufImg, "png", imgFile);
	}

	
	/**
	 * 
	 *
	 * @param args
	 *            [����˵��]
	 * @author <a href=mailto:zhangweize@nryuncang.com>zhangweize</a>
	 * @version 1.0.0
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date d = new Date();
		String str = sdf.format(d);
		String imgPath = "F:/" + str + ".png";
		String content = "http://www.163.com/";
		encoderQRCode(content, imgPath);
		System.out.println("imgPath:" + imgPath);

		System.out.println("encoder QRcode success");
	}

}
