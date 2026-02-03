package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import static java.time.Month.FEBRUARY;
import static org.assertj.core.api.Assertions.assertThat;

class FixedToLocalDateTest {

  @Test
  void ensureToConvertFixedToLocalDate() {

    final FixedHolidayConfiguration fixed = new FixedHolidayConfiguration() {
      @Override
      public List<MovingCondition> conditions() {
        return null;
      }

      @Override
      public Year validFrom() {
        return null;
      }

      @Override
      public Year validTo() {
        return null;
      }

      @Override
      public YearCycle cycle() {
        return null;
      }

      @Override
      public MonthDay day() {
        return MonthDay.of(2, 28);
      }

      @Override
      public String descriptionPropertiesKey() {
        return null;
      }

      @Override
      public HolidayType holidayType() {
        return null;
      }
    };

    final LocalDate localDate = new FixedToLocalDate(Year.of(2024)).apply(fixed);
    assertThat(localDate)
      .hasYear(2024)
      .hasMonth(FEBRUARY)
      .hasDayOfMonth(28);
  }
}
