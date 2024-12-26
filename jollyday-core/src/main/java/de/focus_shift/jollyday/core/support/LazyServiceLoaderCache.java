package de.focus_shift.jollyday.core.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.String.format;

public class LazyServiceLoaderCache<S> {

  private static final Logger LOG = LoggerFactory.getLogger(LazyServiceLoaderCache.class.getName());

  private final Class<S> clz;
  private List<S> services;

  public LazyServiceLoaderCache(final Class<S> clz) {
    this.clz = clz;
  }

  public List<S> getServices() {
    if (services == null) {
      loadServices();
    }
    return services;
  }

  private synchronized void loadServices() {
    services = new CopyOnWriteArrayList<>();
    try {
      for (S s : ServiceLoader.load(clz)) {
        services.add(s);
      }
    } catch (ServiceConfigurationError serviceConfigurationError) {
      final String message = format("Cannot load services of type [%s].%n    %s",
        clz.getName(),
        serviceConfigurationError.getMessage()
      );
      LOG.warn(message);
    }
  }
}
