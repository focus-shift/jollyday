package de.focus_shift.jollyday.core.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Properties;

import static de.focus_shift.jollyday.core.configuration.CustomConfigurationProvider.CONFIG_PROVIDERS_PROPERTY;
import static org.assertj.core.api.Assertions.assertThat;

class CustomConfigurationProviderTest {

  private CustomConfigurationProvider sut;

  @BeforeEach
  void setUp() {
    sut = new CustomConfigurationProvider();
  }

  @ParameterizedTest
  @CsvSource(value = {
    "de.focus_shift.jollyday.core.configuration.CustomConfigurationProviderTest$TestProvider:value",
    "de.focus_shift.jollyday.core.configuration.CustomConfigurationProviderTest$TestProvider,de.focus_shift.jollyday.core.configuration.CustomConfigurationProviderTest$SecondTestProvider:second-value",
    "de.focus_shift.jollyday.core.configuration.CustomConfigurationProviderTest$TestProvider, java.lang.String: value",
  }, delimiter = ':')
  void ensuresToOverridePropertiesAndIgnoreExceptions(final String classList, final String property) {
    System.setProperty(CONFIG_PROVIDERS_PROPERTY, classList);

    final Properties properties = sut.getProperties();
    assertThat(properties.getProperty("key")).isEqualTo(property);

    System.clearProperty(CONFIG_PROVIDERS_PROPERTY);
  }

  @Test
  void ensuresToDoNothingIfConfigProvidersPropertyIsNotSet() {
    System.clearProperty(CONFIG_PROVIDERS_PROPERTY);

    final Properties properties = sut.getProperties();
    assertThat(properties).isEmpty();
  }

  @Test
  void ensuresCorrectConfigProvidersPropertyKey() {
    assertThat(CONFIG_PROVIDERS_PROPERTY).isEqualTo("de.focus_shift.jollyday.config.providers");
  }

  static class TestProvider implements ConfigurationProvider {

    private final Properties properties = new Properties();

    TestProvider() {
      properties.setProperty("key", "value");
    }

    @Override
    public Properties getProperties() {
      return properties;
    }
  }

  static class SecondTestProvider implements ConfigurationProvider {

    private final Properties properties = new Properties();

    SecondTestProvider() {
      properties.setProperty("key", "second-value");
    }

    @Override
    public Properties getProperties() {
      return properties;
    }
  }
}
