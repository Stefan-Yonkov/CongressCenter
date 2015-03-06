package com.course.congress.objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Event implements Serializable {
	
	//private static final long serialVersionUID = 1L;
	private int ID;
	private String name;
	private int duration;
	private Date startDate;
	private Date endDate;
	private String type;
	private String description;
	
	private String hallID;						
	private ArrayList<Equipment> equipments;
	private HallArrangement arrangement;
	
	public Event(int iD, String name, int duration, Date startDate,
			Date endDate, String type, String description, String hallID,
			ArrayList<Equipment> equipments, HallArrangement arrangement) {
		super();
		ID = iD;
		this.name = name;
		this.duration = duration;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.description = description;
		this.hallID = hallID;
		this.equipments = equipments;
		this.arrangement = arrangement;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHallID() {
		return hallID;
	}
	public void setHallID(String hallID) {
		this.hallID = hallID;
	}
	public ArrayList<Equipment> getEquipments() {
		return equipments;
	}
	public void setEquipments(ArrayList<Equipment> equipments) {
		this.equipments = equipments;
	}
	public HallArrangement getArrangement() {
		return arrangement;
	}
	public void setArrangement(HallArrangement arrangement) {
		this.arrangement = arrangement;
	}
	
	@Override
	public String toString(){
		return this.name;
	}

}
