package com.marist.mscs721;
/**
 * Input validation class providing methods for the various Roomscheduler validations that are necessary.
 * @author derohde
 *
 */
public class UserInputValidator {

	/**
	 * Send user input for integer validation here. It will convert the String to int, and validate
	 * it is in the range specified. (null for min, or null for max indicates no min, or no max)
	 * @param input
	 */
	protected static boolean validateUserInputIsInt(String input, Integer min, Integer max){		
		Integer numInt = null;
		boolean moreThanMin = false;
		boolean lessThanMax = true;
		try{
			numInt = Integer.parseInt(input);
	    //catch any number format exceptions if the given string isn't actually an integer
		}catch(NumberFormatException e){			
			return false;
		}
		//validate that the selection is within acceptable range
		if(min!= null){
			moreThanMin = (numInt >= min);
		}else{
			moreThanMin=true;
		}
		
		if(max!=null){
			lessThanMax = (numInt <= max);
		}else{
			lessThanMax=true;
		}
		
		return (moreThanMin && lessThanMax);
		
		
	}
	
	/**
	 * Simply prints date validation error. So I don't have to type it multiple times
	 */
	private static void printDateValidationError(){
    	System.out.println("This entered date is not valid. Please enter in the format yyyy-MM-dd");
	}
	
	/**
	 * Simply prints time validation error. So I don't have to type it multiple times
	 */
	private static void printTimeValidationError(){
    	System.out.println("This entered time is not valid. Please enter in the format HH:mm");
	}

	/**
	 * Validates the user entered date in the format yyyy-mm-dd, if it's not a valid date, then this method will return false
	 * @return
	 */
	static boolean validateUserInputDate(String timeStr){
		//split timestamp by '-' parse each piece separately
		String[] dateSplits = timeStr.split("-");
		if(dateSplits.length!=3){
			printDateValidationError();
			return false;
		}
		String year = dateSplits[0];
		String month = dateSplits[1];
		String day = dateSplits[2];
		//validate year is between 2016 and 2030
		if(!validateUserInputIsInt(year, 2016,2030)){
			printDateValidationError();
			return false;
		}
		//validate month is between 1 and 12
		if(!validateUserInputIsInt(month, 1,12)){
			printDateValidationError();
			return false;
		}
		//validates day is between 1 and 31
		if(!validateUserInputIsInt(day,1,31)){
			printDateValidationError();
			return false;
		}
	    
		return true;
	}

	/**
	 * Validates the user entered time in the format HH:mm;
	 * validates hour and minute
	 * @return boolean timeValidated
	 */
	static boolean validateUserInputTime(String timeStr){
	   String[] timeSplits = timeStr.split(":");
	   if(timeSplits.length != 2){
			printTimeValidationError();
			return false;
		}
	   String hour = timeSplits[0];
	   String min = timeSplits[1];
	
	   //validate the hour
	   if(!validateUserInputIsInt(hour, 0,23)){
			printTimeValidationError();
			return false;
		}
	
	   if(!validateUserInputIsInt(min,0,60)){
			printTimeValidationError();
			return false;
	   }
	
	   return true;
	}
	
	

}
