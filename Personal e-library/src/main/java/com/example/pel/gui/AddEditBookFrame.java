package com.example.pel.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.example.pel.dao.DaoListBook;
import com.example.pel.db.DB;
import com.example.pel.entity.ListBook;
import com.example.pel.images.ImgPath;

public class AddEditBookFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DB db;
	private MainFrame mainFrame;

	private JPanel panel, panelLabel, panelTextField;
	private JScrollPane spDescription, sptfName, sptfYear, sptfAuthor, sptfPages, sptfISBN, sptfReadingStatus, sptfPath;
	private JLabel lName, lYear, lAuthor, lPages, lISBN, lReadingStatus, lPath, lDescription;
	private JTextArea tfName, tfYear, tfAuthor, tfPages, tfISBN, tfReadingStatus, tfPath, tfDescription;
	private JButton bSave;

	public AddEditBookFrame(DB db, MainFrame mainFrame, String checking) {
		this.db = db;
		this.mainFrame = mainFrame;
		ImageIcon image = Resources.createIcon(ImgPath.ICO.toString());
		setIconImage(image.getImage());
		setSize(300, 500);
		setLocationRelativeTo(null);
		setTitle("Add & Edit Book");
		initComponents();
		action(checking);
		checkWhichButtonCalled(checking);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void initComponents() {
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Add & Edit Book"));
		panelLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelLabel.setPreferredSize(new Dimension(73, 195));
		panelTextField = new JPanel();
		panelTextField.setPreferredSize(new Dimension(180, 195));
		lName = new JLabel("*Name:");
		lName.setPreferredSize(new Dimension(58, 18));
		lYear = new JLabel("*Year:");
		lYear.setPreferredSize(new Dimension(58, 19));
		lAuthor = new JLabel("*Author:");
		lAuthor.setPreferredSize(new Dimension(58, 19));
		lPages = new JLabel("*Pages:");
		lPages.setPreferredSize(new Dimension(58, 19));
		lISBN = new JLabel("*ISBN:");
		lISBN.setPreferredSize(new Dimension(58, 19));
		lReadingStatus = new JLabel("*Status:");
		lReadingStatus.setPreferredSize(new Dimension(58, 19));
		lPath = new JLabel("Path:");
		lPath.setPreferredSize(new Dimension(58, 19));
		lDescription = new JLabel("Description:");
		lDescription.setPreferredSize(new Dimension(74, 19));
		tfName = new JTextArea(1, 15);
		sptfName = new JScrollPane(tfName);
		sptfName.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfYear = new JTextArea(1, 15);
		sptfYear = new JScrollPane(tfYear);
		sptfYear.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfAuthor = new JTextArea(1, 15);
		sptfAuthor = new JScrollPane(tfAuthor);
		sptfAuthor.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfPages = new JTextArea(1, 15);
		sptfPages = new JScrollPane(tfPages);
		sptfPages.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfISBN = new JTextArea(1, 15);
		sptfISBN = new JScrollPane(tfISBN);
		sptfISBN.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfReadingStatus = new JTextArea(1, 15);
		sptfReadingStatus = new JScrollPane(tfReadingStatus);
		sptfReadingStatus.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfPath = new JTextArea(1, 15);
		sptfPath = new JScrollPane(tfPath);
		sptfPath.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfDescription = new JTextArea(12, 22);
		tfDescription.setLineWrap(true);
		tfDescription.setWrapStyleWord(true);
		spDescription = new JScrollPane(tfDescription);
		spDescription.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelLabel.add(lName);
		panelLabel.add(lAuthor);
		panelLabel.add(lYear);
		panelLabel.add(lPages);
		panelLabel.add(lISBN);
		panelLabel.add(lReadingStatus);
		panelLabel.add(lPath);
		panelLabel.add(lDescription);
		panelTextField.add(sptfName);
		panelTextField.add(sptfAuthor);
		panelTextField.add(sptfYear);
		panelTextField.add(sptfPages);
		panelTextField.add(sptfISBN);
		panelTextField.add(sptfReadingStatus);
		panelTextField.add(sptfPath);

		bSave = new JButton("Save Book", Resources.createIcon(ImgPath.BOOK_SAVE.toString()));
		panel.add(panelLabel);
		panel.add(panelTextField);
		panel.add(spDescription);
		panel.add(bSave);
		add(panel);
	}

	private void action(String checking) {
		bSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DaoListBook daoListBook = new DaoListBook(db);
				if (textVerification()) {
					try {
						if (checking.equals("Add")) {
							daoListBook.addBook(new ListBook(tfName.getText(), Integer.parseInt(tfYear.getText()),
									tfAuthor.getText(), Integer.parseInt(tfPages.getText()), tfISBN.getText(),
									tfDescription.getText(), tfPath.getText(), tfReadingStatus.getText()));
							mainFrame.updateTable();
							clearText();
							JOptionPane.showMessageDialog(null, "The book is successfully added", "Message",
									JOptionPane.INFORMATION_MESSAGE,
									Resources.createIcon(ImgPath.MESSAGE_SAVE_ICO.toString()));
						} else {
							daoListBook.updateBook(new ListBook(
									Integer.parseInt(String.valueOf(mainFrame.getTableBook()
											.getValueAt(mainFrame.getTableBook().getSelectedRow(), 0))),
									tfName.getText(), Integer.parseInt(tfYear.getText()), tfAuthor.getText(),
									Integer.parseInt(tfPages.getText()), tfISBN.getText(), tfDescription.getText(),
									tfPath.getText(), tfReadingStatus.getText()));
							mainFrame.updateTable();
							dispose();
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Error SQL:\n" + e1, "Error SQL",
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error:\n" + e1, "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Some fields marked with * are filled!\nFill in these fields.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void checkWhichButtonCalled(String checking) {
		if (checking.equals("Edit")) {
			tfName.setText(mainFrame.getTfName().getText());
			tfName.setCaretPosition(0);
			tfYear.setText(mainFrame.getTfYear().getText());
			tfAuthor.setText(mainFrame.getTfAuthor().getText());
			tfPages.setText(mainFrame.getTfPages().getText());
			tfISBN.setText(mainFrame.getTfISBN().getText());
			tfReadingStatus.setText(mainFrame.getTfReadingStatus().getText());
			tfDescription.setText(mainFrame.getTfDescription().getText());
			tfDescription.setCaretPosition(0);
			tfPath.setText(
					String.valueOf(mainFrame.getTableBook().getValueAt(mainFrame.getTableBook().getSelectedRow(), 7)));
			tfPath.setCaretPosition(0);
		}
	}

	public void clearText() {
		tfName.setText("");
		tfYear.setText("");
		tfAuthor.setText("");
		tfPages.setText("");
		tfISBN.setText("");
		tfReadingStatus.setText("");
		tfDescription.setText("");
		tfPath.setText("");
	}

	public boolean textVerification() {
		if (tfName.getText().equals("") || tfYear.getText().equals("") || tfAuthor.getText().equals("")
				|| tfPages.getText().equals("") || tfISBN.getText().equals("")
				|| tfReadingStatus.getText().equals("")) {
			return false;
		} else {
			return true;
		}
	}

}
