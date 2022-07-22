package com.macprogram;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator 
{
	public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception 
	{
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = 
				barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}

	//	public String generateQRCodeImage() throws Exception
	//	{
	//		final String data = "https://www.youtube.com/watch?v=qzYpgbP8RHA";
	//		final String filepath = "C:\\Users\\JmJ23\\Documents\\Code\\Internship Code\\QRcode.png";
	//
	//		QRCodeWriter qrCodeWriter = new QRCodeWriter();
	//		BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 250, 250);
	//
	//		final Path path = FileSystems.getDefault().getPath(filepath);
	//		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	//		
	//		return "QR Code successfully generated...";
	//	}
}

