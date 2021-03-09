/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.mytime.zone;

import java.time.ZoneId;
import org.junit.Test;

public class ZoneIdList {

  @Test
  public void testListAvailableZoneIds() {
    ZoneId.getAvailableZoneIds().stream().sorted().forEach(System.out::println);
  }
}