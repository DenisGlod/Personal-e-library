package com.example.pel.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import com.example.pel.db.DB;
import com.example.pel.db.DBCreate;
import com.example.pel.images.ImgPath;

public class StartFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JPanel panel, panelLable, panelTextField, panelButton;
	private JLabel labelUrl, labelName, labelLogin, labelPass;
	private JTextField tfUrl, tfName, tfLogin;
	private JPasswordField tfPass;
	private JButton createButton, deleteButton, connectButton;

	public StartFrame() {
		ImageIcon image = Resources.createIcon(ImgPath.ICO.toString());
		setIconImage(image.getImage());
		setSize(344, 236);
		setLocationRelativeTo(null);
		setTitle("Personal e-library");
		initComponents();
		action();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void initComponents() {
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Database"));
		panelLable = new JPanel(new GridLayout(4, 1, 10, 18));
		panelTextField = new JPanel(new GridLayout(4, 1, 10, 10));
		panelButton = new JPanel(new GridLayout(1, 3, 5, 20));
		labelUrl = new JLabel("DB URL: ");
		labelName = new JLabel("DB Name: ");
		labelLogin = new JLabel("DB Login: ");
		labelPass = new JLabel("DB Pass: ");
		tfUrl = new JTextField("jdbc:mysql://www.db4free.net:3306/", 20);
		tfUrl.setPreferredSize(new Dimension(WIDTH, 24));
		tfName = new JTextField("pelibrary", 20);
		tfName.setPreferredSize(new Dimension(WIDTH, 24));
		tfLogin = new JTextField("pelibrary", 20);
		tfLogin.setPreferredSize(new Dimension(WIDTH, 24));
		tfPass = new JPasswordField("pelibrary", 20);
		tfPass.setPreferredSize(new Dimension(WIDTH, 24));
		createButton = new JButton("Create", Resources.createIcon(ImgPath.ADD.toString()));
		deleteButton = new JButton("Delete", Resources.createIcon(ImgPath.DELETE.toString()));
		connectButton = new JButton("Connect", Resources.createIcon(ImgPath.CONNECT.toString()));

		panelLable.add(labelUrl);
		panelLable.add(labelName);
		panelLable.add(labelLogin);
		panelLable.add(labelPass);

		panelTextField.add(tfUrl);
		panelTextField.add(tfName);
		panelTextField.add(tfLogin);
		panelTextField.add(tfPass);

		panelButton.add(createButton);
		panelButton.add(deleteButton);
		panelButton.add(connectButton);

		panel.add(panelLable);
		panel.add(panelTextField);
		panel.add(panelButton);

		add(panel);
	}

	private void action() {
		createButton.addActionListener(this);
		deleteButton.addActionListener(this);
		connectButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			switch (e.getActionCommand()) {
			case "Create":
				DBCreate.createDB(tfUrl.getText(), tfLogin.getText(), String.valueOf(tfPass.getPassword()),
						tfName.getText());
				JOptionPane.showMessageDialog(null, "Create database successful", "Message",
						JOptionPane.INFORMATION_MESSAGE, Resources.createIcon(ImgPath.MESSAGE_SAVE_ICO.toString()));
				break;
			case "Delete":
				DBCreate.deleteDB(tfUrl.getText(), tfLogin.getText(), String.valueOf(tfPass.getPassword()),
						tfName.getText());
				JOptionPane.showMessageDialog(null, "Delete database successful", "Message",
						JOptionPane.INFORMATION_MESSAGE, Resources.createIcon(ImgPath.MESSAGE_SAVE_ICO.toString()));
				break;
			case "Connect":
				DB db = new DB(tfUrl.getText() + tfName.getText(), tfLogin.getText(),
						String.valueOf(tfPass.getPassword()));
				JOptionPane.showMessageDialog(null, "Connect database successful", "Message",
						JOptionPane.INFORMATION_MESSAGE, Resources.createIcon(ImgPath.MESSAGE_SAVE_ICO.toString()));
				new MainFrame(db);
				dispose();
				break;
			default:
				break;
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Error " + e.getActionCommand().toLowerCase() + " database\n" + e1,
					"Error", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e2) {
			JOptionPane.showMessageDialog(null, "Error loading driver\n" + e2, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
