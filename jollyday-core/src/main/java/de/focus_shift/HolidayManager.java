package de.focus_shift;

import de.focus_shift.caching.HolidayManagerValueHandler;
import de.focus_shift.configuration.ConfigurationProviderManager;
import de.focus_shift.datasource.ConfigurationDataSource;
import de.focus_shift.parser.functions.CalendarToLocalDate;
import de.focus_shift.util.Cache;
import de.focus_shift.util.Cache.ValueHandler;
import de.focus_shift.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Abstract base class for all holiday manager implementations. Upon call of
 * getInstance method the implementing class will be read from the
 * jollyday.properties file and instantiated.
 *
 * @author Sven Diedrichsen
 * @version $Id: $
 */
public abstract class HolidayManager {

  private static final Logger LOG = LoggerFactory.getLogger(HolidayManager.class);

  /**
   * Signifies if caching of manager instances is enabled. If not every call
   * to getInstance will return a newly instantiated and initialized manager.
   */
  private static boolean cachingEnabled = true;
  /**
   * Cache for manager instances on a per country basis.
   */
  private static final Cache<HolidayManager> HOLIDAY_MANAGER_CACHE = new Cache<>();
  /**
   * Manager for configuration providers. Delivers the jollyday configuration.
   */
  private static final ConfigurationProviderManager CONFIGURATION_MANAGER_PROVIDER = new ConfigurationProviderManager();
  /**
   * the holiday cache
   */
  private final Cache<Set<Holiday>> holidayCache = new Cache<>();
  /**
   * Utility for calendar operations
   */
  protected final CalendarUtil calendarUtil = new CalendarUtil();
  /**
   * The datasource to get the holiday data from.
   */
  private ConfigurationDataSource configurationDataSource;
  /**
   * the manager parameter
   */
  private ManagerParameter managerParameter;

  /**
   * Creates a HolidayManager instance for the default locale country using
   * the configured properties from the configuration file.
   *
   * @return a eventually cached HolidayManager instance
   */
  public static HolidayManager getInstance() {
    return getInstance(ManagerParameters.create((String) null, null));
  }

  /**
   * Creates a HolidayManager instance for the default locale country using
   * the provided properties.
   *
   * @param properties the overriding configuration properties.
   * @return a eventually cached HolidayManager instance
   */
  public static HolidayManager getInstance(Properties properties) {
    return getInstance(ManagerParameters.create((String) null, properties));
  }

  /**
   * Creates and returns a {@link HolidayManager} for the provided
   * {@link ManagerParameters}
   *
   * @param parameter the {@link ManagerParameters} to create the manager with
   * @return the {@link HolidayManager} instance
   */
  public static HolidayManager getInstance(ManagerParameter parameter) {
    return createManager(parameter);
  }

  /**
   * Creates a new <code>HolidayManager</code> instance for the country and
   * puts it to the manager cache.
   *
   * @param parameter the parameter will be merged into the current configuration
   * @return created or cached holiday manager
   */
  private static HolidayManager createManager(final ManagerParameter parameter) {
    LOG.debug("Creating HolidayManager for calendar '{}'. Caching enabled: {}", parameter, isManagerCachingEnabled());
    CONFIGURATION_MANAGER_PROVIDER.mergeConfigurationProperties(parameter);
    final String managerImplClassName = readManagerImplClassName(parameter);
    final HolidayManagerValueHandler holidayManagerValueHandler = new HolidayManagerValueHandler(parameter, managerImplClassName);
    if (isManagerCachingEnabled()) {
      return HOLIDAY_MANAGER_CACHE.get(holidayManagerValueHandler);
    } else {
      return holidayManagerValueHandler.createValue();
    }
  }

  /**
   * Reads the managers implementation class from the properties config file.
   *
   * @param parameter the parameter to read the manager implementation class from
   * @return the manager implementation class name
   */
  private static String readManagerImplClassName(ManagerParameter parameter) {
    final String className = parameter.getManagerImplClassName();
    if (className == null) {
      throw new IllegalStateException("Missing configuration '" + ManagerParameter.MANAGER_IMPL_CLASS_PREFIX + "'. Cannot create manager.");
    }
    return className;
  }

  /**
   * If true, instantiated managers will be cached. If false every call to
   * getInstance will create new manager. True by default.
   *
   * @param managerCachingEnabled the managerCachingEnabled to set
   */
  public static void setManagerCachingEnabled(boolean managerCachingEnabled) {
    cachingEnabled = managerCachingEnabled;
  }

  /**
   * <p>
   * isManagerCachingEnabled.
   * </p>
   *
   * @return the CACHING_ENABLED
   */
  public static boolean isManagerCachingEnabled() {
    return cachingEnabled;
  }

  /**
   * Clears the manager cache from all cached manager instances.
   */
  public static void clearManagerCache() {
    HOLIDAY_MANAGER_CACHE.clear();
  }

  /**
   * Calls isHoliday with {@link LocalDate} object.
   *
   * @param c           {@link java.util.Calendar} to check.
   * @param holidayType type holidays to be considered. NULL checks any.
   * @param args        a {@link java.lang.String} object.
   * @return if the date is a holiday
   */
  public boolean isHoliday(final Calendar c, HolidayType holidayType, final String... args) {
    return isHoliday(new CalendarToLocalDate().apply(c), holidayType, args);
  }

  public boolean isHoliday(final Calendar c, final String... args) {
    return isHoliday(c, null, args);
  }

  /**
   * Show if the requested date is a holiday.
   *
   * @param c           The potential holiday.
   * @param holidayType a {@link HolidayType} object
   * @param args        Hierarchy to request the holidays for. i.e. args = {'ny'} -&gt;
   *                    New York holidays
   * @return is a holiday in the state/region
   */
  public boolean isHoliday(final LocalDate c, HolidayType holidayType, final String... args) {
    final StringBuilder keyBuilder = new StringBuilder();
    keyBuilder.append(c.getYear());
    for (String arg : args) {
      keyBuilder.append("_");
      keyBuilder.append(arg);
    }
    Set<Holiday> holidays = holidayCache.get(new ValueHandler<Set<Holiday>>() {
      @Override
      public String getKey() {
        return keyBuilder.toString();
      }

      @Override
      public Set<Holiday> createValue() {
        return getHolidays(c.getYear(), args);
      }
    });
    return calendarUtil.contains(holidays, c, holidayType);
  }

  /**
   * Calls #isHoliday(c, null, args)
   *
   * @param c    the date to check
   * @param args the arguments to find the calendar
   * @return whether the date is a holiday
   */
  public boolean isHoliday(final LocalDate c, final String... args) {
    return isHoliday(c, null, args);
  }

  /**
   * Returns a set of all currently supported calendar codes.
   *
   * @return Set of supported calendar codes.
   */
  public static Set<String> getSupportedCalendarCodes() {
    Set<String> supportedCalendars = new HashSet<>();
    for (HolidayCalendar c : HolidayCalendar.values()) {
      supportedCalendars.add(c.getId());
    }
    return supportedCalendars;
  }

  /**
   * Sets the configuration datasource with this holiday manager.
   *
   * @param configurationDataSource the {@link ConfigurationDataSource} to use.
   */
  public void setConfigurationDataSource(ConfigurationDataSource configurationDataSource) {
    this.configurationDataSource = configurationDataSource;
  }

  /**
   * Returns the {@link ConfigurationDataSource} to be used to retrieve
   * holiday data.
   *
   * @return the {@link ConfigurationDataSource} to use.
   */
  public ConfigurationDataSource getConfigurationDataSource() {
    return configurationDataSource;
  }

  public ManagerParameter getManagerParameter() {
    return managerParameter;
  }

  /**
   * Initializes the implementing manager for the provided calendar.
   *
   * @param parameters i.e. us, uk, de
   */
  public void init(ManagerParameter parameters) {
    this.managerParameter = parameters;
    this.doInit();
  }

  public abstract void doInit();

  /**
   * Returns the holidays for the requested year and hierarchy structure.
   *
   * @param year i.e. 2010
   * @param args i.e. args = {'ny'}. returns US/New York holidays. No args -&gt;
   *             holidays common to whole country
   * @return the list of holidays for the requested year
   */
  public abstract Set<Holiday> getHolidays(int year, String... args);

  /**
   * Returns the holidays for the requested interval and hierarchy structure.
   *
   * @param startDateInclusive the start date of the interval in which holidays lie, inclusive
   * @param endDateInclusive   the end date of the interval in which holidays lie, inclusive
   * @param args               a {@link java.lang.String} object.
   * @return list of holidays within the interval
   */
  public abstract Set<Holiday> getHolidays(LocalDate startDateInclusive,
                                           LocalDate endDateInclusive, String... args);

  /**
   * Returns the configured hierarchy structure for the specific manager. This
   * hierarchy shows how the configured holidays are structured and can be
   * retrieved.
   *
   * @return Current calendars hierarchy
   */
  public abstract CalendarHierarchy getCalendarHierarchy();

}
