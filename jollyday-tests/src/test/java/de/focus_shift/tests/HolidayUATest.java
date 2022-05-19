package de.focus_shift.tests;

import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidayUATest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "ua";

  @Test
  void testManagerUAStructure2013() {
    validateCalendarData(ISO_CODE, 2013);
  }

  @Test
  void testManagerUAStructure2014() {
    validateCalendarData(ISO_CODE, 2018);
  }

  @Test
  void testManagerUAStructure2015() {
    validateCalendarData(ISO_CODE, 2015);
  }

  @Test
  void testManagerUAStructure2016() {
    validateCalendarData(ISO_CODE, 2016);
  }

  @Test
  void testManagerUAStructure2017() {
    validateCalendarData(ISO_CODE, 2017);
  }

  @Test
  void testManagerUAStructure2018() {
    validateCalendarData(ISO_CODE, 2018);
  }

  @Test
  void testManagerUAStructure2019() {
    validateCalendarData(ISO_CODE, 2019);
  }

  @Test
  void testManagerUAStructure2020() {
    validateCalendarData(ISO_CODE, 2020);
  }

}
