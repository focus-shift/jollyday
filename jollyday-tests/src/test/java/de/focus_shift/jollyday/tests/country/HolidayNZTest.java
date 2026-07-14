package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.NEW_ZEALAND;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Relation.AFTER;
import static de.focus_shift.jollyday.core.spi.Relation.BEFORE;
import static de.focus_shift.jollyday.core.spi.Relation.CLOSEST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayNZTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(NEW_ZEALAND)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("DAY_AFTER_NEW_YEAR", JANUARY, 2)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("WAITANGI_DAY", FEBRUARY, 6)
        .validTo(Year.of(2015))
      .and()
      .hasFixedHoliday("WAITANGI_DAY", FEBRUARY, 6)
        .validFrom(Year.of(2016))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("ANZAC", APRIL, 25)
        .validTo(Year.of(2014))
      .and()
      .hasFixedHoliday("ANZAC", APRIL, 25)
        .validFrom(Year.of(2015))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("QUEEN_ELIZABETH_II_MEMORIAL_DAY", SEPTEMBER, 26)
        .notValidBetween(YEAR_FROM, Year.of(2021))
        .validBetween(Year.of(2022), Year.of(2022))
        .notValidBetween(Year.of(2023), YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      // Matariki: pre-computed lunar-calendar dates, one fixed day per year
      .hasFixedHoliday("MATARIKI", JUNE, 24).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("MATARIKI", JULY, 14).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("MATARIKI", JUNE, 28).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("MATARIKI", JUNE, 20).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("MATARIKI", JULY, 10).validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("MATARIKI", JUNE, 25).validBetween(Year.of(2027), Year.of(2027)).and()
      .hasFixedHoliday("MATARIKI", JULY, 14).validBetween(Year.of(2028), Year.of(2028)).and()
      .hasFixedHoliday("MATARIKI", JULY, 6).validBetween(Year.of(2029), Year.of(2029)).and()
      .hasFixedHoliday("MATARIKI", JUNE, 21).validBetween(Year.of(2030), Year.of(2030)).and()
      .hasFixedHoliday("MATARIKI", JULY, 11).validBetween(Year.of(2031), Year.of(2031)).and()
      .hasFixedHoliday("MATARIKI", JULY, 2).validBetween(Year.of(2032), Year.of(2032)).and()
      .hasFixedHoliday("MATARIKI", JUNE, 24).validBetween(Year.of(2033), Year.of(2033)).and()
      .hasFixedHoliday("MATARIKI", JULY, 7).validBetween(Year.of(2034), Year.of(2034)).and()
      .hasFixedHoliday("MATARIKI", JUNE, 29).validBetween(Year.of(2035), Year.of(2035)).and()
      .hasFixedHoliday("MATARIKI", JULY, 18).validBetween(Year.of(2036), Year.of(2036)).and()
      .hasFixedHoliday("MATARIKI", JULY, 10).validBetween(Year.of(2037), Year.of(2037)).and()
      .hasFixedHoliday("MATARIKI", JUNE, 25).validBetween(Year.of(2038), Year.of(2038)).and()
      .hasFixedHoliday("MATARIKI", JULY, 15).validBetween(Year.of(2039), Year.of(2039)).and()
      .hasFixedHoliday("MATARIKI", JULY, 6).validBetween(Year.of(2040), Year.of(2040)).and()
      .hasFixedHoliday("MATARIKI", JULY, 19).validBetween(Year.of(2041), Year.of(2041)).and()
      .hasFixedHoliday("MATARIKI", JULY, 11).validBetween(Year.of(2042), Year.of(2042)).and()
      .hasFixedHoliday("MATARIKI", JULY, 3).validBetween(Year.of(2043), Year.of(2043)).and()
      .hasFixedHoliday("MATARIKI", JUNE, 24).validBetween(Year.of(2044), Year.of(2044)).and()
      .hasFixedHoliday("MATARIKI", JULY, 7).validBetween(Year.of(2045), Year.of(2045)).and()
      .hasFixedHoliday("MATARIKI", JUNE, 29).validBetween(Year.of(2046), Year.of(2046)).and()
      .hasFixedHoliday("MATARIKI", JULY, 19).validBetween(Year.of(2047), Year.of(2047)).and()
      .hasFixedHoliday("MATARIKI", JULY, 3).validBetween(Year.of(2048), Year.of(2048)).and()
      .hasFixedHoliday("MATARIKI", JUNE, 25).validBetween(Year.of(2049), Year.of(2049)).and()
      .hasFixedHoliday("MATARIKI", JULY, 15).validBetween(Year.of(2050), Year.of(2050)).and()
      .hasFixedHoliday("MATARIKI", JUNE, 30).validBetween(Year.of(2051), Year.of(2051)).and()
      .hasFixedHoliday("MATARIKI", JUNE, 21).validBetween(Year.of(2052), Year.of(2052)).and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", FIRST, MONDAY, JUNE)
        .validTo(Year.of(2022))
      .and()
      .hasFixedWeekdayHoliday("KINGS_DAY", FIRST, MONDAY, JUNE)
        .validFrom(Year.of(2023))
      .and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FOURTH, MONDAY, OCTOBER).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      // Regional anniversary days
      .hasFixedWeekdayRelativeToFixedHoliday("AUCKLAND_ANNIVERSARY", FIRST, MONDAY, CLOSEST, MonthDay.of(JANUARY, 29))
        .inSubdivision("auk")
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("AUCKLAND_ANNIVERSARY", FIRST, MONDAY, CLOSEST, MonthDay.of(JANUARY, 29))
        .inSubdivision("bop")
      .and()
      .hasFixedWeekdayHoliday("CANTERBURY_SOUTH_ANNIVERSARY", FOURTH, MONDAY, SEPTEMBER)
        .inSubdivision("can")
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("CHATHAM_ISLANDS_ANNIVERSARY", FIRST, MONDAY, CLOSEST, MonthDay.of(NOVEMBER, 30))
        .inSubdivision("cit")
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("AUCKLAND_ANNIVERSARY", FIRST, MONDAY, CLOSEST, MonthDay.of(JANUARY, 29))
        .inSubdivision("gis")
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("WELLINGTON_ANNIVERSARY", FIRST, MONDAY, CLOSEST, MonthDay.of(JANUARY, 22))
        .inSubdivision("mwt")
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("NELSON_ANNIVERSARY", FIRST, MONDAY, CLOSEST, MonthDay.of(FEBRUARY, 1))
        .inSubdivision("nsn")
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("AUCKLAND_ANNIVERSARY", FIRST, MONDAY, CLOSEST, MonthDay.of(JANUARY, 29))
        .inSubdivision("ntl")
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("OTAGO_ANNIVERSARY", FIRST, MONDAY, CLOSEST, MonthDay.of(MARCH, 23))
        .inSubdivision("ota")
      .and()
      .hasChristianHoliday("SOUTHLAND_ANNIVERSARY", true)
        .validFrom(Year.of(2012))
        .inSubdivision("stl")
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("SOUTHLAND_ANNIVERSARY", FIRST, MONDAY, CLOSEST, MonthDay.of(JANUARY, 17))
        .validTo(Year.of(2011))
        .inSubdivision("stl")
      .and()
      .hasFixedWeekdayHoliday("TARANAKI_ANNIVERSARY", SECOND, MONDAY, MARCH)
        .inSubdivision("tki")
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("WELLINGTON_ANNIVERSARY", FIRST, MONDAY, CLOSEST, MonthDay.of(JANUARY, 22))
        .inSubdivision("wgn")
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("AUCKLAND_ANNIVERSARY", FIRST, MONDAY, CLOSEST, MonthDay.of(JANUARY, 29))
        .inSubdivision("wko")
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("WESTLAND_ANNIVERSARY", FIRST, MONDAY, CLOSEST, MonthDay.of(DECEMBER, 1))
        .inSubdivision("wtc")
      .and()
      .hasRelativeToWeekdayInMonthHoliday("HAWKES_BAY_ANNIVERSARY", FRIDAY, BEFORE, FOURTH, MONDAY, OCTOBER)
        .inSubdivision("hkb")
      .and()
      .hasRelativeToWeekdayInMonthHoliday("MARLBOROUGH_ANNIVERSARY", MONDAY, AFTER, FOURTH, MONDAY, OCTOBER)
        .inSubdivision("mbh")
      .check();
  }

  @Test
  void testSouthlandAnniversary2012() {

    final HolidayManager holidayManager = HolidayManager.getInstance(create(NEW_ZEALAND));

    // Easter Tuesday
    final LocalDate expected = LocalDate.of(2012, Month.APRIL, 10);
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(2012), "stl");

    boolean found = holidays.stream()
      .anyMatch(holiday -> "SOUTHLAND_ANNIVERSARY".equals(holiday.getPropertiesKey()) && holiday.getDate().equals(expected));
    assertThat(found).isTrue();
  }
}
