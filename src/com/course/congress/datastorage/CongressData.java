package com.course.congress.datastorage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.course.congress.objects.Event;
import com.course.congress.objects.Hall;

public class CongressData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Event[] events;
	private Hall[] halls;
	private HashMap<String, ArrayList<Event>> schedules;
	
	public Event[] getEvents() {
		return events;
	}
	public void setEvents(Event[] events) {
		this.events = events;
	}
	public Hall[] getHalls() {
		return halls;
	}
	public void setHalls(Hall[] halls) {
		this.halls = halls;
	}
	public HashMap<String, ArrayList<Event>> getSchedules() {
		return schedules;
	}
	public void setSchedules(HashMap<String, ArrayList<Event>> schedules) {
		this.schedules = schedules;
	}
	
}
