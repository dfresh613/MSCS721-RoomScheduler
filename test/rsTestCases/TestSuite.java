/**
   * Douglas Rohde License
   * This is an example license used throughout RoomScheduler
   * Other possible licenses to use include Apache, GPL, etc...
   * More license text.
   */
package rsTestCases;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RoomTests.class, MeetingTests.class, InputTests.class })
public class TestSuite {

}
