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
import de.jollyday.config.Weekday;
import de.jollyday.config.When;
import de.jollyday.jaxb.Holidays;
import de.jollyday.parser.impl.RelativeToFixedParser;
import de.jollyday.spi.Fixed;
import de.jollyday.spi.RelativeToFixed;
import de.jollyday.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Sven
 *
 */
class RelativeToFixedParserTest {

    private final RelativeToFixedParser rtfp = new RelativeToFixedParser();
    private final CalendarUtil calendarUtil = new CalendarUtil();

    @Test
    void testEmpty() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = new Holidays();
        rtfp.parse(2010, holidays, config);
        assertTrue(holidays.isEmpty(), "Expected to be empty.");
    }

    @Test
    void testInvalid() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = new Holidays();
        RelativeToFixed rule = new RelativeToFixed();
        rule.setValidFrom(2011);
        config.getRelativeToFixed().add(rule);
        rtfp.parse(2010, holidays, config);
        assertTrue(holidays.isEmpty(), "Expected to be empty.");
    }

    @Test
    void testWeekday() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = new Holidays();
        RelativeToFixed rule = new RelativeToFixed();
        rule.setWeekday(Weekday.THURSDAY);
        rule.setWhen(When.AFTER);
        Fixed date = new Fixed();
        date.setDay(5);
        date.setMonth(Month.AUGUST);
        rule.setDate(date);
        config.getRelativeToFixed().add(rule);
        rtfp.parse(2011, holidays, config);
        assertEquals(1, holidays.size(), "Number of holidays wrong.");
        assertEquals(calendarUtil.create(2011, 8, 11), holidays.iterator().next().getDate(), "Wrong date.");
    }

    @Test
    void testSameWeekday() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = new Holidays();
        RelativeToFixed rule = new RelativeToFixed();
        rule.setWeekday(Weekday.WEDNESDAY);
        rule.setWhen(When.BEFORE);
        Fixed date = new Fixed();
        date.setDay(23);
        date.setMonth(Month.NOVEMBER);
        rule.setDate(date);
        config.getRelativeToFixed().add(rule);
        rtfp.parse(2016, holidays, config);
        assertEquals(1, holidays.size(), "Number of holidays wrong.");
        assertEquals(calendarUtil.create(2016, 11, 16), holidays.iterator().next().getDate(), "Wrong date.");
    }

    @Test
    void testNumberOfDays() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = new Holidays();
        RelativeToFixed rule = new RelativeToFixed();
        rule.setDays(3);
        rule.setWhen(When.BEFORE);
        Fixed date = new Fixed();
        date.setDay(5);
        date.setMonth(Month.AUGUST);
        rule.setDate(date);
        config.getRelativeToFixed().add(rule);
        rtfp.parse(2011, holidays, config);
        assertEquals(1, holidays.size(), "Number of holidays wrong.");
        assertEquals(calendarUtil.create(2011, 8, 2), holidays.iterator().next().getDate(), "Wrong date.");
    }

}
