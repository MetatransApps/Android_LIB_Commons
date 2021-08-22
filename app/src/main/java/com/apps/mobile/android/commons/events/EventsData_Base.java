package com.apps.mobile.android.commons.events;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.apps.mobile.android.commons.events.api.IEvent_Base;


public class EventsData_Base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7379046045998146619L;
	
	
	public long installation_time;
	public long last_game_change;
	public long last_mainscreen_interaction;
	
	public List<IEvent_Base> events_local = new ArrayList<IEvent_Base>();
	public List<IEvent_Base> events_remote = new ArrayList<IEvent_Base>();
	
	
	public EventsData_Base() {
		
		if (installation_time == 0) {
			installation_time = System.currentTimeMillis();
		}
		
		if (events_local == null) {
			events_local = new ArrayList<IEvent_Base>();
		}
		
		if (events_remote == null) {
			events_remote = new ArrayList<IEvent_Base>();
		}
	}
	
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		
	    if (installation_time <= 0) {
	    	installation_time = System.currentTimeMillis();
	    	System.out.println("EventsData_Base: writeObject - updating install time");
	    }
	    
	    // default serialization 
	    oos.defaultWriteObject();
	   
	    
	    // write the object
	    //oos.writeObject(this);
	}
	

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
	    
	    // default deserialization
	    ois.defaultReadObject();
	    
	    /*if (installation_time <= 0) {
	    	installation_time = System.currentTimeMillis();
	    	System.out.println("EventsData_Base: readObject - updating install time");
	    }*/
	    
	    //EventsData_Base obj = (EventsData_Base) ois.readObject(); // Replace with real deserialization
	}
}
