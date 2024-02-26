package de.focus_shift.jollyday.core.configuration;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.entry;

class ClasspathConfigurationProviderContentTest {

  @Test
  void ensureToOnlyContainKnownKeys() {
    final ClasspathConfigurationProvider sut = new ClasspathConfigurationProvider();
    final Properties properties = sut.getProperties();
    assertThat(properties).containsOnlyKeys(
      "manager.impl",
      "manager.impl.jp",
      "parser.impl.de.focus_shift.jollyday.core.spi.Fixed",
      "parser.impl.de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth",
      "parser.impl.de.focus_shift.jollyday.core.spi.IslamicHoliday",
      "parser.impl.de.focus_shift.jollyday.core.spi.ChristianHoliday",
      "parser.impl.de.focus_shift.jollyday.core.spi.RelativeToFixed",
      "parser.impl.de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonth",
      "parser.impl.de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixed",
      "parser.impl.de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixed",
      "parser.impl.de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHoliday",
      "parser.impl.de.focus_shift.jollyday.core.spi.RelativeToEasterSunday"
    );
  }

  @Test
  void ensureToConfigureDifferentConfigurationFile() {
    final ClasspathConfigurationProvider sut = new ClasspathConfigurationProvider();
    sut.overrideConfigurationFileName("alternative-jollyday.properties");
    final Properties properties = sut.getProperties();
    assertThat(properties).contains(entry("manager.impl", "de.focus_shift.jollyday.core.impl.JapaneseHolidayManager"));
  }

  @Test
  void ensureToThrowIllegalStateExceptionIfConfigurationFileNotFoundOnClasspath() {
    final ClasspathConfigurationProvider sut = new ClasspathConfigurationProvider();

    assertThatThrownBy(() -> sut.overrideConfigurationFileName("notAvailableFileName.properties"))
      .isInstanceOf(IllegalStateException.class)
      .hasMessageContaining("Configuration file 'notAvailableFileName.properties' not found on classpath.");
  }
}
