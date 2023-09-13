package de.focus_shift.tests.country;

import de.focus_shift.Holiday;
import de.focus_shift.HolidayCalendar;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameters;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Stream;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;


class UtilTest {

  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testWeekend() {
    final LocalDate dateFriday = LocalDate.of(2010, MARCH, 12);
    final LocalDate dateSaturday = LocalDate.of(2010, MARCH, 13);
    final LocalDate dateSunday = LocalDate.of(2010, MARCH, 14);
    final LocalDate dateMonday = LocalDate.of(2010, MARCH, 15);
    assertThat(calendarUtil.isWeekend(dateFriday)).isFalse();
    assertThat(calendarUtil.isWeekend(dateSaturday)).isTrue();
    assertThat(calendarUtil.isWeekend(dateSunday)).isTrue();
    assertThat(calendarUtil.isWeekend(dateMonday)).isFalse();
  }

  @Test
  void testCalendarIslamicNewYear() {
    final Set<LocalDate> expected = new HashSet<>();
    expected.add(LocalDate.of(2008, JANUARY, 10));
    expected.add(LocalDate.of(2008, DECEMBER, 29));
    final Stream<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2008, 1, 1);

    final Set<LocalDate> collect = holidays.collect(toSet());
    assertThat(collect)
      .isEqualTo(expected)
      .hasSameSizeAs(expected);
  }

  @Test
  void testCalendarIslamicAschura2008() {
    final Set<LocalDate> expected = new HashSet<>();
    expected.add(LocalDate.of(2008, JANUARY, 19));
    final Stream<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2008, 1, 10);

    final Set<LocalDate> collect = holidays.collect(toSet());
    assertThat(collect)
      .isEqualTo(expected)
      .hasSameSizeAs(expected);
  }

  @Test
  void testCalendarIslamicAschura2009() {
    final Set<LocalDate> expected = new HashSet<>();
    expected.add(LocalDate.of(2009, JANUARY, 7));
    expected.add(LocalDate.of(2009, DECEMBER, 27));
    final Stream<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2009, 1, 10);

    final Set<LocalDate> collect = holidays.collect(toSet());
    assertThat(collect)
      .isEqualTo(expected)
      .hasSameSizeAs(expected);
  }

  @Test
  void testCalendarIslamicIdAlFitr2008() {
    final Set<LocalDate> expected = new HashSet<>();
    expected.add(LocalDate.of(2008, OCTOBER, 1));
    final Stream<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2008, 10, 1);
    final Set<LocalDate> collect = holidays.collect(toSet());
    assertThat(collect)
      .isEqualTo(expected)
      .hasSameSizeAs(expected);
  }

  @Test
  void testCalendarIslamicIdAlFitr2009() {
    final Set<LocalDate> expected = new HashSet<>();
    expected.add(LocalDate.of(2009, SEPTEMBER, 20));
    final Stream<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2009, 10, 1);
    final Set<LocalDate> collect = holidays.collect(toSet());
    assertThat(collect)
      .isEqualTo(expected)
      .hasSameSizeAs(expected);
  }

  @Test
  void testUmlaut() {
    final LocalDate aDate = LocalDate.of(2010, JANUARY, 6);
    final HolidayManager aMgr = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.AUSTRIA));
    final Set<Holiday> hs = aMgr.getHolidays(aDate, aDate.plusDays(1));
    assertThat(hs)
      .isNotNull()
      .hasSize(1);
    assertThat(hs.iterator().next().getDescription(Locale.GERMANY)).isEqualTo("Heilige Drei K\u00F6nige");
  }
}
