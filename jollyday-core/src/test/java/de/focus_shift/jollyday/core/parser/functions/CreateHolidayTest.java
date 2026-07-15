package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Described;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static java.time.Month.APRIL;
import static org.assertj.core.api.Assertions.assertThat;

class CreateHolidayTest {

  @Test
  void ensuresToConvertDescribedToHoliday() {

    final Described described = new Described() {
      @Override
      public @NonNull String descriptionPropertiesKey() {
        return "propertiesKey";
      }

      @Override
      public @NonNull HolidayType holidayType() {
        return HolidayType.PUBLIC_HOLIDAY;
      }
    };

    final Holiday holiday = new CreateHoliday(LocalDate.of(2020, Month.APRIL, 1)).apply(described);

    assertThat(holiday.getDate()).hasYear(2020).hasMonth(APRIL).hasDayOfMonth(1);
    assertThat(holiday.getType()).isEqualTo(HolidayType.PUBLIC_HOLIDAY);
    assertThat(holiday.getPropertiesKey()).isEqualTo("propertiesKey");
  }

  @Test
  void ensuresGivenPropertiesKeyOverridesTheDescribedPropertiesKey() {

    final Described described = new Described() {
      @Override
      public @NonNull String descriptionPropertiesKey() {
        return "describedPropertiesKey";
      }

      @Override
      public @NonNull HolidayType holidayType() {
        return HolidayType.PUBLIC_HOLIDAY;
      }
    };

    final LocalDate actualDate = LocalDate.of(2020, Month.APRIL, 1);
    final LocalDate observedDate = LocalDate.of(2020, Month.APRIL, 3);

    final Holiday holiday = new CreateHoliday(actualDate, observedDate, "overriddenPropertiesKey").apply(described);

    assertThat(holiday.getPropertiesKey()).isEqualTo("overriddenPropertiesKey");
    assertThat(holiday.getDate()).isEqualTo(observedDate);
  }
}
