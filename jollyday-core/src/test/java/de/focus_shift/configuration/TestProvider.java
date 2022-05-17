package de.focus_shift.configuration;

import java.util.Properties;

class TestProvider implements ConfigurationProvider {

  private final Properties properties = new Properties();

  public TestProvider() {
    properties.setProperty("key", "value");
  }

  @Override
  public Properties getProperties() {
    return properties;
  }
}
