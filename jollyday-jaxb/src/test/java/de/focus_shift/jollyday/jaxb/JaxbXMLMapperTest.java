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

  @ParameterizedTest
  @ValueSource(strings = {"Holidays_at.xml", "Holidays_de.xml", "Holidays_gb.xml", "Holidays_ua.xml", "Holidays_tr.xml", "Holidays_za.xml"})
  void unmarshalRealResource(String holidayFileName) {
    final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("holidays/" + holidayFileName);
    final Configuration configuration = sut.unmarshallConfiguration(inputStream);
    assertThat(configuration.getHolidays()).isNotNull();
  }
}
