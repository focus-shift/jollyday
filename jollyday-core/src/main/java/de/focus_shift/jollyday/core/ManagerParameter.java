package de.focus_shift.jollyday.core;

import org.jspecify.annotations.NonNull;

import java.net.URL;
import java.util.Optional;
import java.util.Properties;

public interface ManagerParameter {

  String MANAGER_IMPL_CLASS_PREFIX = "manager.impl";

  String CONFIGURATION_SERVICE_IMPL = "configuration.service.impl";

  void mergeProperties(final Properties properties);

  @NonNull Optional<String> getProperty(final String key);

  void setProperty(final String key, final String value);

  @NonNull String getManagerImplClassName();

  @NonNull String getConfigurationServiceImplClassName();

  @NonNull String createCacheKey();

  @NonNull String getDisplayName();

  @NonNull URL createResourceUrl();

}
