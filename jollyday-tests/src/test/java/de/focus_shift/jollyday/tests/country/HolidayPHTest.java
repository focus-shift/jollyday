package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;
import java.util.stream.Collectors;

import static de.focus_shift.jollyday.core.HolidayCalendar.PHILIPPINES;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.APRIL;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayPHTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    // R.A. No. 9492 nominally moves several regular holidays to the "Monday nearest"
    // their statutory date, but the President has consistently overridden that default
    // every year observed (CY2024-CY2026) to keep them on their literal calendar date,
    // so all fixed-date holidays are asserted unmoved here.
    assertFor(PHILIPPINES)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ARAW_NG_KAGITINGAN", APRIL, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JUNE, 12).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NINOY_AQUINO_DAY", AUGUST, 21).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("BONIFACIO_DAY", NOVEMBER, 30).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("RIZAL_DAY", DECEMBER, 30).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("MAUNDY_THURSDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }

  @Test
  void ensuresNationalHeroesDayIsLastMondayOfAugust() {
    // National Heroes Day is fixed to the last Monday of August, not to a statutory
    // calendar date, so it cannot be expressed as a hasFixedHoliday check.
    final HolidayManager manager = HolidayManager.getInstance(create(PHILIPPINES));

    assertThat(holidayDates(manager, Year.of(2024))).contains(LocalDate.of(2024, AUGUST, 26));
    assertThat(holidayDates(manager, Year.of(2025))).contains(LocalDate.of(2025, AUGUST, 25));
    assertThat(holidayDates(manager, Year.of(2026))).contains(LocalDate.of(2026, AUGUST, 31));
    assertThat(holidayDates(manager, Year.of(2027))).contains(LocalDate.of(2027, AUGUST, 30));
  }

  private static Set<LocalDate> holidayDates(final HolidayManager manager, final Year year) {
    return manager.getHolidays(year).stream().map(Holiday::getDate).collect(Collectors.toSet());
  }
}
