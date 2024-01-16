package de.focus_shift.jollyday.core.impl;

import de.focus_shift.jollyday.core.CalendarHierarchy;
import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.spi.Configuration;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.util.ClassLoadingUtil;
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
   * Parser cache by XML class name.
   */
  private final Map<String, HolidayParser> parserCache = new HashMap<>();

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
  public Set<Holiday> getHolidays(int year, final String... args) {
    Set<Holiday> holidaySet = Collections.synchronizedSet(new HashSet<>());
    getHolidays(year, configuration, holidaySet, args);
    return holidaySet;
  }

  /**
   * {@inheritDoc}
   * <p>
   * Calls <code>getHolidays(year, args)</code> for each year within the
   * interval and returns a list of holidays which are then contained in the
   * interval.
   */
  @Override
  public Set<Holiday> getHolidays(LocalDate startDateInclusive, LocalDate endDateInclusive, final String... args) {
    Objects.requireNonNull(startDateInclusive, "startDateInclusive is null");
    Objects.requireNonNull(endDateInclusive, "endInclusive is null");
    final Set<Holiday> holidays = new HashSet<>();
    for (int year = startDateInclusive.getYear(); year <= endDateInclusive.getYear(); year++) {
      final Set<Holiday> yearHolidays = getHolidays(year, args);
      for (Holiday h : yearHolidays) {
        if (!startDateInclusive.isAfter(h.getDate()) && !endDateInclusive.isBefore(h.getDate())) {
          holidays.add(h);
        }
      }
    }
    return holidays;
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
  private void getHolidays(int year, final Configuration configuration, Set<Holiday> holidaySet, final String... args) {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Adding holidays for {}", configuration.description());
    }
    parseHolidays(year, holidaySet, configuration.holidays());
    if (args != null && args.length > 0) {
      final String hierarchy = args[0];
      configuration.subConfigurations()
        .filter(sub -> hierarchy.equalsIgnoreCase(sub.hierarchy()))
        .forEach(config ->
          getHolidays(year, config, holidaySet, copyOfRange(args, 1, args.length))
        );
    }
  }

  /**
   * Copies the specified range from the original array to a new array. This
   * is a replacement for Java 1.6 Arrays.copyOfRange() specialized in String.
   *
   * @param original the original array to copy range from
   * @param from     the start of the range to copy from the original array
   * @param to       the inclusive end of the range to copy from the original array
   * @return the copied range
   */
  private String[] copyOfRange(final String[] original, int from, int to) {
    int newLength = to - from;
    if (newLength < 0) {
      throw new IllegalArgumentException(from + " > " + to);
    }
    final String[] copy = new String[newLength];
    System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
    return copy;
  }

  /**
   * Iterates of the list of parsers and calls parse on each of them.
   *
   * @param year     the year to parse the holidays for
   * @param holidays the set to put the holidays into
   * @param config   the holiday configuration
   */
  private void parseHolidays(int year, Set<Holiday> holidays, final Holidays config) {
    final Collection<HolidayParser> parsers = getParsers(config);
    for (HolidayParser p : parsers) {
      holidays.addAll(p.parse(year, config));
    }
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

  private HolidayParser instantiateParser(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
    HolidayParser holidayParser = parserCache.get(className);
    if (holidayParser == null) {
      final String propName = PARSER_IMPL_PREFIX + className;
      final String parserClassName = getManagerParameter().getProperty(propName);
      if (parserClassName != null) {
        final Class<?> parserClass = ClassLoadingUtil.loadClass(parserClassName);
        holidayParser = (HolidayParser) parserClass.getConstructor().newInstance();
        parserCache.put(className, holidayParser);
      }
    }
    return holidayParser;
  }

  /**
   * Logs the hierarchy structure.
   *
   * @param configuration Configuration to log hierarchy for.
   * @param level         an int.
   */
  protected static void logHierarchy(final Configuration configuration, int level) {
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
   * @param c a {@link Configuration} object.
   */
  protected static void validateConfigurationHierarchy(final Configuration c) {
    final Map<String, Integer> hierarchyMap = new HashMap<>();
    final Set<String> multipleHierarchies = new HashSet<>();
    c.subConfigurations().forEach(subConfig -> {
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
        .append(c.hierarchy())
        .append(" contains  multiple SubConfigurations with the same hierarchy id. ");
      for (String hierarchy : multipleHierarchies) {
        msg.append(hierarchy)
          .append(" ")
          .append(hierarchyMap.get(hierarchy).toString())
          .append(" times ");
      }
      throw new IllegalArgumentException(msg.toString().trim());
    }
    c.subConfigurations().forEach(DefaultHolidayManager::validateConfigurationHierarchy);
  }

  /**
   * Creates the configuration hierarchy for the provided configuration.
   *
   * @param c the full configuration
   * @param h the calendars hierarchy
   * @return configuration hierarchy
   */
  private static CalendarHierarchy createConfigurationHierarchy(final Configuration c, final CalendarHierarchy h) {
    final CalendarHierarchy hierarchy = new CalendarHierarchy(h, c.hierarchy());
    hierarchy.setFallbackDescription(c.description());
    c.subConfigurations().forEach(sub -> {
        final CalendarHierarchy subHierarchy = createConfigurationHierarchy(sub, hierarchy);
        hierarchy.getChildren().put(subHierarchy.getId(), subHierarchy);
      }
    );
    return hierarchy;
  }
}
