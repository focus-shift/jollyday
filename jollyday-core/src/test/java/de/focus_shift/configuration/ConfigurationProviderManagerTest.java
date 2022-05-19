package de.focus_shift.configuration;

import de.focus_shift.ManagerParameter;
import de.focus_shift.ManagerParameters;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static de.focus_shift.configuration.ConfigurationProvider.CONFIG_PROVIDERS_PROPERTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConfigurationProviderManagerTest {

  @Mock
  private ConfigurationProvider defaultConfigurationProvider;
  @Mock
  private ConfigurationProvider urlConfigurationProvider;

  @InjectMocks
  private final ConfigurationProviderManager configurationProviderManager = new ConfigurationProviderManager();

  private final ManagerParameter managerParameter = ManagerParameters.create((String) null);

  @AfterEach
  void teardown() {
    System.clearProperty(CONFIG_PROVIDERS_PROPERTY);
  }

  @Test
  void testGetPropertiesWithEmptyProvidersList() {
    System.setProperty(CONFIG_PROVIDERS_PROPERTY, "");
    configurationProviderManager.mergeConfigurationProperties(managerParameter);
    assertResult(managerParameter);
  }

  @Test
  void testGetPropertiesWithWrongClass() {
    System.setProperty(CONFIG_PROVIDERS_PROPERTY, "java.lang.String");
    configurationProviderManager.mergeConfigurationProperties(managerParameter);
    assertResult(managerParameter);
  }

  @Test
  void testGetPropertiesWithCorrectClass() {
    System.setProperty(CONFIG_PROVIDERS_PROPERTY, "de.focus_shift.configuration.TestProvider");
    configurationProviderManager.mergeConfigurationProperties(managerParameter);
    assertResult(managerParameter);
    assertEquals("value", managerParameter.getProperty("key"), "Wrong value for property: key");
  }

  @Test
  void testGetPropertiesWithWrongAndCorrectClass() {
    System.setProperty(CONFIG_PROVIDERS_PROPERTY, "de.focus_shift.configuration.TestProvider, java.lang.String");
    configurationProviderManager.mergeConfigurationProperties(managerParameter);
    assertResult(managerParameter);
    assertEquals("value", managerParameter.getProperty("key"), "Wrong value for property: key");
  }

  @Test
  void testGetPropertiesWithManualOverride() {
    managerParameter.setProperty("MANUAL_KEY", "MANUAL_VALUE");
    managerParameter.setProperty("manager.impl", "NewImpl");

    configurationProviderManager.mergeConfigurationProperties(managerParameter);

    assertResult(managerParameter);
    assertEquals("MANUAL_VALUE", managerParameter.getProperty("MANUAL_KEY"), "Wrong value for property: MANUAL_KEY");
    assertEquals("NewImpl", managerParameter.getProperty("manager.impl"), "Wrong value for property: manager.impl");
  }

  private void assertResult(ManagerParameter parameter) {
    assertNotNull(parameter);
    verify(defaultConfigurationProvider).getProperties();
    verify(urlConfigurationProvider).getProperties();
  }
}
