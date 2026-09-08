package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.jaxb.mapping.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

  @Test
  void rejectsXmlWithDoctypeToPreventXxe() {
    final String maliciousXml =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        + "<!DOCTYPE Configuration [<!ENTITY xxe SYSTEM \"file:///etc/passwd\">]>\n"
        + "<Configuration hierarchy=\"test\" description=\"&xxe;\"></Configuration>";
    final InputStream inputStream = new ByteArrayInputStream(maliciousXml.getBytes(StandardCharsets.UTF_8));

    assertThatThrownBy(() -> sut.unmarshallConfiguration(inputStream))
      .isInstanceOf(IllegalStateException.class);
  }

  @Test
  void rejectsHolidayElementsThatViolateTheSchemasDeclaredSequenceOrder() {
    final String outOfOrderXml = """
      <?xml version="1.0" encoding="UTF-8"?>
      <Configuration hierarchy="xx" description="Test" xmlns="https://focus_shift.de/jollyday/schema/holiday">
        <Holidays>
          <ChristianHoliday type="GOOD_FRIDAY"/>
          <Fixed month="JANUARY" day="1" descriptionPropertiesKey="NEW_YEAR"/>
        </Holidays>
      </Configuration>
      """;
    final InputStream inputStream = new ByteArrayInputStream(outOfOrderXml.getBytes(StandardCharsets.UTF_8));

    assertThatThrownBy(() -> sut.unmarshallConfiguration(inputStream))
      .isInstanceOf(IllegalStateException.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {"Holidays_at.xml", "Holidays_de.xml", "Holidays_gb.xml", "Holidays_ua.xml", "Holidays_tr.xml", "Holidays_za.xml"})
  void unmarshalRealResource(String holidayFileName) {
    final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("holidays/" + holidayFileName);
    final Configuration configuration = sut.unmarshallConfiguration(inputStream);
    assertThat(configuration.getHolidays()).isNotNull();
  }

  @Test
  void failsWhenTheHolidaySchemaIsNotOnTheClassPath() {
    final ClassLoader withoutSchema = classLoaderProviding(null);

    assertThatThrownBy(() -> JaxbXMLMapper.loadSchema(withoutSchema))
      .isInstanceOf(IllegalStateException.class)
      .hasMessage("Cannot find holiday schema on the classpath: focus_shift.de/jollyday/schema/holiday/holiday.xsd")
      .hasNoCause();
  }

  @Test
  void failsWhenTheHolidaySchemaResourceIsNotASchema() {
    final ClassLoader withBrokenSchema = classLoaderProviding(new ByteArrayInputStream("<not-a-schema/>".getBytes(StandardCharsets.UTF_8)));

    assertThatThrownBy(() -> JaxbXMLMapper.loadSchema(withBrokenSchema))
      .isInstanceOf(IllegalStateException.class)
      .hasMessage("Cannot load holiday schema: focus_shift.de/jollyday/schema/holiday/holiday.xsd")
      .hasCauseInstanceOf(SAXException.class);
  }

  @Test
  void failsWhenTheHolidaySchemaResourceCannotBeRead() {
    final ClassLoader withUnreadableSchema = classLoaderProviding(new InputStream() {
      @Override
      public int read() throws IOException {
        throw new IOException("schema resource is not readable");
      }
    });

    assertThatThrownBy(() -> JaxbXMLMapper.loadSchema(withUnreadableSchema))
      .isInstanceOf(IllegalStateException.class)
      .hasMessage("Cannot load holiday schema: focus_shift.de/jollyday/schema/holiday/holiday.xsd");
  }

  private static ClassLoader classLoaderProviding(final InputStream resource) {
    return new ClassLoader(JaxbXMLMapperTest.class.getClassLoader()) {
      @Override
      public InputStream getResourceAsStream(final String name) {
        return resource;
      }
    };
  }
}
