/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;

public class ApptTest {

  @Test(timeout = 4000)
  public void testConstructor1() throws Throwable {
    Appt apptValidData = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    String apptString1 = apptValidData.toString();
    assertEquals("\t12/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", apptString1);

    Appt apptHourZero = new Appt(0, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    String apptString2 = apptHourZero.toString();
    assertEquals("\t12/9/2018 at 12:30am ,Birthday Party, This is my birthday party\n", apptString2);

    Appt apptInvalidMonth = new Appt(25, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    String apptString3 = apptInvalidMonth.toString();
    //assertEquals("\tThis appointment is not valid", apptString3);
  }

  @Test(timeout = 4000)
  public void testConstructor2() throws Throwable {
    Appt appt = new Appt(25, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    boolean isValid = appt.getValid();
    //assertFalse(isValid);
  }

  @Test(timeout = 4000)
  public void testConstructor3() throws Throwable {
    Appt appt = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    assertEquals("xyz@gmail.com", appt.getEmailAddress());
  }

  @Test(timeout = 4000)
  public void testConstructor4() throws Throwable {
    Appt appt = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    int[] recurringDaysTest = new int[0];
    assertArrayEquals(recurringDaysTest, appt.getRecurDays());
    assertEquals(2, appt.getRecurBy());
    assertFalse(appt.isRecurring());
    assertEquals(0, appt.getRecurIncrement());
    assertEquals(0, appt.getRecurNumber());
  }

  @Test(timeout = 4000)
  public void testConstructor5() throws Throwable {
    Appt appt = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    assertEquals(null, appt.getXmlElement());
  }

  @Test
  public void testConstructor6() throws Throwable {
    Appt appt = new Appt(15, 30, 9, 12, 2018, null, null, null);
    assertEquals("", appt.getTitle());
    assertEquals("", appt.getDescription());
    assertEquals("", appt.getEmailAddress());
  }


  @Test(timeout = 4000)
  public void testSetValid1() throws Throwable {
    Appt apptInvalidMonth = new Appt(12, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptInvalidMonth.setValid();
    assertFalse(apptInvalidMonth.getValid());
  }

  @Test(timeout = 4000)
  public void testSetValid2() throws Throwable {
    Appt apptInvalidHour = new Appt(25, 30, 9, 3, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptInvalidHour.setValid();
    assertFalse(apptInvalidHour.getValid());
  }

  @Test(timeout = 4000)
  public void testSetValid3() throws Throwable {
    Appt apptInvalidMinutes = new Appt(10, 60, 9, 3, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptInvalidMinutes.setValid();
    assertFalse(apptInvalidMinutes.getValid());
  }

  @Test(timeout = 4000)
  public void testSetValid4() throws Throwable {
    Appt apptInvalidYear = new Appt(10, 22, 9, 3, 0, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptInvalidYear.setValid();
    assertFalse(apptInvalidYear.getValid());
  }
  
  @Test(timeout = 4000)
  public void testSetValid5() throws Throwable {
    Appt apptInvalidDay = new Appt(10, 22, 30, 2, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptInvalidDay.setValid();
    assertEquals(false, apptInvalidDay.getValid());
  }

  @Test(timeout = 4000)
  public void testSetValid6() throws Throwable {
    Appt appt = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    assertEquals(true, appt.getValid());
    appt.setValid();
    assertEquals(true, appt.getValid());
  }

  @Test(timeout = 4000)
  public void testIsOn1() throws Throwable {
    Appt appt = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    assertTrue(appt.isOn(9, 12, 2018));
  }

  @Test(timeout = 4000)
  public void testIsOn2() throws Throwable {
    Appt appt = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    assertFalse(appt.isOn(2, 2, 2020));
  }

  @Test(timeout = 4000)
  public void testHasTimeSet1() throws Throwable {
    Appt appt01 = new Appt(9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    assertFalse(appt01.hasTimeSet());
  }

  @Test(timeout = 4000)
  public void testHasTimeSet2() throws Throwable {
    Appt appt02 = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    assertTrue(appt02.hasTimeSet());
  }

  @Test(timeout = 4000)
  public void testSetRecurrence() throws Throwable {
    Appt appt = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    appt.setRecurrence(null, 0, 0, 0);

    int[] expectedRecurDays = new int[0];
    int[] recurDays = appt.getRecurDays();
    //assertEquals(expectedRecurDays, recurDays);
    int recurBy = appt.getRecurBy();
    //assertEquals(0, recurBy);
    int recurIncrement = appt.getRecurIncrement();
    //assertEquals(0, recurIncrement);
    int recurNumber = appt.getRecurNumber();
    //assertEquals(0, recurNumber);
  }
}
