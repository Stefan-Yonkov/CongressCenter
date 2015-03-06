package com.course.congress.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.course.congress.controlers.PPanelControler;
import com.course.congress.datastorage.DataStorage;
import com.course.congress.pannels.PAboutForm;
import com.course.congress.pannels.PAddScheduleForm;
import com.course.congress.pannels.PEventForm;
import com.course.congress.pannels.PHallForm;

public class CongressCenterForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane jDesktopPane;
	
	public CongressCenterForm() {
		super("Congress Center");
		jDesktopPane = PPanelControler.getDesktopPane();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem saveFile = new JMenuItem("Save");
		fileMenu.add(saveFile);
		JMenuItem loadFile = new JMenuItem("Load");
		fileMenu.add(loadFile);
		fileMenu.addSeparator();
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		
		JMenu hallMenu = new JMenu("Halls");
		JMenuItem addHall = new JMenuItem("Add");
		hallMenu.add(addHall);
		
		JMenu eventMenu = new JMenu("Events");
		JMenuItem addEvent = new JMenuItem("Add Event");
		eventMenu.add(addEvent);
		
		JMenu schedule = new JMenu("Schedule");
		JMenuItem showSchedule = new JMenuItem("Show Schedule");
		schedule.add(showSchedule);

		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutMenuItem = new JMenuItem("About our companies");
		helpMenu.add(aboutMenuItem);

		JMenuBar bar = new JMenuBar();
		bar.add(fileMenu);
		bar.add(hallMenu);
		bar.add(eventMenu);
		bar.add(schedule);
		bar.add(helpMenu);
		setJMenuBar(bar);
		add(jDesktopPane);
		
		loadFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DataStorage.initFromFile("save file.ser");
					PPanelControler.refreshCurrentPanel();
				} catch (ClassNotFoundException | IOException e1) {
					System.out.println("Error reading from file ! " + e);
				}
			}
		});
		
		saveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					DataStorage.saveState();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Can't save data!");
				}
			}
		});
		
		addHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PHallForm pHallForm = new PHallForm();
				PPanelControler.showPanel(pHallForm);
			}
		});
		
		addEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PEventForm eventForm = new PEventForm();
				PPanelControler.showPanel(eventForm);
			}
		});
		
		showSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PAddScheduleForm addScheduleForm = new PAddScheduleForm();
				PPanelControler.showPanel(addScheduleForm);
			}
		});
		
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PAboutForm aboutForm = new PAboutForm();
				PPanelControler.showPanel(aboutForm);
			}
		});
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
	}
}
