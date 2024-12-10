package de.focus_shift.jollyday.core.datasource;

import de.focus_shift.jollyday.core.spi.ConfigurationService;
import de.focus_shift.jollyday.core.support.LazyServiceLoaderCache;

import java.util.List;

/**
 * This manager is responsible for instantiating
 * the provided implementation of the {@link ConfigurationService}
 * which is used to access the holiday data.
 */
public class ConfigurationServiceManager {

  private final LazyServiceLoaderCache<ConfigurationService> configurationServiceCache;

  public ConfigurationServiceManager(LazyServiceLoaderCache<ConfigurationService> configurationServiceCache) {
    this.configurationServiceCache = configurationServiceCache;
  }

  public ConfigurationService getConfigurationService(final String configurationServiceImplClassName) {
    return instantiateDataSource(configurationServiceImplClassName);
  }

  private ConfigurationService instantiateDataSource(final String configurationServiceImplClassName) {

    final List<ConfigurationService> services = configurationServiceCache.getServices();

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
