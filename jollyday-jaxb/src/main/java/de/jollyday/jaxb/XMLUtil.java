package de.jollyday.jaxb;

import de.jollyday.HolidayType;
import de.jollyday.jaxb.mapping.Configuration;
import de.jollyday.jaxb.mapping.Month;
import de.jollyday.jaxb.mapping.ObjectFactory;
import de.jollyday.jaxb.mapping.Weekday;
import de.jollyday.util.ClassLoadingUtil;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.InputStream;
import java.time.DayOfWeek;
import java.util.logging.Logger;

public class XMLUtil {

  /**
   * the package name to search for the generated java classes.
   */
  public static final String PACKAGE = "de.jollyday.jaxb.mapping";

  private static final Logger LOG = Logger.getLogger(XMLUtil.class.getName());

  private final JAXBContextCreator contextCreator = new JAXBContextCreator();
  private final ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

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
      JAXBContext ctx;
      try {
        ctx = contextCreator.create(XMLUtil.PACKAGE, classLoadingUtil.getClassloader());
      } catch (JAXBException e) {
        LOG.warning("Could not create JAXB context using the current threads context classloader. Falling back to ObjectFactory class classloader.");
        ctx = null;
      }
      if (ctx == null) {
        ctx = contextCreator.create(XMLUtil.PACKAGE, ObjectFactory.class.getClassLoader());
      }
      final Unmarshaller um = ctx.createUnmarshaller();
      @SuppressWarnings("unchecked") final JAXBElement<Configuration> el = (JAXBElement<Configuration>) um.unmarshal(stream);
      return el.getValue();
    } catch (JAXBException ue) {
      throw new IllegalStateException("Cannot parse holidays XML file.", ue);
    }
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

  /**
   * Gets the type.
   *
   * @param type the type of holiday in the config
   * @return the type of holiday
   */
  public HolidayType getType(de.jollyday.jaxb.mapping.HolidayType type) {
    switch (type) {
      case OFFICIAL_HOLIDAY:
        return HolidayType.OFFICIAL_HOLIDAY;
      case UNOFFICIAL_HOLIDAY:
        return HolidayType.UNOFFICIAL_HOLIDAY;
      default:
        throw new IllegalArgumentException("Unknown type " + type);
    }
  }

  public static class JAXBContextCreator {
    public JAXBContext create(String packageName, ClassLoader classLoader) throws JAXBException {
      return JAXBContext.newInstance(packageName, classLoader);
    }
  }
}
