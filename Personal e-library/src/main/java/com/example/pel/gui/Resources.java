package com.example.pel.gui;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Resources {
	public static ImageIcon createIcon(String path) {
		URL imgURL = Class.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			JOptionPane.showMessageDialog(null, "Image not found!\n" + path, "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}
