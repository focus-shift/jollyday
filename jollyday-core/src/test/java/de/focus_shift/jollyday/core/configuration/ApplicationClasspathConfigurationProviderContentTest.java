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
                "parser.impl.de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration",
                "parser.impl.de.focus_shift.jollyday.core.spi.FixedWeekdayInMonthHolidayConfiguration",
                "parser.impl.de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration",
                "parser.impl.de.focus_shift.jollyday.core.spi.ChristianHolidayConfiguration",
                "parser.impl.de.focus_shift.jollyday.core.spi.RelativeToFixedHolidayConfiguration",
                "parser.impl.de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonthHolidayConfiguration",
                "parser.impl.de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixedHolidayConfiguration",
                "parser.impl.de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixedHolidayConfiguration",
                "parser.impl.de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHolidayConfiguration",
                "parser.impl.de.focus_shift.jollyday.core.spi.RelativeToEasterSundayHolidayConfiguration",
                "configuration.service.impl"
        );
    }
}
