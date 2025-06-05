package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.jaxb.mapping.Configuration;
import de.focus_shift.jollyday.jaxb.mapping.Holidays;
import de.focus_shift.jollyday.jaxb.mapping.Sources;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JaxbConfigurationTest {

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

        final JaxbConfiguration sut = new JaxbConfiguration(configuration);
        assertThat(sut.holidays()).isNotNull();
        assertThat(sut.subConfigurations().findFirst())
                .hasValueSatisfying(subConf -> assertThat(subConf.hierarchy()).isEqualTo("subHierarchy"));
        assertThat(sut.hierarchy()).isEqualTo("hierarchy");
        assertThat(sut.description()).isEqualTo("description");
    }
}
