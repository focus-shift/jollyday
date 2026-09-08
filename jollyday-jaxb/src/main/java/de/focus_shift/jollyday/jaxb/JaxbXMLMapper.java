package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.util.ClassLoadingUtil;
import de.focus_shift.jollyday.jaxb.mapping.Configuration;
import de.focus_shift.jollyday.jaxb.mapping.ObjectFactory;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;

public class JaxbXMLMapper {

  /**
   * the package name to search for the generated java classes.
   */
  private static final String PACKAGE = "de.focus_shift.jollyday.jaxb.mapping";

  /**
   * classpath location of the XSD that bundled and consumer-supplied holiday XML is validated against.
   */
  private static final String SCHEMA_RESOURCE = "focus_shift.de/jollyday/schema/holiday/holiday.xsd";

  private static final Logger LOG = LoggerFactory.getLogger(JaxbXMLMapper.class);

  private static final JAXBContext jaxbContext = new JAXBContextCreator().create();

  /**
   * StAX factory hardened against XXE. DTD processing and external entity resolution are
   * disabled, so a {@code <!DOCTYPE ...>} declaration (and any external or parameter entity it
   * might carry) is rejected rather than resolved. The protection is configured explicitly here
   * instead of relying on the defaults of whichever StAX/JAXB provider happens to be on the
   * classpath, which could change if a consumer swaps or downgrades that provider.
   */
  private static final XMLInputFactory XML_INPUT_FACTORY = createHardenedXmlInputFactory();

  /**
   * the holiday XSD every configuration is validated against while unmarshalling, so that structural
   * mistakes like elements out of the schema's declared sequence order fail fast instead of being
   * silently ignored by JAXB.
   */
  private static final Schema HOLIDAY_SCHEMA = loadSchema(ClassLoadingUtil.getClassloader());

  /**
   * Unmarshalls the configuration from the stream. Uses <code>JAXB</code> for
   * this.
   *
   * @param stream a {@link java.io.InputStream} object.
   * @return The unmarshalled configuration.
   */
  public @NonNull Configuration unmarshallConfiguration(@Nullable final InputStream stream) {
    if (stream == null) {
      throw new IllegalArgumentException("Stream is NULL. Cannot read XML.");
    }

    try {
      final XMLStreamReader xmlStreamReader = XML_INPUT_FACTORY.createXMLStreamReader(stream);
      try {
        final Unmarshaller um = jaxbContext.createUnmarshaller();
        um.setSchema(HOLIDAY_SCHEMA);
        final JAXBElement<Configuration> jaxbElement = um.unmarshal(xmlStreamReader, Configuration.class);
        return jaxbElement.getValue();
      } finally {
        xmlStreamReader.close();
      }
    } catch (JAXBException | XMLStreamException exception) {
      throw new IllegalStateException("Cannot parse holidays XML file.", exception);
    }
  }

  static @NonNull Schema loadSchema(@NonNull final ClassLoader classLoader) {
    try (InputStream schemaStream = classLoader.getResourceAsStream(SCHEMA_RESOURCE)) {
      if (schemaStream == null) {
        throw new IllegalStateException("Cannot find holiday schema on the classpath: " + SCHEMA_RESOURCE);
      }
      return createHardenedSchemaFactory().newSchema(new StreamSource(schemaStream));
    } catch (IOException | SAXException exception) {
      throw new IllegalStateException("Cannot load holiday schema: " + SCHEMA_RESOURCE, exception);
    }
  }

  private static @NonNull XMLInputFactory createHardenedXmlInputFactory() {
    final XMLInputFactory factory = XMLInputFactory.newFactory();
    factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
    factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
    return factory;
  }

  /**
   * Schema factory hardened against XXE. External DTDs and external schema documents are not
   * accessed while the holiday XSD is compiled, so neither the bundled schema nor a replacement on a
   * consumer's classpath can pull in a document from the file system or the network.
   */
  private static @NonNull SchemaFactory createHardenedSchemaFactory() throws SAXException {
    final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
    factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
    return factory;
  }

  private static class JAXBContextCreator {
    private JAXBContext create() {
      return createJAXBContext();
    }

    private static @NonNull JAXBContext createJAXBContext() {
      JAXBContext ctx = null;
      try {
        ctx = JAXBContext.newInstance(JaxbXMLMapper.PACKAGE, ClassLoadingUtil.getClassloader());
      } catch (JAXBException e) {
        LOG.warn("Could not create JAXB context using the current classloader from ClassLoadingUtil. Falling back to ObjectFactory class classloader.");
      }

      if (ctx == null) {
        try {
          ctx = JAXBContext.newInstance(JaxbXMLMapper.PACKAGE, ObjectFactory.class.getClassLoader());
        } catch (JAXBException exception) {
          throw new IllegalStateException("Could not create JAXB context using ObjectFactory classloader.", exception);
        }
      }

      return ctx;
    }
  }
}
