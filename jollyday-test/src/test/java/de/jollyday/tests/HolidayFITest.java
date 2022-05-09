package de.jollyday.tests;

import de.jollyday.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

public class HolidayFITest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "fi";
  private static final int YEAR = 2010;

  @Test
  public void testManagerFIStructure() throws Exception {
    validateCalendarData(ISO_CODE, YEAR);
  }

}
