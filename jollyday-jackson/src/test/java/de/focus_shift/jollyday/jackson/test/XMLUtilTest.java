package de.focus_shift.jollyday.jackson.test;

import de.focus_shift.jollyday.jackson.JacksonXMLMapper;
import de.focus_shift.jollyday.jackson.mapping.Configuration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class XMLUtilTest {

  @ParameterizedTest
  @ValueSource(strings = {"Holidays_at.xml", "Holidays_de.xml", "Holidays_gb.xml", "Holidays_ua.xml", "Holidays_tr.xml", "Holidays_za.xml"})
  void unmarshalRealResource(String holidayFileName) {
    final JacksonXMLMapper sut = new JacksonXMLMapper();
    final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("holidays/" + holidayFileName);
    final Configuration configuration = sut.unmarshallConfiguration(inputStream);
    assertThat(configuration.getHolidays()).isNotNull();
  }
}
