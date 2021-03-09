/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.mytime;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import org.junit.Test;

public class DateTimeTest {
  
	/**
	 * TASK: create current date, time, and date-time via now() and print them.
	 */
  @Test
  public void testNow() {
	  System.out.println(LocalDate.now());
	  System.out.println(LocalTime.now());
	  System.out.println(LocalDateTime.now());
  }
  
	@Test
	public void testCreateAndGetValues() {
	  // TODO: create your birthday via of() and verify some of the fields are correct
		LocalDate bDay = LocalDate.of(1991, Month.MARCH, 12);
		System.out.println(bDay);
	  
	  // TODO: what day of the week were you born on? (you can use visual inspection)
		System.out.println(bDay.getDayOfWeek());
	  
	  // TODO: what day of the year were you born on? [1-366] (you can use visual inspection)
	  // Note: try this using the generalized get(TemporalField) method
		System.out.println(bDay.get(ChronoField.DAY_OF_YEAR));
	  
	  // OPTIONAL: use of() to represent the 1st lunar landing as a date-time - it happened on 7/20/69 at 3:18pm eastern time
	  // Note: ignore time-zone, just assume eastern time is the local time
	  // You can just print the date-time, and/or verify its fields with assertions
		System.out.println(LocalDateTime.of(1969, 7, 20, 15, 18));
	  
	}
	
	/**
	 * NOTE: verify these via JUnit assertions.
	 * Use visual inspection (sysout) if you are pressed for time.
	 */
	@Test
	public void testParse() {
		// TODO: create your birthday by parsing a text representation in standard format
		System.out.println(LocalDate.parse("1991-03-12"));

		// TODO: now create it by parsing text in the form "2/15/2016"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println(LocalDate.parse("12/03/1991", formatter));

		// TODO: create the training class start time by parsing a text representation in standard format
		System.out.println(LocalTime.parse("13:00"));
	  
	  // TODO: now create it by parsing text in the form "8:30AM"
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
		System.out.println(LocalTime.parse("01:00PM", timeFormatter));

		// OPTIONAL: create the training class start date-time by parsing text in the form "2/15/2016 @ 8:30am"
	  // Note: the 'am' is deliberately lowercase.
	  // solution 1: use a formatter builder to remove case sensitivity from the parse (example in coursebook).
		DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
		builder.parseLenient().parseCaseInsensitive().appendPattern("dd/MM/yyyy @ hh:mma");
		DateTimeFormatter lenient = builder.toFormatter();
		System.out.println(LocalDateTime.parse("08/02/2021 @ 01:00pm", lenient));
	  
	  // solution 2: pre-process the input text for easier parsing.
	  
	}
	
	/**
	 * TASK: create format strings from the date below in these formats:
	 *  10/14/1066
	 *  14-10-1066
	 *  October 14, 1066 (hint: use MMMM for the full month, works for all months)
	 */
	@Test
	public void testFormat() {
	  LocalDate hastings = LocalDate.of(1066, 10, 14);
	  
	  // TODO: 10/14/1066
		System.out.println(DateTimeFormatter.ofPattern("MM/dd/yyyy").format(hastings));
	  	  
	  // TODO: 14-10-1066
		System.out.println(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(hastings));

	  // TODO: October 14, 1066
		System.out.println(DateTimeFormatter.ofPattern("MMMM dd, yyyy").format(hastings));

	}
}