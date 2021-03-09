/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.catalog;

// TODO: Uncomment the static imports that we supply as you need them
 import static java.util.Comparator.*;
 import static java.util.stream.Collectors.*;

import static org.junit.Assert.*;
import java.util.Collection;
 import java.util.Comparator;
 import java.util.OptionalDouble;
import org.junit.Before;
import org.junit.Test;

public class AdvancedCatalogStreamTest {
  
  private Collection<MusicItem> allMusicItems;

  @Before
  public void setUp() {
    Catalog catalog = new InMemoryCatalog();
    allMusicItems = catalog.getAll();
  }
  
  /**
   * TASK: determine average price of our low-cost, extensive catalog of music.
   * 
   * RESULT: 15.309 (via calculator)
   */
  @Test
  public void testAveragePrice() {
    OptionalDouble avgPriceOptional = allMusicItems.stream()
      .mapToDouble(MusicItem::getPrice)  // item -> item.getPrice()
      .average();
    double avgPrice = avgPriceOptional.getAsDouble();
    
    assertEquals(15.309, avgPrice, .001);
  }
  
  /**
   * TASK: determine the titles of all "pop" items, sorted by natural order.
   * 
   * RESULT: 
   */
  @Test
  public void testTitlesPopSortNaturalOrder() {
    allMusicItems.stream()
            .filter(musicItem -> musicItem.getMusicCategory() == MusicCategory.POP)
            .sorted()
            .map(MusicItem::getTitle)
            .forEach(item -> System.out.println(item));
  }
  
  /**
   * TASK: are all items priced at least $10?
   * 
   * RESULT: 
   */
  @Test
  public void testAllPriceGreaterThanEqual() {
    boolean result = allMusicItems.stream()
            .anyMatch(item -> item.getPrice() >= 10);
    assertTrue(result);
  }
  
  /**
   * TASK: do we sell any "jazz" items?
   * 
   * RESULT: 
   */
  @Test
  public void testAnyMusicCategory() {
    boolean result = allMusicItems.stream()
            .anyMatch(item -> item.getMusicCategory() == MusicCategory.JAZZ);
    assertFalse(result);
  }
  
  /**
   * TASK: how many "blues" items do we sell?
   * 
   * RESULT: 
   */
  @Test
  public void testCountMusicCategory() {
    long count = allMusicItems.stream()
            .filter(item -> item.getMusicCategory() == MusicCategory.BLUES)
            .count();
    System.out.println(count);
  }
  
  /**
   * TASK: print to stdout any 3 "rock" items.
   * 
   * RESULT: 
   */
  @Test
  public void testPrintAny3MusicCategory() {
    allMusicItems.stream()
            .filter(item -> item.getMusicCategory() == MusicCategory.ROCK)
            .limit(3)
            .forEach(System.out::println);
  }
  
  /**
   * TASK: determine the average price of the 3 most recent items we sell.
   * 
   * RESULT: 
   */
  @Test
  public void testAvgPrice3Newest() {
    double avg = allMusicItems.stream()
            .sorted(Comparator.comparing(MusicItem::getReleaseDate).reversed())
            .limit(3)
            .mapToDouble(MusicItem::getPrice)
            .average().getAsDouble();
    System.out.println(avg);
  }
  
  /**
   * TASK: determine the 2 highest-priced MusicItems, sorted by title.
   * HINT: you will need to sort twice in the pipeline.
   * 
   * RESULT: 
   */
  @Test
  public void testPrice2HighestSortTitle() {
    allMusicItems.stream()
            .sorted(Comparator.comparing(MusicItem::getPrice).reversed())
            .limit(2)
            .sorted(Comparator.comparing(MusicItem::getTitle))
            .forEach(System.out::println);
  }
  
  /**
   * TASK: what is the price of our cheapest "pop" item?
   * 
   * RESULT: 
   */
  @Test
  public void testMinPriceMusicCategory() {
    Double price = allMusicItems.stream()
            .filter(item -> item.getMusicCategory() == MusicCategory.POP)
            .min(comparing(MusicItem::getPrice))
            .get().getPrice();
    System.out.println(price);
  }
}