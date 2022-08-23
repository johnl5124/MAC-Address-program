package com.macprogram;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class ProgramMainInterface extends JFrame {

	JFrame mainwindow;
	private final int BUTTON_WIDTH = 20, BUTTON_HEIGHT = 20,
			TEXTFIELD_WIDTH = 350, TEXTFIELD_HEIGHT = 20,
			LABEL_WIDTH = 200, LABEL_HEIGHT = 20;
	final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
	private static JTextField stuID_input, firstname_input, lastname_input;
	JButton submitBtn, clearBtn;
	JPanel mainpanel, textpanel, imagepanel, btnpanel;
	JLabel stuID_label,
			firstname_label,
			lastname_label,
			photoholder,
			programtitle,
			stuID_error,
			firstname_error,
			lastname_error;
	JCheckBox stuCheck,
			firstnameCheck,
			lastnameCheck;

	public ProgramMainInterface() {
		// ------------------------------------- STUDENT ID ELEMENTS -------------------------------------
		stuID_label = new JLabel("Please enter your student ID:", JLabel.CENTER);
		stuID_label.setSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		stuID_label.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		stuID_label.setMaximumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		stuID_label.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));

		stuID_input = new JTextField();
		stuID_input.setSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		stuID_input.setMinimumSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		stuID_input.setMaximumSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		stuID_input.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		stuID_input.setHorizontalAlignment(JTextField.CENTER);

		stuID_error = new JLabel("");
		stuID_error.setSize(new Dimension(50, LABEL_HEIGHT));
		stuID_error.setMinimumSize(new Dimension(50, LABEL_HEIGHT));
		stuID_error.setMaximumSize(new Dimension(50, LABEL_HEIGHT));
		stuID_error.setPreferredSize(new Dimension(50, LABEL_HEIGHT));
		stuID_error.setVisible(false);
		stuCheck = new JCheckBox();
		stuCheck.setEnabled(false);

		// ------------------------------------- FIRST NAME ELEMENTS -------------------------------------
		firstname_label = new JLabel("Please enter your first name:", JLabel.CENTER);
		firstname_label.setSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		firstname_label.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		firstname_label.setMaximumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		firstname_label.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));

		firstname_input = new JTextField(JTextField.CENTER);
		firstname_input.setSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		firstname_input.setMinimumSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		firstname_input.setMaximumSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		firstname_input.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		firstname_input.setHorizontalAlignment(JTextField.CENTER);

		firstname_error = new JLabel("");
		firstname_error.setSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		firstname_error.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		firstname_error.setMaximumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		firstname_error.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		firstname_error.setVisible(false);
		firstnameCheck = new JCheckBox();
		firstnameCheck.setEnabled(false);

		// ------------------------------------- LAST NAME ELEMENTS -------------------------------------
		lastname_input = new JTextField(JTextField.CENTER);
		lastname_input.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		lastname_input.setHorizontalAlignment(JTextField.CENTER);
		lastname_error = new JLabel("");
		lastname_error.setSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		lastname_error.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		lastname_error.setMaximumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		lastname_error.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
		lastname_error.setVisible(false);
		lastnameCheck = new JCheckBox();
		lastnameCheck.setEnabled(false);
		lastname_label = new JLabel("Please enter your last name:", JLabel.CENTER);
		lastname_label.setSize(LABEL_WIDTH, LABEL_HEIGHT);

		// BUTTONS
		submitBtn = new JButton("Submit");
		submitBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		submitBtn.setHorizontalAlignment(JTextField.CENTER);
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (stuID_input.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Error, please enter your student ID");
				} else if (stuID_input.getText().length() != 7) {
					JOptionPane.showMessageDialog(null, "Error, your student ID must be 7 digits in length");
				} else if (onlyNumbers(stuID_input.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Error, make sure your student ID contains only digits");
				} else if (firstname_input.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Error, please enter your first name");
				} else if (onlyLetters(firstname_input.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Error, make sure your first name contains only letters");
				} else if (lastname_input.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Error, please enter your last name");
				} else if (onlyLetters(lastname_input.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Error, make sure your last name contains only letters");
				} else {
					QRCodeGenerator obj = new QRCodeGenerator();

					try {
						obj.generateQR();
						File desktopPath = FileSystemView.getFileSystemView().getHomeDirectory();
						desktopPath.getAbsolutePath();
						JOptionPane.showMessageDialog(null,
								"QR code generated, check your Desktop: (" + desktopPath + ")",
								"Program message...", 1);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		// ------------------------------------- MAIN PANEL CREATION -------------------------------------
		mainpanel = new JPanel();
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// ------------------------------------- BRUNEL LOGO IMAGE -------------------------------------
		imagepanel = new JPanel();
		photoholder = new JLabel();
		photoholder.setSize(600, 300);
		photoholder.setHorizontalAlignment(SwingConstants.CENTER);
		photoholder.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon logo = new ImageIcon("C:/Users/JmJ23/Documents/Code/Internship Code/brunellogo.jpg");
		Image logoimage = logo.getImage();
		Image modifiedlogo = logoimage.getScaledInstance(photoholder.getWidth(), photoholder.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon scaledlogo = new ImageIcon(modifiedlogo);
		photoholder.setIcon(scaledlogo);
		imagepanel.setOpaque(false);
		imagepanel.add(photoholder);

		textpanel = new JPanel();
		textpanel.setOpaque(false);
		textpanel.setLayout(new GridBagLayout());
		GridBagConstraints txtConstaints = new GridBagConstraints();

		txtConstaints.gridx = 0;
		txtConstaints.gridy = 0;
		txtConstaints.gridwidth = 1;
		txtConstaints.gridheight = 1;
		textpanel.add(stuID_label, txtConstaints);

		txtConstaints.gridx = 1;
		txtConstaints.gridy = 0;
		txtConstaints.gridwidth = 1;
		txtConstaints.gridheight = 1;
		textpanel.add(stuID_input, txtConstaints);

		txtConstaints.gridx = 2;
		txtConstaints.gridy = 0;
		txtConstaints.gridwidth = 1;
		txtConstaints.gridheight = 1;
		textpanel.add(stuCheck, txtConstaints);

		txtConstaints.gridx = 3;
		txtConstaints.gridy = 0;
		txtConstaints.gridwidth = 1;
		txtConstaints.gridheight = 1;
		textpanel.add(stuID_error, txtConstaints);

		txtConstaints.gridx = 0;
		txtConstaints.gridy = 1;
		txtConstaints.gridwidth = 1;
		txtConstaints.gridheight = 1;
		textpanel.add(firstname_label, txtConstaints);

		txtConstaints.gridx = 1;
		txtConstaints.gridy = 1;
		txtConstaints.gridwidth = 1;
		txtConstaints.gridheight = 1;
		textpanel.add(firstname_input, txtConstaints);

		txtConstaints.gridx = 2;
		txtConstaints.gridy = 1;
		txtConstaints.gridwidth = 1;
		txtConstaints.gridheight = 1;
		textpanel.add(firstnameCheck, txtConstaints);

		txtConstaints.gridx = 3;
		txtConstaints.gridy = 1;
		txtConstaints.gridwidth = 1;
		txtConstaints.gridheight = 1;
		textpanel.add(firstname_error, txtConstaints);

		txtConstaints.gridx = 0;
		txtConstaints.gridy = 2;
		txtConstaints.gridwidth = 1;
		txtConstaints.gridheight = 1;
		textpanel.add(lastname_label, txtConstaints);

		txtConstaints.gridx = 1;
		txtConstaints.gridy = 2;
		txtConstaints.gridwidth = 1;
		txtConstaints.gridheight = 1;
		textpanel.add(lastname_input, txtConstaints);

		txtConstaints.gridx = 2;
		txtConstaints.gridy = 2;
		txtConstaints.gridwidth = 1;
		txtConstaints.gridheight = 1;
		textpanel.add(lastnameCheck, txtConstaints);

		txtConstaints.gridx = 3;
		txtConstaints.gridy = 2;
		txtConstaints.gridwidth = 1;
		txtConstaints.gridheight = 1;
		textpanel.add(lastname_error, txtConstaints);

		stuID_input.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (stuID_input.getText().length() == 7 && onlyNumbers(stuID_input.getText()) == true) {
					stuCheck.setSelected(true);
				} else {
					stuCheck.setSelected(false);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (stuID_input.getText().length() != 7) {
					stuCheck.setSelected(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		// boolean allLetters =
		firstname_input.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (firstname_input.getText().length() > 0 && onlyLetters(firstname_input.getText()) == true) {
					firstnameCheck.setSelected(true);
				} else if (onlyLetters(firstname_input.getText()) == false) {
					firstnameCheck.setSelected(false);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (firstname_input.getText().length() == 0 || onlyLetters(firstname_input.getText()) == false) {
					firstnameCheck.setSelected(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		lastname_input.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (lastname_input.getText().length() > 0 && onlyLetters(lastname_input.getText()) == true) {
					lastnameCheck.setSelected(true);
				} else {
					lastnameCheck.setSelected(false);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (lastname_input.getText().length() == 0 || onlyLetters(lastname_input.getText()) == false) {
					lastnameCheck.setSelected(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		btnpanel = new JPanel();
		btnpanel.setLayout(new GridBagLayout());
		GridBagConstraints btnConstraints = new GridBagConstraints();

		btnConstraints.fill = GridBagConstraints.HORIZONTAL;
		btnConstraints.ipady = 30;
		btnConstraints.ipadx = 250;
		btnConstraints.gridx = 0;
		btnConstraints.gridy = 1;
		btnConstraints.gridwidth = 5;
		btnpanel.add(submitBtn, btnConstraints);

		mainpanel.add(imagepanel, BorderLayout.NORTH);
		mainpanel.add(textpanel, BorderLayout.CENTER);
		mainpanel.add(btnpanel, BorderLayout.SOUTH);
		add(mainpanel);

		setTitle("Robot Setup");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 750);
		setMinimumSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	public boolean onlyLetters(String name) {
		char[] chars = name.toCharArray();

		for (char c : chars) {
			if (!Character.isLetter(c)) {
				return false;
			}
		}

		return true;
	}

	public boolean onlyNumbers(String name) {
		char[] chars = name.toCharArray();

		for (char c : chars) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}

		return true;
	}

	public static String getStudentID() {
		return stuID_input.getText();
	}

	public static String getFirstName() {
		return firstname_input.getText();
	}

	public static String getLastName() {
		return lastname_input.getText();
	}
}
