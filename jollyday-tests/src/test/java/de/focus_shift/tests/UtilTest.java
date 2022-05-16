package de.focus_shift.tests;

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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Sven
 */
class UtilTest {

  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testWeekend() {
    LocalDate dateFriday = LocalDate.of(2010, MARCH, 12);
    LocalDate dateSaturday = LocalDate.of(2010, MARCH, 13);
    LocalDate dateSunday = LocalDate.of(2010, MARCH, 14);
    LocalDate dateMonday = LocalDate.of(2010, MARCH, 15);
    assertFalse(calendarUtil.isWeekend(dateFriday));
    assertTrue(calendarUtil.isWeekend(dateSaturday));
    assertTrue(calendarUtil.isWeekend(dateSunday));
    assertFalse(calendarUtil.isWeekend(dateMonday));
  }

  @Test
  void testCalendarIslamicNewYear() {
    Set<LocalDate> expected = new HashSet<>();
    expected.add(LocalDate.of(2008, JANUARY, 10));
    expected.add(LocalDate.of(2008, DECEMBER, 29));
    Stream<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2008, 1, 1);

    final Set<LocalDate> collect = holidays.collect(Collectors.toSet());
    assertEquals(expected.size(), collect.size(), "Wrong number of islamic new years in 2008.");
    assertEquals(expected, collect, "Wrong islamic New Year holidays in 2008.");
  }

  @Test
  void testCalendarIslamicAschura2008() {
    Set<LocalDate> expected = new HashSet<>();
    expected.add(LocalDate.of(2008, JANUARY, 19));
    Stream<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2008, 1, 10);

    final Set<LocalDate> collect = holidays.collect(Collectors.toSet());
    assertEquals(expected.size(), collect.size(), "Wrong number of islamic Aschura holidays in 2008.");
    assertEquals(expected, collect, "Wrong islamic Aschura holidays in 2008.");
  }

  @Test
  void testCalendarIslamicAschura2009() {
    Set<LocalDate> expected = new HashSet<>();
    expected.add(LocalDate.of(2009, JANUARY, 7));
    expected.add(LocalDate.of(2009, DECEMBER, 27));
    Stream<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2009, 1, 10);

    final Set<LocalDate> collect = holidays.collect(Collectors.toSet());
    assertEquals(expected.size(), collect.size(), "Wrong number of islamic Aschura holidays in 2009.");
    assertEquals(expected, collect, "Wrong islamic Aschura holidays in 2009.");
  }

  @Test
  void testCalendarIslamicIdAlFitr2008() {
    Set<LocalDate> expected = new HashSet<>();
    expected.add(LocalDate.of(2008, OCTOBER, 1));
    Stream<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2008, 10, 1);
    final Set<LocalDate> collect = holidays.collect(Collectors.toSet());
    assertEquals(expected.size(), collect.size(), "Wrong number of islamic IdAlFitr holidays in 2008.");
    assertEquals(expected, collect, "Wrong islamic IdAlFitr holidays in 2008.");
  }

  @Test
  void testCalendarIslamicIdAlFitr2009() {
    Set<LocalDate> expected = new HashSet<>();
    expected.add(LocalDate.of(2009, SEPTEMBER, 20));
    Stream<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2009, 10, 1);
    final Set<LocalDate> collect = holidays.collect(Collectors.toSet());
    assertEquals(expected.size(), collect.size(), "Wrong number of islamic IdAlFitr holidays in 2009.");
    assertEquals(expected, collect, "Wrong islamic IdAlFitr holidays in 2009.");
  }

  @Test
  void testUmlaut() {
    final LocalDate aDate = LocalDate.of(2010, JANUARY, 6);
    final HolidayManager aMgr = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.AUSTRIA, null));
    final Set<Holiday> hs = aMgr.getHolidays(aDate, aDate.plusDays(1));
    assertNotNull(hs);
    assertEquals(1, hs.size());
    assertEquals("Heilige Drei K\u00F6nige", hs.iterator().next().getDescription(Locale.GERMANY));
  }
}
