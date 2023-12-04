package de.focus_shift.jollyday.jackson;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.jackson.mapping.Configuration;
import de.focus_shift.jollyday.jackson.mapping.Month;
import de.focus_shift.jollyday.jackson.mapping.Weekday;

public class XMLUtil {

  private final XmlMapper mapper;

  public XMLUtil() {
    mapper = new XmlMapper();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
  }

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
  public HolidayType getType(de.focus_shift.jollyday.jackson.mapping.HolidayType type) {
    switch (type) {
      case OFFICIAL_HOLIDAY:
        return HolidayType.OFFICIAL_HOLIDAY;
      case UNOFFICIAL_HOLIDAY:
        return HolidayType.UNOFFICIAL_HOLIDAY;
      default:
        throw new IllegalArgumentException("Unknown type " + type);
    }
  }
}
