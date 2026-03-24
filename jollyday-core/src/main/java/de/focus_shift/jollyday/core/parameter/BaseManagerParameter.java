package de.focus_shift.jollyday.core.parameter;

import de.focus_shift.jollyday.core.ManagerParameter;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
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

  public @NonNull Optional<String> getProperty(@NonNull String key) {
    return Optional.ofNullable(properties.getProperty(key));
  }

  @Override
  public void setProperty(@NonNull String key, @NonNull String value) {
    this.properties.setProperty(key, value);
  }

  @Override
  public @NonNull String getManagerImplClassName() {
    final Optional<String> managerImplClass = getProperty(MANAGER_IMPL_CLASS_PREFIX);
    if (managerImplClass.isEmpty()) {
      throw new IllegalStateException("Missing configuration '" + MANAGER_IMPL_CLASS_PREFIX + "'. Cannot create manager.");
    }
    return managerImplClass.get();
  }

  @Override
  public @NonNull String getParserImplClassName(@NonNull final String className) {
    final Optional<String> parserImplClass = getProperty(PARSER_IMPL_PREFIX + className);
    if (parserImplClass.isEmpty()) {
      throw new IllegalStateException("Cannot create parsers. No parser implementation defined for class " + className + " in properties with key " + PARSER_IMPL_PREFIX + className);
    }
    return parserImplClass.get();
  }

  @Override
  public @NonNull String getConfigurationServiceImplClassName() {
    final Optional<String> configurationServiceImplClass = getProperty(CONFIGURATION_SERVICE_IMPL);
    if (configurationServiceImplClass.isEmpty()) {
      throw new IllegalStateException("Missing configuration '" + CONFIGURATION_SERVICE_IMPL + "'. Cannot create configuration service.");
    }
    return configurationServiceImplClass.get();
  }
}
