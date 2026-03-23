package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import static java.time.Month.FEBRUARY;
import static org.assertj.core.api.Assertions.assertThat;

class FixedToLocalDateTest {

  @Test
  void ensureToConvertFixedToLocalDate() {

    final FixedHolidayConfiguration fixed = new FixedHolidayConfiguration() {
      @Override
      public @NonNull List<MovingCondition> conditions() {
        return null;
      }

      @Override
      public @NonNull Optional<Year> validFrom() {
        return Optional.empty();
      }

      @Override
      public @NonNull Optional<Year> validTo() {
        return Optional.empty();
      }

      @Override
      public @NonNull YearCycle cycle() {
        return null;
      }

      @Override
      public @NonNull MonthDay day() {
        return MonthDay.of(2, 28);
      }

      @Override
      public @NonNull String descriptionPropertiesKey() {
        return null;
      }

      @Override
      public @NonNull HolidayType holidayType() {
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
