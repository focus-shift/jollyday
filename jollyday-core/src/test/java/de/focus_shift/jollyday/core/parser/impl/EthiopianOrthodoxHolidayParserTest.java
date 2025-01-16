package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHoliday;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.Limited;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.List;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHoliday.EthiopianOrthodoxHolidayType.TIMKAT;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EthiopianOrthodoxHolidayParserTest {

  @Mock
  private Holidays holidays;

  @Nested
  class EthiopianOrthodoxHolidayTypeTests {

    @ParameterizedTest
    @EnumSource(EthiopianOrthodoxHoliday.EthiopianOrthodoxHolidayType.class)
    void ensureThatAllEthiopianOrthodoxHolidayTypesProvideAHoliday(final EthiopianOrthodoxHoliday.EthiopianOrthodoxHolidayType type) {

      final EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday = getEthiopianOrthodoxHoliday(type);

      final EthiopianOrthodoxHolidayParser sut = new EthiopianOrthodoxHolidayParser();
      when(holidays.ethiopianOrthodoxHolidays()).thenReturn(List.of(ethiopianOrthodoxHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo(type.name());
    }
  }

  @Nested
  class LimitedTests {

    @Test
    void ensureThatEthiopianOrthodoxHolidaysAreLimitedAndIsValid() {

      final EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday = getEthiopianOrthodoxHoliday(TIMKAT, Year.of(2022), Year.of(2022), EVERY_YEAR);

      final EthiopianOrthodoxHolidayParser sut = new EthiopianOrthodoxHolidayParser();
      when(holidays.ethiopianOrthodoxHolidays()).thenReturn(List.of(ethiopianOrthodoxHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo("TIMKAT");
    }

    @Test
    void ensureThatEthiopianOrthodoxHolidaysAreLimitedAndIsInvalid() {

      final EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday = getEthiopianOrthodoxHoliday(TIMKAT, Year.of(2023), Year.of(2023), EVERY_YEAR);

      final EthiopianOrthodoxHolidayParser sut = new EthiopianOrthodoxHolidayParser();
      when(holidays.ethiopianOrthodoxHolidays()).thenReturn(List.of(ethiopianOrthodoxHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  private static EthiopianOrthodoxHoliday getEthiopianOrthodoxHoliday(final EthiopianOrthodoxHoliday.EthiopianOrthodoxHolidayType type) {
    return getEthiopianOrthodoxHoliday(type, null, null, EVERY_YEAR);
  }

  private static EthiopianOrthodoxHoliday getEthiopianOrthodoxHoliday(final EthiopianOrthodoxHoliday.EthiopianOrthodoxHolidayType type, final Year validFrom, final Year validTo, final Limited.YearCycle yearCycle) {
    return new EthiopianOrthodoxHoliday() {

      @Override
      public String descriptionPropertiesKey() {
        return type.name();
      }

      @Override
      public HolidayType holidayType() {
        return PUBLIC_HOLIDAY;
      }

      @Override
      public EthiopianOrthodoxHolidayType type() {
        return type;
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
