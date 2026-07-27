package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ISRAEL;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;

class HolidayILTest {

  @Test
  void ensuresHolidays() {
    assertFor(ISRAEL)
      // Tishrei 1-2: Rosh Hashanah
      .hasHebrewHoliday("ROSH_HASHANA").validFrom(Year.of(1948)).and()
      .hasHebrewHoliday("ROSH_HASHANA_II").validFrom(Year.of(1948)).and()
      // Tishrei 10: Yom Kippur
      .hasHebrewHoliday("YOM_KIPPUR").validFrom(Year.of(1948)).and()
      // Tishrei 15: Sukkot
      .hasHebrewHoliday("SUKKOT").validFrom(Year.of(1948)).and()
      // Tishrei 22: Shemini Atzeret / Simchat Torah
      .hasHebrewHoliday("SHEMINI_ATZERET").validFrom(Year.of(1948)).and()
      // Nisan 15: Pesach (first Israeli occurrence 1949, since 1948's fell before independence)
      .hasHebrewHoliday("PESACH").validFrom(Year.of(1949)).and()
      // Nisan 21: Seventh day of Pesach
      .hasHebrewHoliday("PESACH_VII").validFrom(Year.of(1949)).and()
      // Sivan 6: Shavuot
      .hasHebrewHoliday("SHAVUOT").validFrom(Year.of(1948)).and()
      // Iyar 5: Yom Ha'atzmaut, first observed 1949
      .hasHebrewHoliday("YOM_HAATZMAUT").validFrom(Year.of(1949))
      .check();
  }
}
