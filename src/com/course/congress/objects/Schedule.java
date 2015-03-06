package com.course.congress.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Schedule implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private HashMap<String, ArrayList<Event>> schedules;

	public Schedule() {
	}
	
	public HashMap<String, ArrayList<Event>> getSchedules() {
		return schedules;
	}

	public void setSchedules(HashMap<String, ArrayList<Event>> schedules) {
		this.schedules = schedules;
	}
	
	
}
