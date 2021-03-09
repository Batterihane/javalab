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
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;

public class MusicItemPeriodTest {
  
  private Collection<MusicItem> allMusicItems;

  @Before
  public void setUp() {
    Catalog catalog = new InMemoryCatalog();
    allMusicItems = catalog.getAll();
  }
  
  /**
   * OPTIONAL: how many years does the massive JavaTunes catalog span?
   * Tip: consider using a java.util.Deque to collect the LocalDates of each MusicItem.
   *  You can get this via the Collectors.toCollection() method.
   *  This way, you can stream-map-sort-collect in one pass, and then 
   *  get the oldest (first) and newest (last) items from the resulting Deque.
   * 
   * RESULT: 
   */
  @Test
  public void testCatalogSpan() {
    // TODO
  }
}