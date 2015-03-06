package com.course.congress.controlers;

import java.awt.Graphics;
import java.awt.Image;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;

import com.course.congress.main.CongressCenterForm;

public class PPanelControler {
	
	private static ArrayList<JPanel> panels = new ArrayList<JPanel>();
	public static JDesktopPane jDesktopPane = new JDesktopPane(){
		private static final long serialVersionUID = 1L;
		private Image bg = new ImageIcon("img\\background.jpeg").getImage();
		public void paintComponent(Graphics g) {
			g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		}
    };
	public static CongressCenterForm mainPanel = new CongressCenterForm();
	
	private static void setNewPanel(JPanel panel) {
		mainPanel.remove(jDesktopPane);
		jDesktopPane = new JDesktopPane();
		jDesktopPane.add(panel);
		jDesktopPane.setVisible(true);
		panel.setVisible(true);
		panel.setSize(1000, 700);
		mainPanel.add(jDesktopPane);
		mainPanel.revalidate();
	}
	
	public static void refreshCurrentPanel() {
		if(panels.size() > 0) {
			Class<?> clazz;
			try {
				clazz = Class.forName(panels.get(0).getClass().getCanonicalName());
				Constructor<?> ctor = clazz.getConstructor();
				JPanel object = (JPanel) ctor.newInstance();
				showPanel(object);			
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void showPanel(JPanel panel) {
		panels.clear();
		panels.add(panel);
		setNewPanel(panel);
	}
	
	public static void showNextPanel(JPanel nextPanel) {
		panels.add(nextPanel);
		setNewPanel(nextPanel);
	}
	
	public static void showPrevPanel() {
		panels.remove(panels.size()-1);
		setNewPanel(panels.get(panels.size()-1));
	}
	
	public static JDesktopPane getDesktopPane() {
		return jDesktopPane;
	}

	public static CongressCenterForm getMainPanel() {
		return mainPanel;
	}

}
