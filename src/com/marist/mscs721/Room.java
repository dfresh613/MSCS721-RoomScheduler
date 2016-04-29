/**
   * Douglas Rohde License
   * This is an example license used throughout RoomScheduler
   * Other possible licenses to use include Apache, GPL, etc...
   * More license text.
   */
package com.marist.mscs721;

import java.util.ArrayList;

public class Room {	
	
	private String name;
	private int capacity;
	private ArrayList<Meeting> meetings;
	private String location;
	private String building;
	
	public Room(String newName, int newCapacity, String location, String building) {
		setName(newName);
		setCapacity(newCapacity);
		setMeetings(new ArrayList<Meeting>());
		setLocation(location);
		setBuilding(building);
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
	public Room(String newName, int newCapacity, ArrayList<Meeting> newMeetings, String location, String building){
		this.name = newName;
		this.capacity = newCapacity;
		this.meetings = newMeetings;
		this.setLocation(location);
		this.setBuilding(building);
	}
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
	/**
	 * @return the building
	 */
	public String getBuilding() {
		return building;
	}
	/**
	 * @param building the building to set
	 */
	public void setBuilding(String building) {
		this.building = building;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
}
