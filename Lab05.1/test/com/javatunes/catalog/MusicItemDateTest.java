/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.catalog;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class MusicItemDateTest {
  
  private Collection<MusicItem> allMusicItems;

  @Before
  public void setUp() {
    Catalog catalog = new InMemoryCatalog();
    allMusicItems = catalog.getAll();
  }
  
  /**
   * OPTIONAL: how many items were released on a Friday?
   * Just use visual inspection (sysout), we don't expect you to look up all those dates on the calendar!
   * 
   * RESULT: 
   */
  @Test
  public void testReleaseDay() {
    long count = allMusicItems.stream()
            .filter(item -> item.getReleaseDate().toLocalDate().getDayOfWeek() == DayOfWeek.FRIDAY)
            .count();
    System.out.println(count);
    // TODO
  }

  /**
   * OPTIONAL: group MusicItems by month released.
   * 
   * RESULT:
   */
  @Test
  public void testGroupByMonth() {
    Map<Month, List<MusicItem>> collect = allMusicItems.stream()
            .collect(groupingBy(item -> item.getReleaseDate().toLocalDate().getMonth()));
    dumpMap(collect);
    // TODO
  }

  private static void dumpMap(Map<?,?> map) {
    map.entrySet().forEach(System.out::println);
  }
}