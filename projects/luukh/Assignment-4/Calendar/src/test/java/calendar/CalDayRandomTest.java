package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.Test;

import org.junit.Assert.*;
import org.junit.rules.*;
/**
 * Random Test Generator for Appt class.
 */

public class CalDayRandomTest {
	
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS = 100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
	public static String RandomSelectMethod(Random random) {
		// The list of the of methods to be tested in the Appt class
		String[] methodArray = new String[] { "addAppt"};

		// get a random number between 0 (inclusive) and methodArray.length (exclusive)
		int n = random.nextInt(methodArray.length);

		return methodArray[n]; // return the method name
	}

	/**
	 * Generate Random Tests that tests Appt Class.
	 */
	@Test
	public void randomTest() throws Throwable {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing CalDay class...");

		try {
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed = System.currentTimeMillis(); // 10
				// System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);

                int gYear = ValuesGenerator.getRandomIntBetween(random, -1, 2018);
                int gMonth = ValuesGenerator.getRandomIntBetween(random, -1, 13);
                int gDay = ValuesGenerator.getRandomIntBetween(random, -1, 32);
                GregorianCalendar gCal = new GregorianCalendar(gYear, gMonth, gDay);
                CalDay cal = new CalDay(gCal);

				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = CalDayRandomTest.RandomSelectMethod(random);
					if (methodName.equals("addAppt")) {
                        int newApptHour = ValuesGenerator.getRandomIntBetween(random, -1, 24);
                        int newApptMinute = ValuesGenerator.getRandomIntBetween(random, -1, 60);
                        int newApptDay = ValuesGenerator.getRandomIntBetween(random, -1, 32);
                        int newApptMonth = ValuesGenerator.getRandomIntBetween(random, -1, 13);
                        int newApptYear = ValuesGenerator.getRandomIntBetween(random, -10, 2018);
                        String newApptTitle = "Birthday Party";
                        String newApptDescription = "This is my birthday party.";
                        String newApptEmailAddress = "xyz@gmail.com";
                        Appt newAppt = new Appt(newApptHour, newApptMinute, newApptDay, newApptMonth, newApptYear, newApptTitle, newApptDescription,
                        newApptEmailAddress);
                        newAppt.setValid();            
                        cal.addAppt(newAppt);
                        // TODO: Assert
                    }
				}

				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if ((iteration % 10000) == 0 && iteration != 0)
					System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);

			}
		} catch (NullPointerException e) {

		}

		System.out.println("Done testing CalDay class...");
	}

}
