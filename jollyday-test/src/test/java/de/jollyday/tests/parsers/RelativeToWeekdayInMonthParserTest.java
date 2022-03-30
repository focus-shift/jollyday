/**
 * Copyright 2011 Sven Diedrichsen
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
package de.jollyday.tests.parsers;

import de.jollyday.Holiday;
import de.jollyday.config.FixedWeekdayInMonth;
import de.jollyday.config.Holidays;
import de.jollyday.config.Month;
import de.jollyday.config.RelativeToWeekdayInMonth;
import de.jollyday.config.Weekday;
import de.jollyday.config.When;
import de.jollyday.config.Which;
import de.jollyday.parser.impl.RelativeToWeekdayInMonthParser;
import de.jollyday.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author svdi1de
 *
 */
class RelativeToWeekdayInMonthParserTest {

    private final RelativeToWeekdayInMonthParser rtwim = new RelativeToWeekdayInMonthParser();
    private final CalendarUtil calendarUtil = new CalendarUtil();

    @Test
    void testEmpty() {
        Set<Holiday> result = new HashSet<>();
        Holidays config = new Holidays();
        rtwim.parse(2011, result, config);
        assertTrue(result.isEmpty(), "Result is not empty.");
    }

    @Test
    void testInvalid() {
        Set<Holiday> result = new HashSet<>();
        Holidays config = new Holidays();
        RelativeToWeekdayInMonth rule = new RelativeToWeekdayInMonth();
        rule.setWeekday(Weekday.TUESDAY);
        rule.setWhen(When.AFTER);
        FixedWeekdayInMonth date = new FixedWeekdayInMonth();
        date.setWhich(Which.SECOND);
        date.setWeekday(Weekday.MONDAY);
        date.setMonth(Month.JULY);
        rule.setFixedWeekday(date);
        config.getRelativeToWeekdayInMonth().add(rule);
        rule.setValidFrom(2012);
        rtwim.parse(2011, result, config);
        assertTrue(result.isEmpty(), "Result is not empty.");
    }

    @Test
    void testTueAfter2ndMondayJuly() {
        Set<Holiday> result = new HashSet<>();
        Holidays config = new Holidays();
        RelativeToWeekdayInMonth rule = new RelativeToWeekdayInMonth();
        rule.setWeekday(Weekday.TUESDAY);
        rule.setWhen(When.AFTER);
        FixedWeekdayInMonth date = new FixedWeekdayInMonth();
        date.setWhich(Which.SECOND);
        date.setWeekday(Weekday.MONDAY);
        date.setMonth(Month.JULY);
        rule.setFixedWeekday(date);
        config.getRelativeToWeekdayInMonth().add(rule);
        rtwim.parse(2011, result, config);
        assertEquals(1, result.size(), "Wrong number of dates.");
        assertEquals(calendarUtil.create(2011, 7, 12), result.iterator().next().getDate(), "Wrong date.");
    }

    @Test
    void testMonAfter4thMondayOctober() {
        Set<Holiday> result = new HashSet<>();
        Holidays config = new Holidays();
        RelativeToWeekdayInMonth rule = new RelativeToWeekdayInMonth();
        rule.setWeekday(Weekday.MONDAY);
        rule.setWhen(When.AFTER);
        FixedWeekdayInMonth date = new FixedWeekdayInMonth();
        date.setWhich(Which.FOURTH);
        date.setWeekday(Weekday.MONDAY);
        date.setMonth(Month.OCTOBER);
        rule.setFixedWeekday(date);
        config.getRelativeToWeekdayInMonth().add(rule);
        rtwim.parse(2018, result, config);
        assertEquals(1, result.size(), "Wrong number of dates.");
        assertEquals(calendarUtil.create(2018, 10, 29), result.iterator().next().getDate(), "Wrong date.");
    }

}
