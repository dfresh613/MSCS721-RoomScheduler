package rsTestCases;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RoomTests.class, MeetingTests.class, InputTests.class })
public class TestSuite {

}
