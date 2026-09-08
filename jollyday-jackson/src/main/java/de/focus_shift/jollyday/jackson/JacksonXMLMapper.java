package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.jackson.mapping.Configuration;
import org.jspecify.annotations.NonNull;
import tools.jackson.dataformat.xml.XmlFactory;
import tools.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.XMLInputFactory;
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
  public @NonNull Configuration unmarshallConfiguration(@NonNull final InputStream stream) {
    try {
      return mapper.readValue(stream, Configuration.class);
    } catch (Exception e) {
      throw new IllegalStateException("Cannot parse holidays XML file.", e);
    }
  }

  private static class JacksonMapperCreator {
    private @NonNull XmlMapper create() {
      return XmlMapper.builder(XmlFactory.builder().xmlInputFactory(createHardenedXmlInputFactory()).build())
        .propertyNamingStrategy(UPPER_CAMEL_CASE)
        .build();
    }

    /**
     * StAX factory hardened against XXE. DTD processing and external entity resolution are
     * disabled, so a {@code <!DOCTYPE ...>} declaration (and any external or parameter entity it
     * might carry) is rejected rather than resolved. The protection is configured explicitly here
     * instead of relying on the defaults of whichever StAX provider happens to be on the classpath,
     * which could change if a consumer swaps or downgrades that provider.
     */
    private @NonNull XMLInputFactory createHardenedXmlInputFactory() {
      final XMLInputFactory factory = XMLInputFactory.newFactory();
      factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
      factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
      return factory;
    }
  }
}
