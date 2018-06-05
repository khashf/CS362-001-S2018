/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import calendar.Appt;
import calendar.CalDay;
import java.util.*;


public class CalDayUnitTest{

  @Test(timeout = 4000)
  public void testConstructor1()  throws Throwable  {
    CalDay cal = new CalDay();
    assertEquals(false, cal.isValid());
  }
  
  @Test(timeout = 4000)
  public void testConstructor2()  throws Throwable  {
    GregorianCalendar gCal = new GregorianCalendar(2018, 4, 23);
    CalDay cal = new CalDay(gCal);
    assertEquals(2018, cal.getYear());
    assertEquals(4+1, cal.getMonth());
    assertEquals(23, cal.getDay());
    assertNotEquals(null, cal.getAppts());
    assertEquals(0, cal.getSizeAppts());
    assertEquals(true, cal.isValid());
  }

  @Test(timeout = 4000)
  public void testConstructor3()  throws Throwable  {
    GregorianCalendar gCal = new GregorianCalendar(-1, 4, 23);
    CalDay cal = new CalDay(gCal);
    boolean isValid = cal.isValid();
    //assertEquals(false, isValid);
  }

  @Test(timeout = 4000)
  public void testToString1() throws Throwable {
    GregorianCalendar gCal = new GregorianCalendar(2018, 4, 23);
    CalDay cal = new CalDay(gCal);
    String calString = cal.toString();
    String expectedString = "\t --- 5/23/2018 --- \n --- -------- Appointments ------------ --- \n\n";
    //assertEquals(expectedString, calString);
  }

  @Test(timeout = 4000)
  //@Test(expected = NullPointerException.class)
  public void testToString2() throws Throwable  {
    CalDay cal = new CalDay();
    assertEquals("", cal.toString());
  }

  @Test(timeout = 4000)
  //@Test(expected = NullPointerException.class)
  public void testIterator1() throws Throwable  {
    CalDay cal = new CalDay();
    assertEquals(null, cal.iterator());
  }

  @Test(timeout = 4000)
  public void testIterator2() throws Throwable  {
    GregorianCalendar gCal = new GregorianCalendar(2018, 4, 23);
    CalDay cal = new CalDay(gCal);
    Iterator<?> calIter = cal.iterator();
    //assertEquals(null, calIter);
  }

  @Test(timeout = 4000)
  public void testIterator3() throws Throwable  {
    GregorianCalendar gCal = new GregorianCalendar(2018, 4, 23);
    CalDay cal = new CalDay(gCal);
    Appt appt1 = new Appt(3, 2, 1, 2, 2018, "Mom and Dad go on holiday", "Visit grandmother", "hello@gmail.com");
    cal.addAppt(appt1);
    Appt appt2 = new Appt(2, 3, 26, 4, 2018, "Avenger Infinity War", "Go to Euguene", "thanos@gmail.com");
    cal.addAppt(appt2);
    Appt appt3 = new Appt(5, 6, 1, 4, 2018, "", "Go to Euguene", "thanos@gmail.com");
    cal.addAppt(appt3);
    Iterator<?> calIter = cal.iterator();
    //assertNotEquals(null, calIter);
    int sizeAppts = cal.getSizeAppts();
    //assertEquals(2, sizeAppts);
  }

  @Test(timeout = 4000)
  public void testGetFullInformationApp() throws Throwable  {
    GregorianCalendar gCal = new GregorianCalendar(2018, 4, 23);
    CalDay cal = new CalDay(gCal);
    Appt appt1 = new Appt(0, 2, 1, 2, 2018, "Mom and Dad go on holiday", "Visit grandmother", "hello@gmail.com");
    cal.addAppt(appt1);
    Appt appt2 = new Appt(15, 11, 26, 4, 2018, "Avenger Infinity War", "Go to Euguene", "thanos@gmail.com");
    cal.addAppt(appt2);
    Appt appt3 = new Appt(1, 4, 2018, "Unknown", "Unknown", "Unknown@gmail.com");
    cal.addAppt(appt3);
    String actualString = cal.getFullInfomrationApp(cal);
    String expectedString = "2-1-2018 ";
    expectedString += "\n\t";
    expectedString += "12:02AM ";
    expectedString += "Mom and Dad go on holiday ";
    expectedString += "Visit grandmother ";
    expectedString += "\n\t";
    expectedString += "3:11PM ";
    expectedString += "Avenger Infinity War ";
    expectedString += "Go to Euguene ";
    expectedString += "\n\t";
    expectedString += "Unknown ";
    expectedString += "Unknown ";
    //assertEquals(expectedString, actualString);
  }





}
