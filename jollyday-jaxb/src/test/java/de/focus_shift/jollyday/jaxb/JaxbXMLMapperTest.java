package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.jaxb.mapping.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JaxbXMLMapperTest {

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

  @Test
  void rejectsHolidayElementsThatViolateTheSchemasDeclaredSequenceOrder() {
    final String outOfOrderXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
      "<Configuration hierarchy=\"xx\" description=\"Test\"\n" +
      "               xmlns=\"https://focus_shift.de/jollyday/schema/holiday\"\n" +
      "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
      "               xsi:schemaLocation=\"https://focus_shift.de/jollyday/schema/holiday https://focus_shift.de/jollyday/schema/holiday/holiday.xsd\">\n" +
      "  <Holidays>\n" +
      "    <ChristianHoliday type=\"GOOD_FRIDAY\"/>\n" +
      "    <Fixed month=\"JANUARY\" day=\"1\" descriptionPropertiesKey=\"NEW_YEAR\"/>\n" +
      "  </Holidays>\n" +
      "</Configuration>\n";

    final InputStream inputStream = new ByteArrayInputStream(outOfOrderXml.getBytes(StandardCharsets.UTF_8));

    assertThatThrownBy(() -> sut.unmarshallConfiguration(inputStream))
      .isInstanceOf(IllegalStateException.class);
  }
}
