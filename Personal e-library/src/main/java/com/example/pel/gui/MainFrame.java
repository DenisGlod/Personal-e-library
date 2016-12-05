package com.example.pel.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.example.pel.dao.DaoListBook;
import com.example.pel.db.DB;
import com.example.pel.images.ImgPath;
import com.example.pel.table.Table;

public class MainFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private DB db;
	private MainFrame mainFrame;

	private JMenuBar menuBar;
	private JMenu mFile, mEdit, mHelp;
	private JMenuItem miAddBook, miLogOut, miExit, miEditBook, miDeleteBook, miAbout;
	private JPanel panel, panelCentre, panelTable, panelLeft, panelButton, panelLabel, panelTextField, panelSearch;
	private JButton bAddBook, bEditBook, bDeleteBook, bOpenBook;
	private Table tableBook;
	private JScrollPane spTableBook, sptfName, sptfYear, sptfAuthor, sptfPages, sptfISBN, sptfReadingStatus,
			sptfDescription;
	private JLabel lName, lYear, lAuthor, lPages, lISBN, lReadingStatus, lDescription;
	private JTextArea tfName, tfYear, tfAuthor, tfPages, tfISBN, tfReadingStatus, tfDescription;
	private JTextField tfSearch;

	public MainFrame(DB db) {
		this.db = db;
		mainFrame = this;
		ImageIcon image = Resources.createIcon(ImgPath.ICO.toString());
		setIconImage(image.getImage());
		setSize(950, 520);
		setMinimumSize(new Dimension(950, 520));
		setLocationRelativeTo(null);
		setTitle("Personal e-library");
		initComponents();
		action();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
	}

	private void initComponents() {
		// -- Start Menu --
		menuBar = new JMenuBar();
		mFile = new JMenu("File");
		mEdit = new JMenu("Edit");
		mHelp = new JMenu("Help");
		miLogOut = new JMenuItem("Log Out", Resources.createIcon(ImgPath.LOG_OUT.toString()));
		miExit = new JMenuItem("Exit", Resources.createIcon(ImgPath.EXIT.toString()));
		miAddBook = new JMenuItem("Add Book", Resources.createIcon(ImgPath.BOOK_ADD.toString()));
		miEditBook = new JMenuItem("Edit Book", Resources.createIcon(ImgPath.BOOK_EDIT.toString()));
		miDeleteBook = new JMenuItem("Delete Book", Resources.createIcon(ImgPath.BOOK_DELETE.toString()));
		miAbout = new JMenuItem("About", Resources.createIcon(ImgPath.ABOUT.toString()));
		mFile.add(miLogOut);
		mFile.addSeparator();
		mFile.add(miExit);
		mEdit.add(miAddBook);
		mEdit.add(miEditBook);
		mEdit.add(miDeleteBook);
		mHelp.add(miAbout);
		menuBar.add(mFile);
		menuBar.add(mEdit);
		menuBar.add(mHelp);
		setJMenuBar(menuBar);
		// -- End Menu --
		// -- Start Table Book --
		try {
			tableBook = new Table(new DaoListBook(db).getListBook());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
		}
		spTableBook = new JScrollPane(tableBook);
		panelTable = new JPanel(new BorderLayout());
		panelTable.add(spTableBook, BorderLayout.CENTER);
		panelTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "All Book"));
		// -- End Table Book --
		// -- Start Panel & Panel Center --
		panel = new JPanel(new BorderLayout());
		panelCentre = new JPanel(new BorderLayout());
		panelButton = new JPanel();
		bAddBook = new JButton("Add Book", Resources.createIcon(ImgPath.BOOK_ADD.toString()));
		bEditBook = new JButton("Edit Book", Resources.createIcon(ImgPath.BOOK_EDIT.toString()));
		bDeleteBook = new JButton("Delete Book", Resources.createIcon(ImgPath.BOOK_DELETE.toString()));
		panelButton.add(bAddBook);
		panelButton.add(bEditBook);
		panelButton.add(bDeleteBook);
		panelCentre.add(panelButton, BorderLayout.SOUTH);
		panelCentre.add(panelTable, BorderLayout.CENTER);
		// -- End Panel Center --
		// -- Start Panel Search --
		panelSearch = new JPanel(new BorderLayout(5, 5));
		panelSearch.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Search Book"));
		panelSearch.setPreferredSize(new Dimension(WIDTH, 45));
		tfSearch = new JTextField(20);
		panelSearch.add(tfSearch, BorderLayout.CENTER);
		panelCentre.add(panelSearch, BorderLayout.NORTH);
		// -- End Panel Search --
		// -- Panel Left --
		panelLeft = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		panelLeft.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Book"));
		panelLeft.setPreferredSize(new Dimension(310, HEIGHT));
		panelLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelLabel.setPreferredSize(new Dimension(73, 170));
		panelTextField = new JPanel();
		panelTextField.setPreferredSize(new Dimension(180, 170));
		lName = new JLabel("Name:");
		lName.setPreferredSize(new Dimension(58, 18));
		lYear = new JLabel("Year:");
		lYear.setPreferredSize(new Dimension(58, 19));
		lAuthor = new JLabel("Author:");
		lAuthor.setPreferredSize(new Dimension(58, 19));
		lPages = new JLabel("Pages:");
		lPages.setPreferredSize(new Dimension(58, 19));
		lISBN = new JLabel("ISBN:");
		lISBN.setPreferredSize(new Dimension(58, 19));
		lReadingStatus = new JLabel("Status:");
		lReadingStatus.setPreferredSize(new Dimension(58, 19));
		lDescription = new JLabel("Description:");
		lDescription.setPreferredSize(new Dimension(74, 19));
		tfName = new JTextArea(1, 15);
		tfName.setEditable(false);
		sptfName = new JScrollPane(tfName);
		sptfName.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfYear = new JTextArea(1, 15);
		tfYear.setEditable(false);
		sptfYear = new JScrollPane(tfYear);
		sptfYear.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfAuthor = new JTextArea(1, 15);
		tfAuthor.setEditable(false);
		sptfAuthor = new JScrollPane(tfAuthor);
		sptfAuthor.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfPages = new JTextArea(1, 15);
		tfPages.setEditable(false);
		sptfPages = new JScrollPane(tfPages);
		sptfPages.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfISBN = new JTextArea(1, 15);
		tfISBN.setEditable(false);
		sptfISBN = new JScrollPane(tfISBN);
		sptfISBN.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfReadingStatus = new JTextArea(1, 15);
		tfReadingStatus.setEditable(false);
		sptfReadingStatus = new JScrollPane(tfReadingStatus);
		sptfReadingStatus.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfDescription = new JTextArea(12, 22);
		tfDescription.setEditable(false);
		tfDescription.setLineWrap(true);
		tfDescription.setWrapStyleWord(true);
		sptfDescription = new JScrollPane(tfDescription);
		sptfDescription.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		bOpenBook = new JButton("Open Book", Resources.createIcon(ImgPath.BOOK_OPEN.toString()));
		panelLabel.add(lName);
		panelLabel.add(lAuthor);
		panelLabel.add(lYear);
		panelLabel.add(lPages);
		panelLabel.add(lISBN);
		panelLabel.add(lReadingStatus);
		panelLabel.add(lDescription);
		panelTextField.add(sptfName);
		panelTextField.add(sptfAuthor);
		panelTextField.add(sptfYear);
		panelTextField.add(sptfPages);
		panelTextField.add(sptfISBN);
		panelTextField.add(sptfReadingStatus);
		panelLeft.add(panelLabel);
		panelLeft.add(panelTextField);
		panelLeft.add(sptfDescription);
		panelLeft.add(bOpenBook);
		// -- End Panel Left --
		panel.add(panelLeft, BorderLayout.WEST);
		panel.add(panelCentre, BorderLayout.CENTER);
		add(panel);
	}

	private void action() {
		miAddBook.addActionListener(this);
		miEditBook.addActionListener(this);
		miDeleteBook.addActionListener(this);
		miLogOut.addActionListener(this);
		miExit.addActionListener(this);
		miAbout.addActionListener(this);
		bAddBook.addActionListener(this);
		bEditBook.addActionListener(this);
		bDeleteBook.addActionListener(this);
		bOpenBook.addActionListener(this);
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				search(tfSearch.getText());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				search(tfSearch.getText());
			}
		});
		actionMouseAdapter();
	}

	private void actionMouseAdapter() {
		tableBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					ResultSet rsBook = new DaoListBook(db).getBook(
							Integer.parseInt(String.valueOf(tableBook.getValueAt(tableBook.getSelectedRow(), 0))));
					rsBook.next();
					switch (rsBook.getString("status")) {
					case "Read":
						tfReadingStatus.setForeground(Color.GREEN);
						break;
					case "Do not read":
						tfReadingStatus.setForeground(Color.RED);
						break;
					default:
						tfReadingStatus.setForeground(Color.BLUE);
						break;
					}
					tfName.setText(rsBook.getString("name"));
					tfName.setCaretPosition(0);
					tfAuthor.setText(rsBook.getString("author"));
					tfYear.setText(rsBook.getString("year"));
					tfPages.setText(rsBook.getString("pages"));
					tfISBN.setText(rsBook.getString("isbn"));
					tfReadingStatus.setText(rsBook.getString("status"));
					tfDescription.setText(rsBook.getString("description"));
					tfDescription.setCaretPosition(0);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Error:\n" + e1, "Error: NumberFormatException",
							JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error:\n" + e1, "Error: SQLException",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Add Book":
			new AddEditBookFrame(db, mainFrame, "Add");
			break;
		case "Edit Book":
			if (tableBook.getSelectedRow() != -1) {
				new AddEditBookFrame(db, mainFrame, "Edit");
			}
			break;
		case "Delete Book":
			try {
				DaoListBook daoListBook = new DaoListBook(db);
				if (tableBook.getSelectedRow() != -1) {
					daoListBook.deleteBook(
							Integer.parseInt(String.valueOf(tableBook.getValueAt(tableBook.getSelectedRow(), 0))));
					updateTable();
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Error:\n" + e1, "Error", JOptionPane.ERROR_MESSAGE);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Error SQL:\n" + e1, "Error SQL", JOptionPane.ERROR_MESSAGE);
			}
			clearText();
			break;
		case "Log Out":
			new StartFrame();
			dispose();
			break;
		case "Exit":
			dispose();
			break;
		case "About":
			JOptionPane.showMessageDialog(null, "Developed by Denis © 2016", "About", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "Open Book":
			if (tableBook.getSelectedRow() != -1) {
				try {
					ResultSet rsBook = new DaoListBook(db).getBook(Integer.parseInt(String.valueOf(
							mainFrame.getTableBook().getValueAt(mainFrame.getTableBook().getSelectedRow(), 0))));
					rsBook.next();
					String path = rsBook.getString("url");
					if (path != "") {
						if (path.charAt(1) == ':') {
							Desktop.getDesktop().open(new File(path));
						} else {
							Desktop.getDesktop().browse(new URI(path));
						}
					}
				} catch (IOException | URISyntaxException e1) {
					JOptionPane.showMessageDialog(null, "Error File:\n" + e1, "Error", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error SQL:\n" + e1, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
		}
	}

	private void search(String str) {
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableBook.getModel());
		tableBook.setRowSorter(rowSorter);
		rowSorter.setRowFilter(RowFilter.regexFilter(str));
	}

	public void updateTable() {
		try {
			panelTable.remove(spTableBook);
			panelCentre.remove(panelTable);
			panelTable = new JPanel(new BorderLayout());
			panelTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "All Book"));
			tableBook = new Table(new DaoListBook(db).getListBook());
			actionMouseAdapter();
			spTableBook = new JScrollPane(tableBook);
			panelTable.add(spTableBook, BorderLayout.CENTER);
			panelTable.updateUI();
			panelCentre.add(panelTable, BorderLayout.CENTER);
			panelCentre.updateUI();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Error SQL:\n" + e1, "Error SQL", JOptionPane.ERROR_MESSAGE);
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
	}

	public Table getTableBook() {
		return tableBook;
	}

	public JTextArea getTfDescription() {
		return tfDescription;
	}

	public JTextArea getTfName() {
		return tfName;
	}

	public JTextArea getTfYear() {
		return tfYear;
	}

	public JTextArea getTfAuthor() {
		return tfAuthor;
	}

	public JTextArea getTfPages() {
		return tfPages;
	}

	public JTextArea getTfISBN() {
		return tfISBN;
	}

	public JTextArea getTfReadingStatus() {
		return tfReadingStatus;
	}

}
