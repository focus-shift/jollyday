package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.COCOS_KEELING_ISLANDS;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
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
import static org.assertj.core.api.Assertions.assertThat;

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
      .hasFixedHoliday("HARI_RAYA_PUASA", MAY, 13).validBetween(of(2021), of(2021)).and()
      .hasFixedHoliday("HARI_RAYA_PUASA", MAY, 2).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("HARI_RAYA_PUASA", APRIL, 24).validBetween(of(2023), of(2023)).and()
      .hasFixedHoliday("HARI_RAYA_PUASA", APRIL, 10).validBetween(of(2024), of(2024)).and()
      .hasFixedHoliday("HARI_RAYA_PUASA", MARCH, 31).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("HARI_RAYA_PUASA", MARCH, 20).validBetween(of(2026), of(2026)).and()
      .hasFixedHoliday("HARI_RAYA_PUASA", MARCH, 9).validBetween(of(2027), of(2027)).and()

      // Hari Raya Haji - gazetted per confirmed year only, see Holidays_cc.xml
      .hasFixedHoliday("HARI_RAYA_HAJI", JULY, 20).validBetween(of(2021), of(2021)).and()
      .hasFixedHoliday("HARI_RAYA_HAJI", JULY, 11).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("HARI_RAYA_HAJI", JUNE, 29).validBetween(of(2023), of(2023)).and()
      .hasFixedHoliday("HARI_RAYA_HAJI", JUNE, 17).validBetween(of(2024), of(2024)).and()
      .hasFixedHoliday("HARI_RAYA_HAJI", JUNE, 6).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("HARI_RAYA_HAJI", MAY, 27).validBetween(of(2026), of(2026)).and()
      .hasFixedHoliday("HARI_RAYA_HAJI", MAY, 17).validBetween(of(2027), of(2027)).and()

      // Hari Maulaud Nabi - gazetted per confirmed year only, see Holidays_cc.xml
      .hasFixedHoliday("HARI_MAULAUD_NABI", OCTOBER, 19).validBetween(of(2021), of(2021)).and()
      .hasFixedHoliday("HARI_MAULAUD_NABI", OCTOBER, 10).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("HARI_MAULAUD_NABI", SEPTEMBER, 27).validBetween(of(2023), of(2023)).and()
      .hasFixedHoliday("HARI_MAULAUD_NABI", SEPTEMBER, 16).validBetween(of(2024), of(2024)).and()
      .hasFixedHoliday("HARI_MAULAUD_NABI", SEPTEMBER, 5).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("HARI_MAULAUD_NABI", AUGUST, 26).validBetween(of(2026), of(2026)).and()
      .hasFixedHoliday("HARI_MAULAUD_NABI", AUGUST, 16).validBetween(of(2027), of(2027)).and()

      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()

      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
      .and()

      // King's/Queen's Birthday - 6 June, weekend moved to Monday; 2025 one-off on 9 June because
      // 6 June 2025 was Hari Raya Haji, see Holidays_cc.xml
      .hasFixedHoliday("QUEENS_BIRTHDAY", JUNE, 6).validBetween(YEAR_FROM, of(2023))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("KINGS_BIRTHDAY", JUNE, 6).validBetween(of(2024), of(2024)).and()
      .hasFixedHoliday("KINGS_BIRTHDAY", JUNE, 9).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("KINGS_BIRTHDAY", JUNE, 6).validBetween(of(2026), YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .check();
  }

  @Test
  void ensuresGazettedSovereignsBirthdayDates() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(COCOS_KEELING_ISLANDS));
    assertThat(holidayManager.isHoliday(LocalDate.of(2021, JUNE, 6))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2021, JUNE, 7))).isTrue();
    assertThat(holidayManager.isHoliday(LocalDate.of(2023, JUNE, 6))).isTrue();
    assertThat(holidayManager.isHoliday(LocalDate.of(2025, JUNE, 9))).isTrue();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, JUNE, 8))).isTrue();
    assertThat(holidayManager.isHoliday(LocalDate.of(2027, JUNE, 7))).isTrue();
    assertThat(holidayManager.isHoliday(LocalDate.of(2027, JUNE, 14))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2028, JUNE, 6))).isTrue();
    assertThat(holidayManager.isHoliday(LocalDate.of(2028, JUNE, 12))).isFalse();
  }
}
