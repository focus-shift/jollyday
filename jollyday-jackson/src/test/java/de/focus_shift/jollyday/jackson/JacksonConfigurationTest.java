package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.jackson.mapping.Configuration;
import de.focus_shift.jollyday.jackson.mapping.Holidays;
import de.focus_shift.jollyday.jackson.mapping.Sources;
import de.focus_shift.jollyday.jackson.mapping.Weekday;
import de.focus_shift.jollyday.jackson.mapping.Weekend;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonConfigurationTest {

    @Test
    void ensuresCorrectMapping() {

        final Configuration configuration = new Configuration();
        configuration.setDescription("description");
        configuration.setHierarchy("hierarchy");

        final Configuration subConfiguration = new Configuration();
        subConfiguration.setHierarchy("subHierarchy");
        configuration.getSubConfigurations().add(subConfiguration);

        final Holidays holidays = new Holidays();
        configuration.setHolidays(holidays);

        final Sources sources = new Sources();
        configuration.setSources(sources);

        final Weekend weekend = new Weekend();
        weekend.setWeekday(Weekday.FRIDAY);
        configuration.getWeekend().add(weekend);

        final JacksonConfiguration sut = new JacksonConfiguration(configuration);
        assertThat(sut.holidays()).isNotNull();
        assertThat(sut.subConfigurations().findFirst())
                .hasValueSatisfying(subConf -> assertThat(subConf.hierarchy()).isEqualTo("subHierarchy"));
        assertThat(sut.weekends().findFirst())
                .hasValueSatisfying(weekendConf -> assertThat(weekendConf.weekday()).isEqualTo(DayOfWeek.FRIDAY));
        assertThat(sut.hierarchy()).isEqualTo("hierarchy");
        assertThat(sut.description()).isEqualTo("description");
    }
}
