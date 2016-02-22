package rsTestCases;


import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

public class CreateRoom {

	private InputStream convertToInputStream(String input){
		InputStream inStream = new ByteArrayInputStream(input.getBytes());
		return inStream;
	}
	
	/**
	 * Tests invalid input at main menu, including text and out of range numbers
	 */
	@Test
	public void testMain() {
		try{
			System.setIn(convertToInputStream("asdfasfdasdf"));
			System.setIn(convertToInputStream("99"));
		}catch (Exception e){
			fail("Main menu threw exception when text is entered");
		}
		
	}

	@Test
	public void testAddRoom() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveRoom() {
		fail("Not yet implemented");
	}

	@Test
	public void testListRooms() {
		fail("Not yet implemented");
	}

	@Test
	public void testScheduleRoom() {
		fail("Not yet implemented");
	}

}
