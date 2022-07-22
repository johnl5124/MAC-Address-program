package com.macprogram;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator 
{
	//ProgramMainInterface student_info = new ProgramMainInterface();
	MACFinder macObj = new MACFinder();

	public void generateQR() throws Exception
	{
		// creates JSON object...
		JSONObject JSONobj = new JSONObject();

		try 
		{
			JSONobj.put("student_info", new String[] { 
					ProgramMainInterface.getStudentID(),
					ProgramMainInterface.getFirstName(),
					ProgramMainInterface.getLastName(),
					macObj.getMac()});
		} 
		catch (JSONException e2) 
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e2) 
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// With four indent spaces
		//System.out.println(JSONobj.toString(4));

		final String data = JSONobj.toString(4);
		final String filepath = ".//QRcode.png";

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix;

		bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 450, 450);

		final Path path = FileSystems.getDefault().getPath(filepath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	}
}

