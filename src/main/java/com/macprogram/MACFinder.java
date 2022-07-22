package com.macprogram;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

// https://www.javatpoint.com/java-swing -- as a reference for learning
// https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html -- layouts...

public class MACFinder extends JFrame implements ActionListener
{
	// constants for button/textfield sizes etc..
	private final int BUTTON_WIDTH = 100;
	private final int BUTTON_HEIGHT = 20;
	private final int TEXTFIELD_WIDTH = 100;
	private final int TEXTFIELD_HEIGHT = 20;

	static JTextField student_ID;
	static JTextField first_name;
	static JTextField last_name;
	JButton confirmButton, cancelButton;
	JPanel panel;

	InetAddress localHost;

	public MACFinder()
	{	
		// FRAME 
		setSize(640, 400);
		setLocation(750, 750);
		setTitle("Robot Setup");
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); // border layout is good
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("/Users/johnlloyd/Documents/Coding/Brunel Internship/BrunelLogo.jpg").getImage());

		// IMAGE
		//		ImageIcon icon = new ImageIcon("/Users/johnlloyd/Documents/Coding/Brunel Internship/BrunelLogo.jpg");
		//		Image scaleImage = icon.getImage().getScaledInstance(28, 28,Image.SCALE_DEFAULT);
		//		getContentPane().add(new JLabel(new ImageIcon("/Users/johnlloyd/Documents/Coding/Brunel Internship/BrunelLogo.jpg")));

		// TEXTFIELDS
		this.student_ID = new JTextField(); // should only take numbers (equal to 7 digits)
		student_ID.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		student_ID.setHorizontalAlignment(JTextField.CENTER);
		student_ID.addKeyListener(new KeyAdapter() 
		{
			public void keyPressed(final KeyEvent ke) 
			{
				final String value = student_ID.getText();
				final int l = value.length();
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') // -- need to include backspace && ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) 
				{
					student_ID.setEditable(true);
				} 
				else 
				{
					student_ID.setEditable(false);
					System.out.println("* Enter only numeric digits(0-9)");
				}
			}
		});
		add(student_ID, Component.CENTER_ALIGNMENT); 

		first_name = new JTextField();
		first_name.addKeyListener(new KeyAdapter() 
		{
			public void keyPressed(final KeyEvent ke) 
			{
				final String value = first_name.getText();
				final int l = value.length();
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') 
				{
					first_name.setEditable(false);
					System.out.println("Error, only input letters");
				} 
				else 
				{
					first_name.setEditable(true);
				}
			}
		});
		first_name.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		first_name.setHorizontalAlignment(JTextField.CENTER);
		add(first_name, Component.CENTER_ALIGNMENT); 

		last_name = new JTextField();
		last_name.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		last_name.setHorizontalAlignment(JTextField.CENTER);
		add(last_name, Component.CENTER_ALIGNMENT); 

		// BUTTONS
		confirmButton = new JButton("Confirm");
		//confirmButton.setBounds(50, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
		add(confirmButton, Component.CENTER_ALIGNMENT);
		confirmButton.addActionListener(this);

		cancelButton = new JButton("Cancel");
		//cancelButton.setBounds(50, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
		add(cancelButton, Component.RIGHT_ALIGNMENT);

		setVisible(true); 
	}

	public static void main(final String[] args) throws Exception
	{
		System.out.println("Welcome... starting GUI");

		final MACFinder GM = new MACFinder();

		getMac();

		System.out.println();

		//QRCodeGenerator qrgen = new QRCodeGenerator();

		//GM.generateQRCodeImage();
	}

//	public void generateQRCodeImage() throws Exception
//	{
//		// creates JSON object...
//		JSONObject JSONobj = new JSONObject();
//
//		JSONobj.put("student_info", new String[] { 
//				student_ID.getText(), 
//				first_name.getText(), 
//				last_name.getText(),
//				getMac()});
//
//		// With four indent spaces
//		//System.out.println(JSONobj.toString(4));
//		
//		//final String data = "https://www.codevoila.com/post/65/java-json-tutorial-and-example-json-java-orgjson";
//		final String data = JSONobj.toString(4);
//		final String filepath = ".//QRcode.png";
//
//		QRCodeWriter qrCodeWriter = new QRCodeWriter();
//		BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 450, 450);
//
//		final Path path = FileSystems.getDefault().getPath(filepath);
//		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
//
//		System.out.println("QR Code succuessfully generated...");
//	}

//	// get rid of static ...
//	private static void createJSON() throws JSONException, Exception 
//	{    		
//		// creates JSON object...
//		JSONObject JSONobj = new JSONObject();
//
//		JSONobj.put("student_info", new String[] { 
//				student_ID.getText(), 
//				first_name.getText(), 
//				last_name.getText(),
//				getMac()});
//
//		// With four indent spaces
//		System.out.println(JSONobj.toString(4));
//	}

	@Override
	public void actionPerformed(final ActionEvent e)
	{
		if (student_ID.getText().equals(""))
		{
			System.out.println("Error, please enter your student ID");
		}
		else if (student_ID.getText().length() != 7)
		{
			System.out.println("Error, your student ID must be 7 digits in length");
		}
		else if (first_name.getText().equals(""))
		{
			System.out.println("Error, please enter your first name");
		}
		else if (last_name.getText().equals(""))
		{
			System.out.println("Error, please enter your last name");
		}
		else
		{
			// creates JSON object...
			JSONObject JSONobj = new JSONObject();

			try 
			{
				JSONobj.put("student_info", new String[] { 
						student_ID.getText(), 
						first_name.getText(), 
						last_name.getText(),
						getMac()});
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
			
			//final String data = "https://www.codevoila.com/post/65/java-json-tutorial-and-example-json-java-orgjson";
			final String data = JSONobj.toString(4);
			final String filepath = ".//QRcode.png";

			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix;
			try 
			{
				bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 450, 450);
				
				final Path path = FileSystems.getDefault().getPath(filepath);
				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

				System.out.println("QR Code succuessfully generated...");
			} 
			catch (WriterException | IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}    

	public static String getMac() throws Exception
	{		
		// gets Internet address of local machine
		final InetAddress ip = InetAddress.getLocalHost();
		//System.out.println("Current IP address: " + ip);

		// get network interface that has ip address bound to it
		final NetworkInterface network = NetworkInterface.getByInetAddress(ip);

		// get MAC address from the network interface in byte
		final byte[] mac = network.getHardwareAddress();

		// displays MAC address
		final StringBuilder sb = new StringBuilder();
		for(int i = 0; i < mac.length; i++)
		{
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}

		return ("MAC address: " + sb.toString());

		//System.out.println("MAC address: " + sb.toString());
	}
}
