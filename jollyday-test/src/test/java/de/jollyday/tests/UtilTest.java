/**
 * Copyright 2010 Sven Diedrichsen
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package de.jollyday.tests;

import de.jollyday.Holiday;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import de.jollyday.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

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
 *
 */
class UtilTest {

    private CalendarUtil calendarUtil = new CalendarUtil();

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
        Set<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2008, 1, 1);
        assertNotNull(holidays);
        assertEquals(expected.size(), holidays.size(), "Wrong number of islamic new years in 2008.");
        assertEquals(expected, holidays, "Wrong islamic New Year holidays in 2008.");
    }

    @Test
    void testCalendarIslamicAschura2008() {
        Set<LocalDate> expected = new HashSet<>();
        expected.add(LocalDate.of(2008, JANUARY, 19));
        Set<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2008, 1, 10);
        assertNotNull(holidays);
        assertEquals(expected.size(), holidays.size(), "Wrong number of islamic Aschura holidays in 2008.");
        assertEquals(expected, holidays, "Wrong islamic Aschura holidays in 2008.");
    }

    @Test
    void testCalendarIslamicAschura2009() {
        Set<LocalDate> expected = new HashSet<>();
        expected.add(LocalDate.of(2009, JANUARY, 7));
        expected.add(LocalDate.of(2009, DECEMBER, 27));
        Set<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2009, 1, 10);
        assertNotNull(holidays);
        assertEquals(expected.size(), holidays.size(), "Wrong number of islamic Aschura holidays in 2009.");
        assertEquals(expected, holidays, "Wrong islamic Aschura holidays in 2009.");
    }

    @Test
    void testCalendarIslamicIdAlFitr2008() {
        Set<LocalDate> expected = new HashSet<>();
        expected.add(LocalDate.of(2008, OCTOBER, 1));
        Set<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2008, 10, 1);
        assertNotNull(holidays);
        assertEquals(expected.size(), holidays.size(), "Wrong number of islamic IdAlFitr holidays in 2008.");
        assertEquals(expected, holidays, "Wrong islamic IdAlFitr holidays in 2008.");
    }

    @Test
    void testCalendarIslamicIdAlFitr2009() {
        Set<LocalDate> expected = new HashSet<>();
        expected.add(LocalDate.of(2009, SEPTEMBER, 20));
        Set<LocalDate> holidays = calendarUtil.getIslamicHolidaysInGregorianYear(2009, 10, 1);
        assertNotNull(holidays);
        assertEquals(expected.size(), holidays.size(), "Wrong number of islamic IdAlFitr holidays in 2009.");
        assertEquals(expected, holidays, "Wrong islamic IdAlFitr holidays in 2009.");
    }

    @Test
    void testEaster2000() {
        checkEasterDate(2000, 4, 23);
    }

    @Test
    void testEaster2001() {
        checkEasterDate(2001, 4, 15);
    }

    @Test
    void testEaster2002() {
        checkEasterDate(2002, 3, 31);
    }

    @Test
    void testEaster2003() {
        checkEasterDate(2003, 4, 20);
    }

    @Test
    void testEaster2004() {
        checkEasterDate(2004, 4, 11);
    }

    @Test
    void testEaster2005() {
        checkEasterDate(2005, 3, 27);
    }

    @Test
    void testEaster2006() {
        checkEasterDate(2006, 4, 16);
    }

    @Test
    void testEaster2007() {
        checkEasterDate(2007, 4, 8);
    }

    @Test
    void testEaster2008() {
        checkEasterDate(2008, 3, 23);
    }

    @Test
    void testEaster2009() {
        checkEasterDate(2009, 4, 12);
    }

    @Test
    void testEaster2010() {
        checkEasterDate(2010, 4, 4);
    }

    @Test
    void testEaster2011() {
        checkEasterDate(2011, 4, 24);
    }

    @Test
    void testEaster2012() {
        checkEasterDate(2012, 4, 8);
    }

    @Test
    void testEaster2013() {
        checkEasterDate(2013, 3, 31);
    }

    private void checkEasterDate(Integer year, int month, int day) {
        assertEquals(LocalDate.of(year, month, day),
                calendarUtil.getEasterSunday(year),
                "Wrong easter date.");
    }

    @Test
    void testCalendarUtilEasterJulian() {
        assertEquals(LocalDate.of(1583, 4, 10),
                calendarUtil.getEasterSunday(1583),
                "Wrong easter date.");
    }

    @Test
    void testCalendarUtilEasterGregorian() {
        assertEquals(LocalDate.of(1584, 4, 1),
                calendarUtil.getEasterSunday(1584),
                "Wrong easter date.");
    }

    @Test
    void testUmlaut() {
        final LocalDate aDate = LocalDate.of(2010, JANUARY, 6);
        final HolidayManager aMgr = HolidayManager.getInstance(HolidayCalendar.AUSTRIA);
        final Set<Holiday> hs = aMgr.getHolidays(aDate, aDate.plusDays(1));
        assertNotNull(hs);
        assertEquals(1, hs.size());
        assertEquals("Heilige Drei K\u00F6nige", hs.iterator().next().getDescription(Locale.GERMANY));
    }

}
