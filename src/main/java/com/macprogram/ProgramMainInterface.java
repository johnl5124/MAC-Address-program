package com.macprogram;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ProgramMainInterface extends JFrame implements ActionListener
{
	// constants for button/textfield sizes etc..
	private final int BUTTON_WIDTH = 100;
	private final int BUTTON_HEIGHT = 20;
	private final int TEXTFIELD_WIDTH = 100;
	private final int TEXTFIELD_HEIGHT = 20;

	private static JTextField student_ID, first_name, last_name;
	JButton confirmButton, cancelButton;
	JPanel panel;

	public ProgramMainInterface()
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
		student_ID = new JTextField(); // should only take numbers (equal to 7 digits)
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
	
	public static String getStudentID()
	{	
		return student_ID.getText();
	}
	
	public static String getFirstName()
	{
		return first_name.getText();
	}
	
	public static String getLastName()
	{
		return last_name.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
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
			QRCodeGenerator obj = new QRCodeGenerator();
			
			try 
			{
				obj.generateQR();
				
				JOptionPane.showMessageDialog(null, "QR code generated, check your desktop.", "Program message...", 1);
			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
