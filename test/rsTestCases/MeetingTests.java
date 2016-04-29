/**
   * Douglas Rohde License
   * This is an example license used throughout RoomScheduler
   * Other possible licenses to use include Apache, GPL, etc...
   * More license text.
   */
package rsTestCases;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.marist.mscs721.Meeting;
import com.marist.mscs721.Room;
import com.marist.mscs721.RoomScheduler;

public class MeetingTests {
	private Meeting meeting;
	private Timestamp startTime = Timestamp.from(Instant.now());
	private Timestamp endTimeAfter = Timestamp.from(Instant.now().plusSeconds(5000));
	private String meetingName = "our meeting";
	
	
	/**
	 * Creates a meeting for use in tests
	 */
	@Before
	public void createMeeting(){
		//Creates a meeting from now, until 5000 seconds after.
		meeting = new Meeting(startTime, endTimeAfter, meetingName);
	}
	@Test
	/**
	 * Verify the startime of the created meeting
	 */
	public void testStartTime(){
		assertEquals(meeting.getStartTime(), startTime);
	}
	
	@Test
	/**
	 * Verify endTime of createdMeeting
	 */
	public void testEndTime(){
		assertEquals(meeting.getStopTime(), endTimeAfter);
	}
	
	@Test
	/**
	 * Verify subject of created meeting
	 */
	public void testSubject(){
		assertEquals(meeting.getSubject(), meetingName);
	}
	
	@Test
	/**
	 * Create two rooms, add a meeting to one.
	 * query the available rooms for the same meeting. 
	 * only 1 room should be listed
	 */
	public void testAvailableRoomsQuery(){
		ArrayList<Room> roomList = new ArrayList<Room>();
		Room conflictRoom = new Room("conflictRoom", 23, "Marist", "hancock");
		Room availableRoom = new Room("availableRoom", 23, "Marist", "Donnely");
		meeting = new Meeting(startTime, endTimeAfter, meetingName);
		conflictRoom.addMeeting(meeting);
		roomList.add(conflictRoom);
		roomList.add(availableRoom);
		List<Room> availableRooms = RoomScheduler.queryAvailableRooms(roomList, meeting);
		assertEquals(false, availableRooms.contains(conflictRoom));
		assertEquals(true, availableRooms.contains(availableRoom));
	}
	
	
	
	
	
	
}
