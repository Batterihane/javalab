/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.mytime.zone;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;

/**
 * REMEMBER: UTC never changes clock time.
 * That is, UTC doesn't "spring ahead" / "fall back" like clocks do with they enter/exit daylight time.
 * 
 * NOTE: different parts of the world enter/exit daylight time on different dates.
 * 
 * AND: some places don't do it at all, e.g., Hawaii, Arizona.
 * BUT: there are places-within-other-places that DO do it, e.g., native lands in Arizona.
 * 
 * KEY POINT: region-based ZoneId's "know" all the rules, whereas
 * offset-based ZoneId's are fixed amounts of time, all year round.
 * 
 * These test methods will show some of the issues.
 */
public class TimeZoneDemo {
  private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d | Z");
  
  // 2/15/2016 10:00pm [standard time]
  private LocalDateTime feb15 = LocalDateTime.of(2016, 2, 15, 22, 0);
  
  // 3/15/2016 10:00pm [daylight time in USA **but not in Europe**]
  private LocalDateTime mar15 = LocalDateTime.of(2016, 3, 15, 22, 0);
  
  // 4/15/2016 10:00pm [daylight time in USA **and in Europe**]
  private LocalDateTime apr15 = LocalDateTime.of(2016, 4, 15, 22, 0);
  
  
  @Test
  public void testZoneRegionUSA() {
    ZoneId zone = ZoneId.of("America/New_York");
    
    ZonedDateTime feb15zoned = feb15.atZone(zone);
    ZonedDateTime mar15zoned = mar15.atZone(zone);
    ZonedDateTime apr15zoned = apr15.atZone(zone);
    
    System.out.println(feb15zoned.format(formatter));
    System.out.println(mar15zoned.format(formatter));
    System.out.println(apr15zoned.format(formatter));
  }
  
  /**
   * NOTE: if "UTC-05:00" was a synonym for "America/New_York", we'd get the same results as above.
   * BUT WE DON'T!
   */
  @Test
  public void testZoneOffsetUSA() {
    ZoneId zone = ZoneId.of("UTC-05:00");
    
    ZonedDateTime feb15zoned = feb15.atZone(zone);
    ZonedDateTime mar15zoned = mar15.atZone(zone);
    ZonedDateTime apr15zoned = apr15.atZone(zone);
    
    System.out.println(feb15zoned.format(formatter));
    System.out.println(mar15zoned.format(formatter));
    System.out.println(apr15zoned.format(formatter));
  }
  
  
  @Test
  public void testZoneRegionCentralEurope() {
    ZoneId zone = ZoneId.of("Europe/Paris");
    
    ZonedDateTime feb15zoned = feb15.atZone(zone);
    ZonedDateTime mar15zoned = mar15.atZone(zone);
    ZonedDateTime apr15zoned = apr15.atZone(zone);
    
    System.out.println(feb15zoned.format(formatter));
    System.out.println(mar15zoned.format(formatter));
    System.out.println(apr15zoned.format(formatter));
  }

  /**
   * NOTE: if "UTC+01:00" was a synonym for "Europe/Paris", we'd get the same results as above.
   * BUT WE DON'T!
   */
  @Test
  public void testZoneOffsetCentralEurope() {
    ZoneId zone = ZoneId.of("UTC+01:00");
    
    ZonedDateTime feb15zoned = feb15.atZone(zone);
    ZonedDateTime mar15zoned = mar15.atZone(zone);
    ZonedDateTime apr15zoned = apr15.atZone(zone);
    
    System.out.println(feb15zoned.format(formatter));
    System.out.println(mar15zoned.format(formatter));
    System.out.println(apr15zoned.format(formatter));
  }
  
  
  /**
   * Some regions don't do daylight time at all.
   */
  @Test
  public void testZoneRegionArizona() {
    ZoneId zone = ZoneId.of("America/Phoenix");
    
    ZonedDateTime feb15zoned = feb15.atZone(zone);
    ZonedDateTime mar15zoned = mar15.atZone(zone);
    ZonedDateTime apr15zoned = apr15.atZone(zone);
    
    System.out.println(feb15zoned.format(formatter));
    System.out.println(mar15zoned.format(formatter));
    System.out.println(apr15zoned.format(formatter));
  }
  
  /**
   * Some regions do daylight time, even though they're inside a region that does not!
   */
  @Test
  public void testZoneRegionArizonaNativeLand() {
    ZoneId zone = ZoneId.of("Navajo");
    
    ZonedDateTime feb15zoned = feb15.atZone(zone);
    ZonedDateTime mar15zoned = mar15.atZone(zone);
    ZonedDateTime apr15zoned = apr15.atZone(zone);
    
    System.out.println(feb15zoned.format(formatter));
    System.out.println(mar15zoned.format(formatter));
    System.out.println(apr15zoned.format(formatter));
  }
}