package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.MONGOLIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayMNTest {

  @Test
  void ensuresHolidays() {
    assertFor(MONGOLIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).and()
      .hasFixedHoliday("CHILDRENS_DAY", JUNE, 1).and()
      .hasFixedHoliday("NAADAM", JULY, 10).validFrom(Year.of(2022)).notValidBetween(Year.of(1900), Year.of(2021)).and()
      .hasFixedHoliday("NAADAM", JULY, 11).and()
      .hasFixedHoliday("NAADAM", JULY, 12).and()
      .hasFixedHoliday("NAADAM", JULY, 13).and()
      .hasFixedHoliday("NAADAM", JULY, 14).and()
      .hasFixedHoliday("NAADAM", JULY, 15).and()
      .hasFixedHoliday("REPUBLIC_DAY", NOVEMBER, 26)
        .validTo(Year.of(2011))
        .validFrom(Year.of(2016))
        .notValidBetween(Year.of(2012), Year.of(2015))
      .and()
      .hasFixedHoliday("RESTORATION_OF_INDEPENDENCE", DECEMBER, 29).validFrom(Year.of(2011)).notValidBetween(Year.of(1900), Year.of(2010))
      .check();
  }

  @Test
  void ensuresLunarHolidays() {
    assertFor(MONGOLIA)
      // Tsagaan Sar
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 24).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("SECOND_DAY_OF_LUNAR_NEW_YEAR", FEBRUARY, 25).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("THIRD_DAY_OF_LUNAR_NEW_YEAR", FEBRUARY, 26).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 12).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("SECOND_DAY_OF_LUNAR_NEW_YEAR", FEBRUARY, 13).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("THIRD_DAY_OF_LUNAR_NEW_YEAR", FEBRUARY, 14).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 2).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("SECOND_DAY_OF_LUNAR_NEW_YEAR", FEBRUARY, 3).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("THIRD_DAY_OF_LUNAR_NEW_YEAR", FEBRUARY, 4).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 21).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("SECOND_DAY_OF_LUNAR_NEW_YEAR", FEBRUARY, 22).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("THIRD_DAY_OF_LUNAR_NEW_YEAR", FEBRUARY, 23).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 10).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("SECOND_DAY_OF_LUNAR_NEW_YEAR", FEBRUARY, 11).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("THIRD_DAY_OF_LUNAR_NEW_YEAR", FEBRUARY, 12).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", MARCH, 1).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("SECOND_DAY_OF_LUNAR_NEW_YEAR", MARCH, 2).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("THIRD_DAY_OF_LUNAR_NEW_YEAR", MARCH, 3).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 18).validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("SECOND_DAY_OF_LUNAR_NEW_YEAR", FEBRUARY, 19).validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("THIRD_DAY_OF_LUNAR_NEW_YEAR", FEBRUARY, 20).validBetween(Year.of(2026), Year.of(2026)).and()
      // Buddha's Day
      .hasFixedHoliday("VESAK_DAY", JUNE, 5).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("VESAK_DAY", MAY, 26).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("VESAK_DAY", JUNE, 14).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("VESAK_DAY", JUNE, 4).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("VESAK_DAY", MAY, 23).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("VESAK_DAY", JUNE, 11).validBetween(Year.of(2025), Year.of(2025)).and()
      // Chinggis Khaan's Day
      .hasFixedHoliday("CHINGGIS_KHAAN_DAY", NOVEMBER, 16).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("CHINGGIS_KHAAN_DAY", NOVEMBER, 5).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("CHINGGIS_KHAAN_DAY", NOVEMBER, 24).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("CHINGGIS_KHAAN_DAY", NOVEMBER, 14).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("CHINGGIS_KHAAN_DAY", NOVEMBER, 2).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("CHINGGIS_KHAAN_DAY", NOVEMBER, 21).validBetween(Year.of(2025), Year.of(2025))
      .check();
  }
}
