package de.focus_shift.jollyday.jackson;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import de.focus_shift.jollyday.jackson.mapping.Configuration;
import de.focus_shift.jollyday.jackson.mapping.Fixed;
import de.focus_shift.jollyday.jackson.mapping.Holidays;
import de.focus_shift.jollyday.jackson.mapping.Month;

class XMLUtilTest {

  @ParameterizedTest
  @ValueSource(strings = { "Holidays_at.xml", "Holidays_de.xml", "Holidays_gb.xml", "Holidays_ua.xml", "Holidays_tr.xml", "Holidays_za.xml" })
  void unmarshalRealResource(String holidayFileName) {
    XMLUtil xmlUtil = new XMLUtil();
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("holidays/" + holidayFileName);
    Configuration configuration = xmlUtil.unmarshallConfiguration(inputStream);
    Assertions.assertThat(configuration.getHolidays()).isNotNull();
  }

  @Test
  void remarshalExampleResource() throws Exception {

    Configuration configuration = new Configuration();
    configuration.setHolidays(new Holidays());

    Fixed fixed = new Fixed();
    fixed.setValidFrom(1234);
    fixed.setValidTo(4444);
    fixed.setDay(4);
    fixed.setMonth(Month.JANUARY);
    configuration.getHolidays().getFixed().add(fixed);

    XmlMapper mapper = new XmlMapper();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
    String xml = mapper.writeValueAsString(configuration);

    XMLUtil xmlUtil = new XMLUtil();
    Configuration newConfiguration = xmlUtil.unmarshallConfiguration(new ByteArrayInputStream(xml.getBytes()));
    Assertions.assertThat(newConfiguration.getHolidays()).isNotNull();
    Assertions.assertThat(newConfiguration.getHolidays().getFixed()).isNotEmpty();
  }

}
