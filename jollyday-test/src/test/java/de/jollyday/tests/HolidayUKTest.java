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
import de.jollyday.ManagerParameters;
import de.jollyday.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HolidayUKTest extends AbstractCountryTestBase {

    private static final int YEAR = 2010;
    private static final String ISO_CODE = "gb";

    @Test
    void testManagerUKStructure() throws Exception {
        validateCalendarData(ISO_CODE, YEAR);
    }

    @Test
    void testManagerUKChristmasMovingDaysWhenChristimasOnSunday() {
        doChristmasContainmentTest(2011, 26, 27);
    }

    @Test
    void testManagerUKChristmasMovingDaysWhenChristimasOnSaturday() {
        doChristmasContainmentTest(2010, 27, 28);
    }

    @Test
    void testManagerUKChristmasMovingDaysWhenChristimasOnFriday() {
        doChristmasContainmentTest(2009, 25, 28);
    }

    private void doChristmasContainmentTest(int year, int dayOfChristmas, int dayOfBoxingday) {
        LocalDate christmas = LocalDate.of(year, 12, dayOfChristmas);
        LocalDate boxingday = LocalDate.of(year, 12, dayOfBoxingday);
        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.UNITED_KINGDOM));
        Set<Holiday> holidays = holidayManager.getHolidays(year);
        assertTrue(contains(christmas, holidays), "There should be christmas on " + christmas);
        assertTrue(contains(boxingday, holidays), "There should be boxing day on " + boxingday);
    }

    private boolean contains(LocalDate localDate, Set<Holiday> holidays) {
        for (Holiday h : holidays) {
            if (localDate.equals(h.getDate())) {
                return true;
            }
        }
        return false;
    }

}
