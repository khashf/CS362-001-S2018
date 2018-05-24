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

public class DataHandlerRandomTest {
	
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS = 100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
	public static String RandomSelectMethod(Random random) {
		// The list of the of methods to be tested in the Appt class
		String[] methodArray = new String[] { "deleteAppt", "getApptRange"};

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

		System.out.println("Start testing DataHandler class...");

		try {
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed = System.currentTimeMillis(); // 10
				Random random = new Random(randomseed);

                

				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = CalDayRandomTest.RandomSelectMethod(random);
					if (methodName.equals("deleteAppt")) {
                        
                    } else if (methodName.equals("getApptRange")) {

                    }
				}

				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if ((iteration % 10000) == 0 && iteration != 0)
					System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);

			}
		} catch (NullPointerException e) {

		}

		System.out.println("Done testing DataHandler class...");
	}

}
