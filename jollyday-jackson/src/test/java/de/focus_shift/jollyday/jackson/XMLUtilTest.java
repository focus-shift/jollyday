package de.focus_shift.jollyday.jackson;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.focus_shift.jollyday.jackson.mapping.Configuration;
import de.focus_shift.jollyday.jackson.mapping.Fixed;
import de.focus_shift.jollyday.jackson.mapping.Holidays;
import de.focus_shift.jollyday.jackson.mapping.Month;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class XMLUtilTest {

  @ParameterizedTest
  @ValueSource(strings = {"Holidays_at.xml", "Holidays_de.xml", "Holidays_gb.xml", "Holidays_ua.xml", "Holidays_tr.xml", "Holidays_za.xml"})
  void unmarshalRealResource(String holidayFileName) {
    final XMLUtil xmlUtil = new XMLUtil();
    final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("holidays/" + holidayFileName);
    final Configuration configuration = xmlUtil.unmarshallConfiguration(inputStream);
    assertThat(configuration.getHolidays()).isNotNull();
  }

  @Test
  void remarshalExampleResource() throws Exception {

    final Configuration configuration = new Configuration();
    configuration.setHolidays(new Holidays());

    final Fixed fixed = new Fixed();
    fixed.setValidFrom(1234);
    fixed.setValidTo(4444);
    fixed.setDay(4);
    fixed.setMonth(Month.JANUARY);
    configuration.getHolidays().getFixed().add(fixed);

    final XmlMapper mapper = new XmlMapper();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
    final String xml = mapper.writeValueAsString(configuration);

    final XMLUtil xmlUtil = new XMLUtil();
    final Configuration newConfiguration = xmlUtil.unmarshallConfiguration(new ByteArrayInputStream(xml.getBytes()));
    assertThat(newConfiguration.getHolidays()).isNotNull();
    assertThat(newConfiguration.getHolidays().getFixed()).isNotEmpty();
  }
}
