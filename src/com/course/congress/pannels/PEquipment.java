package com.course.congress.pannels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.course.congress.controlers.DataFlowControler;
import com.course.congress.controlers.PPanelControler;
import com.course.congress.objects.Equipment;

public class PEquipment extends JPanel{

//	private static final long serialVersionUID = 1L;
	private Image bg = new ImageIcon("img\\background.jpeg").getImage();

	private JLabel michrophoneLabel;
	private JLabel soundSystemLabel;
	private JLabel mobileScreenLabel;
	private JLabel additionalLightingLabel;
	private JLabel computerLabel;
	private JLabel projectorLabel;
	
	private JTextField michrophones;
	private JCheckBox soundSystem;
	private JTextField mobileScreens;
	private JCheckBox additionalLighting;
	private JTextField computer;
	private JTextField projector;
	
	private JButton request;
	private JButton cancel;
	
	PEquipment() {
		setLayout(null);
		
		michrophoneLabel = new JLabel("Microphones");
		michrophoneLabel.setBounds(10, 10, 90, 20);
		add(michrophoneLabel);
		
		michrophones = new JTextField();
		michrophones.setBounds(100,10,150,25);
		add(michrophones);
		
		soundSystemLabel = new JLabel("Sound System");
		soundSystemLabel.setBounds(10, 40, 150, 20);
		add(soundSystemLabel);
		
		soundSystem = new JCheckBox();
		soundSystem.setBounds(100, 43, 20, 15);
		add(soundSystem);
		soundSystem.setBackground(Color.GRAY);
		
		mobileScreenLabel = new JLabel("Mobile Screens");
		mobileScreenLabel.setBounds(10, 70, 150, 20);
		add(mobileScreenLabel);
		
		mobileScreens = new JTextField();
		mobileScreens.setBounds(100, 70, 150, 25);
		add(mobileScreens);
		
		additionalLightingLabel = new JLabel("Additional light");
		additionalLightingLabel.setBounds(10, 100, 90, 20);
		add(additionalLightingLabel);
		
		additionalLighting = new JCheckBox();
		additionalLighting.setBounds(100, 103, 20, 15);
		add(additionalLighting);
		additionalLighting.setBackground(Color.GRAY);
		
		computerLabel = new JLabel("Computers");
		computerLabel.setBounds(10, 130, 90, 20);
		add(computerLabel);
		
		computer = new JTextField();
		computer.setBounds(100, 130, 150, 25);
		add(computer);
		
		projectorLabel = new JLabel("Projectors");
		projectorLabel.setBounds(10, 160, 90, 20);
		add(projectorLabel);
		
		projector = new JTextField();
		projector.setBounds(100, 160, 150, 25);
		add(projector);
		
		initData();
		
		request = new JButton("Request");
		request.setBounds(10, 190, 100, 25);
		add(request);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(150, 190, 100, 25);
		add(cancel);
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PPanelControler.showPrevPanel();
			}
		});
		
		request.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Equipment> equipments = new ArrayList<Equipment>();
				
				setEquipmentValue("Microphones", michrophones.getText(), equipments);
				setEquipmentValue("Sound System", soundSystem.isSelected(), equipments);
				setEquipmentValue("Mobile Screens", mobileScreens.getText(), equipments);
				setEquipmentValue("Additional light", additionalLighting.isSelected(), equipments);
				setEquipmentValue("Computers", computer.getText(), equipments);
				setEquipmentValue("Projectors", projector.getText(), equipments);
				DataFlowControler.getCurrentEvent().setEquipments(equipments);
				PPanelControler.showPrevPanel();
			}

			private void setEquipmentValue(String type, String value, ArrayList<Equipment> equipments) {
				if(value.length() > 0 && !value.equals("0")) {
					if(checkIfEqIsInArray(type, equipments)) {
						setQuontityToEquipment(value, type, equipments);
					} else {
						addNewEq(value, type, equipments);
					}
				} else {
					if(checkIfEqIsInArray(type, equipments)) {
						removeEq(type, equipments);
					}
				}
			}
			
			private void setEquipmentValue(String type, boolean value, ArrayList<Equipment> equipments) {
				if(value) {
					if(checkIfEqIsInArray(type, equipments)) {
						setQuontityToEquipment("1", type, equipments);
					} else {
						addNewEq("1", type, equipments);
					}
				} else {
					if(checkIfEqIsInArray(type, equipments)) {
						removeEq(type, equipments);
					}
				}
			}

			private void removeEq(String string, ArrayList<Equipment> equipments) {
				for(int i=0; i< equipments.size(); i++) {
					if(equipments.get(i).getType().equals(string)) {
						equipments.remove(i);
						return;
					}
				}
				return;
			}

			private void addNewEq(String text, String string, ArrayList<Equipment> equipments) {
				Equipment eq = new Equipment();
				eq.setName(string);
				eq.setType(string);
				eq.setQuantity(Integer.parseInt(text));
				equipments.add(eq);
			}

			private void setQuontityToEquipment(String text, String string, ArrayList<Equipment> equipments) {
				for(int i=0; i< equipments.size(); i++) {
					if(equipments.get(i).getType().equals(string)) {
						equipments.get(i).setQuantity(Integer.parseInt(text));
					}
				}	
			}

			private boolean checkIfEqIsInArray(String string, ArrayList<Equipment> equipments) {
				for(int i=0; i< equipments.size(); i++) {
					if(equipments.get(i).getType().equals(string)) {
						return true;
					}
				}
				return false;
			}
		});
	}

	private void initData() {
		if(DataFlowControler.getCurrentEvent().getEquipments() != null) {
			if(DataFlowControler.getCurrentEvent().getEquipments().size() > 0) {
				ArrayList<Equipment> eqs = DataFlowControler.getCurrentEvent().getEquipments();
				for(int i = 0; i< eqs.size(); i++) {
					switch (eqs.get(i).getType()) {
					case "Microphones":
						michrophones.setText(eqs.get(i).getQuantity() + "");
						break;
					case "Sound System":
						soundSystem.setSelected(true);
						break;
					case "Mobile Screens":
						mobileScreens.setText(eqs.get(i).getQuantity() + "");
						break;
					case "Additional light":
						additionalLighting.setSelected(true);
						break;
					case "Computers":
						computer.setText(eqs.get(i).getQuantity() + "");
						break;
					case "Projectors":
						projector.setText(eqs.get(i).getQuantity() + "");
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
}
