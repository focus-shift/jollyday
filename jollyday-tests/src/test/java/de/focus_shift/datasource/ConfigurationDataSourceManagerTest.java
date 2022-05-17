package de.focus_shift.datasource;

import de.focus_shift.ManagerParameter;
import de.focus_shift.ManagerParameters;
import de.focus_shift.jaxb.JaxbConfigurationService;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigurationDataSourceManagerTest {

  private final ConfigurationDataSourceManager configurationDataSourceManager = new ConfigurationDataSourceManager();
  private final Properties properties = new Properties();
  private final ManagerParameter managerParameter = ManagerParameters.create((String) null, properties);

  @Test
  void testGetConfigurationDataSourceMissingConfig() {
    assertThrows(IllegalStateException.class, () -> configurationDataSourceManager.getConfigurationDataSource(managerParameter));
  }

  @Test
  void testGetConfigurationDataSourceMissingClass() {
    managerParameter.setProperty(ManagerParameter.CONFIGURATION_DATASOURCE_IMPL_CLASS, "ThisIsNoClass");
    assertThrows(IllegalStateException.class, () -> configurationDataSourceManager.getConfigurationDataSource(managerParameter));
  }

  @Test
  void testGetConfigurationDataSourceMissingConstructor() {
    managerParameter.setProperty(ManagerParameter.CONFIGURATION_DATASOURCE_IMPL_CLASS, "java.util.Calendar");
    assertThrows(IllegalStateException.class, () -> configurationDataSourceManager.getConfigurationDataSource(managerParameter));
  }

  @Test
  void testGetConfigurationDataSourceXmlFileDataSource() {
    managerParameter.setProperty(ManagerParameter.CONFIGURATION_DATASOURCE_IMPL_CLASS, JaxbConfigurationService.class.getName());
    ConfigurationDataSource datasource = configurationDataSourceManager.getConfigurationDataSource(managerParameter);
    assertNotNull(datasource, "Missing datasource.");
    assertEquals(JaxbConfigurationService.class, datasource.getClass(), "Unexpected class.");
  }
}
