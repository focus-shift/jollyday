package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.PANAMA;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayPATest {

  @Test
  void ensuresHolidays() {
    assertFor(PANAMA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("MARTYRS_DAY", JANUARY, 9).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("SEPARATION", NOVEMBER, 3).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("FLAG_DAY", NOVEMBER, 4).and()
      .hasFixedHoliday("CONSOLIDATION_OF_SEPARATION", NOVEMBER, 5).validFrom(Year.of(2002)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("FIRST_CRY_OF_INDEPENDENCE", NOVEMBER, 10).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 28).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("MOTHERS_DAY", DECEMBER, 8).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NATIONAL_DAY_OF_MOURNING", DECEMBER, 20).validFrom(Year.of(2022)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasChristianHoliday("EASTER", OBSERVANCE).and()
      .hasChristianHoliday("CLEAN_MONDAY", OBSERVANCE).and()
      .hasChristianHoliday("CARNIVAL").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }

  @Test
  void ensuresDatesFromOfficialNotices() {
    // Superintendencia de Bancos de Panamá closure notices
    assertThat(datesOf(2022, "NATIONAL_DAY_OF_MOURNING")).containsExactly(LocalDate.of(2022, DECEMBER, 20));
    assertThat(datesOf(2022, "CHRISTMAS")).containsExactly(LocalDate.of(2022, DECEMBER, 26));
    assertThat(datesOf(2023, "NEW_YEAR")).containsExactly(LocalDate.of(2023, JANUARY, 2));
    assertThat(datesOf(2023, "CONSOLIDATION_OF_SEPARATION")).containsExactly(LocalDate.of(2023, NOVEMBER, 6));
    assertThat(datesOf(2023, "FIRST_CRY_OF_INDEPENDENCE")).containsExactly(LocalDate.of(2023, NOVEMBER, 10));
    assertThat(datesOf(2024, "SEPARATION")).containsExactly(LocalDate.of(2024, NOVEMBER, 4));
    assertThat(datesOf(2024, "CONSOLIDATION_OF_SEPARATION")).containsExactly(LocalDate.of(2024, NOVEMBER, 5));
    assertThat(datesOf(2024, "FIRST_CRY_OF_INDEPENDENCE")).containsExactly(LocalDate.of(2024, NOVEMBER, 11));
    assertThat(datesOf(2001, "CONSOLIDATION_OF_SEPARATION")).isEmpty();
    assertThat(datesOf(2021, "NATIONAL_DAY_OF_MOURNING")).isEmpty();
  }

  private static Set<LocalDate> datesOf(final int year, final String propertiesKey) {
    final Set<Holiday> holidays = HolidayManager.getInstance(create(PANAMA)).getHolidays(Year.of(year));
    return holidays.stream()
      .filter(holiday -> holiday.getPropertiesKey().equals(propertiesKey))
      .map(Holiday::getDate)
      .collect(toSet());
  }
}
