package de.focus_shift.jollyday.core;

import de.focus_shift.jollyday.core.caching.Cache;
import de.focus_shift.jollyday.core.configuration.ConfigurationProviderManager;
import de.focus_shift.jollyday.core.datasource.ConfigurationServiceManager;
import de.focus_shift.jollyday.core.parser.functions.CalendarToLocalDate;
import de.focus_shift.jollyday.core.spi.HolidayCalendarConfigurationService;
import de.focus_shift.jollyday.core.support.LazyServiceLoaderCache;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

/**
 * Abstract base class for all holiday manager implementations. Upon call of
 * getInstance method the implementing class will be read from the
 * jollyday.properties file and instantiated.
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
   * Manager for providing configuration data sources which return the holiday
   * data.
   */
  private static final ConfigurationServiceManager configurationServiceManager =
    new ConfigurationServiceManager(new LazyServiceLoaderCache<>(HolidayCalendarConfigurationService.class));

  /**
   * The datasource to get the holiday data from.
   */
  private HolidayCalendarConfigurationService configurationService;

  /**
   * the manager parameter
   */
  private ManagerParameter managerParameter;

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
   * Creates a HolidayManager instance for the default locale country using
   * the configured properties from the configuration file.
   *
   * @return an eventually cached HolidayManager instance
   */
  public static HolidayManager getInstance() {
    return getInstance(ManagerParameters.create((String) null, null));
  }

  /**
   * Creates a HolidayManager instance for the default locale country using
   * the provided properties.
   *
   * @param properties the overriding configuration properties.
   * @return an eventually cached HolidayManager instance
   */
  public static HolidayManager getInstance(final Properties properties) {
    return getInstance(ManagerParameters.create((String) null, properties));
  }

  /**
   * Creates and returns a {@link HolidayManager} for the provided
   * {@link ManagerParameters}
   *
   * @param parameter the {@link ManagerParameters} to create the manager with
   * @return the {@link HolidayManager} instance
   */
  public static HolidayManager getInstance(final ManagerParameter parameter) {
    return createManager(parameter);
  }

  /**
   * Creates a new {@code HolidayManager} instance for the country and
   * puts it to the manager cache.
   *
   * @param parameter the parameter will be merged into the current configuration
   * @return created or cached holiday manager
   */
  private static HolidayManager createManager(final ManagerParameter parameter) {
    LOG.debug("Creating HolidayManager for calendar '{}'. Caching enabled: {}", parameter, isManagerCachingEnabled());
    CONFIGURATION_MANAGER_PROVIDER.mergeConfigurationProperties(parameter);

    final String managerImplClassName = readManagerImplClassName(parameter);
    final String configurationServiceImplClassName = readConfigurationServiceImplClassName(parameter);
    final HolidayManagerValueHandler holidayManagerValueHandler = new HolidayManagerValueHandler(parameter, managerImplClassName, configurationServiceImplClassName, configurationServiceManager);
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
  private static String readManagerImplClassName(final ManagerParameter parameter) {
    final String className = parameter.getManagerImplClassName();
    if (className == null) {
      throw new IllegalStateException("Missing configuration '" + ManagerParameter.MANAGER_IMPL_CLASS_PREFIX + "'. Cannot create manager.");
    }
    return className;
  }

  /**
   * Reads the managers implementation class from the properties config file.
   *
   * @param parameter the parameter to read the manager implementation class from
   * @return the manager implementation class name
   */
  private static String readConfigurationServiceImplClassName(final ManagerParameter parameter) {
    final String className = parameter.getConfigurationServiceImplClassName();
    if (className == null) {
      throw new IllegalStateException("Missing configuration '" + ManagerParameter.CONFIGURATION_SERVICE_IMPL + "'. Cannot create configuration service.");
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
   * Returns true or false if the requested calendar date is a holiday in the state or
   * the optional given region and below
   *
   * @param calendar {@link java.util.Calendar} to check.
   * @param args     Hierarchy to request the holidays for. i.e. args = {'ny'} -&gt; New York holidays
   * @return if the date is a holiday
   */
  public boolean isHoliday(final Calendar calendar, final String... args) {
    return isHoliday(calendar, null, args);
  }

  /**
   * Returns true or false if the requested calendar date is a holiday in the state or
   * based on the given {@link HolidayType} and
   * the optional given region and below
   *
   * @param calendar    {@link java.util.Calendar} to check.
   * @param holidayType a {@link HolidayType} to be considered
   * @param args        Hierarchy to request the holidays for. i.e. args = {'ny'} -&gt; New York holidays
   * @return if the date is a holiday
   */
  public boolean isHoliday(final Calendar calendar, final HolidayType holidayType, final String... args) {
    return isHoliday(new CalendarToLocalDate().apply(calendar), holidayType, args);
  }

  /**
   * Returns true or false if the requested date is a holiday in the state or
   * the optional given region and below
   *
   * @param localDate The potential holiday.
   * @param args      Hierarchy to request the holidays for. i.e. args = {'ny'} -&gt; New York holidays
   * @return true if the given date is a holiday in the state/region and below, otherwise false
   */
  public boolean isHoliday(final LocalDate localDate, final String... args) {
    return isHoliday(localDate, null, args);
  }

  /**
   * Returns true or false if the requested date is a holiday in the state,
   * based on the given {@link HolidayType} and
   * the optional given region and below
   *
   * @param localDate   The potential holiday.
   * @param holidayType a {@link HolidayType} to be considered
   * @param args        Hierarchy to request the holidays for. i.e. args = {'ny'} -&gt; New York holidays
   * @return true if the given date is a holiday in the state/region and below, otherwise false
   */
  public boolean isHoliday(final LocalDate localDate, final HolidayType holidayType, final String... args) {
    final Set<Holiday> holidays = getHolidays(Year.of(localDate.getYear()), args);
    return CalendarUtil.contains(holidays, localDate, holidayType);
  }

  /**
   * Returns a set of all currently supported ISO 3166-1 alpha-2 codes.
   *
   * @return Set of supported calendar codes.
   */
  public static Set<String> getSupportedCalendarCodes() {
    return HolidayCalendar.getSupportedCalendarCodes();
  }

  /**
   * Sets the configuration datasource with this holiday manager.
   *
   * @param configurationService the {@link HolidayCalendarConfigurationService} to use.
   */
  public void setConfigurationService(HolidayCalendarConfigurationService configurationService) {
    this.configurationService = configurationService;
  }

  /**
   * Returns the {@link HolidayCalendarConfigurationService} to be used to retrieve
   * holiday data.
   *
   * @return the {@link HolidayCalendarConfigurationService} to use.
   */
  public HolidayCalendarConfigurationService getConfigurationService() {
    return configurationService;
  }

  /**
   * Returns the {@link ManagerParameter} to be used
   *
   * @return the {@link ManagerParameter} to use.
   */
  public ManagerParameter getManagerParameter() {
    return managerParameter;
  }

  /**
   * Returns the holidays for the requested year and hierarchy structure.
   *
   * @param year i.e. 2010
   * @param args i.e. args = {'ny'}. returns US/New York holidays. No args means holidays common to whole country
   * @return a set of holidays for the requested year
   */
  public abstract Set<Holiday> getHolidays(final Year year, final String... args);

  /**
   * Returns the holidays for the requested year, the given {@link HolidayType} and the hierarchy structure
   *
   * @param year        i.e. 2010
   * @param holidayType a {@link HolidayType} to be considered
   * @param args        i.e. args = {'ny'}. returns US/New York holidays. No args means holidays common to whole country
   * @return a set of holidays of the given {@link HolidayType} for the requested year
   */
  public abstract Set<Holiday> getHolidays(final Year year, final HolidayType holidayType, final String... args);

  /**
   * Returns the holidays for the requested interval and hierarchy structure.
   *
   * @param startDateInclusive the start date of the interval in which holidays lie, inclusive
   * @param endDateInclusive   the end date of the interval in which holidays lie, inclusive
   * @param args               i.e. args = {'ny'}. returns US/New York holidays. No args means holidays common to whole country
   * @return set of holidays within the interval
   */
  public abstract Set<Holiday> getHolidays(final LocalDate startDateInclusive, final LocalDate endDateInclusive, final String... args);

  /**
   * Returns the holidays for the requested interval, the given {@link HolidayType} and hierarchy structure.
   *
   * @param startDateInclusive the start date of the interval in which holidays lie, inclusive
   * @param endDateInclusive   the end date of the interval in which holidays lie, inclusive
   * @param holidayType        a {@link HolidayType} to be considered
   * @param args               i.e. args = {'ny'}. returns US/New York holidays. No args means holidays common to whole country
   * @return set of holidays of the given {@link HolidayType} within the interval
   */
  public abstract Set<Holiday> getHolidays(final LocalDate startDateInclusive, final LocalDate endDateInclusive, final HolidayType holidayType, final String... args);

  /**
   * Returns the configured hierarchy structure for the specific manager. This
   * hierarchy shows how the configured holidays are structured and can be
   * retrieved.
   *
   * @return Current calendars hierarchy
   */
  public abstract CalendarHierarchy getCalendarHierarchy();
}
