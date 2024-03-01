package de.focus_shift.jollyday.core.impl;

import de.focus_shift.jollyday.core.CalendarHierarchy;
import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.caching.Cache;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.spi.Configuration;
import de.focus_shift.jollyday.core.spi.Holidays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static de.focus_shift.jollyday.core.util.ClassLoadingUtil.loadClass;
import static java.util.Arrays.copyOfRange;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toUnmodifiableSet;
import static java.util.stream.IntStream.rangeClosed;

/**
 * Manager implementation for reading data from the configuration datasource.
 * It uses a list a parsers for parsing the different type of XML nodes.
 */
public class DefaultHolidayManager extends HolidayManager {

  private static final Logger LOG = LoggerFactory.getLogger(DefaultHolidayManager.class);

  /**
   * The configuration prefix for parser implementations.
   */
  private static final String PARSER_IMPL_PREFIX = "parser.impl.";

  /**
   * Caches all {@link HolidayParser} instances actually used by the HolidayManager
   */
  private final Cache<HolidayParser> parserCache = new Cache<>();

  /**
   * Caches the instance based country specific holidays so that e.g.
   * the created HolidayManager via
   * {@code HolidayManager.getInstance(create("de"))} only contains the german holidays.
   * <p>
   * This is also the reason this cache is not static.
   * If it was static all holidays over all holiday manager instances would be cached,
   * but only the german holidays are important for the german holiday manager, so only save them.
   */
  private final Cache<Set<Holiday>> holidayCache = new Cache<>();

  /**
   * Configuration parsed on initialization.
   */
  protected Configuration configuration;

  /**
   * {@inheritDoc}
   * <p>
   * Initializes the DefaultHolidayManager by loading the holidays XML file as resource
   * from the classpath. When the XML file is found it will be unmarshalled
   * with JAXB to some Java classes.
   */
  @Override
  public void doInit() {
    configuration = getConfigurationService().getConfiguration(getManagerParameter());
    validateConfigurationHierarchy(configuration);
    logHierarchy(configuration, 0);
  }

  /**
   * {@inheritDoc}
   * <p>
   * Calls
   * <code>Set&lt;LocalDate&gt; getHolidays(int year, Configuration c, String... args)</code>
   * with the configuration from initialization.
   */
  @Override
  public Set<Holiday> getHolidays(final int year, final String... args) {

    final StringBuilder keyBuilder = new StringBuilder();
    keyBuilder.append(year);
    for (String arg : args) {
      keyBuilder.append("_");
      keyBuilder.append(arg);
    }

    final Cache.ValueHandler<Set<Holiday>> holidayValueHandler = new Cache.ValueHandler<>() {
      @Override
      public String getKey() {
        return keyBuilder.toString();
      }

      @Override
      public Set<Holiday> createValue() {
        final Set<Holiday> holidaySet = Collections.synchronizedSet(new HashSet<>());
        getHolidays(year, configuration, holidaySet, args);
        return holidaySet;
      }
    };

    return holidayCache.get(holidayValueHandler);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<Holiday> getHolidays(final int year, final HolidayType holidayType, final String... args) {
    return getHolidays(year, args).stream()
      .filter(holiday -> holiday.getType().equals(holidayType))
      .collect(toSet());
  }

  /**
   * {@inheritDoc}
   * <p>
   * Calls <code>getHolidays(year, args)</code> for each year within the
   * interval and returns a list of holidays which are then contained in the
   * interval.
   */
  @Override
  public Set<Holiday> getHolidays(final LocalDate startDateInclusive, final LocalDate endDateInclusive, final String... args) {
    Objects.requireNonNull(startDateInclusive, "startDateInclusive is null");
    Objects.requireNonNull(endDateInclusive, "endDateInclusive is null");

    return rangeClosed(startDateInclusive.getYear(), endDateInclusive.getYear())
      .mapToObj(year -> getHolidays(year, args))
      .flatMap(Collection::stream)
      .filter(holiday -> !startDateInclusive.isAfter(holiday.getDate()) && !endDateInclusive.isBefore(holiday.getDate()))
      .collect(toUnmodifiableSet());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<Holiday> getHolidays(final LocalDate startDateInclusive, final LocalDate endDateInclusive, final HolidayType holidayType, final String... args) {
    return getHolidays(startDateInclusive, endDateInclusive, args).stream()
      .filter(holiday -> holiday.getType().equals(holidayType))
      .collect(toSet());
  }

  /**
   * {@inheritDoc}
   * <p>
   * Returns the configurations hierarchy.<br>
   * i.e. Hierarchy 'us' -&gt; Children 'al','ak','ar', ... ,'wv','wy'. Every
   * child might itself have children. The ids be used to call
   * getHolidays()/isHoliday().
   */
  @Override
  public CalendarHierarchy getCalendarHierarchy() {
    return createConfigurationHierarchy(configuration, null);
  }

  /**
   * Parses the provided configuration for the provided year and fills the
   * list of holidays.
   *
   * @param year          the year to get the holidays for
   * @param configuration the holiday configuration
   * @param holidaySet    the set of holidays
   * @param args          the arguments to descend down the configuration tree
   */
  private void getHolidays(final int year, final Configuration configuration, final Set<Holiday> holidaySet, final String... args) {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Adding holidays for {}", configuration.description());
    }

    parseHolidays(year, holidaySet, configuration.holidays());

    if (args != null && args.length > 0) {
      final String hierarchy = args[0];

      configuration.subConfigurations()
        .filter(sub -> hierarchy.equalsIgnoreCase(sub.hierarchy()))
        .forEach(config -> getHolidays(year, config, holidaySet, copyOfRange(args, 1, args.length))
        );
    }
  }

  /**
   * Iterates of the list of parsers and calls parse on each of them.
   *
   * @param year     the year to parse the holidays for
   * @param holidays the set to put the holidays into
   * @param config   the holiday configuration
   */
  private void parseHolidays(final int year, final Set<Holiday> holidays, final Holidays config) {
    getParsers(config).stream()
      .map(holidayParser -> holidayParser.parse(year, config))
      .flatMap(Collection::stream)
      .collect(toCollection(() -> holidays));
  }

  /**
   * Creates a list of parsers by reading the configuration and trying to find
   * an <code>HolidayParser</code> implementation for by XML class type.
   *
   * @param config the holiday configuration
   * @return A list of parsers to for this configuration.
   */
  private Collection<HolidayParser> getParsers(final Holidays config) {
    final Collection<HolidayParser> parsers = new HashSet<>();
    try {
      final Method[] declaredMethods = config.getClass().getDeclaredMethods();
      for (Method declaredMethod : declaredMethods) {
        if (declaredMethod.getGenericReturnType() instanceof ParameterizedType) {
          final ParameterizedType parameterizedType = (ParameterizedType) declaredMethod.getGenericReturnType();
          final Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
          final List<?> holidays = (List<?>) declaredMethod.invoke(config);
          if (!holidays.isEmpty()) {
            final HolidayParser holidayParser = instantiateParser(actualTypeArgument.getTypeName());
            if (holidayParser != null) {
              parsers.add(holidayParser);
            }
          }
        }
      }
    } catch (Exception e) {
      throw new IllegalStateException("Cannot create parsers.", e);
    }
    return parsers;
  }

  private HolidayParser instantiateParser(final String className) {

    final Cache.ValueHandler<HolidayParser> parserValueHandler = new Cache.ValueHandler<>() {
      @Override
      public String getKey() {
        return className;
      }

      @Override
      public HolidayParser createValue() {
        final String parserClassName = getManagerParameter().getProperty(PARSER_IMPL_PREFIX + className);
        if (parserClassName != null) {

          try {
            return (HolidayParser) loadClass(parserClassName).getConstructor().newInstance();
          } catch (ReflectiveOperationException | SecurityException e) {
            throw new IllegalStateException("Cannot create parsers.", e);
          }
        }

        return null;
      }
    };

    return parserCache.get(parserValueHandler);
  }

  /**
   * Logs the hierarchy structure.
   *
   * @param configuration Configuration to log hierarchy for.
   * @param level         an int.
   */
  protected static void logHierarchy(final Configuration configuration, final int level) {
    if (LOG.isDebugEnabled()) {
      final StringBuilder space = new StringBuilder();
      space.append("-".repeat(level));
      LOG.debug("{} {} ({}).", space, configuration.description(), configuration.hierarchy());
      configuration.subConfigurations()
        .forEach(
          config -> logHierarchy(config, level + 1)
        );
    }
  }

  /**
   * Validates the content of the provided configuration by checking for
   * multiple hierarchy entries within one configuration. It traverses down
   * the configuration tree.
   *
   * @param configuration a {@link Configuration} object.
   */
  protected static void validateConfigurationHierarchy(final Configuration configuration) {
    final Map<String, Integer> hierarchyMap = new HashMap<>();
    final Set<String> multipleHierarchies = new HashSet<>();

    configuration.subConfigurations()
      .forEach(subConfig -> {
          final String hierarchy = subConfig.hierarchy();
          if (!hierarchyMap.containsKey(hierarchy)) {
            hierarchyMap.put(hierarchy, 1);
          } else {
            int count = hierarchyMap.get(hierarchy);
            hierarchyMap.put(hierarchy, ++count);
            multipleHierarchies.add(hierarchy);
          }
        }
      );

    if (!multipleHierarchies.isEmpty()) {
      final StringBuilder msg = new StringBuilder();
      msg.append("Configuration for ")
        .append(configuration.hierarchy())
        .append(" contains  multiple SubConfigurations with the same hierarchy id. ");
      for (String hierarchy : multipleHierarchies) {
        msg.append(hierarchy)
          .append(" ")
          .append(hierarchyMap.get(hierarchy).toString())
          .append(" times ");
      }
      throw new IllegalArgumentException(msg.toString().trim());
    }
    configuration.subConfigurations().forEach(DefaultHolidayManager::validateConfigurationHierarchy);
  }

  /**
   * Creates the configuration hierarchy for the provided configuration.
   *
   * @param configuration     the full configuration
   * @param calendarHierarchy the calendars hierarchy
   * @return configuration hierarchy
   */
  private static CalendarHierarchy createConfigurationHierarchy(final Configuration configuration, final CalendarHierarchy calendarHierarchy) {
    final CalendarHierarchy hierarchy = new CalendarHierarchy(calendarHierarchy, configuration.hierarchy());
    hierarchy.setFallbackDescription(configuration.description());
    configuration.subConfigurations()
      .forEach(subConfiguration -> {
          final CalendarHierarchy subHierarchy = createConfigurationHierarchy(subConfiguration, hierarchy);
          hierarchy.getChildren().put(subHierarchy.getId(), subHierarchy);
        }
      );
    return hierarchy;
  }
}
