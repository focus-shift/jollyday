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

  public ConfigurationService getConfigurationService() {
    return instantiateDataSource();
  }

  private ConfigurationService instantiateDataSource() {

    final List<ConfigurationService> services = configurationServiceCache.getServices();

    if (services.size() > 1) {
      throw new IllegalStateException("Cannot instantiate datasource instance because there are two or more implementations available " + services);
    }
    if (services.isEmpty()) {
      throw new IllegalStateException("Cannot instantiate datasource instance because there is no implementations");
    }

    return services.get(0);
  }
}
