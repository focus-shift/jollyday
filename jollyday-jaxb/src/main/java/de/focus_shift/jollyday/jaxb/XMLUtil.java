package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.util.ClassLoadingUtil;
import de.focus_shift.jollyday.jaxb.mapping.Configuration;
import de.focus_shift.jollyday.jaxb.mapping.Month;
import de.focus_shift.jollyday.jaxb.mapping.ObjectFactory;
import de.focus_shift.jollyday.jaxb.mapping.Weekday;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.time.DayOfWeek;

public class XMLUtil {

  /**
   * the package name to search for the generated java classes.
   */
  private static final String PACKAGE = "de.focus_shift.jollyday.jaxb.mapping";

  private static final Logger LOG = LoggerFactory.getLogger(XMLUtil.class);

  private JAXBContextCreator contextCreator = new JAXBContextCreator();

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
      final JAXBContext ctx = createJAXBContext();
      final Unmarshaller um = ctx.createUnmarshaller();
      @SuppressWarnings("unchecked") final JAXBElement<Configuration> jaxbElement = (JAXBElement<Configuration>) um.unmarshal(stream);
      return jaxbElement.getValue();
    } catch (JAXBException exception) {
      throw new IllegalStateException("Cannot parse holidays XML file.", exception);
    }
  }

  private JAXBContext createJAXBContext() throws JAXBException {
    JAXBContext ctx = null;
    try {
      ctx = contextCreator.create(XMLUtil.PACKAGE, ClassLoadingUtil.getClassloader());
    } catch (JAXBException e) {
      LOG.warn("Could not create JAXB context using the current threads context classloader. Falling back to ObjectFactory class classloader.");
    }

    if (ctx == null) {
      ctx = contextCreator.create(XMLUtil.PACKAGE, ObjectFactory.class.getClassLoader());
    }
    return ctx;
  }

  /**
   * Returns the {@link DayOfWeek} equivalent for the given weekday.
   *
   * @param weekday a {@link Weekday} object.
   * @return a DayOfWeek instance.
   */
  public final DayOfWeek getWeekday(Weekday weekday) {
    return DayOfWeek.valueOf(weekday.value());
  }

  /**
   * Returns the value for the given month.
   *
   * @param month a {@link Month} object.
   * @return a 1-12 value.
   */
  public int getMonth(Month month) {
    return month.ordinal() + 1;
  }

  public static class JAXBContextCreator {
    public JAXBContext create(String packageName, ClassLoader classLoader) throws JAXBException {
      return JAXBContext.newInstance(packageName, classLoader);
    }
  }
}
