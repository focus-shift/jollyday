package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigurationProviderManagerTest {

  private ConfigurationProviderManager sut;

  @BeforeEach
  void setUp() {
    sut = new ConfigurationProviderManager();
  }

  @Test
  void ensuresGetPropertiesWithManualOverride() {
    final ManagerParameter managerParameter = ManagerParameters.create((String) null);
    managerParameter.setProperty("MANUAL_KEY", "MANUAL_VALUE");
    managerParameter.setProperty("manager.impl", "NewImpl");

    sut.mergeConfigurationProperties(managerParameter);

    assertThat(managerParameter.getProperty("MANUAL_KEY")).isEqualTo("MANUAL_VALUE");
    assertThat(managerParameter.getProperty("manager.impl")).isEqualTo("NewImpl");
  }
}
