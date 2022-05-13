package de.focus_shift.datasource;

import de.focus_shift.ManagerParameter;
import de.focus_shift.ManagerParameters;
import de.focus_shift.datasource.impl.XmlFileDataSource;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigurationDataSourceManagerTest {

  ConfigurationDataSourceManager configurationDataSourceManager = new ConfigurationDataSourceManager();
  Properties properties = new Properties();
  ManagerParameter managerParameter = ManagerParameters.create((String) null, properties);

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
    managerParameter.setProperty(ManagerParameter.CONFIGURATION_DATASOURCE_IMPL_CLASS, XmlFileDataSource.class.getName());
    ConfigurationDataSource datasource = configurationDataSourceManager.getConfigurationDataSource(managerParameter);
    assertNotNull(datasource, "Missing datasource.");
    assertEquals(XmlFileDataSource.class, datasource.getClass(), "Unexpected class.");
  }
}
