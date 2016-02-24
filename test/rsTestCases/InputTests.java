package rsTestCases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.marist.mscs721.UserInputValidator;

public class InputTests {

	private UserInputValidator validator;
	
	/**
	 * initializes validator for use in tests
	 */
	@Before
	public void initValidator(){
		validator = new UserInputValidator();
	}
	
	
	/**
	 * tests the int validator is working, and the logic
	 * for the range vaildation is working
	 */
	@Test
	public void testRangeValidation(){
		assertFalse(validator.validateUserInputIsInt("asdfasdf", 0, 1));
		assertFalse(validator.validateUserInputIsInt("13", 15, 20));
		assertTrue(validator.validateUserInputIsInt("13", 12, 20));
	}
	
	/**
	 * tests the date validator is working by providing
	 * a correct date, and an invalid date
	 */
	@Test
	public void TestInputDate(){
		assertTrue(validator.validateUserInputDate("2017-02-02"));
		assertFalse(validator.validateUserInputDate("232-2323-2323"));
	}
	/**
	 * tests that the validator will not accept any text input for its methods
	 */
	@Test
	public void TestTextualInput(){
		assertFalse(validator.validateUserInputDate("asdfadsfad"));
		assertFalse(validator.validateUserInputIsInt("asdfasdf", 0, 1));
		assertFalse(validator.validateUserInputTime("sdfsadf"));

	}
	
	
	/**
	 * tests the time validator is working by providing
	 *  a correct time, and an invalid time
	 */
	@Test
	public void TestInputTime(){
		assertFalse(validator.validateUserInputTime("sdfsadf"));
		assertFalse(validator.validateUserInputTime("322:32"));
		assertTrue(validator.validateUserInputTime("23:12"));
	}
	
	

}
