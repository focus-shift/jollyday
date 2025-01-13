package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static de.focus_shift.jollyday.core.configuration.CustomConfigurationProvider.CONFIG_PROVIDERS_PROPERTY;
import static de.focus_shift.jollyday.core.configuration.URLConfigurationProvider.CONFIG_URLS_PROPERTY;
import static org.assertj.core.api.Assertions.assertThat;

class ConfigurationProviderManagerTest {

  private ConfigurationProviderManager sut;

  @BeforeEach
  void setUp() {
    sut = new ConfigurationProviderManager();
  }

  @Test
  void ensuresUrlConfigurationProviderOverridesBaseClasspathConfigurationProvider() {
    System.setProperty(CONFIG_URLS_PROPERTY, "file:./src/test/resources/URLConfigurationProviderTest.properties");
    System.clearProperty(CONFIG_PROVIDERS_PROPERTY);

    final ManagerParameter managerParameter = ManagerParameters.create((String) null);
    sut.mergeConfigurationProperties(managerParameter);

    assertThat(managerParameter.getProperty("manager.impl")).isEqualTo("UrlConfigurationProviderOverloaded");
    assertThat(managerParameter.getProperty("manager.impl.url")).isEqualTo("UrlConfigurationProviderOverloaded.url");
    System.clearProperty(CONFIG_URLS_PROPERTY);
  }

  @Test
  void ensuresCustomConfigurationProviderOverridesUrlConfigurationProvider() {
    System.setProperty(CONFIG_URLS_PROPERTY, "file:./src/test/resources/URLConfigurationProviderTest.properties");
    System.setProperty(CONFIG_PROVIDERS_PROPERTY, "de.focus_shift.jollyday.core.configuration.ConfigurationProviderManagerTest$CustomConfigurationProvider");

    final ManagerParameter managerParameter = ManagerParameters.create((String) null);
    sut.mergeConfigurationProperties(managerParameter);

    assertThat(managerParameter.getProperty("manager.impl")).isEqualTo("CustomConfigurationProviderOverloaded");
    assertThat(managerParameter.getProperty("manager.impl.url")).isEqualTo("UrlConfigurationProviderOverloaded.url");
    assertThat(managerParameter.getProperty("manager.impl.custom")).isEqualTo("CustomConfigurationProviderOverloaded.custom");

    System.clearProperty(CONFIG_URLS_PROPERTY);
    System.clearProperty(CONFIG_PROVIDERS_PROPERTY);
  }

  @Test
  void ensuresManagerParametersOverridesCustomConfigurationProvider() {

    System.setProperty(CONFIG_URLS_PROPERTY, "file:./src/test/resources/URLConfigurationProviderTest.properties");
    System.setProperty(CONFIG_PROVIDERS_PROPERTY, "de.focus_shift.jollyday.core.configuration.ConfigurationProviderManagerTest$CustomConfigurationProvider");

    final ManagerParameter managerParameter = ManagerParameters.create((String) null);
    managerParameter.setProperty("manager.impl", "ManagerParameterOverloaded");
    managerParameter.setProperty("manager.impl.manager", "ManagerParameterOverloaded.manager");

    sut.mergeConfigurationProperties(managerParameter);

    assertThat(managerParameter.getProperty("manager.impl")).isEqualTo("ManagerParameterOverloaded");
    assertThat(managerParameter.getProperty("manager.impl.manager")).isEqualTo("ManagerParameterOverloaded.manager");

    System.clearProperty(CONFIG_URLS_PROPERTY);
    System.clearProperty(CONFIG_PROVIDERS_PROPERTY);
  }

  static class CustomConfigurationProvider implements ConfigurationProvider {

    private final Properties properties = new Properties();

    CustomConfigurationProvider() {
      properties.setProperty("manager.impl", "CustomConfigurationProviderOverloaded");
      properties.setProperty("manager.impl.custom", "CustomConfigurationProviderOverloaded.custom");
    }

    @Override
    public Properties getProperties() {
      return properties;
    }
  }
}
