package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.jackson.mapping.Configuration;
import tools.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;

import static tools.jackson.databind.PropertyNamingStrategies.UPPER_CAMEL_CASE;

public class JacksonXMLMapper {

  private static final XmlMapper mapper = new JacksonMapperCreator().create();

  /**
   * Unmarshalls the configuration from the stream. Uses <code>jackson</code> for
   * this.
   *
   * @param stream a {@link InputStream} object.
   * @return The unmarshalled configuration.
   */
  public Configuration unmarshallConfiguration(InputStream stream) {
    try {
      return mapper.readValue(stream, Configuration.class);
    } catch (Exception e) {
      throw new IllegalStateException("Cannot parse holidays XML file.", e);
    }
  }

  private static class JacksonMapperCreator {
    private XmlMapper create() {
      return XmlMapper.builder()
        .propertyNamingStrategy(UPPER_CAMEL_CASE)
        .build();
    }
  }
}
