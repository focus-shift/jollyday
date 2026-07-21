package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.COCOS_KEELING_ISLANDS;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static java.time.Year.of;

class HolidayCCTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(COCOS_KEELING_ISLANDS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()

      .hasFixedHoliday("NATIONAL_DAY", JANUARY, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()

      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()

      // Act of Self Determination Day - individually gazetted each year, no stable formula, see
      // Holidays_cc.xml. 2024 is a one-off ceremonial override (40th anniversary); 2026 is an Easter
      // Monday collision, not a weekend substitution.
      .hasFixedHoliday("ACT_OF_SELF_DETERMINATION_DAY", APRIL, 6).validBetween(YEAR_FROM, of(2023)).and()
      .hasFixedHoliday("ACT_OF_SELF_DETERMINATION_DAY", APRIL, 29).validBetween(of(2024), of(2024)).and()
      .hasFixedHoliday("ACT_OF_SELF_DETERMINATION_DAY", APRIL, 7).validBetween(of(2025), of(2026)).and()
      .hasFixedHoliday("ACT_OF_SELF_DETERMINATION_DAY", APRIL, 6).validBetween(of(2027), YEAR_TO).and()

      .hasFixedHoliday("ANZAC", APRIL, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()

      // Hari Raya Puasa - gazetted per confirmed year only, see Holidays_cc.xml
      .hasFixedHoliday("HARI_RAYA_PUASA", MAY, 2).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("HARI_RAYA_PUASA", APRIL, 24).validBetween(of(2023), of(2023)).and()
      .hasFixedHoliday("HARI_RAYA_PUASA", APRIL, 10).validBetween(of(2024), of(2024)).and()
      .hasFixedHoliday("HARI_RAYA_PUASA", MARCH, 31).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("HARI_RAYA_PUASA", MARCH, 20).validBetween(of(2026), of(2026)).and()

      // Hari Raya Haji - gazetted per confirmed year only, see Holidays_cc.xml
      .hasFixedHoliday("HARI_RAYA_HAJI", JULY, 11).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("HARI_RAYA_HAJI", JUNE, 29).validBetween(of(2023), of(2023)).and()
      .hasFixedHoliday("HARI_RAYA_HAJI", JUNE, 17).validBetween(of(2024), of(2024)).and()
      .hasFixedHoliday("HARI_RAYA_HAJI", JUNE, 6).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("HARI_RAYA_HAJI", MAY, 27).validBetween(of(2026), of(2026)).and()

      // Hari Maulaud Nabi - gazetted per confirmed year only, see Holidays_cc.xml
      .hasFixedHoliday("HARI_MAULAUD_NABI", OCTOBER, 10).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("HARI_MAULAUD_NABI", SEPTEMBER, 27).validBetween(of(2023), of(2023)).and()
      .hasFixedHoliday("HARI_MAULAUD_NABI", SEPTEMBER, 16).validBetween(of(2024), of(2024)).and()
      .hasFixedHoliday("HARI_MAULAUD_NABI", SEPTEMBER, 5).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("HARI_MAULAUD_NABI", AUGUST, 26).validBetween(of(2026), of(2026)).and()

      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()

      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
      .and()

      // King's/Queen's Birthday - fixed 6 June through 2024 regardless of weekday, changed to the
      // second Monday of June from 2025, see Holidays_cc.xml
      .hasFixedHoliday("QUEENS_BIRTHDAY", JUNE, 6).validBetween(YEAR_FROM, of(2023)).and()
      .hasFixedHoliday("KINGS_BIRTHDAY", JUNE, 6).validBetween(of(2024), of(2024)).and()
      .hasFixedWeekdayHoliday("KINGS_BIRTHDAY", SECOND, MONDAY, JUNE).validBetween(of(2025), YEAR_TO)
      .check();
  }
}
