package com.marist.mscs721;

import java.util.ArrayList;

public class Room {	
	
	private String name;
	private int capacity;
	private ArrayList<Meeting> meetings;
	
	
	public Room(String newName, int newCapacity) {
		setName(newName);
		setCapacity(newCapacity);
		setMeetings(new ArrayList<Meeting>());
	}
	/**
	 * Jackson json mapping requires a default constructor
	 */
	public Room(){				
	}
	/**
	 * Constructor for including setting meetings. necessary for mapping json objs
	 * @param newMeeting
	 */
	public Room(String newName, int newCapacity, ArrayList<Meeting> newMeetings){
		this.name = newName;
		this.capacity = newCapacity;
		this.meetings = newMeetings;
	}

	public void addMeeting(Meeting newMeeting) {
		this.getMeetings().add(newMeeting);
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


	public ArrayList<Meeting> getMeetings() {
		return meetings;
	}


	public void setMeetings(ArrayList<Meeting> meetings) {
		this.meetings = meetings;
	}
	
}
