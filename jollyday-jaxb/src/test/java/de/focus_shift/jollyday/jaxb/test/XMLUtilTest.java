package de.focus_shift.jollyday.jaxb.test;

import de.focus_shift.jollyday.jaxb.JaxbXMLMapper;
import de.focus_shift.jollyday.jaxb.mapping.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class XMLUtilTest {

  private final JaxbXMLMapper sut = new JaxbXMLMapper();

  @Test
  void testUnmarshallConfigurationNullCheck() {
    assertThatThrownBy(() -> sut.unmarshallConfiguration(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {"Holidays_at.xml", "Holidays_de.xml", "Holidays_gb.xml", "Holidays_ua.xml", "Holidays_tr.xml", "Holidays_za.xml"})
  void unmarshalRealResource(String holidayFileName) {
    final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("holidays/" + holidayFileName);
    final Configuration configuration = sut.unmarshallConfiguration(inputStream);
    assertThat(configuration.getHolidays()).isNotNull();
  }
}
