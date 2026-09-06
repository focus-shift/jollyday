package de.focus_shift.jollyday.core.impl;

import de.focus_shift.jollyday.core.CalendarHierarchy;
import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.caching.Cache;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.spi.HolidayCalendarConfiguration;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.Year;
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
   * Caches all {@link HolidayParser} instances actually used by the HolidayManager
   * Bounded to 100 entries to prevent memory leaks.
   */
  private final Cache<HolidayParser> parserCache = new Cache<>(100);

  /**
   * Caches the instance based country specific holidays so that e.g.
   * the created HolidayManager via
   * {@code HolidayManager.getInstance(create("de"))} only contains the german holidays.
   * <p>
   * This is also the reason this cache is not static.
   * If it was static all holidays over all holiday manager instances would be cached,
   * but only the german holidays are important for the german holiday manager, so only save them.
   */
  private final Cache<Set<Holiday>> holidayCache = new Cache<>(100);

  /**
   * HolidayCalendarConfiguration parsed on initialization.
   */
  protected HolidayCalendarConfiguration holidayCalendarConfiguration;

  /**
   * {@inheritDoc}
   * <p>
   * Initializes the DefaultHolidayManager by loading the holidays XML file as resource
   * from the classpath. When the XML file is found it will be unmarshalled
   * with JAXB to some Java classes.
   */
  @Override
  public void doInit() {
    holidayCalendarConfiguration = getConfigurationService().getHolidayCalendarConfiguration(getManagerParameter());
    validateConfigurationHierarchy(holidayCalendarConfiguration);
    logHierarchy(holidayCalendarConfiguration, 0);
  }

  /**
   * {@inheritDoc}
   * <p>
   * Calls
   * <code>Set&lt;LocalDate&gt; getHolidays(Year year, Configuration c, String... args)</code>
   * with the configuration from initialization.
   */
  @Override
  public @NonNull Set<Holiday> getHolidays(@NonNull final Year year, @NonNull final String... args) {

    final StringBuilder keyBuilder = new StringBuilder();
    keyBuilder.append(year);
    for (String arg : args) {
      keyBuilder.append("_");
      keyBuilder.append(arg);
    }

    final Cache.ValueHandler<Set<Holiday>> holidayValueHandler = new Cache.ValueHandler<>() {

      @Override
      public @NonNull String getKey() {
        return keyBuilder.toString();
      }

      @Override
      public @NonNull Set<Holiday> createValue() {
        final Set<Holiday> holidaySet = Collections.synchronizedSet(new HashSet<>());
        getHolidays(year, holidayCalendarConfiguration, holidaySet, args);
        return holidaySet;
      }
    };

    return holidayCache.get(holidayValueHandler);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public @NonNull Set<Holiday> getHolidays(@NonNull final Year year, @NonNull final HolidayType holidayType, @NonNull final String... args) {
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
  public @NonNull Set<Holiday> getHolidays(@NonNull final LocalDate startDateInclusive, @NonNull final LocalDate endDateInclusive, @NonNull final String... args) {
    Objects.requireNonNull(startDateInclusive, "startDateInclusive is null");
    Objects.requireNonNull(endDateInclusive, "endDateInclusive is null");

    return rangeClosed(startDateInclusive.getYear(), endDateInclusive.getYear())
      .mapToObj(Year::of)
      .map(year -> getHolidays(year, args))
      .flatMap(Collection::stream)
      .filter(holiday -> !startDateInclusive.isAfter(holiday.getDate()) && !endDateInclusive.isBefore(holiday.getDate()))
      .collect(toUnmodifiableSet());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public @NonNull Set<Holiday> getHolidays(@NonNull final LocalDate startDateInclusive, @NonNull final LocalDate endDateInclusive, @NonNull final HolidayType holidayType, @NonNull final String... args) {
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
  public @NonNull CalendarHierarchy getCalendarHierarchy() {
    return createConfigurationHierarchy(holidayCalendarConfiguration, null);
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
  private void getHolidays(@NonNull final Year year, @NonNull final HolidayCalendarConfiguration configuration, @NonNull final Set<Holiday> holidaySet, @NonNull final String... args) {
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
  private void parseHolidays(@NonNull final Year year, @NonNull final Set<Holiday> holidays, @NonNull final HolidayConfigurations config) {
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
  private @NonNull Collection<HolidayParser> getParsers(@NonNull final HolidayConfigurations config) {
    final Collection<HolidayParser> parsers = new HashSet<>();
    try {
      final Method[] declaredMethods = config.getClass().getDeclaredMethods();
      for (Method declaredMethod : declaredMethods) {
        if (declaredMethod.getGenericReturnType() instanceof ParameterizedType parameterizedType) {
          final Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
          final List<?> holidays = (List<?>) declaredMethod.invoke(config);
          if (!holidays.isEmpty()) {
            parsers.add(instantiateParser(actualTypeArgument.getTypeName()));
          }
        }
      }
    } catch (Exception e) {
      throw new IllegalStateException("Cannot create parsers.", e);
    }
    return parsers;
  }

  private @NonNull HolidayParser instantiateParser(@NonNull final String className) {

    final Cache.ValueHandler<HolidayParser> parserValueHandler = new Cache.ValueHandler<>() {
      @Override
      public @NonNull String getKey() {
        return className;
      }

      @Override
      public @NonNull HolidayParser createValue() {
        final String parserClassName = getManagerParameter().getParserImplClassName(className);
        try {
          return (HolidayParser) loadClass(parserClassName).getConstructor().newInstance();
        } catch (ReflectiveOperationException | SecurityException e) {
          throw new IllegalStateException("Cannot create parsers.", e);
        }
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
  protected static void logHierarchy(@NonNull final HolidayCalendarConfiguration configuration, final int level) {
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
   * @param configuration a {@link HolidayCalendarConfiguration} object.
   */
  protected static void validateConfigurationHierarchy(@NonNull final HolidayCalendarConfiguration configuration) {
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
  private static @NonNull CalendarHierarchy createConfigurationHierarchy(@NonNull final HolidayCalendarConfiguration configuration, @Nullable final CalendarHierarchy calendarHierarchy) {
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
