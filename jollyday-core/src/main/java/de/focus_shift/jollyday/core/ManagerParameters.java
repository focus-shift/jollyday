package de.focus_shift.jollyday.core;

import de.focus_shift.jollyday.core.parameter.CalendarPartManagerParameter;
import de.focus_shift.jollyday.core.parameter.UrlManagerParameter;

import java.net.URL;
import java.util.Locale;
import java.util.Properties;

public final class ManagerParameters {

  private ManagerParameters() {
  }

  /**
   * Uses the calendar part as identification for the holidays
   * <p>
   * Example:
   * <pre>final ManagerParameter parameters = ManagerParameters.create("de");</pre>
   *
   * @param calendarPart The calendar part to create parameters from.
   * @return an {@link CalendarPartManagerParameter} based on {@link ManagerParameter}
   */
  public static ManagerParameter create(final String calendarPart) {
    return create(calendarPart, null);
  }

  /**
   * Uses the calendar part as identification for the holidays
   * <p>
   * Example:
   * <pre>final ManagerParameter parameters = ManagerParameters.create("de", properties);</pre>
   *
   * @param calendarPart The calendar part to create parameters from.
   * @param properties   Additional properties
   * @return an {@link CalendarPartManagerParameter} based on {@link ManagerParameter}
   */
  public static ManagerParameter create(final String calendarPart, final Properties properties) {
    return new CalendarPartManagerParameter(prepareCalendarName(calendarPart), properties);
  }

  /**
   * Uses the locales country if it exists or its language otherwise.
   * <p>
   * Example:
   * <pre>final ManagerParameter parameters = ManagerParameters.create(Locale.GERMANY, properties);</pre>
   *
   * @param locale The locale to create parameters from.
   * @return an {@link CalendarPartManagerParameter} based on {@link ManagerParameter}
   */
  public static ManagerParameter create(final Locale locale) {
    return create(locale, null);
  }

  /**
   * Uses the locales country if it exists or its language otherwise.
   * <p>
   * Example:
   * <pre>final ManagerParameter parameters = ManagerParameters.create(Locale.GERMANY, properties);</pre>
   *
   * @param locale     The locale to create parameters from.
   * @param properties Additional properties
   * @return an {@link CalendarPartManagerParameter} based on {@link ManagerParameter}
   */
  public static ManagerParameter create(final Locale locale, final Properties properties) {
    final String calendarPart = "".equals(locale.getCountry()) ? locale.getLanguage() : locale.getCountry();
    return create(calendarPart, properties);
  }

  /**
   * Uses the holiday calendar based country if it exists or its language otherwise.
   * <p>
   * Example:
   * <pre>final ManagerParameter parameters = ManagerParameters.create(HolidayCalendar.GERMANY);</pre>
   *
   * @param calendar A specific {@link HolidayCalendar} to create parameters from.
   * @return an {@link CalendarPartManagerParameter} based on {@link ManagerParameter}
   */
  public static ManagerParameter create(final HolidayCalendar calendar) {
    return create(calendar, null);
  }


  /**
   * Uses the holiday calendar based country if it exists or its language otherwise.
   * <p>
   * Example:
   * <pre>final ManagerParameter parameters = ManagerParameters.create(HolidayCalendar.GERMANY, properties);</pre>
   *
   * @param calendar   A specific {@link HolidayCalendar} to create parameters from.
   * @param properties Additional properties
   * @return an {@link CalendarPartManagerParameter} based on {@link ManagerParameter}
   */
  public static ManagerParameter create(final HolidayCalendar calendar, final Properties properties) {
    return create(calendar.getId(), properties);
  }

  /**
   * Uses a given calendar file url
   * <p>
   * Example:
   * <pre>
   *   final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
   *   final URL url = classLoader.getResource("Holidays_de.xml");
   *   final ManagerParameter parameters = ManagerParameters.create(url);
   * </pre>
   *
   * @param calendarFileUrl A specific calendar file {@link URL} to create parameters from.
   * @return an {@link CalendarPartManagerParameter} based on {@link ManagerParameter}
   */
  public static ManagerParameter create(final URL calendarFileUrl) {
    return create(calendarFileUrl, null);
  }

  /**
   * Uses a given calendar file url
   * <p>
   * Example:
   * <pre>
   *   final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
   *   final URL url = classLoader.getResource("Holidays_de.xml");
   *   final ManagerParameter parameters = ManagerParameters.create(url, properties);
   * </pre>
   *
   * @param calendarFileUrl A specific calendar file {@link URL} to create parameters from.
   * @param properties      Additional properties
   * @return an {@link CalendarPartManagerParameter} based on {@link ManagerParameter}
   */
  public static ManagerParameter create(final URL calendarFileUrl, final Properties properties) {
    return new UrlManagerParameter(calendarFileUrl, properties);
  }

  private static String prepareCalendarName(final String calendar) {
    if (calendar == null || calendar.trim().isEmpty()) {
      return Locale.getDefault().getCountry().toLowerCase();
    } else {
      return calendar.trim().toLowerCase();
    }
  }
}
