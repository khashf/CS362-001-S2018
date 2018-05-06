
/** A JUnit test class to test the class DataHandler. */


package calendar;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.List;
import org.xml.sax.*;
import org.junit.Test;


public class DataHandlerTest{

  @Test(timeout = 4000)
  public void testConstructor1()  throws Throwable  {
    DataHandler dh = new DataHandler();
    assert(DataHandler.class.isInstance(dh));
  }

  @Test(expected = SAXException.class)
  public void testConstructor2()  throws Throwable  {
    String badFile = "calendar_bad_syntax.xml";
    DataHandler dh = new DataHandler(badFile, true);
  }

  @Test(timeout = 4000)
  public void testConstructor3()  throws Throwable  {
    String badFile = "non-existed.xml";
    // this will create a 'calendar.xml' file
    DataHandler dh = new DataHandler(badFile, true); 
    // String expectedDefaultFileName = "calendar.xml";
    // String expectedFileName = System.getProperty("user.dir") 
    //   + System.getProperty("file.separator") 
    //   + expectedDefaultFileName;
    // File file = new File(expectedFileName);
    // assertEquals(true, 
    //   file.isFile() && file.getName() == expectedDefaultFileName);
  }

  @Test(expected = DateOutOfRangeException.class)
  public void testGetApptRange1()  throws Throwable  {
    GregorianCalendar firstDay = new GregorianCalendar(1, 1, 2020);
    GregorianCalendar lastDay = new GregorianCalendar(1, 1, 1900);
    DataHandler dh = new DataHandler();
    List<CalDay> l = dh.getApptRange(firstDay, lastDay);
  }

  @Test(timeout = 4000)
  public void testGetApptRange2()  throws Throwable  {
    GregorianCalendar firstDay = new GregorianCalendar(1, 1, 2000);
    GregorianCalendar lastDay = new GregorianCalendar(1, 1, 3020);
    String expectedDefaultFileName = "calendar.xml";
    String expectedFileName = System.getProperty("user.dir") 
      + System.getProperty("file.separator") 
      + expectedDefaultFileName;
    DataHandler dh = new DataHandler(expectedFileName, true); // this will load 'calendar.xml'
    Appt newAppt = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    dh.saveAppt(newAppt);
    List<CalDay> l = dh.getApptRange(firstDay, lastDay);
    //assertEquals(5, l.size()); // 1020
  }

  @Test(timeout = 4000)
  public void testGetApptRange3()  throws Throwable  {

  }



}
