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

  private static final Schema holidaySchema = loadSchema();

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
      final Unmarshaller um = jaxbContext.createUnmarshaller();
      um.setSchema(holidaySchema);
      @SuppressWarnings("unchecked") final JAXBElement<Configuration> jaxbElement = (JAXBElement<Configuration>) um.unmarshal(stream);
      return jaxbElement.getValue();
    } catch (JAXBException exception) {
      throw new IllegalStateException("Cannot parse holidays XML file.", exception);
    }
  }

  private static @NonNull Schema loadSchema() {
    try (InputStream schemaStream = ClassLoadingUtil.getClassloader().getResourceAsStream(SCHEMA_RESOURCE)) {
      if (schemaStream == null) {
        throw new IllegalStateException("Cannot find holiday schema on the classpath: " + SCHEMA_RESOURCE);
      }
      final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      return schemaFactory.newSchema(new StreamSource(schemaStream));
    } catch (IOException | SAXException exception) {
      throw new IllegalStateException("Cannot load holiday schema: " + SCHEMA_RESOURCE, exception);
    }
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
