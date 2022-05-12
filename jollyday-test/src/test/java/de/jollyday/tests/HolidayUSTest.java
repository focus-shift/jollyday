package de.focus_shift.tests;

import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

public class HolidayUSTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "us";
  private static final int YEAR = 2010;

  @Test
  public void testManagerUSStructure() throws Exception {
    validateCalendarData(ISO_CODE, YEAR);
  }

}
