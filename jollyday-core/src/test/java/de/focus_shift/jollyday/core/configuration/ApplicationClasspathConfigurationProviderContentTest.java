package de.focus_shift.jollyday.core.configuration;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationClasspathConfigurationProviderContentTest {

    @Test
    void ensureToOnlyContainKnownKeys() {
        final ApplicationClasspathConfigurationProvider sut = new ApplicationClasspathConfigurationProvider();
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
                "parser.impl.de.focus_shift.jollyday.core.spi.RelativeToEasterSunday",
                "configuration.service.impl"
        );
    }
}
