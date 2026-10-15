package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.MAURITIUS;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVEN_YEARS;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.ODD_YEARS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class HolidayMUTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(MAURITIUS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2).validBetween(YEAR_FROM, Year.of(2026)).validBetween(Year.of(2023), Year.of(2023)).and()
      // Public Holidays Act, s. 3(3) (Act No. 13 of 2026, in force 13 August 2026): Sunday holiday -> following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2).validBetween(Year.of(2027), YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()
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
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", JANUARY, 29).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", FEBRUARY, 17).validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("CHINESE_SPRING_FESTIVAL", FEBRUARY, 6).validBetween(Year.of(2027), Year.of(2027)).canBeMovedFrom(SUNDAY, MONDAY).and()

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
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", FEBRUARY, 11).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", FEBRUARY, 1).validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("THAIPOOSAM_CAVEDEE", JANUARY, 22).validBetween(Year.of(2027), Year.of(2027)).canBeMovedFrom(SUNDAY, MONDAY).and()

      .hasFixedHoliday("ABOLITION_OF_SLAVERY", FEBRUARY, 1).validBetween(YEAR_FROM, Year.of(2026)).and()
      .hasFixedHoliday("ABOLITION_OF_SLAVERY", FEBRUARY, 1).validBetween(Year.of(2027), YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()

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
      .hasFixedHoliday("MAHA_SHIVRATREE", FEBRUARY, 26).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", FEBRUARY, 15).validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("MAHA_SHIVRATREE", MARCH, 6).validBetween(Year.of(2027), Year.of(2027)).canBeMovedFrom(SUNDAY, MONDAY).and()

      .hasFixedHoliday("NATIONAL_DAY", MARCH, 12).validTo(Year.of(2016)).and()
      .hasFixedHoliday("INDEPENDENCE_AND_REPUBLIC_DAY", MARCH, 12).validBetween(Year.of(2017), Year.of(2026)).and()
      .hasFixedHoliday("INDEPENDENCE_AND_REPUBLIC_DAY", MARCH, 12).validBetween(Year.of(2027), YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()

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
      .hasFixedHoliday("UGAADI", MARCH, 30).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("UGAADI", MARCH, 19).validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("UGAADI", APRIL, 7).validBetween(Year.of(2027), Year.of(2027)).canBeMovedFrom(SUNDAY, MONDAY).and()

      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, Year.of(2026)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(Year.of(2027), YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()

      // ASSUMPTION_BLESSED_VIRGIN_MARY only occurs every even year
      .hasFixedHoliday("ASSUMPTION_BLESSED_VIRGIN_MARY", AUGUST, 15)
        .every(EVEN_YEARS)
        .validBetween(YEAR_FROM, Year.of(2025))
      .and()
      .hasFixedHoliday("ASSUMPTION_BLESSED_VIRGIN_MARY", AUGUST, 15)
        .every(EVEN_YEARS)
        .validBetween(Year.of(2026), YEAR_TO)
        .canBeMovedFrom(SUNDAY, MONDAY)
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
      .hasFixedHoliday("GANESH_CHATURTHI", AUGUST, 28).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("GANESH_CHATURTHI", SEPTEMBER, 15).validBetween(Year.of(2026), Year.of(2026)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("GANESH_CHATURTHI", SEPTEMBER, 5).validBetween(Year.of(2027), Year.of(2027)).canBeMovedFrom(SUNDAY, MONDAY).and()

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
      .hasFixedHoliday("DIVALI", OCTOBER, 20).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("DIVALI", NOVEMBER, 8).validBetween(Year.of(2026), Year.of(2026)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DIVALI", OCTOBER, 29).validBetween(Year.of(2027), Year.of(2027)).canBeMovedFrom(SUNDAY, MONDAY).and()

      // ALL_SAINTS only occurs every odd year
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1)
        .every(ODD_YEARS)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()

      .hasFixedHoliday("ARRIVAL_OF_INDENTURED_LABORERS", NOVEMBER, 2).validBetween(YEAR_FROM, Year.of(2025)).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("ARRIVAL_OF_INDENTURED_LABORERS", NOVEMBER, 2).validBetween(Year.of(2026), YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()

      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, Year.of(2025)).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(Year.of(2026), YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()

      // ID_AL_FITR: 5 overlapping Fixed-weekday/cycle entries in the XML (some typed ID_AL_FITR, some
      // ID_AL_FITR_2, an artifact of a historical day-shift quirk), but they collectively tile every
      // single year from 1900 onward with no gaps, so the observable behaviour is simply "every year"
      // (verified against the real HolidayManager output, not just derived from the XML by hand).
      .hasIslamicHoliday("ID_AL_FITR")
        .validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }

  /**
   * General Notice No. 989 of 2024, https://pmo.govmu.org/Communique/Notice-Public_Holidays_2025.pdf
   */
  @Test
  void ensuresPublicHolidays2025() {
    final Set<Holiday> holidays = HolidayManager.getInstance(create(MAURITIUS)).getHolidays(Year.of(2025));
    assertThat(holidays)
      .extracting(Holiday::getDate)
      .containsExactlyInAnyOrder(
        LocalDate.of(2025, JANUARY, 1),
        LocalDate.of(2025, JANUARY, 2),
        LocalDate.of(2025, JANUARY, 29),
        LocalDate.of(2025, FEBRUARY, 1),
        LocalDate.of(2025, FEBRUARY, 11),
        LocalDate.of(2025, FEBRUARY, 26),
        LocalDate.of(2025, MARCH, 12),
        LocalDate.of(2025, MARCH, 30),
        LocalDate.of(2025, MARCH, 31),
        LocalDate.of(2025, MAY, 1),
        LocalDate.of(2025, AUGUST, 28),
        LocalDate.of(2025, OCTOBER, 20),
        LocalDate.of(2025, NOVEMBER, 1),
        LocalDate.of(2025, NOVEMBER, 2),
        LocalDate.of(2025, DECEMBER, 25)
      );
  }

  /**
   * General Notice No. 1195 of 2025 as amended by General Notice No. 611 of 2026
   * (https://pmo.govmu.org/Communique/GN_No._611-Amendment_to_Public_Holidays_2026.pdf), plus Monday 9 November 2026
   * for Divali on Sunday 8 November 2026 under s. 3(3) of the Public Holidays Act (in force 13 August 2026).
   * Abolition of Slavery and Thaipoosam Cavadee are both on Sunday 1 February 2026, before s. 3(3) came into force.
   */
  @Test
  void ensuresPublicHolidays2026() {
    final Set<Holiday> holidays = HolidayManager.getInstance(create(MAURITIUS)).getHolidays(Year.of(2026));
    assertThat(holidays)
      .extracting(Holiday::getDate)
      .containsExactlyInAnyOrder(
        LocalDate.of(2026, JANUARY, 1),
        LocalDate.of(2026, JANUARY, 2),
        LocalDate.of(2026, FEBRUARY, 1),
        LocalDate.of(2026, FEBRUARY, 1),
        LocalDate.of(2026, FEBRUARY, 15),
        LocalDate.of(2026, FEBRUARY, 17),
        LocalDate.of(2026, MARCH, 12),
        LocalDate.of(2026, MARCH, 19),
        LocalDate.of(2026, MARCH, 21),
        LocalDate.of(2026, MAY, 1),
        LocalDate.of(2026, AUGUST, 15),
        LocalDate.of(2026, SEPTEMBER, 15),
        LocalDate.of(2026, NOVEMBER, 2),
        LocalDate.of(2026, NOVEMBER, 9),
        LocalDate.of(2026, DECEMBER, 25)
      );
    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("DIVALI"))
      .extracting(Holiday::getActualDate, Holiday::getDate)
      .containsExactly(tuple(LocalDate.of(2026, NOVEMBER, 8), LocalDate.of(2026, NOVEMBER, 9)));
  }

  /**
   * List of public holidays 2027 as taken note of by Cabinet on 28 August 2026
   * (https://pmo.govmu.org/CabinetDecision/2026/Highlights%20of%20Cabinet%20Meeting%20Friday%2028%20August%20%202026.pdf):
   * Ganesh Chaturthi on Sunday 5 September 2027, with Monday 6 September 2027 as public holiday under s. 3(3).
   */
  @Test
  void ensuresPublicHolidays2027() {
    final Set<Holiday> holidays = HolidayManager.getInstance(create(MAURITIUS)).getHolidays(Year.of(2027));
    assertThat(holidays)
      .extracting(Holiday::getDate)
      .containsExactlyInAnyOrder(
        LocalDate.of(2027, JANUARY, 1),
        LocalDate.of(2027, JANUARY, 2),
        LocalDate.of(2027, JANUARY, 22),
        LocalDate.of(2027, FEBRUARY, 1),
        LocalDate.of(2027, FEBRUARY, 6),
        LocalDate.of(2027, MARCH, 6),
        LocalDate.of(2027, MARCH, 10),
        LocalDate.of(2027, MARCH, 12),
        LocalDate.of(2027, APRIL, 7),
        LocalDate.of(2027, MAY, 1),
        LocalDate.of(2027, SEPTEMBER, 6),
        LocalDate.of(2027, OCTOBER, 29),
        LocalDate.of(2027, NOVEMBER, 1),
        LocalDate.of(2027, NOVEMBER, 2),
        LocalDate.of(2027, DECEMBER, 25)
      );
    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("GANESH_CHATURTHI"))
      .extracting(Holiday::getActualDate, Holiday::getDate)
      .containsExactly(tuple(LocalDate.of(2027, SEPTEMBER, 5), LocalDate.of(2027, SEPTEMBER, 6)));
  }

  /**
   * s. 3(3) is not applied to 1 January and 1 November, because the following day is a public holiday anyway.
   */
  @Test
  void ensuresNoSundayMoveOntoTheFollowingPublicHoliday() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MAURITIUS));
    // 1 January 2034 is a Sunday, 2 January 2034 a Monday
    assertThat(holidayManager.getHolidays(Year.of(2034)))
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("NEW_YEAR"))
      .extracting(Holiday::getDate)
      .containsExactlyInAnyOrder(LocalDate.of(2034, JANUARY, 1), LocalDate.of(2034, JANUARY, 2));
    // 1 November 2037 is a Sunday, 2 November 2037 a Monday
    assertThat(holidayManager.getHolidays(Year.of(2037)))
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("ALL_SAINTS"))
      .extracting(Holiday::getDate)
      .containsExactly(LocalDate.of(2037, NOVEMBER, 1));
  }
}
