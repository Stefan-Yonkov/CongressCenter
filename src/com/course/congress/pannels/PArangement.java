package com.course.congress.pannels;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.course.congress.controlers.DataFlowControler;
import com.course.congress.controlers.PPanelControler;
import com.course.congress.objects.HallArrangement;

public class PArangement extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Image bg = new ImageIcon("img\\background.jpeg").getImage();
	
	private JComboBox<String> styles;
	private JComboBox<String> bannerStyles;
	private JLabel picture;
	private JButton apply;
	final JPanel thisPanel;
	private JButton cancel;

	private static final String[] types = {"-None-", "Banquet", "Classroom", "ExecutiveBoard", "Square", "Theatre", "UShape"};
	
	public PArangement() {
		thisPanel = this;
		
		setLayout(null);
		
		apply = new JButton("Apply");
		apply.setBounds(10, 190, 100, 25);
		add(apply);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(10, 250, 100, 25);
		add(cancel);
		
		styles = new JComboBox<String>(types);
		styles.setBounds(10,10,130,25);
		styles.setVisible(true);
		styles.setMaximumRowCount(5);
		
		add(styles);
		
		bannerStyles = new JComboBox<String>();
		bannerStyles.setBounds(350,10,130,25);
		bannerStyles.setVisible(true);
		bannerStyles.setMaximumRowCount(5);
		bannerStyles.setEnabled(false);
		add(bannerStyles);
		
		picture = new JLabel();
		initData();
		styles.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				
				if(event.getStateChange() == ItemEvent.SELECTED){
					if(styles.getSelectedIndex() == 0) {
						picture.setIcon(new ImageIcon());
						add(picture);
						
						bannerStyles.removeAll();
						thisPanel.repaint();
					} else {
						HallArrangement hallarr = new HallArrangement((String) styles.getSelectedItem());
						picture.setIcon(new ImageIcon(hallarr.getImage()));
						picture.setBounds(130, 10, 300, 300);
						picture.setVisible(true);
						add(picture);
						
						ArrayList<String> bannersTypes = HallArrangement.getArrangementswithbanners().get((String) styles.getSelectedItem());
						if(bannersTypes != null) {
							while(bannerStyles.getItemCount() > 0) {
								bannerStyles.removeItemAt(0);
							}
							bannerStyles.addItem("-None-");
							for (int i = 0; i < bannersTypes.size(); i++) {
								bannerStyles.addItem(bannersTypes.get(i));
							}
							bannerStyles.setSelectedIndex(0);
							bannerStyles.setEnabled(true);
						} else {
							while(bannerStyles.getItemCount() > 0) {
								bannerStyles.removeItemAt(0);
							}
							bannerStyles.setEnabled(false);
						}
						thisPanel.repaint();
					}
				}
			}
		});
		
		bannerStyles.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED){
					if(bannerStyles.getSelectedIndex() == 0) {
						HallArrangement hallarr = new HallArrangement((String) styles.getSelectedItem());
						picture.setIcon(new ImageIcon(hallarr.getImage()));
						picture.setBounds(130, 10, 300, 300);
						picture.setVisible(true);
						add(picture);
						thisPanel.repaint();
					} else {
						HallArrangement hallarr = new HallArrangement((String) bannerStyles.getSelectedItem());
						picture.setIcon(new ImageIcon(hallarr.getImage()));
						picture.setBounds(130, 10, 300, 300);
						picture.setVisible(true);
						add(picture);
					}
				}
			}
		});
		
		apply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if( !((String)styles.getSelectedItem()).equals("-None-") ) {
					if(!((String)bannerStyles.getSelectedItem()).equals("-None-") && bannerStyles.getItemCount() > 0) {
						HallArrangement arr = new HallArrangement( ((String) bannerStyles.getSelectedItem()) );
						arr.setBannersType( ((String) bannerStyles.getSelectedItem()) );
						arr.setType(((String)styles.getSelectedItem()));
						DataFlowControler.getCurrentEvent().setArrangement(arr);
					} else {						
						DataFlowControler.getCurrentEvent().setArrangement(new HallArrangement( ((String) styles.getSelectedItem()) ));
					}
				}
				PPanelControler.showPrevPanel();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PPanelControler.showPrevPanel();
			}
		});
	}

	private void initData() {
		if (DataFlowControler.getCurrentEvent().getArrangement() != null) {
			HallArrangement hallarr;
			if(DataFlowControler.getCurrentEvent().getArrangement().getBannersType() != null) {
				styles.setSelectedItem(DataFlowControler.getCurrentEvent().getArrangement().getType());	
				ArrayList<String> bannersTypes = HallArrangement.getArrangementswithbanners().get((String) styles.getSelectedItem());
				if(bannersTypes != null) {
					bannerStyles.addItem("-None-");
					for (int i = 0; i < bannersTypes.size(); i++) {
						bannerStyles.addItem(bannersTypes.get(i));
					}					
				}
				bannerStyles.setSelectedItem(DataFlowControler.getCurrentEvent().getArrangement().getBannersType());
				bannerStyles.setEnabled(true);
				hallarr = new HallArrangement((String) bannerStyles.getSelectedItem());
			} else {
				
				styles.setSelectedItem(DataFlowControler.getCurrentEvent().getArrangement().getType());	
				ArrayList<String> bannersTypes = HallArrangement.getArrangementswithbanners().get((String) styles.getSelectedItem());
				if(bannersTypes != null) {
					bannerStyles.addItem("-None-");
					for (int i = 0; i < bannersTypes.size(); i++) {
						bannerStyles.addItem(bannersTypes.get(i));
					}				
					bannerStyles.setEnabled(true);
				}
				hallarr = new HallArrangement((String) styles.getSelectedItem());
			}
			
			picture.setIcon(new ImageIcon(hallarr.getImage()));
			picture.setBounds(130, 10, 300, 300);
			picture.setVisible(true);
			add(picture);			
			thisPanel.repaint();
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}

}
