package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.SURINAME;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

class HolidaySRTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(SURINAME)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("REVOLUTION", FEBRUARY, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDIAN_ARRIVAL_DAY", JUNE, 5).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("KETI_KOTI", JULY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("JAVANESE_ARRIVAL_DAY", AUGUST, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDIGENOUS_PEOPLES_DAY", AUGUST, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("DAY_OF_THE_MAROONS", OCTOBER, 10).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHINESE_ARRIVAL_DAY", OCTOBER, 20).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", JANUARY, 25).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 12).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 1).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", JANUARY, 22).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 10).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", JANUARY, 29).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 17).validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 6).validBetween(Year.of(2027), Year.of(2027)).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", JANUARY, 26).validBetween(Year.of(2028), Year.of(2028)).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 13).validBetween(Year.of(2029), Year.of(2029)).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 3).validBetween(Year.of(2030), Year.of(2030)).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", JANUARY, 23).validBetween(Year.of(2031), Year.of(2031)).and()
      .hasFixedHoliday("DEEPAVALI", NOVEMBER, 14).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("DEEPAVALI", NOVEMBER, 4).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("DEEPAVALI", OCTOBER, 24).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("DEEPAVALI", NOVEMBER, 12).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("DEEPAVALI", OCTOBER, 31).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("DEEPAVALI", OCTOBER, 20).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("DEEPAVALI", OCTOBER, 8).validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("DEEPAVALI", OCTOBER, 27).validBetween(Year.of(2027), Year.of(2027)).and()
      .hasFixedHoliday("DEEPAVALI", NOVEMBER, 14).validBetween(Year.of(2028), Year.of(2028)).and()
      .hasFixedHoliday("DEEPAVALI", NOVEMBER, 3).validBetween(Year.of(2029), Year.of(2029)).and()
      .hasFixedHoliday("DEEPAVALI", OCTOBER, 23).validBetween(Year.of(2030), Year.of(2030)).and()
      .hasFixedHoliday("DEEPAVALI", NOVEMBER, 11).validBetween(Year.of(2031), Year.of(2031)).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("ASCENSION_DAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("NEWYEAR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
