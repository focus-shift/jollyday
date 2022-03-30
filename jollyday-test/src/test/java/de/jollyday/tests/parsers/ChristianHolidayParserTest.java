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
import de.jollyday.config.ChristianHolidayType;
import de.jollyday.config.ChronologyType;
import de.jollyday.config.Holidays;
import de.jollyday.config.RelativeToEasterSunday;
import de.jollyday.parser.impl.ChristianHolidayParser;
import de.jollyday.parser.impl.RelativeToEasterSundayParser;
import de.jollyday.spi.ChristianHoliday;
import de.jollyday.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChristianHolidayParserTest {

	private ChristianHolidayParser hp = new ChristianHolidayParser();
	private CalendarUtil calendarUtil = new CalendarUtil();

    @Test
    void testEmpty() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = new Holidays();
        hp.parse(2010, holidays, config);
        assertTrue(holidays.isEmpty(), "Expected to be empty.");
    }

    @Test
    void testEaster() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = createConfig(ChristianHolidayType.EASTER);
        Stream<ChristianHoliday> lala = new ChristianHoliday();
        final ChristianHolidayParser hp = new ChristianHolidayParser(lala);
        hp.parse(2011, holidays, config);
        assertEquals(1, holidays.size(), "Wrong number of holidays.");
        Holiday easterDate = holidays.iterator().next();
        LocalDate ed = calendarUtil.create(2011, 4, 24);
        assertEquals(ed, easterDate.getDate(), "Wrong easter date.");
    }

    @Test
    void testChristianInvalidDate() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = createConfig(ChristianHolidayType.EASTER);
        config.getChristianHoliday().get(0).setValidTo(2010);
        hp.parse(2011, holidays, config);
        assertEquals(0, holidays.size(), "Wrong number of holidays.");
    }

    @Test
    void testRelativeToEasterSunday() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = createConfig(1);
        RelativeToEasterSundayParser p = new RelativeToEasterSundayParser();
        p.parse(2011, holidays, config);
        List<LocalDate> expected = new ArrayList<>();
        expected.add(calendarUtil.create(2011, 4, 25));
        assertEquals(expected.size(), holidays.size(), "Wrong number of holidays.");
        assertEquals(expected.get(0), holidays.iterator().next().getDate(), "Wrong holiday.");
    }

    @Test
    void testChristianDates() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = createConfig(ChristianHolidayType.EASTER, ChristianHolidayType.CLEAN_MONDAY,
                ChristianHolidayType.EASTER_SATURDAY, ChristianHolidayType.EASTER_TUESDAY,
                ChristianHolidayType.GENERAL_PRAYER_DAY, ChristianHolidayType.PENTECOST,
                ChristianHolidayType.SACRED_HEART);
        hp.parse(2011, holidays, config);
        List<LocalDate> expected = new ArrayList<>();
        expected.add(calendarUtil.create(2011, 3, 7));
        expected.add(calendarUtil.create(2011, 4, 23));
        expected.add(calendarUtil.create(2011, 4, 24));
        expected.add(calendarUtil.create(2011, 4, 26));
        expected.add(calendarUtil.create(2011, 5, 20));
        expected.add(calendarUtil.create(2011, 6, 12));
        expected.add(calendarUtil.create(2011, 7, 1));

        assertEquals(expected.size(), holidays.size(), "Wrong number of holidays.");

        Collections.sort(expected);
        List<Holiday> found = new ArrayList<>(holidays);
        Collections.sort(found, new HolidayComparator());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), found.get(i).getDate(), "Wrong date.");
        }

    }

    @Test
    void testCustomPropertiesKey() {
        Set<Holiday> holidays = new HashSet<>();
        Holidays config = createConfig(ChristianHolidayType.EASTER_TUESDAY);
        config.getChristianHoliday().get(0).setDescriptionPropertiesKey("CUSTOM_KEY");
        hp.parse(2019, holidays, config);

        String expectedPropertiesKey = "CUSTOM_KEY";
        LocalDate expectedDate = calendarUtil.create(2019, 4, 23);

        assertEquals(1, holidays.size(), "Wrong number of holidays.");

        Holiday easterTuesday = holidays.iterator().next();
        assertEquals(expectedDate, easterTuesday.getDate(), "Wrong holiday date.");
        assertEquals(expectedPropertiesKey, easterTuesday.getPropertiesKey(), "Wrong holiday key.");
    }

    private Holidays createConfig(int... days) {
        Holidays config = new Holidays();
        for (int day : days) {
            RelativeToEasterSunday d = new RelativeToEasterSunday();
            d.setDays(day);
            d.setChronology(ChronologyType.GREGORIAN);
            config.getRelativeToEasterSunday().add(d);
        }
        return config;
    }

    private Holidays createConfig(ChristianHolidayType... types) {
        Holidays config = new Holidays();
        for (ChristianHolidayType type : types) {
            ChristianHoliday h = new ChristianHoliday();
            h.setType(type);
            config.getChristianHoliday().add(h);
        }
        return config;
    }

}
