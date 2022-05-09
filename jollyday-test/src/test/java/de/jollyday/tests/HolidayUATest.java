package de.jollyday.tests;

import de.jollyday.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

public class HolidayUATest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "ua";

  @Test
  public void testManagerUAStructure2013() throws Exception {
    validateCalendarData(ISO_CODE, 2013);
  }

  @Test
  public void testManagerUAStructure2014() throws Exception {
    validateCalendarData(ISO_CODE, 2018);
  }

  @Test
  public void testManagerUAStructure2015() throws Exception {
    validateCalendarData(ISO_CODE, 2015);
  }

  @Test
  public void testManagerUAStructure2016() throws Exception {
    validateCalendarData(ISO_CODE, 2016);
  }

  @Test
  public void testManagerUAStructure2017() throws Exception {
    validateCalendarData(ISO_CODE, 2017);
  }

  @Test
  public void testManagerUAStructure2018() throws Exception {
    validateCalendarData(ISO_CODE, 2018);
  }

  @Test
  public void testManagerUAStructure2019() throws Exception {
    validateCalendarData(ISO_CODE, 2019);
  }

  @Test
  public void testManagerUAStructure2020() throws Exception {
    validateCalendarData(ISO_CODE, 2020);
  }

}
