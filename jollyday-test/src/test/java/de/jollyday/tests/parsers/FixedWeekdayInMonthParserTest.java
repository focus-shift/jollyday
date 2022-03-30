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
import de.jollyday.parser.impl.FixedWeekdayInMonthParser;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author svdi1de
 *
 */
class FixedWeekdayInMonthParserTest {

    private FixedWeekdayInMonthParser parser = new FixedWeekdayInMonthParser();

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
        FixedWeekdayInMonth e = new FixedWeekdayInMonth();
        e.setValidFrom(2011);
        config.getFixedWeekday().add(e);
        parser.parse(2010, holidays, config);
        assertEquals(0, holidays.size(), "Expected to be empty.");
    }

}
