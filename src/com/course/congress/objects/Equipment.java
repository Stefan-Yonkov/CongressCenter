package com.course.congress.objects;

import java.io.Serializable;

public class Equipment implements Serializable {
	
//	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	private int quantity;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
