package com.marist.mscs721;



import java.sql.Timestamp;

import org.apache.log4j.Logger;

/**
 * Input validation class providing methods for the various Roomscheduler validations that are necessary.
 * @author derohde
 *
 */
public class UserInputValidator {
	private static Logger logger = Logger.getLogger(UserInputValidator.class);
	public UserInputValidator(){
	}

	/**
	 * Send user input for integer validation here. It will attempt to convert the String to int , and validate
	 * it is in the range specified. (null for min, or null for max indicates no min, or no max)
	 * returns false if the input isn't an int, or if it's not in the specified range.
	 * @param input
	 * @return boolean validated
	 */
	public boolean validateUserInputIsInt(String input, Integer min, Integer max){		
		logger.info("attempting to validate user input: "+input);
		Integer numInt = null;


		boolean moreThanMin = false;
		boolean lessThanMax = true;
		try{
			numInt = Integer.parseInt(input);
	    //catch any number format exceptions if the given string isn't actually an integer
		}catch(NumberFormatException e){	
			logger.warn("Unable to validate input: "+input+", exception ocurred");
			logger.warn(e,e.getCause());
			return false;
		}
		//validate that the selection is within acceptable range
		if(min!= null){
			moreThanMin = (numInt >= min);
			logger.info("checking that input "+numInt +" is >= "+ min);
		}else{
			
			moreThanMin=true;
		}
		
		if(max!=null){
			lessThanMax = (numInt <= max);
			logger.info("checking that input "+numInt +" is <= "+ max);
		}else{
			lessThanMax=true;
		}
		if(moreThanMin && lessThanMax){
			logger.info("input validated");
		}else{
			logger.warn("input not in between min and max");
		}
		return (moreThanMin && lessThanMax);
		
		
	}
	/**
	 * Validates that no meetings are taking place at the same time. Returns true if there is NOT a conflict 
	 * returns false if there is a conflict
	 * @param room
	 * @param meeting
	 * @return boolean Notconflicting
	 */
	public boolean validateNoMeetingConflicts(Room room, Meeting meeting){
		logger.info("checking room conflicts for room: "+room+" and meeting: "+ meeting.getSubject());
		for (Meeting existingMeeting : room.getMeetings()){
			Timestamp existingStartTime = existingMeeting.getStartTime();
			Timestamp existingEndTime = existingMeeting.getStopTime();
			
			Timestamp newStartTime = meeting.getStartTime();
			Timestamp newEndTime = meeting.getStopTime();
			
			//Convert the times to epoch, making it more easily comparable
			long existingStartTimeEpoch = existingStartTime.getTime();
			long existingEndTimeEpoch = existingEndTime.getTime();
			
			long newStartTimeEpoch = newStartTime.getTime();
			long newEndTimeEpoch = newEndTime.getTime();
			//First lets make sure that the end time is after the start time
			if(newEndTimeEpoch <= newStartTimeEpoch){
				System.out.println("The end time must be after the start time");
				logger.warn("user attempted to schedule a meeting with end time < startTime");
				return false;
			}
			
			//check that the start time is not in between existing meeting times 
			if(newStartTimeEpoch >= existingStartTimeEpoch && newStartTimeEpoch <= existingEndTimeEpoch){
				System.out.println("This meeting conflicts with an already scheduled meeting");
				logger.warn(meeting.getSubject()+" meeting conflicts in room "+room);
				return false;
			}
			
			//check that the end time is not in between existing meeting times
			if(newEndTimeEpoch >= existingStartTimeEpoch && newEndTimeEpoch <= existingEndTimeEpoch){
				System.out.println("This meeting conflicts with an already scheduled meeting");
				logger.warn(meeting.getSubject()+" meeting conflicts in room "+room);
				return false;
			}
			
		}
		return true;
	}
	/**
	 * Simply prints date validation error. So I don't have to type it multiple times
	 */
	private static void printDateValidationError(){
    	System.out.println("This entered date is not valid. Please enter in the format yyyy-MM-dd. The year must be >= 2016 and <=2030");
	}
	
	/**
	 * Simply prints time validation error. So I don't have to type it multiple times
	 */
	private static void printTimeValidationError(){
    	System.out.println("This entered time is not valid. Please enter in the format HH:mm");
	}

	/**
	 * Validates the user entered date in the format yyyy-mm-dd, if it's not a valid date, then this method will return false
	 * this method also validates that the year >=2016 month is 1-12 and day 1-31
	 * returns true if validated, false if not
	 * @return boolean validated
	 */
	public boolean validateUserInputDate(String timeStr){
		logger.info("Validating that user input is valid date "+timeStr);
		//split timestamp by '-' parse each piece separately
		String[] dateSplits = timeStr.split("-");
		if(dateSplits.length!=3){
			printDateValidationError();
			logger.error("User entered date: "+timeStr+" not valid");
			return false;
		}
		String year = dateSplits[0];
		String month = dateSplits[1];
		String day = dateSplits[2];
		//validate year is between 2016 and 2030
		if(!validateUserInputIsInt(year, 2016,2030)){
			logger.error("User entered date: "+timeStr+" not in correct year range");
			printDateValidationError();
			return false;
		}
		//validate month is between 1 and 12
		if(!validateUserInputIsInt(month, 1,12)){
			logger.error("User entered date: "+timeStr+" not in correct month range");

			printDateValidationError();
			return false;
		}
		//validates day is between 1 and 31
		if(!validateUserInputIsInt(day,1,31)){
			logger.error("User entered date: "+timeStr+" not in correct day range");

			printDateValidationError();
			return false;
		}
	    
		return true;
	}

	/**
	 * Validates the user entered time in the format HH:mm;
	 * validates hour 0-23 and minute 0-60
	 * @return boolean timeValidated
	 */
	public boolean validateUserInputTime(String timeStr){
		logger.info("validating user input time of :"+ timeStr);
	   String[] timeSplits = timeStr.split(":");
	   if(timeSplits.length != 2){
			printTimeValidationError();
			return false;
		}
	   String hour = timeSplits[0];
	   String min = timeSplits[1];
	
	   //validate the hour
	   if(!validateUserInputIsInt(hour, 0,23)){
			logger.info("User input time "+ timeStr+ " not a valid hour");

			printTimeValidationError();
			return false;
		}
	
	   if(!validateUserInputIsInt(min,0,60)){
			logger.info("User input time "+ timeStr+ " not a valid minute");

			printTimeValidationError();
			return false;
	   }
	
	   return true;
	}
	
	

}
