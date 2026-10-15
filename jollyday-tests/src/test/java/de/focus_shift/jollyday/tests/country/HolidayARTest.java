package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.ARGENTINA;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.NEXT;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayARTest {

  @Test
  void ensuresHolidays() {
    assertFor(ARGENTINA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("REMEMBRANCE_TRUTH_JUSTICE", MARCH, 24)
        .notValidBetween(Year.of(1900), Year.of(2005))
        .validFrom(Year.of(2006)).and()
      .hasFixedHoliday("MALVINAS", APRIL, 2)
        .notValidBetween(Year.of(1900), Year.of(2000))
        .validFrom(Year.of(2001)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("MAY_REVOLUTION", MAY, 25).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 9).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("CLEAN_MONDAY").and()
      .hasChristianHoliday("CARNIVAL").and()
      .hasChristianHoliday("MAUNDY_THURSDAY", OBSERVANCE).and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }

  @Test
  void ensuresThatEasterSundayChristmasEveAndNewYearsEveAreNoHolidaysAndMaundyThursdayIsNoPublicHoliday() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ARGENTINA));
    for (int year = 1900; year <= 2100; year++) {
      assertThat(holidayManager.getHolidays(Year.of(year)))
        .extracting(Holiday::getPropertiesKey)
        .doesNotContain("christian.EASTER", "CHRISTMAS_EVE", "NEW_YEARS_EVE");
      assertThat(holidayManager.getHolidays(Year.of(year)))
        .filteredOn(holiday -> holiday.getPropertiesKey().equals("christian.MAUNDY_THURSDAY"))
        .extracting(Holiday::getType)
        .containsExactly(OBSERVANCE);
    }
  }

  @Test
  void ensuresGuemesDay() {
    assertFor(ARGENTINA)
      .hasFixedHoliday("GUEMES_DAY", JUNE, 17)
        .notValidBetween(Year.of(1900), Year.of(2015))
        .validBetween(Year.of(2016), Year.of(2016)).and()
      .hasFixedHoliday("GUEMES_DAY", JUNE, 17)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, NEXT, MONDAY)
        .validFrom(Year.of(2017))
      .check();
  }

  @Test
  void ensuresBelgranoFlagDay() {
    assertFor(ARGENTINA)
      .hasFixedHoliday("FLAG_DAY", JUNE, 20)
        .validTo(Year.of(1994))
        .validFrom(Year.of(2011)).and()
      .hasFixedWeekdayHoliday("FLAG_DAY", THIRD, MONDAY, JUNE)
        .validBetween(Year.of(1995), Year.of(2010))
      .check();
  }

  @Test
  void ensuresSanMartinDay() {
    assertFor(ARGENTINA)
      .hasFixedHoliday("MARTIN_DAY", AUGUST, 17)
        .validTo(Year.of(1991)).and()
      .hasFixedHoliday("MARTIN_DAY", AUGUST, 17)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, NEXT, MONDAY)
        .canBeMovedFrom(FRIDAY, NEXT, MONDAY)
        .validBetween(Year.of(1992), Year.of(1994))
        .validFrom(Year.of(2017)).and()
      .hasFixedWeekdayHoliday("MARTIN_DAY", THIRD, MONDAY, AUGUST)
        .validBetween(Year.of(1995), Year.of(2010))
        .validBetween(Year.of(2012), Year.of(2016)).and()
      .hasFixedHoliday("MARTIN_DAY", AUGUST, 22)
        .validBetween(Year.of(2011), Year.of(2011))
      .check();
  }

  @Test
  void ensuresColumbusDay() {
    assertFor(ARGENTINA)
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 12)
        .validTo(Year.of(1987)).and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 12)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, NEXT, MONDAY)
        .canBeMovedFrom(FRIDAY, NEXT, MONDAY)
        .validBetween(Year.of(1988), Year.of(2000))
        .validBetween(Year.of(2004), Year.of(2007))
        .validBetween(Year.of(2017), Year.of(2024))
        .validFrom(Year.of(2026)).and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 12)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, NEXT, MONDAY)
        .canBeMovedFrom(FRIDAY, NEXT, MONDAY)
        .canBeMovedFrom(SATURDAY, NEXT, MONDAY)
        .canBeMovedFrom(SUNDAY, NEXT, MONDAY)
        .validBetween(Year.of(2008), Year.of(2010)).and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validBetween(Year.of(2011), Year.of(2016)).and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 8)
        .validBetween(Year.of(2001), Year.of(2001)).and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 14)
        .validBetween(Year.of(2002), Year.of(2002)).and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 13)
        .validBetween(Year.of(2003), Year.of(2003)).and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 10)
        .validBetween(Year.of(2025), Year.of(2025))
      .check();
  }

  @Test
  void ensuresNationalSovereigntyDay() {
    assertFor(ARGENTINA)
      .hasFixedWeekdayHoliday("NATIONAL_SOVEREIGNTY_DAY", FOURTH, MONDAY, NOVEMBER)
        .validBetween(Year.of(2010), Year.of(2014))
        .validBetween(Year.of(2016), Year.of(2016)).and()
      .hasFixedHoliday("NATIONAL_SOVEREIGNTY_DAY", NOVEMBER, 27)
        .validBetween(Year.of(2015), Year.of(2015)).and()
      .hasFixedHoliday("NATIONAL_SOVEREIGNTY_DAY", NOVEMBER, 20)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, NEXT, MONDAY)
        .canBeMovedFrom(FRIDAY, NEXT, MONDAY)
        .notValidBetween(Year.of(1900), Year.of(2009))
        .validFrom(Year.of(2017))
      .check();
  }

  @Test
  void ensuresTouristHolidays() {
    assertFor(ARGENTINA)
      .hasFixedHoliday("TOURIST_HOLIDAY", APRIL, 30, OBSERVANCE).validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", DECEMBER, 24, OBSERVANCE).validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", DECEMBER, 31, OBSERVANCE).validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", JULY, 8, OBSERVANCE).validBetween(Year.of(2019), Year.of(2019)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", AUGUST, 19, OBSERVANCE).validBetween(Year.of(2019), Year.of(2019)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", OCTOBER, 14, OBSERVANCE).validBetween(Year.of(2019), Year.of(2019)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", MARCH, 23).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", JULY, 10).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", DECEMBER, 7).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", MAY, 24).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", OCTOBER, 8).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", NOVEMBER, 22).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", OCTOBER, 7).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", NOVEMBER, 21).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", DECEMBER, 9).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", MAY, 26).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", JUNE, 19).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", OCTOBER, 13).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", APRIL, 1).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", JUNE, 21).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", OCTOBER, 11).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", MAY, 2, OBSERVANCE).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", AUGUST, 15, OBSERVANCE).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", NOVEMBER, 21, OBSERVANCE).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", MARCH, 23, OBSERVANCE).validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", JULY, 10, OBSERVANCE).validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("TOURIST_HOLIDAY", DECEMBER, 7, OBSERVANCE).validBetween(Year.of(2026), Year.of(2026))
      .check();
  }

  @Test
  void ensuresOneOffHolidays() {
    assertFor(ARGENTINA)
      .hasFixedHoliday("NATIONAL_CENSUS_DAY", MAY, 18).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("WORLD_CUP_VICTORY_CELEBRATION", DECEMBER, 20).validBetween(Year.of(2022), Year.of(2022))
      .check();
  }

  @Test
  void ensuresOfficialHolidays2022() {
    assertThat(holidaysOf(2022)).containsExactlyInAnyOrder(
      publicHoliday(2022, JANUARY, 1, "NEW_YEAR"),
      publicHoliday(2022, FEBRUARY, 28, "christian.CLEAN_MONDAY"),
      publicHoliday(2022, MARCH, 1, "christian.CARNIVAL"),
      publicHoliday(2022, MARCH, 24, "REMEMBRANCE_TRUTH_JUSTICE"),
      publicHoliday(2022, APRIL, 2, "MALVINAS"),
      observance(2022, APRIL, 14, "christian.MAUNDY_THURSDAY"),
      publicHoliday(2022, APRIL, 15, "christian.GOOD_FRIDAY"),
      publicHoliday(2022, MAY, 1, "LABOUR_DAY"),
      publicHoliday(2022, MAY, 18, "NATIONAL_CENSUS_DAY"),
      publicHoliday(2022, MAY, 25, "MAY_REVOLUTION"),
      publicHoliday(2022, JUNE, 17, "GUEMES_DAY"),
      publicHoliday(2022, JUNE, 20, "FLAG_DAY"),
      publicHoliday(2022, JULY, 9, "INDEPENDENCE_DAY"),
      publicHoliday(2022, AUGUST, 15, "MARTIN_DAY"),
      publicHoliday(2022, OCTOBER, 7, "TOURIST_HOLIDAY"),
      publicHoliday(2022, OCTOBER, 10, "COLUMBUS_DAY"),
      publicHoliday(2022, NOVEMBER, 20, "NATIONAL_SOVEREIGNTY_DAY"),
      publicHoliday(2022, NOVEMBER, 21, "TOURIST_HOLIDAY"),
      publicHoliday(2022, DECEMBER, 8, "IMMACULATE_CONCEPTION"),
      publicHoliday(2022, DECEMBER, 9, "TOURIST_HOLIDAY"),
      publicHoliday(2022, DECEMBER, 20, "WORLD_CUP_VICTORY_CELEBRATION"),
      publicHoliday(2022, DECEMBER, 25, "CHRISTMAS")
    );
  }

  @Test
  void ensuresOfficialHolidays2023() {
    assertThat(holidaysOf(2023)).containsExactlyInAnyOrder(
      publicHoliday(2023, JANUARY, 1, "NEW_YEAR"),
      publicHoliday(2023, FEBRUARY, 20, "christian.CLEAN_MONDAY"),
      publicHoliday(2023, FEBRUARY, 21, "christian.CARNIVAL"),
      publicHoliday(2023, MARCH, 24, "REMEMBRANCE_TRUTH_JUSTICE"),
      publicHoliday(2023, APRIL, 2, "MALVINAS"),
      observance(2023, APRIL, 6, "christian.MAUNDY_THURSDAY"),
      publicHoliday(2023, APRIL, 7, "christian.GOOD_FRIDAY"),
      publicHoliday(2023, MAY, 1, "LABOUR_DAY"),
      publicHoliday(2023, MAY, 25, "MAY_REVOLUTION"),
      publicHoliday(2023, MAY, 26, "TOURIST_HOLIDAY"),
      publicHoliday(2023, JUNE, 17, "GUEMES_DAY"),
      publicHoliday(2023, JUNE, 19, "TOURIST_HOLIDAY"),
      publicHoliday(2023, JUNE, 20, "FLAG_DAY"),
      publicHoliday(2023, JULY, 9, "INDEPENDENCE_DAY"),
      publicHoliday(2023, AUGUST, 21, "MARTIN_DAY"),
      publicHoliday(2023, OCTOBER, 13, "TOURIST_HOLIDAY"),
      publicHoliday(2023, OCTOBER, 16, "COLUMBUS_DAY"),
      publicHoliday(2023, NOVEMBER, 20, "NATIONAL_SOVEREIGNTY_DAY"),
      publicHoliday(2023, DECEMBER, 8, "IMMACULATE_CONCEPTION"),
      publicHoliday(2023, DECEMBER, 25, "CHRISTMAS")
    );
  }

  @Test
  void ensuresOfficialHolidays2024() {
    assertThat(holidaysOf(2024)).containsExactlyInAnyOrder(
      publicHoliday(2024, JANUARY, 1, "NEW_YEAR"),
      publicHoliday(2024, FEBRUARY, 12, "christian.CLEAN_MONDAY"),
      publicHoliday(2024, FEBRUARY, 13, "christian.CARNIVAL"),
      publicHoliday(2024, MARCH, 24, "REMEMBRANCE_TRUTH_JUSTICE"),
      observance(2024, MARCH, 28, "christian.MAUNDY_THURSDAY"),
      publicHoliday(2024, MARCH, 29, "christian.GOOD_FRIDAY"),
      publicHoliday(2024, APRIL, 1, "TOURIST_HOLIDAY"),
      publicHoliday(2024, APRIL, 2, "MALVINAS"),
      publicHoliday(2024, MAY, 1, "LABOUR_DAY"),
      publicHoliday(2024, MAY, 25, "MAY_REVOLUTION"),
      publicHoliday(2024, JUNE, 17, "GUEMES_DAY"),
      publicHoliday(2024, JUNE, 20, "FLAG_DAY"),
      publicHoliday(2024, JUNE, 21, "TOURIST_HOLIDAY"),
      publicHoliday(2024, JULY, 9, "INDEPENDENCE_DAY"),
      publicHoliday(2024, AUGUST, 17, "MARTIN_DAY"),
      publicHoliday(2024, OCTOBER, 11, "TOURIST_HOLIDAY"),
      publicHoliday(2024, OCTOBER, 12, "COLUMBUS_DAY"),
      publicHoliday(2024, NOVEMBER, 18, "NATIONAL_SOVEREIGNTY_DAY"),
      publicHoliday(2024, DECEMBER, 8, "IMMACULATE_CONCEPTION"),
      publicHoliday(2024, DECEMBER, 25, "CHRISTMAS")
    );
  }

  @Test
  void ensuresOfficialHolidays2025() {
    assertThat(holidaysOf(2025)).containsExactlyInAnyOrder(
      publicHoliday(2025, JANUARY, 1, "NEW_YEAR"),
      publicHoliday(2025, MARCH, 3, "christian.CLEAN_MONDAY"),
      publicHoliday(2025, MARCH, 4, "christian.CARNIVAL"),
      publicHoliday(2025, MARCH, 24, "REMEMBRANCE_TRUTH_JUSTICE"),
      publicHoliday(2025, APRIL, 2, "MALVINAS"),
      observance(2025, APRIL, 17, "christian.MAUNDY_THURSDAY"),
      publicHoliday(2025, APRIL, 18, "christian.GOOD_FRIDAY"),
      publicHoliday(2025, MAY, 1, "LABOUR_DAY"),
      observance(2025, MAY, 2, "TOURIST_HOLIDAY"),
      publicHoliday(2025, MAY, 25, "MAY_REVOLUTION"),
      publicHoliday(2025, JUNE, 16, "GUEMES_DAY"),
      publicHoliday(2025, JUNE, 20, "FLAG_DAY"),
      publicHoliday(2025, JULY, 9, "INDEPENDENCE_DAY"),
      observance(2025, AUGUST, 15, "TOURIST_HOLIDAY"),
      publicHoliday(2025, AUGUST, 17, "MARTIN_DAY"),
      publicHoliday(2025, OCTOBER, 10, "COLUMBUS_DAY"),
      observance(2025, NOVEMBER, 21, "TOURIST_HOLIDAY"),
      publicHoliday(2025, NOVEMBER, 24, "NATIONAL_SOVEREIGNTY_DAY"),
      publicHoliday(2025, DECEMBER, 8, "IMMACULATE_CONCEPTION"),
      publicHoliday(2025, DECEMBER, 25, "CHRISTMAS")
    );
  }

  @Test
  void ensuresOfficialHolidays2026() {
    assertThat(holidaysOf(2026)).containsExactlyInAnyOrder(
      publicHoliday(2026, JANUARY, 1, "NEW_YEAR"),
      publicHoliday(2026, FEBRUARY, 16, "christian.CLEAN_MONDAY"),
      publicHoliday(2026, FEBRUARY, 17, "christian.CARNIVAL"),
      observance(2026, MARCH, 23, "TOURIST_HOLIDAY"),
      publicHoliday(2026, MARCH, 24, "REMEMBRANCE_TRUTH_JUSTICE"),
      publicHoliday(2026, APRIL, 2, "MALVINAS"),
      observance(2026, APRIL, 2, "christian.MAUNDY_THURSDAY"),
      publicHoliday(2026, APRIL, 3, "christian.GOOD_FRIDAY"),
      publicHoliday(2026, MAY, 1, "LABOUR_DAY"),
      publicHoliday(2026, MAY, 25, "MAY_REVOLUTION"),
      publicHoliday(2026, JUNE, 15, "GUEMES_DAY"),
      publicHoliday(2026, JUNE, 20, "FLAG_DAY"),
      publicHoliday(2026, JULY, 9, "INDEPENDENCE_DAY"),
      observance(2026, JULY, 10, "TOURIST_HOLIDAY"),
      publicHoliday(2026, AUGUST, 17, "MARTIN_DAY"),
      publicHoliday(2026, OCTOBER, 12, "COLUMBUS_DAY"),
      publicHoliday(2026, NOVEMBER, 23, "NATIONAL_SOVEREIGNTY_DAY"),
      observance(2026, DECEMBER, 7, "TOURIST_HOLIDAY"),
      publicHoliday(2026, DECEMBER, 8, "IMMACULATE_CONCEPTION"),
      publicHoliday(2026, DECEMBER, 25, "CHRISTMAS")
    );
  }

  private static Set<Holiday> holidaysOf(final int year) {
    return HolidayManager.getInstance(create(ARGENTINA)).getHolidays(Year.of(year));
  }

  private static Holiday publicHoliday(final int year, final Month month, final int day, final String propertiesKey) {
    return new Holiday(LocalDate.of(year, month, day), propertiesKey, PUBLIC_HOLIDAY);
  }

  private static Holiday observance(final int year, final Month month, final int day, final String propertiesKey) {
    return new Holiday(LocalDate.of(year, month, day), propertiesKey, OBSERVANCE);
  }
}
