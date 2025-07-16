package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.MONACO;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayMCTest {

    @Test
    void ensuresMonacoHolidays() {
        assertFor(MONACO)
            .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
                .canBeMovedFrom(SUNDAY, MONDAY)
          .and()
            .hasFixedHoliday("ST_DEVOTA", JANUARY, 27).and()
            .hasFixedHoliday("MAY_DAY", MAY, 1).and()
            .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
            .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1)
                .canBeMovedFrom(SUNDAY, MONDAY)
          .and()
            .hasFixedHoliday("NATIONAL_DAY", NOVEMBER, 19)
                .canBeMovedFrom(SUNDAY, MONDAY)
          .and()
            .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
            .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
                .canBeMovedFrom(SUNDAY, MONDAY)
          .and()
            .hasChristianHoliday("EASTER_MONDAY").and()
            .hasChristianHoliday("ASCENSION_DAY").and()
            .hasChristianHoliday("WHIT_MONDAY").and()
            .hasChristianHoliday("CORPUS_CHRISTI")
            .check();
    }
}
