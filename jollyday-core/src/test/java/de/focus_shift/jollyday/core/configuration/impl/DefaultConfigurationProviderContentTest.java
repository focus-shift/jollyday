package de.focus_shift.jollyday.core.configuration.impl;

import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultConfigurationProviderContentTest {

  private static final Set<String> KEYS_DEFAULT_CONFIG = Set.of(
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

  private final DefaultConfigurationProvider sut = new DefaultConfigurationProvider();

  @Test
  void testPutConfiguration() {
    final Properties p = sut.getProperties();
    assertThat(p).containsOnlyKeys(KEYS_DEFAULT_CONFIG);
  }
}
