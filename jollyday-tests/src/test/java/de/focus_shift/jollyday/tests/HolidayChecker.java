package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.HolidayType;
import net.jqwik.api.Arbitraries;
import net.jqwik.time.api.arbitraries.YearArbitrary;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static org.assertj.core.api.Assertions.assertThat;

public class HolidayChecker {

  public static final Year YEAR_FROM_DEFAULT = Year.of(1900);
  public static final Year YEAR_TO_DEFAULT = Year.of(2500);

  public static void assertChristian(final HolidayCalendar calendar, final String propertiesKey) {
    assertChristian(calendar, YEAR_FROM_DEFAULT, YEAR_TO_DEFAULT, propertiesKey, PUBLIC_HOLIDAY);
  }
  public static void assertChristian(final HolidayCalendar calendar, final Year from, final Year to, final String propertiesKey) {
    assertChristian(calendar, from, to, propertiesKey, PUBLIC_HOLIDAY);
  }
  public static void assertChristian(final HolidayCalendar calendar, final Year from, final Year to, final String propertiesKey, final HolidayType holidayType) {
    checkByKey(calendar, from, to, "christian." + propertiesKey, holidayType);
  }

  public static void assertIslamic(final HolidayCalendar calendar, final String propertiesKey) {
    assertIslamic(calendar, YEAR_FROM_DEFAULT, YEAR_TO_DEFAULT, propertiesKey, PUBLIC_HOLIDAY);
  }
  public static void assertIslamic(final HolidayCalendar calendar, final Year from, final Year to, final String propertiesKey) {
    assertIslamic(calendar, from, to, propertiesKey, PUBLIC_HOLIDAY);
  }
  public static void assertIslamic(final HolidayCalendar calendar, final Year from, final Year to, final String propertiesKey, final HolidayType holidayType) {
    checkByKey(calendar, from, to, "islamic." + propertiesKey, holidayType);
  }


  public static void assertFixed(final HolidayCalendar calendar, final Month month, final int day, final String propertiesKey) {
    checkByDate(calendar, YEAR_FROM_DEFAULT, YEAR_TO_DEFAULT, month, day, propertiesKey, PUBLIC_HOLIDAY);
  }
  public static void assertFixed(final HolidayCalendar calendar, final Year from, final Year to, final Month month, final int day, final String propertiesKey) {
    checkByDate(calendar, from, to, month, day, propertiesKey, PUBLIC_HOLIDAY);
  }
  public static void assertFixed(final HolidayCalendar calendar, final Year from, final Year to , final Month month, final int day, final String propertiesKey, final HolidayType holidayType) {
    checkByDate(calendar, from, to, month, day, propertiesKey, holidayType);
  }

  private static void checkByKey(final HolidayCalendar calendar, final Year from, final Year to, final String propertiesKey, final HolidayType holidayType) {
    ((YearArbitrary) Arbitraries.defaultFor(Year.class))
      .between(from.getValue(), to.getValue())
      .forEachValue(year -> {
          final Set<Holiday> holidays = HolidayManager.getInstance(create(calendar)).getHolidays(year);
          assertThat(holidays)
            .isNotEmpty()
            .filteredOn(holiday -> holiday.getPropertiesKey().equals(propertiesKey))
            .extracting(Holiday::getType)
            .contains(holidayType);
        }
      );
  }

  private static void checkByDate(final HolidayCalendar calendar, final Year from, final Year to, final Month month, final int day, final String propertiesKey, final HolidayType holidayType) {
    ((YearArbitrary) Arbitraries.defaultFor(Year.class))
      .between(from.getValue(), to.getValue())
      .forEachValue(year -> {
          final Set<Holiday> holidays = HolidayManager.getInstance(create(calendar)).getHolidays(year);
          assertThat(holidays)
            .isNotEmpty()
            .contains(new Holiday(LocalDate.of(year.getValue(), month, day), propertiesKey, holidayType));
        }
      );
  }
}
