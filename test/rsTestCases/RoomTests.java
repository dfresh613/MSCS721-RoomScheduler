/**
   * Douglas Rohde License
   * This is an example license used throughout RoomScheduler
   * Other possible licenses to use include Apache, GPL, etc...
   * More license text.
   */
package rsTestCases;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.marist.mscs721.Meeting;
import com.marist.mscs721.Room;

public class RoomTests {
	private static final String TEST_ROOM_NAME = "Test Room";
	private static final int TEST_ROOM_CAP = 25;
	private static final int TEST_ROOM_CAP2 = 5027822;
	private static String LOCATION = "Lowel Thomas";
	private static String BUILDING = "Hancock";
	private Room room;

	/**
	 * Creates a standard room to use for tests
	 * 
	 */
	@Before
	public void createRoom() {
		room = new Room(TEST_ROOM_NAME, 23, LOCATION, BUILDING);
	}

	/**
	 * Attempts to create a room, and attempts to get all info for it.
	 *
	 */
	@Test
	public void CreateRoomAndGet() {
		assertEquals(TEST_ROOM_NAME, room.getName());
		assertEquals( 23, room.getCapacity());

	}
	
	@Test 
	public void CreateRoomAndCheckDetails(){
		assertEquals(LOCATION, room.getLocation());
		assertEquals(BUILDING, room.getBuilding());
	}

	/**
	 * Create a room and sets the Capacity to something extravagent. The
	 * capacity, shouldn't be set as the number is unrealistic
	 */
	@Ignore
	@Test
	public void CreateRoomSetCapacity() {
		room.setCapacity(TEST_ROOM_CAP2);
		assertEquals(TEST_ROOM_CAP,room.getCapacity());

	}

	/**
	 * Create a room, add a meeting, list meetings, verify the meeting is in the
	 * list.
	 */
	@Test
	public void CreateRoomAddMeeting() {
		// Creates a meeting from now, until 5000 seconds after.
		Meeting newMeeting = new Meeting(Timestamp.from(Instant.now()), Timestamp.from(Instant.now().plusSeconds(5000)),
				"our meeting");
		room.addMeeting(newMeeting);
		// make sure the meeting we added matches
		assertEquals(room.getMeetings().get(0).toString(), newMeeting.toString());
	}
}
