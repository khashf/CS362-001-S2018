/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;

public class ApptUnitTest {

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
  public void testSetValid7() throws Throwable {
    Appt apptTestMonth1 = new Appt(15, 30, 9, 0, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestMonth1.setValid();
    assertEquals(false, apptTestMonth1.getValid());

    Appt apptTestMonth2 = new Appt(15, 30, 9, 1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestMonth2.setValid();
    assertEquals(true, apptTestMonth2.getValid());
    
    Appt apptTestMonth3 = new Appt(15, 30, 9, 2, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestMonth3.setValid();
    assertEquals(true, apptTestMonth3.getValid());
    
    Appt apptTestMonth4 = new Appt(15, 30, 9, 11, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestMonth4.setValid();
    assertEquals(true, apptTestMonth4.getValid());
    
    Appt apptTestMonth5 = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestMonth5.setValid();
    assertEquals(true, apptTestMonth5.getValid());
    
    Appt apptTestMonth6 = new Appt(15, 30, 9, 13, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestMonth6.setValid();
    assertEquals(false, apptTestMonth6.getValid());
  }

  @Test(timeout = 4000)
  public void testSetValid8() throws Throwable {
    Appt apptTestHour1 = new Appt(-1, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestHour1.setValid();
    assertEquals(false, apptTestHour1.getValid());

    Appt apptTestHour2 = new Appt(0, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestHour2.setValid();
    assertEquals(true, apptTestHour2.getValid());

    Appt apptTestHour3 = new Appt(1, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestHour3.setValid();
    assertEquals(true, apptTestHour3.getValid());

    Appt apptTestHour4 = new Appt(22, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestHour4.setValid();
    assertEquals(true, apptTestHour4.getValid());

    Appt apptTestHour5 = new Appt(23, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestHour5.setValid();
    assertEquals(true, apptTestHour5.getValid());

    Appt apptTestHour6 = new Appt(24, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestHour6.setValid();
    assertEquals(false, apptTestHour6.getValid());
  }

  @Test(timeout = 4000)
  public void testSetValid9() throws Throwable {
    Appt apptTestMin1 = new Appt(0, -1, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestMin1.setValid();
    assertEquals(false, apptTestMin1.getValid());

    Appt apptTestMin2 = new Appt(0, 0, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestMin2.setValid();
    assertEquals(true, apptTestMin2.getValid());

    Appt apptTestMin3 = new Appt(1, 1, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestMin3.setValid();
    assertEquals(true, apptTestMin3.getValid());

    Appt apptTestMin4 = new Appt(22, 58, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestMin4.setValid();
    assertEquals(true, apptTestMin4.getValid());

    Appt apptTestMin5 = new Appt(23, 59, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestMin5.setValid();
    assertEquals(true, apptTestMin5.getValid());

    Appt apptTestMin6 = new Appt(0, 60, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestMin6.setValid();
    assertEquals(false, apptTestMin6.getValid());
  }

  @Test(timeout = 4000)
  public void testSetValid10() throws Throwable {
    Appt apptTestDay1 = new Appt(0, 0, 0, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay1.setValid();
    assertEquals(false, apptTestDay1.getValid());

    Appt apptTestDay2 = new Appt(0, 0, 1, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay2.setValid();
    assertEquals(true, apptTestDay2.getValid());

    Appt apptTestDay3 = new Appt(1, 1, 2, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay3.setValid();
    assertEquals(true, apptTestDay3.getValid());

    Appt apptTestDay4 = new Appt(22, 58, 30, 1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay4.setValid();
    assertEquals(true, apptTestDay4.getValid());

    Appt apptTestDay5 = new Appt(23, 59, 31, 1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay5.setValid();
    assertEquals(true, apptTestDay5.getValid());

    Appt apptTestDay6 = new Appt(0, 0, 32, 1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay6.setValid();
    assertEquals(false, apptTestDay6.getValid());

    Appt apptTestDay7 = new Appt(22, 58, 29, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay7.setValid();
    assertEquals(true, apptTestDay7.getValid());

    Appt apptTestDay8 = new Appt(23, 59, 30, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay8.setValid();
    assertEquals(true, apptTestDay8.getValid());

    Appt apptTestDay9 = new Appt(0, 0, 31, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay9.setValid();
    assertEquals(false, apptTestDay9.getValid());

    Appt apptTestDay10 = new Appt(22, 58, 27, 2, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay10.setValid();
    assertEquals(true, apptTestDay10.getValid());

    Appt apptTestDay11 = new Appt(23, 59, 28, 2, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay11.setValid();
    assertEquals(true, apptTestDay11.getValid());

    Appt apptTestDay12 = new Appt(0, 0, 29, 2, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay12.setValid();
    assertEquals(false, apptTestDay12.getValid());

    Appt apptTestDay13 = new Appt(22, 58, 28, 2, 2020, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay13.setValid();
    assertEquals(true, apptTestDay13.getValid());

    Appt apptTestDay14 = new Appt(23, 59, 29, 2, 2020, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay14.setValid();
    assertEquals(true, apptTestDay14.getValid());

    Appt apptTestDay15 = new Appt(0, 0, 30, 2, 2020, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    apptTestDay15.setValid();
    assertEquals(false, apptTestDay15.getValid());
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
