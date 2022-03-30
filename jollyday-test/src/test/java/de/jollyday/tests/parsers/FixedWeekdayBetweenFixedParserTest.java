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
import de.jollyday.config.FixedWeekdayBetweenFixed;
import de.jollyday.config.Holidays;
import de.jollyday.config.Month;
import de.jollyday.config.Weekday;
import de.jollyday.parser.impl.FixedWeekdayBetweenFixedParser;
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
class FixedWeekdayBetweenFixedParserTest extends FixedParserTest {

    private final FixedWeekdayBetweenFixedParser parser = new FixedWeekdayBetweenFixedParser();
    private final CalendarUtil calendarUtil = new CalendarUtil();

    @Test
    void testEmpty() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = new Holidays();
        parser.parse(2010, holidays, config);
        assertTrue(holidays.isEmpty(), "Expected to be empty.");
    }

    @Test
    void testInvalid() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = new Holidays();
        FixedWeekdayBetweenFixed e = new FixedWeekdayBetweenFixed();
        e.setValidTo(2009);
        config.getFixedWeekdayBetweenFixed().add(e);
        parser.parse(2010, holidays, config);
        assertTrue(holidays.isEmpty(), "Expected to be empty.");
    }

    @Test
    void testWednesday() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = new Holidays();
        FixedWeekdayBetweenFixed e = new FixedWeekdayBetweenFixed();
        e.setFrom(createFixed(17, Month.JANUARY));
        e.setTo(createFixed(21, Month.JANUARY));
        e.setWeekday(Weekday.WEDNESDAY);
        config.getFixedWeekdayBetweenFixed().add(e);
        parser.parse(2011, holidays, config);
        assertEquals(1, holidays.size(), "Wrong number of results.");
        assertEquals(calendarUtil.create(2011, 1, 19), holidays.iterator().next().getDate(), "Wrong date.");
    }

}
