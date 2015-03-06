package com.course.congress.main;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.course.congress.controlers.PPanelControler;
import com.course.congress.datastorage.DataStorage;

public class CongressCenterMain {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				try {
					DataStorage.initFromFile("save file.ser");
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error reading from file ! " + e);
				}
				CongressCenterForm mainPanel = PPanelControler.getMainPanel();
				mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainPanel.setSize(1000, 700);
				mainPanel.setResizable(false);
				mainPanel.setVisible(true);
			}
		});
	}

}
