package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.MAURITIUS;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVEN_YEARS;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.ODD_YEARS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayMUTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(MAURITIUS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2).validBetween(YEAR_FROM, YEAR_TO).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("PUBLIC_HOLIDAY", JANUARY, 3).validBetween(Year.of(2023), Year.of(2023)).and()

      // CHINESE_SPRING_FESTIVAL: lunar-calendar date, hardcoded per year
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", FEBRUARY, 10).validBetween(Year.of(2013), Year.of(2013)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", JANUARY, 31).validBetween(Year.of(2014), Year.of(2014)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", FEBRUARY, 19).validBetween(Year.of(2015), Year.of(2015)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", FEBRUARY, 8).validBetween(Year.of(2016), Year.of(2016)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", JANUARY, 28).validBetween(Year.of(2017), Year.of(2017)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", FEBRUARY, 16).validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", FEBRUARY, 5).validBetween(Year.of(2019), Year.of(2019)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", JANUARY, 25).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", FEBRUARY, 12).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", FEBRUARY, 1).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", JANUARY, 22).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", FEBRUARY, 10).validBetween(Year.of(2024), Year.of(2024)).and()

      // THAIPOOSAM_CAVEDEE: lunar-calendar date, hardcoded per year
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", JANUARY, 27).validBetween(Year.of(2013), Year.of(2013)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", JANUARY, 17).validBetween(Year.of(2014), Year.of(2014)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", FEBRUARY, 3).validBetween(Year.of(2015), Year.of(2015)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", JANUARY, 24).validBetween(Year.of(2016), Year.of(2016)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", FEBRUARY, 9).validBetween(Year.of(2017), Year.of(2017)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", JANUARY, 31).validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", JANUARY, 21).validBetween(Year.of(2019), Year.of(2019)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", FEBRUARY, 8).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", JANUARY, 28).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", JANUARY, 18).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", FEBRUARY, 4).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", JANUARY, 25).validBetween(Year.of(2024), Year.of(2024)).and()

      .hasFixedHoliday("ABOLITION_OF_SLAVERY", FEBRUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()

      // MAHA_SHIVRATREE: lunar-calendar date, hardcoded per year
      .hasFixedHoliday("MAHA_SHIVRATREE", MARCH, 10).validBetween(Year.of(2013), Year.of(2013)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", FEBRUARY, 27).validBetween(Year.of(2014), Year.of(2014)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", FEBRUARY, 17).validBetween(Year.of(2015), Year.of(2015)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", MARCH, 7).validBetween(Year.of(2016), Year.of(2016)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", FEBRUARY, 24).validBetween(Year.of(2017), Year.of(2017)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", FEBRUARY, 13).validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", MARCH, 4).validBetween(Year.of(2019), Year.of(2019)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", FEBRUARY, 21).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", MARCH, 11).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", MARCH, 1).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", FEBRUARY, 18).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", MARCH, 8).validBetween(Year.of(2024), Year.of(2024)).and()

      .hasFixedHoliday("NATIONAL_DAY", MARCH, 12).validTo(Year.of(2016)).and()
      .hasFixedHoliday("INDEPENDENCE_AND_REPUBLIC_DAY", MARCH, 12).validBetween(Year.of(2017), YEAR_TO).and()

      // UGAADI: lunar-calendar date, hardcoded per year
      .hasFixedHoliday("UGAADI", APRIL, 11).validBetween(Year.of(2013), Year.of(2013)).and()
      .hasFixedHoliday("UGAADI", MARCH, 31).validBetween(Year.of(2014), Year.of(2014)).and()
      .hasFixedHoliday("UGAADI", MARCH, 21).validBetween(Year.of(2015), Year.of(2015)).and()
      .hasFixedHoliday("UGAADI", APRIL, 8).validBetween(Year.of(2016), Year.of(2016)).and()
      .hasFixedHoliday("UGAADI", MARCH, 29).validBetween(Year.of(2017), Year.of(2017)).and()
      .hasFixedHoliday("UGAADI", MARCH, 18).validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("UGAADI", APRIL, 6).validBetween(Year.of(2019), Year.of(2019)).and()
      .hasFixedHoliday("UGAADI", MARCH, 25).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("UGAADI", APRIL, 13).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("UGAADI", APRIL, 2).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("UGAADI", MARCH, 22).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("UGAADI", APRIL, 9).validBetween(Year.of(2024), Year.of(2024)).and()

      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()

      // ASSUMPTION_BLESSED_VIRGIN_MARY only occurs every even year
      .hasFixedHoliday("ASSUMPTION_BLESSED_VIRGIN_MARY", AUGUST, 15)
        .every(EVEN_YEARS)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()

      // GANESH_CHATURTHI: lunar-calendar date, hardcoded per year
      .hasFixedHoliday("GANESH_CHATURTHI", SEPTEMBER, 10).validBetween(Year.of(2013), Year.of(2013)).and()
      .hasFixedHoliday("GANESH_CHATURTHI", AUGUST, 30).validBetween(Year.of(2014), Year.of(2014)).and()
      .hasFixedHoliday("GANESH_CHATURTHI", SEPTEMBER, 18).validBetween(Year.of(2015), Year.of(2015)).and()
      .hasFixedHoliday("GANESH_CHATURTHI", SEPTEMBER, 6).validBetween(Year.of(2016), Year.of(2016)).and()
      .hasFixedHoliday("GANESH_CHATURTHI", AUGUST, 26).validBetween(Year.of(2017), Year.of(2017)).and()
      .hasFixedHoliday("GANESH_CHATURTHI", SEPTEMBER, 14).validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("GANESH_CHATURTHI", SEPTEMBER, 3).validBetween(Year.of(2019), Year.of(2019)).and()
      .hasFixedHoliday("GANESH_CHATURTHI", AUGUST, 23).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("GANESH_CHATURTHI", SEPTEMBER, 11).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("GANESH_CHATURTHI", SEPTEMBER, 1).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("GANESH_CHATURTHI", SEPTEMBER, 20).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("GANESH_CHATURTHI", SEPTEMBER, 8).validBetween(Year.of(2024), Year.of(2024)).and()

      // DIVALI: lunar-calendar date, hardcoded per year
      .hasFixedHoliday("DIVALI", NOVEMBER, 3).validBetween(Year.of(2013), Year.of(2013)).and()
      .hasFixedHoliday("DIVALI", OCTOBER, 23).validBetween(Year.of(2014), Year.of(2014)).and()
      .hasFixedHoliday("DIVALI", NOVEMBER, 11).validBetween(Year.of(2015), Year.of(2015)).and()
      .hasFixedHoliday("DIVALI", OCTOBER, 30).validBetween(Year.of(2016), Year.of(2016)).and()
      .hasFixedHoliday("DIVALI", OCTOBER, 19).validBetween(Year.of(2017), Year.of(2017)).and()
      .hasFixedHoliday("DIVALI", NOVEMBER, 7).validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("DIVALI", OCTOBER, 27).validBetween(Year.of(2019), Year.of(2019)).and()
      .hasFixedHoliday("DIVALI", NOVEMBER, 14).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("DIVALI", NOVEMBER, 4).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("DIVALI", OCTOBER, 24).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("DIVALI", NOVEMBER, 12).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("DIVALI", OCTOBER, 31).validBetween(Year.of(2024), Year.of(2024)).and()

      // ALL_SAINTS only occurs every odd year
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1)
        .every(ODD_YEARS)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()

      .hasFixedHoliday("ARRIVAL_OF_INDENTURED_LABORERS", NOVEMBER, 2).validBetween(YEAR_FROM, YEAR_TO).validBetween(Year.of(2022), Year.of(2022)).and()

      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).validBetween(Year.of(2022), Year.of(2022)).and()

      // ID_AL_FITR: 5 overlapping Fixed-weekday/cycle entries in the XML (some typed ID_AL_FITR, some
      // ID_AL_FITR_2, an artifact of a historical day-shift quirk), but they collectively tile every
      // single year from 1900 onward with no gaps, so the observable behaviour is simply "every year"
      // (verified against the real HolidayManager output, not just derived from the XML by hand).
      .hasIslamicHoliday("ID_AL_FITR")
        .validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
