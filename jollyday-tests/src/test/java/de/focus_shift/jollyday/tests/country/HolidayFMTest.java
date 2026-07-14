package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.MICRONESIA;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayFMTest {

  @Test
  void ensuresHolidays() {
    assertFor(MICRONESIA)
      // National holidays
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("CULTURE_DAY", MARCH, 31).and()
      .hasFixedHoliday("CONSTITUTION_DAY", MAY, 10).and()
      .hasFixedHoliday("UNITED_NATIONS_DAY", OCTOBER, 24).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 3).and()
      .hasFixedHoliday("VETERANS_DAY", NOVEMBER, 11).and()
      .hasFixedHoliday("PRESIDENTS_DAY", NOVEMBER, 23).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      // Chuuk (trk)
      .hasFixedHoliday("CONSTITUTION_DAY", OCTOBER, 1).inSubdivision("trk").and()
      .hasFixedHoliday("SATOWAN_DAY", NOVEMBER, 1).inSubdivision("trk").and()
      .hasFixedWeekdayHoliday("TEACHERS_APPRECIATION_DAY", SECOND, FRIDAY, OCTOBER).inSubdivision("trk").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("trk").and()
      // Kosrae (ksa)
      .hasFixedHoliday("CONSTITUTION_DAY", JANUARY, 11).inSubdivision("ksa").and()
      .hasFixedHoliday("GOSPEL_DAY", AUGUST, 21).inSubdivision("ksa").and()
      .hasFixedHoliday("LIBERATION_DAY", SEPTEMBER, 8).inSubdivision("ksa").and()
      .hasFixedWeekdayHoliday("THANKSGIVING", FOURTH, THURSDAY, NOVEMBER).inSubdivision("ksa").and()
      // Pohnpei (pni)
      .hasFixedHoliday("LIBERATION_DAY", SEPTEMBER, 11).inSubdivision("pni").and()
      .hasFixedHoliday("CONSTITUTION_DAY", NOVEMBER, 8).inSubdivision("pni").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("pni").and()
      // Yap (yap)
      .hasFixedHoliday("YAP_DAY", MARCH, 1).inSubdivision("yap").and()
      .hasFixedHoliday("YAP_DAY", MARCH, 2).inSubdivision("yap").and()
      .hasFixedHoliday("CONSTITUTION_DAY", DECEMBER, 24).inSubdivision("yap")
      .check();
  }
}
