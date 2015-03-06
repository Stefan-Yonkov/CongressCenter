package com.course.congress.datastorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.course.congress.objects.Equipment;
import com.course.congress.objects.Event;
import com.course.congress.objects.Hall;
import com.course.congress.serializable.Serialization;

public class DataStorage {
	
	private static CongressData data = new CongressData();
	
	public static void saveState() throws IOException {
		Serialization.save(data, "save file.ser");
	}
	
	public static void initFromFile(String fileName) throws ClassNotFoundException, IOException {
		data = Serialization.load("save file.ser");
		initData();
	}

	private static void initData() {
		if(data.getEvents() != null)
		for (int i = 0; i < data.getEvents().length; i++) {
			if(data.getEvents()[i].getArrangement() != null) {
				data.getEvents()[i].getArrangement().init();
			}
		}
	}

	public static void setSchedules(HashMap<String, ArrayList<Event>> schedule) {
		data.setSchedules(schedule);
	}
	public static Event[] getEvents() {
		return data.getEvents();
	}
	public static Hall[] getHalls() {
		return data.getHalls();
	}
	
	public static ArrayList<Equipment> getEquipments(Event event) {
		Event[] events = data.getEvents();
		for (int i = 0; i < events.length; i++) {
			if(events[i].getID() == event.getID()) {
				return events[i].getEquipments();
			}
		}
		return new ArrayList<Equipment>();
	}

	public static HashMap<String, ArrayList<Event>> getSchedule() {
		return data.getSchedules();
	}
	
	public static void addNewHall(Hall hall) {
		Hall[] halls = data.getHalls();
		Hall[] newHalls;
		if(halls != null) {
			newHalls = new Hall[halls.length + 1];						
			for (int i = 0; i < halls.length; i++) {
				newHalls[i] = halls[i];
			}
		} else {
			newHalls = new Hall[1];
		}
		newHalls[newHalls.length - 1] = hall;
		data.setHalls(newHalls);
	}
	
	public static void addNewEvent(Event event) {
		Event[] events = data.getEvents();
		Event[] newEvents;
		if(events != null) {
			newEvents = new Event[events.length + 1];			
			for (int i = 0; i < events.length; i++) {
				newEvents[i] = events[i];
			}
		} else {
			newEvents = new Event[1];
		}
		newEvents[newEvents.length - 1] = event;
		data.setEvents(newEvents);
	}
	
	public static void addNewEquipment(Equipment equipment, int eventID) {
		Event[] events = data.getEvents();
		if(events != null) {
			for (int i = 0; i < events.length; i++) {
				if (events[i].getID() == eventID ) {
					events[i].getEquipments().add(equipment);
				}
			}			
		}
	}
	
	public static void addNewSchedule(String hallName, Event event){
		HashMap<String, ArrayList<Event>> schedules = data.getSchedules();
		ArrayList<Event> eventsPerHall = null;
		if(schedules != null){
			eventsPerHall = schedules.get(hallName) != null ? schedules.get(hallName) : new ArrayList<Event>();
		} else {
			schedules = new HashMap<String, ArrayList<Event>>();
			eventsPerHall = new ArrayList<Event>();
		}
		eventsPerHall.add(event);
		schedules.put(hallName, eventsPerHall);
		data.setSchedules(schedules);
	}
	
	public static void removeSchedule(String hallName, Event event){
		HashMap<String, ArrayList<Event>> schedules = data.getSchedules();
		ArrayList<Event> eventsPerHall = schedules.get(hallName);
		eventsPerHall.remove(event);
		schedules.put(hallName, eventsPerHall);
		data.setSchedules(schedules);
	}

	public static void removeEvent(int id) {
		Event[] newEvents;
		if(data.getEvents().length-1 < 1) {
			newEvents = null;			
		} else {
			newEvents = new Event[data.getEvents().length-1];
		}
		int newEventsCounter = 0;
		for(int i =0; i < data.getEvents().length; i++) {
			if(data.getEvents()[i].getID() == id) {
				
			} else {
				newEvents[newEventsCounter++] = data.getEvents()[i];
			}
		}
		data.setEvents(newEvents);
	}

	public static void removeHall(int id) {
		Hall[] newHalls = new Hall[data.getHalls().length-1];
		int count = 0;
		for(int i = 0; i < data.getHalls().length; i++) {
			if(data.getHalls()[i].getID() != id) {
				newHalls[count++] = data.getHalls()[i];
			}
		}
		data.setHalls(newHalls);
	}
	
}
