/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.mytime;

import static java.time.temporal.ChronoUnit.WEEKS;
import static org.junit.Assert.*;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;

public class DerivedDateTimeTest {

  /**
   * TASK: new American presidents are often measured by their performance 
   * during the first 100 days in office.
   * Inauguration Day 2017 is Jan 20.
   * When is this president's 100-day deadline?
   * 
   * RESULT: 
   */
  @Test
  public void testPresidentsFirst100Days() {
    System.out.println(LocalDate.of(2017, Month.JANUARY, 20).plusDays(100));
    // TODO
  }

  /**
   * TASK: Certain times of the year seem to have more birthdays than other times.
   * Some believe that this is because more babies are conceived during certain times of year.
   * Below, assume a due date 38 weeks after conception, during a non-leap-year.
   * Note: you can just use visual inspection for these (sysout).
   * 
   * RESULT: 
   */
  @Test
  public void testPopularBirthdays() {
    // TODO: what is the average birthday of someone conceived on Valentine's Day?
    System.out.println(LocalDate.of(2021, 2, 14).plusWeeks(38));
    
    // TODO: what is the average birthday of someone conceived on New Year's Eve (after midnight)?
    // Note: try using the generalized plus() method
    System.out.println(LocalDate.of(2021, 1, 1).plus(38, WEEKS));
  }
  
  /**
   * TASK: you've saved diligently your whole life and plan to retire as soon as
   * you can take distributions from your 401(k) penalty-free, which is when you turn 59 1/2.
   * When will / did you retire?
   * 
   * RESULT: 
   */
  @Test
  public void testEarlyRetirement() {
    System.out.println(LocalDate.of(1991, 3, 12).plusYears(59).plusDays(182));
  }
  
  /**
   * TASK: when was Labor Day during the year you were born?
   * Note: start with a LocalDate equal to your birthday.
   * Note: Labor Day is the first Monday in Sept.
   * 
   * RESULT: 
   */
  @Test
  public void testLaborDay() {
    System.out.println(LocalDate.of(1991, 3, 12).withMonth(Month.SEPTEMBER.getValue()).withDayOfMonth(1).with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
  }
  
  /**
   * OPTIONAL: Election Day in the United States is defined as the Tuesday immediately after the 1st Monday in November.
   * When is Election Day 2024?
   * 
   * RESULT: 
   */
  @Test
  public void testElectionDay() {
    System.out.println(LocalDate.of(2024, 11, 1).with(TemporalAdjusters.next(DayOfWeek.MONDAY)).plusDays(1));
  }
  
  /**
   * OPTIONAL: Akesh and Samanta were married on 6/6/1969.
   * They are planning their 50th wedding anniversary, and would like to throw a big party.
   * If their anniversary does not fall on a Saturday, they'd like to have their party the following Saturday.
   * What is the date of the party?
   * 
   * RESULT: 
   */
  @Test
  public void testAnniversary() {
    System.out.println(LocalDate.of(1969, 6, 6).plusYears(50)
            .with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)));
  }
}