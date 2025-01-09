package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConfigurationProviderManagerTest {

  @Mock
  private ClasspathConfigurationProvider classpathConfigurationProvider;
  @Mock
  private URLConfigurationProvider urlConfigurationProvider;

  @InjectMocks
  private final ConfigurationProviderManager sut = new ConfigurationProviderManager();

  private final ManagerParameter managerParameter = ManagerParameters.create((String) null);

  @AfterEach
  void teardown() {
    System.clearProperty(CustomConfigurationProvider.CONFIG_PROVIDERS_PROPERTY);
  }

  @Test
  void testGetPropertiesWithEmptyProvidersList() {
    System.setProperty(CustomConfigurationProvider.CONFIG_PROVIDERS_PROPERTY, "");
    sut.mergeConfigurationProperties(managerParameter);
    assertResult(managerParameter);
  }

  @Test
  void testGetPropertiesWithWrongClass() {
    System.setProperty(CustomConfigurationProvider.CONFIG_PROVIDERS_PROPERTY, "java.lang.String");
    sut.mergeConfigurationProperties(managerParameter);
    assertResult(managerParameter);
  }

  @Test
  void testGetPropertiesWithCorrectClass() {
    System.setProperty(CustomConfigurationProvider.CONFIG_PROVIDERS_PROPERTY, "de.focus_shift.jollyday.core.configuration.TestProvider");
    sut.mergeConfigurationProperties(managerParameter);
    assertResult(managerParameter);
    assertThat(managerParameter.getProperty("key")).isEqualTo("value");
  }

  @Test
  void testGetPropertiesWithWrongAndCorrectClass() {
    System.setProperty(CustomConfigurationProvider.CONFIG_PROVIDERS_PROPERTY, "de.focus_shift.jollyday.core.configuration.TestProvider, java.lang.String");
    sut.mergeConfigurationProperties(managerParameter);
    assertResult(managerParameter);
    assertThat(managerParameter.getProperty("key")).isEqualTo("value");
  }

  @Test
  void testGetPropertiesWithManualOverride() {
    managerParameter.setProperty("MANUAL_KEY", "MANUAL_VALUE");
    managerParameter.setProperty("manager.impl", "NewImpl");

    sut.mergeConfigurationProperties(managerParameter);

    assertResult(managerParameter);
    assertThat(managerParameter.getProperty("MANUAL_KEY")).isEqualTo("MANUAL_VALUE");
    assertThat(managerParameter.getProperty("manager.impl")).isEqualTo("NewImpl");
  }

  private void assertResult(ManagerParameter parameter) {
    assertThat(parameter).isNotNull();
    verify(classpathConfigurationProvider).getProperties();
    verify(urlConfigurationProvider).getProperties();
  }
}
