package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.List;

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
		String[] methodArray = new String[] { "deleteAppt", "getApptRange"};
		int n = random.nextInt(methodArray.length);
		return methodArray[n];
	}

	private static Appt GenerateRandomAppt(Random random) {
		int newApptHour = ValuesGenerator.getRandomIntBetween(random, -1, 24);
		int newApptMinute = ValuesGenerator.getRandomIntBetween(random, -1, 60);
		int newApptDay = ValuesGenerator.getRandomIntBetween(random, -1, 32);
		int newApptMonth = ValuesGenerator.getRandomIntBetween(random, -1, 13);
		int newApptYear = ValuesGenerator.getRandomIntBetween(random, -10, 2018);
		String newApptTitle = "Birthday Party";
		String newApptDescription = "This is my birthday party.";
		String newApptEmailAddress = "xyz@gmail.com";
		Appt newAppt = new Appt(newApptHour, 
								newApptMinute, 
								newApptDay,
								newApptMonth, 
								newApptYear, 
								newApptTitle, 
								newApptDescription,
								newApptEmailAddress);
		int[] recurDays = null;
		int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 3);
		if (sizeArray != 0) {
			recurDays = ValuesGenerator.generateRandomArray(random, sizeArray);
		}
		int recur = ApptRandomTest.RandomSelectRecur(random);
		int recurIncrement = ValuesGenerator.RandInt(random);
		int recurNumber = ApptRandomTest.RandomSelectRecurForEverNever(random);
		newAppt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
		newAppt.setValid();
		return newAppt;
	}

	// Generate 1 Appt between 2 GregorianCalendar days
	private static Appt GenerateRandomAppt(Random random, GregorianCalendar day1, GregorianCalendar day2) {
		int newApptDay = ValuesGenerator.getRandomIntBetween(random, day1.get(Calendar.DAY_OF_MONTH), day2.get(Calendar.DAY_OF_MONTH));
		int newApptMonth = ValuesGenerator.getRandomIntBetween(random, day1.get(Calendar.MONTH), day2.get(Calendar.MONTH));
		int newApptYear = ValuesGenerator.getRandomIntBetween(random, day1.get(Calendar.YEAR), day2.get(Calendar.YEAR));
		String newApptTitle = "Birthday Party";
		String newApptDescription = "This is my birthday party.";
		String newApptEmailAddress = "xyz@gmail.com";
		Appt newAppt = new Appt(newApptDay,
								newApptMonth, 
								newApptYear, 
								newApptTitle, 
								newApptDescription,
								newApptEmailAddress);
		newAppt.setValid();
		return newAppt;
	}

	private static GregorianCalendar GenerateRandomGregorianCal(Random random) {
		int gYear = ValuesGenerator.getRandomIntBetween(random, -1, 2018);
		int gMonth = ValuesGenerator.getRandomIntBetween(random, -1, 13);
		int gDay = ValuesGenerator.getRandomIntBetween(random, -1, 32);
		GregorianCalendar gCal = new GregorianCalendar(gYear, gMonth, gDay);
		return gCal;
	}

	private static GregorianCalendar GenerateRandomGregorianCal(Random random, int year) {
		int gYear = year;
		int gMonth = ValuesGenerator.getRandomIntBetween(random, -1, 13);
		int gDay = ValuesGenerator.getRandomIntBetween(random, -1, 32);
		GregorianCalendar gCal = new GregorianCalendar(gYear, gMonth, gDay);
		return gCal;
	}

	private static GregorianCalendar GenerateRandomGregorianCal(Random random, int year, int month) {
		int gYear = year;
		int gMonth = month;
		int gDay = ValuesGenerator.getRandomIntBetween(random, -1, 32);
		GregorianCalendar gCal = new GregorianCalendar(gYear, gMonth, gDay);
		return gCal;
	}

	private static GregorianCalendar GenerateRandomGregorianCal(Random random, int year, int month, int day) {
		int gYear = year;
		int gMonth = month;
		int gDay = day;
		GregorianCalendar gCal = new GregorianCalendar(gYear, gMonth, gDay);
		return gCal;
	}

	/**
	 * Generate Random Tests that tests DataHandler class.
	 */
	@Test(expected = DateOutOfRangeException.class)
	public void randomTest() throws Throwable {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing DataHandler class...");

		try {
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed = System.currentTimeMillis(); // 10
				Random random = new Random(randomseed);

				// DataHandler dataHandlerAS = new DataHandler("test_autosave", true);
				// DataHandler dataHandlerNoAS = new DataHandler("test_no_autosave", false);
				// DataHandler dataHandlerDefault = new DataHandler("calendar", false);
				DataHandler defaultdh = new DataHandler();

				for (int i = 0; i < NUM_TESTS; i++) {
					
					String methodName = DataHandlerRandomTest.RandomSelectMethod(random);
					//System.out.println(methodName);
					if (methodName.equals("deleteAppt")) {
						//System.out.println("Start testing deleteAppt...");	
						// ArrayList<Appt> newAppts = new ArrayList<Appt>();
						// int nNewAppt = getRandomIntBetween(random, 0, 3);
						// for (int j = 0; j < nNewAppt; ++j) {
						// 	newAppt = GenerateRandomAppt(random);
						// 	newAppts.add(newAppt);
						// 	dataHandlerAS.saveAppt(appt);
						// 	dataHandlerNoAS.saveAppt(appt);
						// }
						Appt newAppt1 = GenerateRandomAppt(random);
						Appt newAppt2 = GenerateRandomAppt(random);
						Appt nonexistedAppt = GenerateRandomAppt(random);

						// dataHandlerAS.saveAppt(newAppt1);
						// dataHandlerNoAS.saveAppt(newAppt1);
						// dataHandlerDefault.saveAppt(newAppt1);
						defaultdh.saveAppt(newAppt1);

						// dataHandlerAS.saveAppt(newAppt2);
						// dataHandlerNoAS.saveAppt(newAppt2);
						// dataHandlerDefault.saveAppt(newAppt2);
						defaultdh.saveAppt(newAppt2);

						// dataHandlerAS.deleteAppt(newAppt1);
						// dataHandlerNoAS.deleteAppt(newAppt1);
						// dataHandlerDefault.deleteAppt(newAppt1);
						defaultdh.deleteAppt(newAppt1);

						// dataHandlerAS.deleteAppt(newAppt2);
						// dataHandlerNoAS.deleteAppt(newAppt2);
						// dataHandlerDefault.deleteAppt(newAppt2);
						defaultdh.deleteAppt(newAppt2);
						
						// dataHandlerAS.deleteAppt(nonexistedAppt);
						// dataHandlerNoAS.deleteAppt(nonexistedAppt);
						// dataHandlerDefault.deleteAppt(nonexistedAppt);
						defaultdh.deleteAppt(nonexistedAppt);

						// TODO: Assert
                    } else if (methodName.equals("getApptRange")) {
						int nApptsBetween = ValuesGenerator.getRandomIntBetween(random, 0, 3);

						// free style
						GregorianCalendar day1a = GenerateRandomGregorianCal(random);
						GregorianCalendar day1b = GenerateRandomGregorianCal(random);
						for (int j = 0; j < nApptsBetween; ++j)
							defaultdh.saveAppt(GenerateRandomAppt(random, day1a, day1b));				
						List<CalDay> l1 = defaultdh.getApptRange(day1a, day1b);

						// intentional invalid
						GregorianCalendar day2a = GenerateRandomGregorianCal(random, 2018);
						GregorianCalendar day2b = GenerateRandomGregorianCal(random, 2017);
						for (int j = 0; j < nApptsBetween; ++j)
							defaultdh.saveAppt(GenerateRandomAppt(random, day2a, day2b));
						List<CalDay> l2 = defaultdh.getApptRange(day2a, day2b);

						// different year
						GregorianCalendar day3a = GenerateRandomGregorianCal(random, 2017);
						GregorianCalendar day3b = GenerateRandomGregorianCal(random, 2018);
						for (int j = 0; j < nApptsBetween; ++j)
							defaultdh.saveAppt(GenerateRandomAppt(random, day3a, day3b));
						List<CalDay> l3 = defaultdh.getApptRange(day3a, day3b);

						// same year difference month
						GregorianCalendar day4a = GenerateRandomGregorianCal(random, 2018);
						GregorianCalendar day4b = GenerateRandomGregorianCal(random, 2018);
						for (int j = 0; j < nApptsBetween; ++j)
							defaultdh.saveAppt(GenerateRandomAppt(random, day4a, day4b));
						List<CalDay> l4 = defaultdh.getApptRange(day4a, day4b);

						// same year same month different day
						GregorianCalendar day5a = GenerateRandomGregorianCal(random, 2018, 10);
						GregorianCalendar day5b = GenerateRandomGregorianCal(random, 2018, 10);
						for (int j = 0; j < nApptsBetween; ++j)
							defaultdh.saveAppt(GenerateRandomAppt(random, day5a, day5b));
						List<CalDay> l5 = defaultdh.getApptRange(day5a, day5b);

						// same year same month same day
						GregorianCalendar day6a = GenerateRandomGregorianCal(random, 2018, 7, 24);
						GregorianCalendar day6b = GenerateRandomGregorianCal(random, 2018, 7, 24);
						for (int j = 0; j < nApptsBetween; ++j)
							defaultdh.saveAppt(GenerateRandomAppt(random, day6a, day6b));
						List<CalDay> l6 = defaultdh.getApptRange(day6a, day6b);
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
