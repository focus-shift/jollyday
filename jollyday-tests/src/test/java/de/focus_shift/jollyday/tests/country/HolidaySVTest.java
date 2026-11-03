package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.EL_SALVADOR;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidaySVTest {

  @Test
  void ensuresHolidays() {
    assertFor(EL_SALVADOR)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("MOTHERS_DAY", MAY, 10).and()
      .hasFixedHoliday("FATHERS_DAY", JUNE, 17).validFrom(Year.of(2013)).and()
      .hasFixedHoliday("DIVINE_SAVIOUR_OF_THE_WORLD", AUGUST, 6).and()
      .hasFixedHoliday("AUGUST_FESTIVALS", AUGUST, 3).inSubdivision("ss").and()
      .hasFixedHoliday("AUGUST_FESTIVALS", AUGUST, 5).inSubdivision("ss").and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 15).and()
      .hasFixedHoliday("DAY_OF_THE_DEAD", NOVEMBER, 2).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_SATURDAY")
      .check();
  }

  @Test
  void ensuresFathersDayDates() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(EL_SALVADOR));
    assertThat(holidayManager.isHoliday(LocalDate.of(2012, JUNE, 17))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2025, JUNE, 17))).isTrue();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, JUNE, 17))).isTrue();
    assertThat(holidayManager.isHoliday(LocalDate.of(2027, JUNE, 17))).isTrue();
  }

  @Test
  void ensuresThirdAndFifthOfAugustOnlyInSanSalvador() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(EL_SALVADOR));
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, AUGUST, 3))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, AUGUST, 5))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, AUGUST, 3), "sm")).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, AUGUST, 3), "ss")).isTrue();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, AUGUST, 5), "ss")).isTrue();
  }

  @Test
  void ensuresNonStatutoryDaysAreNotHolidays() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(EL_SALVADOR));
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, MAY, 3))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, MAY, 7))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, AUGUST, 1))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, AUGUST, 2))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, AUGUST, 4))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, AUGUST, 7))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, OCTOBER, 1))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, OCTOBER, 12))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, NOVEMBER, 10))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, NOVEMBER, 21))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, DECEMBER, 31))).isFalse();
    // Easter Sunday 2026
    assertThat(holidayManager.isHoliday(LocalDate.of(2026, APRIL, 5))).isFalse();
  }
}
