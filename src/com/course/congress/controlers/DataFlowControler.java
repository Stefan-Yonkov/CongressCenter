package com.course.congress.controlers;

import com.course.congress.objects.Event;
import com.course.congress.objects.Hall;

public class DataFlowControler {
	public static Event currentEvent;
	public static Hall currentHall;
	
	public static Event getCurrentEvent() {
		return currentEvent;
	}
	public static void setCurrentEvent(Event currentEvent) {
		DataFlowControler.currentEvent = currentEvent;
	}
	public static Hall getCurrentHall() {
		return currentHall;
	}
	public static void setCurrentHall(Hall currentHall) {
		DataFlowControler.currentHall = currentHall;
	}
	
	
}
