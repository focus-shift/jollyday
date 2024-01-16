package de.focus_shift.jollyday.core.configuration;

import java.util.Properties;

/**
 * This provider is used in {@link ConfigurationProviderManagerTest}
 */
public class TestProvider implements ConfigurationProvider {

  private final Properties properties = new Properties();

  public TestProvider() {
    properties.setProperty("key", "value");
  }

  @Override
  public Properties getProperties() {
    return properties;
  }
}
