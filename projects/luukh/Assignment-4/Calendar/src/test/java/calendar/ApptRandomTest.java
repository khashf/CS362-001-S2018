package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;

import org.junit.Assert.*;
import org.junit.rules.*;
/**
 * Random Test Generator for Appt class.
 */

public class ApptRandomTest {
	
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS = 100;

	// @Rule
	// public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

	/**
	 * Return a randomly selected method to be tests !.
	 */
	public static String RandomSelectMethod(Random random) {
		// The list of the of methods to be tested in the Appt class
		String[] methodArray = new String[] { "setTitle", "setRecurrence", "isOn" };

		// get a random number between 0 (inclusive) and methodArray.length (exclusive)
		int n = random.nextInt(methodArray.length);

		return methodArray[n]; // return the method name
	}

	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
	public static int RandomSelectRecur(Random random) {
		// The list of the of setting appointments to recur Weekly,Monthly, or Yearly
		int[] RecurArray = new int[] { Appt.RECUR_BY_WEEKLY, Appt.RECUR_BY_MONTHLY, Appt.RECUR_BY_YEARLY };

		// get a random number between 0 (inclusive) and RecurArray.length (exclusive)
		int n = random.nextInt(RecurArray.length);
		return RecurArray[n]; // return the value of the appointments to recur
	}

	/**
	 * Return a randomly selected appointments to recur forever or Never recur !.
	 */
	public static int RandomSelectRecurForEverNever(Random random) {
		// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER
		int[] RecurArray = new int[] { Appt.RECUR_NUMBER_FOREVER, Appt.RECUR_NUMBER_NEVER };

		// get a random number between 0 (inclusive) and RecurArray.length (exclusive)
		int n = random.nextInt(RecurArray.length);
		return RecurArray[n]; // return appointments to recur forever or Never recur
	}

	/**
	 * Generate Random Tests that tests Appt Class.
	 */
	@Test
	public void randomTest() throws Throwable {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing Appt class...");

		try {
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed = System.currentTimeMillis(); // 10
				// System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);

				int startHour = ValuesGenerator.getRandomIntBetween(random, -1, 24);
				int startMinute = ValuesGenerator.getRandomIntBetween(random, -1, 60);
				int startDay = ValuesGenerator.getRandomIntBetween(random, -1, 32);
				int startMonth = ValuesGenerator.getRandomIntBetween(random, -1, 13);
				int startYear = ValuesGenerator.getRandomIntBetween(random, -10, 2018);
				String title = "Birthday Party";
				String description = "This is my birthday party.";
				String emailAddress = "xyz@gmail.com";

				// Construct a new Appointment object with the initial data
				Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description,
						emailAddress);

				appt.setValid();
				if (!appt.getValid())
					continue;
				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);
					if (methodName.equals("setTitle")) {
						String newTitle = (String) ValuesGenerator.getString(random);
						appt.setTitle(newTitle);
					} else if (methodName.equals("setRecurrence")) {
						int[] recurDays = null;
						int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 3);
						if (sizeArray != 0) {
							recurDays = ValuesGenerator.generateRandomArray(random, sizeArray);
						}
						int recur = ApptRandomTest.RandomSelectRecur(random);
						int recurIncrement = ValuesGenerator.RandInt(random);
						int recurNumber = ApptRandomTest.RandomSelectRecurForEverNever(random);
						appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
						// TODO: assert
						//softly.assertThat(appt.isOn(startDay, startMonth, startYear)).isEqualTo(true);
						//errorCollector.checkThat(appt.isOn(startDay, startMonth, startYear), equalTo(true));
					} else if (methodName.equals("isOn")) {
						appt.isOn(startDay, startMonth, startYear);
						appt.isOn(startDay+1, startMonth, startYear);
						appt.isOn(startDay, startMonth+1, startYear);
						appt.isOn(startDay, startMonth, startYear+1);
						appt.isOn(startDay+1, startMonth+1, startYear);
						appt.isOn(startDay, startMonth+1, startYear+1);
						appt.isOn(startDay+1, startMonth, startYear+1);
						appt.isOn(startDay+1, startMonth+1, startYear+1);
						// TODO: assert
					}
				}

				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if ((iteration % 10000) == 0 && iteration != 0)
					System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);

			}
		} catch (NullPointerException e) {

		}

		System.out.println("Done testing Appt class...");
	}

}
