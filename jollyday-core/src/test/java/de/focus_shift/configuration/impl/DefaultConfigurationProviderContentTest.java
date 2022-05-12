package de.focus_shift.configuration.impl;

import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultConfigurationProviderContentTest {

  private static final Set<String> KEYS_DEFAULT_CONFIG = Set.of(
    "manager.impl",
    "manager.impl.jp",
    "configuration.datasource.impl",
    "parser.impl.de.focus_shift.spi.Fixed",
    "parser.impl.de.focus_shift.spi.FixedWeekdayInMonth",
    "parser.impl.de.focus_shift.spi.IslamicHoliday",
    "parser.impl.de.focus_shift.spi.ChristianHoliday",
    "parser.impl.de.focus_shift.spi.RelativeToFixed",
    "parser.impl.de.focus_shift.spi.RelativeToWeekdayInMonth",
    "parser.impl.de.focus_shift.spi.FixedWeekdayBetweenFixed",
    "parser.impl.de.focus_shift.spi.FixedWeekdayRelativeToFixed",
    "parser.impl.de.focus_shift.spi.EthiopianOrthodoxHoliday",
    "parser.impl.de.focus_shift.spi.RelativeToEasterSunday"
  );

  private final DefaultConfigurationProvider sut = new DefaultConfigurationProvider();

  @Test
  void testPutConfiguration() {
    final Properties p = sut.getProperties();
    assertThat(p).containsOnlyKeys(KEYS_DEFAULT_CONFIG);
  }
}
