package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Described;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.Month.APRIL;
import static org.assertj.core.api.Assertions.assertThat;

class CreateHolidayTest {

  @Test
  void ensuresToConvertDescribedToHoliday() {

    final Described described = new Described() {
      @Override
      public String descriptionPropertiesKey() {
        return "propertiesKey";
      }

      @Override
      public HolidayType holidayType() {
        return HolidayType.PUBLIC_HOLIDAY;
      }
    };

    final Holiday holiday = new CreateHoliday(LocalDate.of(2020, 4, 1)).apply(described);

    assertThat(holiday.getDate()).hasYear(2020).hasMonth(APRIL).hasDayOfMonth(1);
    assertThat(holiday.getType()).isEqualTo(HolidayType.PUBLIC_HOLIDAY);
    assertThat(holiday.getPropertiesKey()).isEqualTo("propertiesKey");
  }
}
