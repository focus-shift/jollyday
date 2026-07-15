package de.focus_shift.jollyday.core.util;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static de.focus_shift.jollyday.core.util.ResourceUtil.UNDEFINED;
import static org.assertj.core.api.Assertions.assertThat;

class ResourceUtilTest {

  @Test
  void ensureGetCountryDescriptionReturnsUndefinedWhenKeyIsNull() {
    assertThat(ResourceUtil.getCountryDescription(Locale.ENGLISH, null)).isEqualTo(UNDEFINED);
  }

  @Test
  void ensureGetCountryDescriptionWithDefaultLocale() {
    Locale.setDefault(Locale.ENGLISH);
    assertThat(ResourceUtil.getCountryDescription("de")).isEqualTo("Germany");
  }

  @Test
  void ensureGetHolidayDescriptionWithDefaultLocale() {
    Locale.setDefault(Locale.ENGLISH);
    assertThat(ResourceUtil.getHolidayDescription("CHRISTMAS")).isEqualTo("Christmas");
  }
}
