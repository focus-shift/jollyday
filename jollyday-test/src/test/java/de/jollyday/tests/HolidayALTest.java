package de.jollyday.tests;

import de.jollyday.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

public class HolidayALTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "al";
  private static final int YEAR = 2010;

  @Test
  public void testManagerALStructure() throws Exception {
    validateCalendarData(ISO_CODE, YEAR);
  }

}
