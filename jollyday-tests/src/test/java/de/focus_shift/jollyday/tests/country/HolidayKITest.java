package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.KIRIBATI;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Year.of;

class HolidayKITest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(KIRIBATI)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, of(2021)).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 3).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(of(2023), YEAR_TO).and()

      // International Women's Day - the two confirmed weekend cases (2025, 2026) shift in opposite
      // directions, see Holidays_ki.xml
      .hasFixedHoliday("WOMENS_DAY", MARCH, 8).validBetween(YEAR_FROM, of(2024)).and()
      .hasFixedHoliday("WOMENS_DAY", MARCH, 7).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("WOMENS_DAY", MARCH, 9).validBetween(of(2026), of(2026)).and()
      .hasFixedHoliday("WOMENS_DAY", MARCH, 8).validBetween(of(2027), YEAR_TO).and()

      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()

      // renamed from "World Health Day" (8 April) to "National Health Day" (7 April); exact
      // changeover year not confirmed, see Holidays_ki.xml
      .hasFixedHoliday("WORLD_HEALTH_DAY", APRIL, 8).validBetween(YEAR_FROM, of(2022)).and()
      .hasFixedHoliday("NATIONAL_HEALTH_DAY", APRIL, 7).validBetween(of(2023), YEAR_TO).and()

      // International Labour Day - 2025's Thursday->Friday shift is a confirmed bridge-day shift, not
      // a weekend substitution, see Holidays_ki.xml
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, of(2021)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 2).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(of(2023), of(2024)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 2).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(of(2026), YEAR_TO).and()

      // National Police Day does not appear before the 2025 PSO notice, see Holidays_ki.xml
      .hasFixedHoliday("POLICE_DAY", JUNE, 23).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("POLICE_DAY", JUNE, 22).validBetween(of(2026), of(2026)).and()
      .hasFixedHoliday("POLICE_DAY", JUNE, 20).validBetween(of(2027), YEAR_TO).and()

      // Gospel Day - the four-day National Day cluster (Gospel Day, National Culture and Senior
      // Citizens Day, National Day, Kiribati Special Day) is re-gazetted each year with no fixed
      // cascade formula, see Holidays_ki.xml
      .hasFixedHoliday("GOSPEL_DAY", JULY, 10).validBetween(YEAR_FROM, of(2021)).and()
      .hasFixedHoliday("GOSPEL_DAY", JULY, 8).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("GOSPEL_DAY", JULY, 10).validBetween(of(2023), YEAR_TO).and()

      .hasFixedHoliday("NATIONAL_CULTURE_AND_SENIOR_CITIZENS_DAY", JULY, 11).validBetween(YEAR_FROM, of(2025)).and()
      .hasFixedHoliday("NATIONAL_CULTURE_AND_SENIOR_CITIZENS_DAY", JULY, 14).validBetween(of(2026), of(2026)).and()
      .hasFixedHoliday("NATIONAL_CULTURE_AND_SENIOR_CITIZENS_DAY", JULY, 11).validBetween(of(2027), YEAR_TO).and()

      .hasFixedHoliday("NATIONAL_DAY", JULY, 12).validBetween(YEAR_FROM, of(2024)).and()
      .hasFixedHoliday("NATIONAL_DAY", JULY, 14).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("NATIONAL_DAY", JULY, 13).validBetween(of(2026), of(2026)).and()
      .hasFixedHoliday("NATIONAL_DAY", JULY, 12).validBetween(of(2027), YEAR_TO).and()

      .hasFixedHoliday("SPECIAL_DAY", JULY, 13).validBetween(YEAR_FROM, of(2024)).and()
      .hasFixedHoliday("SPECIAL_DAY", JULY, 15).validBetween(of(2025), of(2026)).and()
      .hasFixedHoliday("SPECIAL_DAY", JULY, 13).validBetween(of(2027), YEAR_TO).and()

      // National Youth and Children's Day - "first Friday of August" only confirmed for 2022, fixed
      // 1 August confirmed from 2025, see Holidays_ki.xml
      .hasFixedWeekdayHoliday("NATIONAL_YOUTH_AND_CHILDRENS_DAY", FIRST, FRIDAY, AUGUST).validBetween(YEAR_FROM, of(2022)).and()
      .hasFixedHoliday("NATIONAL_YOUTH_AND_CHILDRENS_DAY", AUGUST, 1).validBetween(of(2023), of(2025)).and()
      .hasFixedHoliday("NATIONAL_YOUTH_AND_CHILDRENS_DAY", AUGUST, 3).validBetween(of(2026), of(2026)).and()
      .hasFixedHoliday("NATIONAL_YOUTH_AND_CHILDRENS_DAY", AUGUST, 1).validBetween(of(2027), YEAR_TO).and()

      .hasFixedHoliday("WORLD_TEACHERS_DAY", OCTOBER, 5).validBetween(YEAR_FROM, of(2024)).and()
      .hasFixedHoliday("WORLD_TEACHERS_DAY", OCTOBER, 6).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("WORLD_TEACHERS_DAY", OCTOBER, 5).validBetween(of(2026), YEAR_TO).and()

      .hasFixedHoliday("HUMAN_RIGHTS", DECEMBER, 12).validBetween(YEAR_FROM, of(2025)).and()
      .hasFixedHoliday("HUMAN_RIGHTS", DECEMBER, 11).validBetween(of(2026), of(2026)).and()
      .hasFixedHoliday("HUMAN_RIGHTS", DECEMBER, 12).validBetween(of(2027), YEAR_TO).and()

      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, of(2021)).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 26).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(of(2023), YEAR_TO).and()

      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).validBetween(YEAR_FROM, of(2021)).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 27).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).validBetween(of(2023), of(2025)).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 28).validBetween(of(2026), of(2026)).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).validBetween(of(2027), YEAR_TO)
      .check();
  }
}
