package com.course.congress.pannels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PAboutForm extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Image bg = new ImageIcon("img\\background.jpeg").getImage();
	
	private JTable aboutCompanyTable;
	private JLabel aboutCompanyLabel;
	private JScrollPane scrollPane;
	
	private String[] columnNames = {"Company Name", "City", "Country", "Adress", "Contact", "Phone"};
	private Object[][] data = {
			{"Spaghetti Company", "Sofia", "Bulgaria", "56 Bulgaria Blvd", "John Smith", "084342"},
			{"Blue sky", "Novi Sad", "Serbia", "23 Offroad Blvd", "Milosh Bosnic", "093642"},
			{"Heaven Resort", "Kuda Huraa", "Maldives", "North Malé Atoll", "Zvika Lait", "+(960)6644800"},
			{"FutureTach", "New York City", "USA", "57 Street", "James Linquist", "043242489"},
			{"Nola 7", "Skopje", "Macedonia", "13 Aleksandyr Makedonski Blvd", "Hristina Rancic", "423408240"},
	};
	
	public PAboutForm() {
		setLayout(null);
		 
		aboutCompanyLabel = new JLabel("Useful information about the companies:");
		aboutCompanyLabel.setBounds(100,30,500,20);
		add(aboutCompanyLabel);
		
		aboutCompanyTable = new JTable(new DefaultTableModel(data, columnNames));
		aboutCompanyTable.setShowGrid(true);
		aboutCompanyTable.setGridColor(new Color(167,206,221));
		scrollPane = new JScrollPane(aboutCompanyTable);
		aboutCompanyTable.setFillsViewportHeight(true);

		aboutCompanyTable.setEnabled(false);
		add(scrollPane).setBounds(100, 60, 800, 270);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
}
