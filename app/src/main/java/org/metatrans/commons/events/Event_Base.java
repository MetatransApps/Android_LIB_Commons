package org.metatrans.commons.events;


import org.metatrans.commons.events.api.IEvent_Base;


public class Event_Base implements IEvent_Base {
	
	
	private static final long serialVersionUID 	= 7765202390359612844L;


	private int id;
	private int subid;
	private int subsubid;
	
	private String name;
	private String subname;
	private String subsubname;


	public Event_Base(int _id, String _name) {

		this(_id, ID_UNDEFINED, _name, null);
	}


	public Event_Base(int _id, int _subid, String _name, String _subname) {

		this(_id, _subid, ID_UNDEFINED, _name, _subname, null);
	}


	public Event_Base(int _id, int _subid, int _subsubid, String _name, String _subname, String _subsubname) {
		
		id = _id;
		subid = _subid;
		subsubid = _subsubid;
		
		name = _name;
		subname = _subname;
		subsubname = _subsubname;
	}
	
	
	@Override
	public int getID() {
		return id;
	}
	
	
	@Override
	public int getSubID() {
		return subid;
	}
	

	@Override
	public int getSubSubID() {
		return subsubid;
	}
	
	
	@Override
	public String getName() {
		return name;
	}

	
	@Override
	public String getSubName() {
		return subname;
	}
	

	@Override
	public String getSubSubName() {
		return subsubname;
	}


	@Override
	public IEvent_Base createByVarianceInCategory3(int cat3_id, String cat3_name) {

		return new Event_Base(id, subid, cat3_id, name, subname, cat3_name);
	}


	@Override
	public String toString() {

		return "Event_Base: [cat1_id=" + id + ", cat2_id=" + subid + ", cat3_id=" + subsubid + ", cat1_name=" + name + ", cat2_name=" + subname + ", cat3_name=" + subsubname + "]";
	}
}
