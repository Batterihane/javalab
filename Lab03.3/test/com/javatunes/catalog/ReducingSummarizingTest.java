/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.catalog;

//TODO: Uncomment the static imports that we supply as you need them
// import static java.util.Comparator.*;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class ReducingSummarizingTest {
  
  private Collection<MusicItem> allMusicItems;
  
  @Before
  public void setUp() {
    Catalog catalog = new InMemoryCatalog();
    allMusicItems = catalog.getAll();
  }
  
  /**
   * TASK: determine the number of items in each music category.
   * 
   * RESULT:
   *  POP           4
   *  BLUES         2
   *  ALTERNATIVE   2
   *  CLASSICAL     1
   *  ROCK          6
   *  RAP           1
   *  COUNTRY       1
   *  CLASSIC_ROCK  1
   *  JAZZ          null   note: map has no entry for JAZZ at all
   */
  @Test
  public void testCountMusicCategory() {
    Map<MusicCategory,Long> countMap = allMusicItems.stream()
      .collect(groupingBy(MusicItem::getMusicCategory, counting()));
    
    assertEquals(Long.valueOf(4), countMap.get(MusicCategory.POP));
    // alternative technique
    assertTrue(4 == countMap.get(MusicCategory.POP));
    assertTrue(2 == countMap.get(MusicCategory.BLUES));
    assertTrue(2 == countMap.get(MusicCategory.ALTERNATIVE));
    assertTrue(1 == countMap.get(MusicCategory.CLASSICAL));
    assertTrue(6 == countMap.get(MusicCategory.ROCK));
    assertTrue(1 == countMap.get(MusicCategory.RAP));
    assertTrue(1 == countMap.get(MusicCategory.COUNTRY));
    assertTrue(1 == countMap.get(MusicCategory.CLASSIC_ROCK));
    assertNull(countMap.get(MusicCategory.JAZZ));
  }
  
  /**
   * TASK: determine the average price of the items in each music category.
   * 
   * EXPECTED RESULT: via calculator
   *  POP           16.23
   *  BLUES         16.48
   *  ALTERNATIVE   16.48
   *  CLASSICAL      9.97
   *  ROCK          15.64
   *  RAP           16.97
   *  COUNTRY       11.97
   *  CLASSIC_ROCK  11.97
   */
  @Test
  public void testAveragePriceMusicCategory() {
    Map<MusicCategory, Double> collect = allMusicItems.stream()
            .collect(groupingBy(MusicItem::getMusicCategory, averagingDouble(MusicItem::getPrice)));
    dumpMap(collect);
  }

  /**
   * TASK: divide all items into the following two groups:
   *  Rock   items in the ROCK and CLASSIC_ROCK music categories.
   *  Other  all others.
   *  Hint: Decide should your test be to see if the item is in one of the two categories above, then use it to partition
   * 
   * For each group, determine the number of items, the highest price, the lowest price, and the average price.
   * You can use one of the summarizing collectors to get this info
   * 
   * EXPECTED RESULT:  count  highest  lowest  average
   *  Rock      7     18.97    11.97   15.116
   *  Other    11     17.97     9.97   15.432
   */
  @Test
  public void testPricingDataRockOther() {
    List<MusicCategory> rockCategories = Arrays.asList(MusicCategory.ROCK, MusicCategory.CLASSIC_ROCK);
    Map<Boolean, DoubleSummaryStatistics> collect = allMusicItems.stream()
            .collect(partitioningBy(item -> rockCategories.contains(item.getMusicCategory()), summarizingDouble(MusicItem::getPrice)));
    dumpMap(collect);
  }

  /**
   * TASK: determine the most recently released item in each music category.
   * Hint: You can get the item with the "maximum" release date to get the most recently released
   * 
   * EXPECTED RESULT:
   *  POP          item  1 (1992-01-04)
   *  BLUES        item  4 (1997-08-26)
   *  ALTERNATIVE  item  9 (1997-12-04)
   *  CLASSICAL    item  7 (1993-12-05)
   *  ROCK         item 12 (1999-10-21)
   *  RAP          item 11 (2000-12-06)
   *  COUNTRY      item 13 (1987-02-20)
   *  CLASSIC_ROCK item 18 (1981-02-25)
   */
  @Test
  public void testMostRecentItemMusicCategory() {
    Map<MusicCategory, Optional<MusicItem>> collect = allMusicItems.stream()
            .collect(groupingBy(MusicItem::getMusicCategory, maxBy(Comparator.comparing(MusicItem::getReleaseDate))));
    dumpMap(collect);
  }
  
  /**
   * TASK: build an "id-entry" map, i.e., the item's ID is the key, and the item itself is the value.
   * 
   * EXPECTED RESULT:
   *  1L   MusicItem 1
   *  2L   MusicItem 2
   *  3L   MusicItem 3
   *  ...
   *  18L  MusicItem 18 
   */
  @Test
  public void testMusicItemIdEntryMap() {
//    Map<Long, List<MusicItem>> collect = allMusicItems.stream()
//            .collect(groupingBy(MusicItem::getId));

    Map<Long, MusicItem> collect = allMusicItems.stream()
            .collect(toMap(MusicItem::getId, Function.identity()));
    dumpMap(collect);
  }
  
  /**
   * OPTIONAL: partition artists into two groups: those with a self-titled album, and all others.
   * Careful: we want artist lists here.
   * 
   * EXPECTED RESULT:
   *  true    List<String>["Seal", "Ian Moore"]
   *  false   List<String>["Annie Lennox", "Sting", ... "Journey"]
   */
  @Test
  public void testPartitionArtistsBySelfTitledAlbum() {
    Map<Boolean, String> collect = allMusicItems.stream()
            .collect(toMap(item -> item.getTitle().equals(item.getArtist()), MusicItem::getArtist));
    dumpMap(collect);
  }
  /**

   * CHALLENGE: what is our most popular "release year," i.e., we have more items released in that year than any other.
   * Hint: you'll need to compare the map entries by value - see Javadoc for Map.Entry
   * 
   * EXPECTED RESULT: 1997 (4 items)
   */
  @Test
  public void testMostPopularReleaseYear() {
    // TODO
  }
  
  /**
   * EXTRA CREDIT: divide all items into the following two groups:
   *  Rock   items in the ROCK and CLASSIC_ROCK music categories.
   *  Other  all others.
   *  
   *  Partition each group into "cheap" and "expensive" items, where "cheap" is <= 14.00.
   *  Yes! this is a partition of partitions.
   * 
   * EXPECTED RESULT: 
   *  Rock    Map<Boolean,List<MusicItem>   8,10,12,15,16,17,18
   *          true  List<MusicItem>[8,16,17,18]
   *          false List<MusicItem>[10,12,15]
   *  
   *  Other   Map<Boolean,List<MusicItem>>  1,2,3,4,5,6,7,9,11,13,14
   *          true  List<MusicItem>[1,7,13]
   *          false List<MusicItem>[2,3,4,5,6,9,11,14]

   */
  @Test
  public void testPriceBreakdownRockOther() {
    // TODO
  }


  /*
   * Helper method to dump map to standard out. Map is assumed to have entries with Collections.
   */
  private static void dumpMap(Map<?,?> map) {
    map.entrySet().forEach(System.out::println);
  }
}