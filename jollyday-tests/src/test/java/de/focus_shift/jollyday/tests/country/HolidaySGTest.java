package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.SINGAPORE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HolidaySGTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void testManagerSGInterval() {
    assertDoesNotThrow(() -> {
      final HolidayManager instance = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.SINGAPORE, null));
      final LocalDate startDateInclusive = LocalDate.of(2022, Month.OCTOBER, 1);
      final LocalDate endDateInclusive = LocalDate.of(2023, Month.JANUARY, 31);
      final Set<Holiday> holidays = instance.getHolidays(startDateInclusive, endDateInclusive);
      final List<LocalDate> expected = List.of(LocalDate.of(2022, Month.OCTOBER, 24),
        LocalDate.of(2022, Month.DECEMBER, 26), LocalDate.of(2023, Month.JANUARY, 2),
        LocalDate.of(2023, Month.JANUARY, 24), LocalDate.of(2023, Month.JANUARY, 23));
      assertThat(holidays).hasSameSizeAs(expected);
      for (LocalDate d : expected) {
        assertThat(CalendarUtil.contains(holidays, d)).isTrue();
      }
    });
  }

  @Test
  void ensuresHolidays() {

    assertFor(SINGAPORE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()

      // CHINESE_NEW_YEAR: two consecutive fixed dates per year, hardcoded per year since the lunar-calendar date shifts
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 10).validBetween(Year.of(2013), Year.of(2013)).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 11).validBetween(Year.of(2013), Year.of(2013)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", JANUARY, 31).validBetween(Year.of(2014), Year.of(2014)).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 1).validBetween(Year.of(2014), Year.of(2014)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 19).validBetween(Year.of(2015), Year.of(2015)).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 20).validBetween(Year.of(2015), Year.of(2015)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 8).validBetween(Year.of(2016), Year.of(2016)).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 9).validBetween(Year.of(2016), Year.of(2016)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", JANUARY, 28).validBetween(Year.of(2017), Year.of(2017)).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", JANUARY, 29).validBetween(Year.of(2017), Year.of(2017)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 16).validBetween(Year.of(2018), Year.of(2018)).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 17).validBetween(Year.of(2018), Year.of(2018)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 5).validBetween(Year.of(2019), Year.of(2019)).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 6).validBetween(Year.of(2019), Year.of(2019)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", JANUARY, 25).validBetween(Year.of(2020), Year.of(2020)).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", JANUARY, 26).validBetween(Year.of(2020), Year.of(2020)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 12).validBetween(Year.of(2021), Year.of(2021)).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 13).validBetween(Year.of(2021), Year.of(2021)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 1).validBetween(Year.of(2022), Year.of(2022)).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 2).validBetween(Year.of(2022), Year.of(2022)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", JANUARY, 22).validBetween(Year.of(2023), Year.of(2023)).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", JANUARY, 23).validBetween(Year.of(2023), Year.of(2023)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 10).validBetween(Year.of(2024), Year.of(2024)).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("CHINESE_NEW_YEAR", FEBRUARY, 11).validBetween(Year.of(2024), Year.of(2024)).canBeMovedFrom(SUNDAY, MONDAY).and()

      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()

      // VESAK_DAY: full moon of the 4th lunar month, hardcoded per year
      .hasFixedHoliday("VESAK_DAY", MAY, 24).validBetween(Year.of(2013), Year.of(2013)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VESAK_DAY", MAY, 13).validBetween(Year.of(2014), Year.of(2014)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VESAK_DAY", JUNE, 1).validBetween(Year.of(2015), Year.of(2015)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VESAK_DAY", MAY, 21).validBetween(Year.of(2016), Year.of(2016)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VESAK_DAY", MAY, 10).validBetween(Year.of(2017), Year.of(2017)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VESAK_DAY", MAY, 29).validBetween(Year.of(2018), Year.of(2018)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VESAK_DAY", MAY, 19).validBetween(Year.of(2019), Year.of(2019)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VESAK_DAY", MAY, 7).validBetween(Year.of(2020), Year.of(2020)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VESAK_DAY", MAY, 26).validBetween(Year.of(2021), Year.of(2021)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VESAK_DAY", MAY, 15).validBetween(Year.of(2022), Year.of(2022)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VESAK_DAY", JUNE, 2).validBetween(Year.of(2023), Year.of(2023)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VESAK_DAY", MAY, 22).validBetween(Year.of(2024), Year.of(2024)).canBeMovedFrom(SUNDAY, MONDAY).and()

      .hasFixedHoliday("SG50_PUBLIC_HOLIDAY", AUGUST, 7).validBetween(Year.of(2015), Year.of(2015)).and()

      .hasFixedHoliday("NATIONAL_DAY", AUGUST, 9)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()

      .hasFixedHoliday("POLLING_DAY", SEPTEMBER, 11).validBetween(Year.of(2015), Year.of(2015)).and()
      .hasFixedHoliday("POLLING_DAY", JULY, 10).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("POLLING_DAY", SEPTEMBER, 1).validBetween(Year.of(2023), Year.of(2023)).and()

      // DEEPAVALI: new moon day in the Hindu calendar, hardcoded per year
      .hasFixedHoliday("DEEPAVALI", NOVEMBER, 2).validBetween(Year.of(2013), Year.of(2013)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEEPAVALI", OCTOBER, 22).validBetween(Year.of(2014), Year.of(2014)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEEPAVALI", NOVEMBER, 10).validBetween(Year.of(2015), Year.of(2015)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEEPAVALI", OCTOBER, 29).validBetween(Year.of(2016), Year.of(2016)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEEPAVALI", OCTOBER, 18).validBetween(Year.of(2017), Year.of(2017)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEEPAVALI", NOVEMBER, 6).validBetween(Year.of(2018), Year.of(2018)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEEPAVALI", OCTOBER, 27).validBetween(Year.of(2019), Year.of(2019)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEEPAVALI", NOVEMBER, 14).validBetween(Year.of(2020), Year.of(2020)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEEPAVALI", NOVEMBER, 4).validBetween(Year.of(2021), Year.of(2021)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEEPAVALI", OCTOBER, 24).validBetween(Year.of(2022), Year.of(2022)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEEPAVALI", NOVEMBER, 12).validBetween(Year.of(2023), Year.of(2023)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEEPAVALI", OCTOBER, 31).validBetween(Year.of(2024), Year.of(2024)).canBeMovedFrom(SUNDAY, MONDAY).and()

      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()

      .hasChristianHoliday("GOOD_FRIDAY")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()

      // HARI_RAYA_PUASA/HARI_RAYA_HAJI: descriptionPropertiesKey is overridden to a plain name in the XML,
      // regardless of which IslamicHoliday type (ID_AL_FITR/_2, ID_UL_ADHA/_2) applies in a given validity window
      .hasIslamicHoliday("HARI_RAYA_PUASA", true)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("HARI_RAYA_HAJI", true)
        .validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
