package de.focus_shift.jollyday.core.parameter;

import de.focus_shift.jollyday.core.ManagerParameter;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Properties;

public abstract class BaseManagerParameter implements ManagerParameter {

  private Properties properties = new Properties();

  BaseManagerParameter(@Nullable final Properties properties) {
    if (properties != null) {
      this.properties.putAll(properties);
    }
  }

  @Override
  public void mergeProperties(@Nullable final Properties properties) {
    if (properties != null) {
      final Properties mergedProperties = new Properties();
      mergedProperties.putAll(properties);
      mergedProperties.putAll(this.properties);
      this.properties = mergedProperties;
    }
  }

  @Override
  public @Nullable String getProperty(@NonNull String key) {
    return properties.getProperty(key);
  }

  @Override
  public void setProperty(@NonNull String key, @NonNull String value) {
    this.properties.setProperty(key, value);
  }

  @Override
  public @Nullable String getManagerImplClassName() {
    return getProperty(MANAGER_IMPL_CLASS_PREFIX);
  }

  @Override
  public @Nullable String getConfigurationServiceImplClassName() {
    return getProperty(CONFIGURATION_SERVICE_IMPL);
  }
}
