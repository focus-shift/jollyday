package de.focus_shift.jollyday.core.datasource;

import de.focus_shift.jollyday.core.spi.HolidayCalendarConfigurationService;
import de.focus_shift.jollyday.core.support.LazyServiceLoaderCache;

import java.util.List;

/**
 * This manager is responsible for instantiating
 * the provided implementation of the {@link HolidayCalendarConfigurationService}
 * which is used to access the holiday data.
 */
public class ConfigurationServiceManager {

  private final LazyServiceLoaderCache<HolidayCalendarConfigurationService> configurationServiceCache;

  public ConfigurationServiceManager(LazyServiceLoaderCache<HolidayCalendarConfigurationService> configurationServiceCache) {
    this.configurationServiceCache = configurationServiceCache;
  }

  public HolidayCalendarConfigurationService getConfigurationService(final String configurationServiceImplClassName) {
    return instantiateDataSource(configurationServiceImplClassName);
  }

  private HolidayCalendarConfigurationService instantiateDataSource(final String configurationServiceImplClassName) {

    final List<HolidayCalendarConfigurationService> services = configurationServiceCache.getServices();

    if (services.size() == 1) {
      return services.get(0);
    }

    if (services.size() > 1) {
      return services.stream()
        .filter(configurationService -> configurationServiceImplClassName.equals(configurationService.getClass().getName()))
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("Cannot instantiate datasource instance because there are two or more implementations available " + configurationServiceImplClassName));
    }

    throw new IllegalStateException("Cannot instantiate datasource instance because there is no implementations");
  }
}
