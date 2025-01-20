package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.RelativeToEasterSunday;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.threeten.extra.Days;

import java.time.LocalDate;
import java.time.Year;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.util.List;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static java.time.Month.APRIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RelativeToEasterSundayParserTest {

  @Mock
  private Holidays holidays;

  @Nested
  class LimitedTests {

    @Test
    void ensureThatRelativeToEasterSundayAreLimitedAndIsValid() {

      final Year year = Year.of(2025);
      final RelativeToEasterSunday relativeToEasterSunday = getRelativeToEasterSunday(Days.of(2), IsoChronology.INSTANCE, year, year, EVERY_YEAR);

      final RelativeToEasterSundayParser sut = new RelativeToEasterSundayParser();
      when(holidays.relativeToEasterSunday()).thenReturn(List.of(relativeToEasterSunday));

      final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2025, APRIL, 22));
    }

    @Test
    void ensureThatRelativeToEasterSundayAreLimitedAndIsInvalid() {

      final RelativeToEasterSunday relativeToEasterSunday = getRelativeToEasterSunday(Days.of(2), IsoChronology.INSTANCE, Year.of(2023), Year.of(2023), EVERY_YEAR);

      final RelativeToEasterSundayParser sut = new RelativeToEasterSundayParser();
      when(holidays.relativeToEasterSunday()).thenReturn(List.of(relativeToEasterSunday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  private static RelativeToEasterSunday getRelativeToEasterSunday(
    final Days days,
    final Chronology chronology,
    final Year validFrom,
    final Year validTo,
    final Limited.YearCycle yearCycle
  ) {
    return new RelativeToEasterSunday() {

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
        return yearCycle;
      }
    };
  }
}
