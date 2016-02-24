package rsTestCases;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.marist.mscs721.Meeting;

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
	
	
	
	
	
	
	
	
}
