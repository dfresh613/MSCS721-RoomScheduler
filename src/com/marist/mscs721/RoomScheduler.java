package com.marist.mscs721;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RoomScheduler {
	private static final String INPUT_SEPARATOR = "---------------------";
	protected static Scanner keyboard = new Scanner(System.in);
	private static UserInputValidator validator = new UserInputValidator();
	public static void main(String[] args) {
		Boolean end = false;
		ArrayList<Room> rooms = new ArrayList<Room>();
		//sets the delimiter to be new line. S that we can support rooms with spaces as input and that the scanner won't treat the spaces as
		//delimiters
		keyboard.useDelimiter("\n");
		while (!end) {
			switch (mainMenu()) {

			case 1:
				System.out.println(addRoom(rooms));
				break;
			case 2:
				System.out.println(removeRoom(rooms));
				break;
			case 3:
				System.out.print(scheduleRoom(rooms));
				break;
			case 4:
				System.out.println(listSchedule(rooms));
				break;
			case 5:
				System.out.println(listRooms(rooms));
				break;
			case 6:
				getJsonForRooms(rooms);
				break;
			case 7:
				importJsonForRooms(rooms);
				break;
			}

		}

	}
	/**
	 * Using jackson libraries imports the given json file as a room object and saves it to memory
	 */
	private static void importJsonForRooms(ArrayList<Room> roomList){
		System.out.println("Please specify the absolute path of the json file location.");
		System.out.println("NOTE: Importing data from json file will overwrite any existing data");

		File jsonFile = new File(keyboard.next());
		ArrayList<Room> roomListFromJson = new ArrayList<Room>();
		//verify specified file exists
		if(!jsonFile.exists() || !jsonFile.canRead()){
			System.out.println("Invalid file specified, it will not be imported");
			return;
		}
		ObjectMapper mapper = new ObjectMapper();
		//Creates the ArrayList<Room> type for the object mapper to reference when reading json.
		try {
			//converts the json file to a ArrayList<Room> object. Had a little help from stackOverflow for this one.
			roomListFromJson = mapper.readValue(jsonFile, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Room.class));
		} catch (JsonParseException e) {
			System.out.println("This File was unable to be parsed as a json file, aborting");
			return;
		} catch (JsonMappingException e) {
			System.out.println("This File was unable to Map to the Rooms List object");
			return;
		} catch (IOException e) {
			System.out.println("Error reading file");
			return;
		}		
		
		//Now add the roomlist to the existing collection
		roomList.clear();
		roomList.addAll(roomListFromJson);
		System.out.println("Rooms successfully imported!");
		
	}
	/**
	 * Using the jackson libraries, converts the room data into json. and prints it
	 * @param obj
	 */
	private static void getJsonForRooms(ArrayList<Room> roomList){
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";				
		try {
			//get the user directory location for writing the file to
			System.out.println("Please enter DIRECTORY for file location (file will be named roomSchedulerData.json)");
			File dir = new File(keyboard.next());
			jsonString = mapper.writeValueAsString(roomList);
			mapper.writeValue(new File(dir.getAbsolutePath()+File.separator+"roomSchedulerData.json"), roomList);
			System.out.println("File written to: "+dir.getAbsolutePath()+File.separator+"roomSchedulerData.json");
		} catch (JsonProcessingException e) {
			System.out.println("There was an error retrieving json");
		} catch (IOException e) {
			System.out.println("Unable to write to the specified directory");
		}
		System.out.println("Json As string:");
		System.out.println(jsonString);
		
	}
	
	/**
	 * List the schedule for a given room
	 * @param roomList
	 * @return String schedule
	 */
	protected static String listSchedule(ArrayList<Room> roomList) {
		//Defect #4. Fixed so that if the room doesn't exist it will return null. If it's null, then the appropriate
		//error will be shown
		String roomName = getRoomName();
		Room meetingRoom = getRoomFromName(roomList, roomName);
		if(meetingRoom == null){
			return "Room "+roomName+" does not exist. No schedule can be shown";
		}
		System.out.println(roomName + " Schedule");
		System.out.println(INPUT_SEPARATOR);
		for (Meeting m : meetingRoom.getMeetings()) {
			System.out.println(m.toString());
		}

		return "";
	}
	
	/**
	 * MainMenu method, Prompts user for selection, and will continue prompting if the selection is invalid
	 * @return int selectionNum
	 */
	public static int mainMenu() {
		//Defect fix #1. Continuously ask the user for input in the event the input isn't a valid selection
		boolean inputValidated = false;
		Integer numInt = 0;
		
		while(!inputValidated){
			System.out.println("Main Menu:");
			System.out.println("  1 - Add a room");
			System.out.println("  2 - Remove a room");
			System.out.println("  3 - Schedule a room");
			System.out.println("  4 - List Schedule");
			System.out.println("  5 - List Rooms");
			System.out.println("  6 - Export Rooms as JSON");
			System.out.println("  7 - Import Rooms from JSON");
			System.out.println("Enter your selection: ");
			String numStr = keyboard.next().trim();
			//validate that the user input is correct
			//In retrospect, it probably would have been better to just use the .next(pattern) method.
			if(validator.validateUserInputIsInt(numStr,1,7)){
				numInt = Integer.parseInt(numStr);
				inputValidated=true;
			}else{
				System.out.println("Invalid input. Please enter a numeric selection between 1-5");
			}		
		}
		return numInt;
	}
	/**
	 * Add a room to the Room scheduling list, validates input.
	 * @param roomList
	 * @return
	 */
	protected static String addRoom(ArrayList<Room> roomList) {
		//no need to check the string input, as  the room name can be anything (letters or nums)
		System.out.println("Add a room:");
		String name = getRoomName();
		int capacity = 0;
		boolean inputValidated = false;
		//Defect #2, when entering capacity, any non numbers throw an exception
		//validate the capacity is an int.
		while(!inputValidated){
			System.out.println("Room capacity?");
			String capacityStr = keyboard.next();
			if(validator.validateUserInputIsInt(capacityStr,null,null)){
				//now we know it's ok to parse the String to an int
				capacity = Integer.parseInt(capacityStr);
				inputValidated=true;
			}else{
				System.out.println("Invalid input for capacity, please enter a valid number");
			}
		}
		Room newRoom = new Room(name, capacity);
		//make sure the room doesn't already exist
		if(getRoomFromName(roomList, newRoom.getName())==null){
			roomList.add(newRoom);
			return "Room '" + newRoom.getName() + "' added successfully!";
		}else{
			return "Room '"+newRoom.getName()+ "' already exists. It was not added";
		}

	}

	/**
	 * Remove a room menu option. Searches for and returns the desired room
	 * @param roomList
	 * @return String resultMessage
	 */
	protected static String removeRoom(ArrayList<Room> roomList) {
		System.out.println("Remove a room:");
		String room = getRoomName();
		int index = findRoomIndex(roomList, room);
		//Defect #3. Check return of room index to verify it is an actual room
		if(index<0){
			return "Specified Room "+room+" does not exist. Unable to remove";
		}
		
		roomList.remove(index);

		return "Room removed successfully!";
	}

	/**
	 * List all rooms entered into the utility
	 * @param roomList
	 * @return ArrayList rooms
	 */
	protected static String listRooms(ArrayList<Room> roomList) {
		System.out.println("Room Name - Capacity");
		System.out.println(INPUT_SEPARATOR);

		for (Room room : roomList) {
			System.out.println(room.getName() + " - " + room.getCapacity());
		}

		System.out.println(INPUT_SEPARATOR);

		return roomList.size() + " Room(s)";
	}
	
	/**
	 * Schedule a room, all input is validated here 
	 * @param roomList
	 * @return resultMessage
	 */
	protected static String scheduleRoom(ArrayList<Room> roomList) {
		String startDate = "";
		String endDate = "";
		String startTime = "";
		String endTime = "";
		//Defect #? all time/date validation was incorrect here.
			//validate the user entered room
			System.out.println("Schedule a room:");
			String name = getRoomName();
			Room curRoom = getRoomFromName(roomList, name);
			if(curRoom == null){
				return "This room does not exist, it can not be scheduled";
			}
			//validate the user entered timestamp
			startDate = getValidatedDate("Start Date? (yyyy-mm-dd):");
			startTime = getValidatedTime("Start Time? (HH:mm)");
			endDate = getValidatedDate("End Date? (yyyy-mm-dd):");
			endTime = getValidatedTime("End Time? (HH:mm)");			
			
			Timestamp startTimestamp = Timestamp.valueOf(startDate + " " + startTime);
			Timestamp endTimestamp = Timestamp.valueOf(endDate + " " + endTime);
	
			System.out.println("Subject?");
			String subject = keyboard.next();
	
	
			Meeting meeting = new Meeting(startTimestamp, endTimestamp, subject);
			if(validator.validateNoMeetingConflicts(curRoom,meeting)){
				curRoom.addMeeting(meeting);				
				return "Successfully scheduled meeting!\n";
			}else{
				System.out.println("The following other rooms may be available: ");
				for(Room room : roomList){
					if(room != curRoom){
						System.out.println(room.getName());
					}
				}
				return "Unable to schedule meeting\n";
			}
	}	
	/**
	 * validate the user entered date
	 * In retrospect, it probably would have been better to just use the .next(pattern) method.
	 * @param requestString
	 * @return date
	 */
	private static String getValidatedDate(String requestString){
		//reset validation for next inputs
		String date= "";
		boolean validateInput=false;
		//validate timestring
		while(!validateInput){
			System.out.println(requestString);
			date = keyboard.next();				
			validateInput = validator.validateUserInputDate(date);
		}
		return date;
		
	}
	
	/**
	 * Gets validated time stamp
	 * @param requestString
	 * @return String timestamp
	 */
	private static String getValidatedTime(String requestString){
		boolean validateInput=false;
		String time = "";
		while(!validateInput){
			System.out.println(requestString);
			time = keyboard.next();				
			validateInput = validator.validateUserInputTime(time);
			time = time + ":00.0";
		}
		return time;
	}
	/**
	 * Get room from name, if specified room doesn't exist. will return null.
	 * @param roomList
	 * @param name
	 * @return
	 */
	protected static Room getRoomFromName(ArrayList<Room> roomList, String name) {
		int index = findRoomIndex(roomList,name);
		if(index<0){
			return null;
		}
		return roomList.get(index);
	}

	/**
	 * Searches room index for the given room and returns the index as an int.
	 * If the room isn't found, it will return a -1
	 * @param roomList
	 * @param roomName
	 * @return int roomIndex
	 */
	protected static int findRoomIndex(ArrayList<Room> roomList, String roomName) {
		//Defect #3. If the room doesn't exist. It shouldn't hit an exception and end. So instead we'll return
		//a negative number. The callers of this method will need to check for that
		int roomIndex = 0;
		for (Room room : roomList) {
			if (room.getName().compareTo(roomName) == 0) {
				return roomIndex;
			}
			roomIndex++;
		}
		
		return -1;
	}

	protected static String getRoomName() {
		System.out.println("Room Name?");
		return keyboard.next();
	}

}
