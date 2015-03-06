package com.course.congress.objects;

import java.io.Serializable;

public class Hall implements Serializable {
	
//	private static final long serialVersionUID = 1L;
	private int ID;
	private String name;
	private int capacity;
	private int floor;
	
	public Hall(int iD, String name, int capacity, int floor) {
		super();
		ID = iD;
		this.name = name;
		this.capacity = capacity;
		this.floor = floor;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	
}
