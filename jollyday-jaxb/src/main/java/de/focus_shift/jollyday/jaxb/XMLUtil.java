package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.util.ClassLoadingUtil;
import de.focus_shift.jollyday.jaxb.mapping.Configuration;
import de.focus_shift.jollyday.jaxb.mapping.ObjectFactory;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class XMLUtil {

  /**
   * the package name to search for the generated java classes.
   */
  private static final String PACKAGE = "de.focus_shift.jollyday.jaxb.mapping";

  private static final Logger LOG = LoggerFactory.getLogger(XMLUtil.class);

  private static final JAXBContext jaxbContext = new JAXBContextCreator().create();

  /**
   * Unmarshalls the configuration from the stream. Uses <code>JAXB</code> for
   * this.
   *
   * @param stream a {@link java.io.InputStream} object.
   * @return The unmarshalled configuration.
   */
  public Configuration unmarshallConfiguration(InputStream stream) {
    if (stream == null) {
      throw new IllegalArgumentException("Stream is NULL. Cannot read XML.");
    }

    try {
      final Unmarshaller um = jaxbContext.createUnmarshaller();
      @SuppressWarnings("unchecked") final JAXBElement<Configuration> jaxbElement = (JAXBElement<Configuration>) um.unmarshal(stream);
      return jaxbElement.getValue();
    } catch (JAXBException exception) {
      throw new IllegalStateException("Cannot parse holidays XML file.", exception);
    }
  }

  private static class JAXBContextCreator {
    private JAXBContext create() {
      return createJAXBContext();
    }

    private static JAXBContext createJAXBContext() {
      JAXBContext ctx = null;
      try {
        ctx = JAXBContext.newInstance(XMLUtil.PACKAGE, ClassLoadingUtil.getClassloader());
      } catch (JAXBException e) {
        LOG.warn("Could not create JAXB context using the current classloader from ClassLoadingUtil. Falling back to ObjectFactory class classloader.");
      }

      if (ctx == null) {
        try {
          ctx = JAXBContext.newInstance(XMLUtil.PACKAGE, ObjectFactory.class.getClassLoader());
        } catch (JAXBException exception) {
          throw new IllegalStateException("Could not create JAXB context using ObjectFactory classloader.", exception);
        }
      }

      return ctx;
    }
  }
}
