package de.focus_shift.datasource;

import de.focus_shift.ManagerParameter;
import de.focus_shift.ManagerParameters;
import de.focus_shift.jaxb.JaxbConfigurationService;
import de.focus_shift.spi.ConfigurationService;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigurationServiceManagerTest {

  private final ConfigurationServiceManager configurationServiceManager = new ConfigurationServiceManager();
  private final Properties properties = new Properties();
  private final ManagerParameter managerParameter = ManagerParameters.create((String) null, properties);

  @Test
  void testGetConfigurationServiceMissingConfig() {
    assertThrows(IllegalStateException.class, () -> configurationServiceManager.getConfigurationService(managerParameter));
  }

  @Test
  void testGetConfigurationServiceMissingClass() {
    managerParameter.setProperty(ManagerParameter.CONFIGURATION_DATASOURCE_IMPL_CLASS, "ThisIsNoClass");
    assertThrows(IllegalStateException.class, () -> configurationServiceManager.getConfigurationService(managerParameter));
  }

  @Test
  void testGetConfigurationServiceMissingConstructor() {
    managerParameter.setProperty(ManagerParameter.CONFIGURATION_DATASOURCE_IMPL_CLASS, "java.util.Calendar");
    assertThrows(IllegalStateException.class, () -> configurationServiceManager.getConfigurationService(managerParameter));
  }

  @Test
  void testGetConfigurationServiceXmlFileDataSource() {
    managerParameter.setProperty(ManagerParameter.CONFIGURATION_DATASOURCE_IMPL_CLASS, JaxbConfigurationService.class.getName());
    ConfigurationService datasource = configurationServiceManager.getConfigurationService(managerParameter);
    assertNotNull(datasource, "Missing datasource.");
    assertEquals(JaxbConfigurationService.class, datasource.getClass(), "Unexpected class.");
  }
}
