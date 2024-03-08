package de.focus_shift.jollyday.core;

import java.net.URL;
import java.util.Properties;

public interface ManagerParameter {

  String MANAGER_IMPL_CLASS_PREFIX = "manager.impl";

  void mergeProperties(final Properties properties);

  String getProperty(final String key);

  void setProperty(final String key, final String value);

  String getManagerImplClassName();

  String createCacheKey();

  String getDisplayName();

  URL createResourceUrl();

}
