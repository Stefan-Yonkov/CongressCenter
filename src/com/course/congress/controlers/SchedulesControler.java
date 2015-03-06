package com.course.congress.controlers;
import java.util.ArrayList;

import com.course.congress.datastorage.DataStorage;
import com.course.congress.objects.Event;

public class SchedulesControler {
	
	private static SchedulesControler theOne;
	
	private SchedulesControler() {
	}
	
	public static SchedulesControler getSchedules() {
		if(theOne == null) {
			theOne = new SchedulesControler();
		}
		return theOne;
	}
	
	public ArrayList<Event> getEventList(String hallID) {
		
		//return DataStorage.getSchedule().getSchedules().get(hallID);
		return null;
	}
	
	public void setEventList(String hallID, ArrayList<Event> event) {
		
		//DataStorage.getSchedule().getSchedules().put(hallID, event);
	}
}
