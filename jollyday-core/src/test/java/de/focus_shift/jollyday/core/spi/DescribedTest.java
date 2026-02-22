package de.focus_shift.jollyday.core.spi;

import de.focus_shift.jollyday.core.HolidayType;
import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static org.assertj.core.api.Assertions.assertThat;

class DescribedTest {

  // Test implementation of the interface
  static class TestHoliday implements Described {

    private final String key;
    private final HolidayType type;

    TestHoliday(String key, HolidayType type) {
      this.key = key;
      this.type = type;
    }

    @Override
    public String descriptionPropertiesKey() {
      return key;
    }

    @Override
    public HolidayType holidayType() {
      return type;
    }
  }

  @Test
  void shouldReturnCorrectDescriptionPropertiesKey() {
    final Described holiday = new TestHoliday("holiday.christmas", PUBLIC_HOLIDAY);
    assertThat(holiday.descriptionPropertiesKey())
      .isEqualTo("holiday.christmas");
  }

  @Test
  void shouldReturnCorrectHolidayType() {
    final Described holiday = new TestHoliday("holiday.christmas", PUBLIC_HOLIDAY);
    assertThat(holiday.holidayType())
      .isEqualTo(PUBLIC_HOLIDAY);
  }

  @Test
  void shouldReturnDefaultDescriptionPropertiesKeyPrefix() {
    final Described holiday = new TestHoliday("holiday.christmas", PUBLIC_HOLIDAY);
    assertThat(holiday.descriptionPropertiesKeyPrefix())
      .isEmpty();
  }

  @Test
  void shouldReturnDefaultDescriptionPropertiesKeyPrefixSeparator() {
    final Described holiday = new TestHoliday("holiday.christmas", PUBLIC_HOLIDAY);
    assertThat(holiday.descriptionPropertiesKeyPrefixSeparator())
      .isEqualTo(".");
  }
}
