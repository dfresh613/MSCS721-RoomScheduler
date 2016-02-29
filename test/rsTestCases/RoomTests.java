package rsTestCases;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.marist.mscs721.Meeting;
import com.marist.mscs721.Room;

public class RoomTests {
	private static final String TEST_ROOM_NAME = "Test Room";
	private static final int TEST_ROOM_CAP = 25;
	private static final int TEST_ROOM_CAP2 = 5027822;
	private Room room;

	/**
	 * Creates a standard room to use for tests
	 * 
	 */
	@Before
	public void createRoom() {
		room = new Room(TEST_ROOM_NAME, 23);
	}

	/**
	 * Attempts to create a room, and attempts to get all info for it.
	 *
	 */
	@Test
	public void CreateRoomAndGet() {
		assertEquals(room.getName(), "Test Room");
		assertEquals(room.getCapacity(), 23);

	}

	/**
	 * Create a room and sets the Capacity to something extravagent. The
	 * capacity, shouldn't be set as the number is unrealistic
	 */
	@Test
	public void CreateRoomSetCapacity() {
		room.setCapacity(TEST_ROOM_CAP2);
		assertEquals(room.getCapacity(), TEST_ROOM_CAP);

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