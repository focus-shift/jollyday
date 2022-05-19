package de.focus_shift.datasource;

import de.focus_shift.ManagerParameter;
import de.focus_shift.ManagerParameters;
import de.focus_shift.jaxb.JaxbConfigurationService;
import de.focus_shift.spi.ConfigurationService;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigurationServiceManagerTest {

  private final ConfigurationServiceManager configurationServiceManager = new ConfigurationServiceManager();
  private final Properties properties = new Properties();
  private final ManagerParameter managerParameter = ManagerParameters.create((String) null, properties);

  @Test
  void testGetConfigurationServiceMissingConfig() {
    assertThatThrownBy(() -> configurationServiceManager.getConfigurationService(managerParameter))
      .isInstanceOf(IllegalStateException.class);
  }

  @Test
  void testGetConfigurationServiceMissingClass() {
    managerParameter.setProperty(ManagerParameter.CONFIGURATION_DATASOURCE_IMPL_CLASS, "ThisIsNoClass");
    assertThatThrownBy(() -> configurationServiceManager.getConfigurationService(managerParameter))
      .isInstanceOf(IllegalStateException.class);
  }

  @Test
  void testGetConfigurationServiceMissingConstructor() {
    managerParameter.setProperty(ManagerParameter.CONFIGURATION_DATASOURCE_IMPL_CLASS, "java.util.Calendar");
    assertThatThrownBy(() -> configurationServiceManager.getConfigurationService(managerParameter))
      .isInstanceOf(IllegalStateException.class);
  }

  @Test
  void testGetConfigurationServiceXmlFileDataSource() {
    managerParameter.setProperty(ManagerParameter.CONFIGURATION_DATASOURCE_IMPL_CLASS, JaxbConfigurationService.class.getName());
    final ConfigurationService datasource = configurationServiceManager.getConfigurationService(managerParameter);
    assertThat(datasource)
      .isNotNull()
      .isInstanceOf(JaxbConfigurationService.class);
  }
}
