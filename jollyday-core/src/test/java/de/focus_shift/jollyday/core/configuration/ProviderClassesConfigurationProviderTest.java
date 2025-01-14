package de.focus_shift.jollyday.core.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Properties;

import static de.focus_shift.jollyday.core.configuration.ProviderClassesConfigurationProvider.CONFIG_PROVIDERS_PROPERTY;
import static org.assertj.core.api.Assertions.assertThat;

class ProviderClassesConfigurationProviderTest {

  private ProviderClassesConfigurationProvider sut;

  @BeforeEach
  void setUp() {
    sut = new ProviderClassesConfigurationProvider();
  }

  @ParameterizedTest
  @CsvSource(value = {
    "de.focus_shift.jollyday.core.configuration.ProviderClassesConfigurationProviderTest$TestProvider:value",
    "de.focus_shift.jollyday.core.configuration.ProviderClassesConfigurationProviderTest$TestProvider,de.focus_shift.jollyday.core.configuration.ProviderClassesConfigurationProviderTest$SecondTestProvider:second-value",
    "de.focus_shift.jollyday.core.configuration.ProviderClassesConfigurationProviderTest$TestProvider,java.lang.String:value",
  }, delimiter = ':')
  void ensuresToOverridePropertiesAndIgnoreExceptions(final String classList, final String propertyValue) {
    System.setProperty(CONFIG_PROVIDERS_PROPERTY, classList);

    final Properties properties = sut.getProperties();
    assertThat(properties.getProperty("key")).isEqualTo(propertyValue);

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
