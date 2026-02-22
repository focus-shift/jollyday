package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import de.focus_shift.jollyday.core.spi.RelativeToEasterSundayHolidayConfiguration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.threeten.extra.Days;
import org.threeten.extra.chrono.JulianChronology;

import java.time.LocalDate;
import java.time.Year;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.util.List;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static java.time.Month.APRIL;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RelativeToEasterSundayParserTest {

  @Mock
  private HolidayConfigurations holidays;

  @Test
  void ensureThatRelativeToEasterSundayWithGregorianChronologyIsValid() {

    final Year year = Year.of(1400);
    final RelativeToEasterSundayHolidayConfiguration relativeToEasterSunday = getRelativeToEasterSunday(Days.of(24), IsoChronology.INSTANCE, year, year);

    final RelativeToEasterSundayParser sut = new RelativeToEasterSundayParser();
    when(holidays.relativeToEasterSunday()).thenReturn(List.of(relativeToEasterSunday));

    final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
    assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(1400, MAY, 14));
  }

  @Test
  void ensureThatRelativeToEasterSundayWithJulianChronologyIsValid() {

    final Year year = Year.of(1400);
    final RelativeToEasterSundayHolidayConfiguration relativeToEasterSunday = getRelativeToEasterSunday(Days.of(24), JulianChronology.INSTANCE, year, year);

    final RelativeToEasterSundayParser sut = new RelativeToEasterSundayParser();
    when(holidays.relativeToEasterSunday()).thenReturn(List.of(relativeToEasterSunday));

    final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
    assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(1400, MAY, 21));
  }

  @Nested
  class LimitedTests {

    @Test
    void ensureThatRelativeToEasterSundayAreLimitedAndIsValid() {

      final Year year = Year.of(2025);
      final RelativeToEasterSundayHolidayConfiguration relativeToEasterSunday = getRelativeToEasterSunday(Days.of(2), IsoChronology.INSTANCE, year, year);

      final RelativeToEasterSundayParser sut = new RelativeToEasterSundayParser();
      when(holidays.relativeToEasterSunday()).thenReturn(List.of(relativeToEasterSunday));

      final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2025, APRIL, 22));
    }

    @Test
    void ensureThatRelativeToEasterSundayAreLimitedAndIsInvalid() {

      final RelativeToEasterSundayHolidayConfiguration relativeToEasterSunday = getRelativeToEasterSunday(Days.of(2), IsoChronology.INSTANCE, Year.of(2023), Year.of(2023));

      final RelativeToEasterSundayParser sut = new RelativeToEasterSundayParser();
      when(holidays.relativeToEasterSunday()).thenReturn(List.of(relativeToEasterSunday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  private static RelativeToEasterSundayHolidayConfiguration getRelativeToEasterSunday(
    final Days days,
    final Chronology chronology,
    final Year validFrom,
    final Year validTo
  ) {
    return new RelativeToEasterSundayHolidayConfiguration() {

      @Override
      public Chronology chronology() {
        return chronology;
      }

      @Override
      public Days days() {
        return days;
      }

      @Override
      public String descriptionPropertiesKey() {
        return "";
      }

      @Override
      public HolidayType holidayType() {
        return PUBLIC_HOLIDAY;
      }

      @Override
      public Year validFrom() {
        return validFrom;
      }

      @Override
      public Year validTo() {
        return validTo;
      }

      @Override
      public YearCycle cycle() {
        return EVERY_YEAR;
      }
    };
  }
}
