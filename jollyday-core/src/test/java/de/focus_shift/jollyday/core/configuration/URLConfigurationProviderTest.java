package de.focus_shift.jollyday.core.configuration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static de.focus_shift.jollyday.core.configuration.ConfigurationProvider.CONFIG_URLS_PROPERTY;
import static org.assertj.core.api.Assertions.assertThat;

class URLConfigurationProviderTest {

  private final URLConfigurationProvider sut = new URLConfigurationProvider();

  @AfterEach
  void teardown() {
    System.clearProperty(CONFIG_URLS_PROPERTY);
  }

  @Test
  void testPutConfigurationWithPropertyNotSet() {
    final Properties props = sut.getProperties();
    assertThat(props).isEmpty();
  }

  @Test
  void testPutConfigurationWithPropertySetEmpty() {
    System.setProperty(CONFIG_URLS_PROPERTY, "");
    final Properties props = sut.getProperties();
    assertThat(props).isEmpty();
  }

  @Test
  void testPutConfigurationWithPropertyWithIllegalURL() {
    System.setProperty(CONFIG_URLS_PROPERTY, "TestIllegalData");
    final Properties props = sut.getProperties();
    assertThat(props).isEmpty();
  }

  @Test
  void testPutConfigurationWithPropertyWithCorrectURL() {
    System.setProperty(CONFIG_URLS_PROPERTY, "file:./src/test/resources/url.load.properties");
    final Properties props = sut.getProperties();
    assertThat(props).isNotEmpty();
    assertThat(props.getProperty("manager.impl.test")).isEqualTo("de.jollyday.impl.DefaultHolidayManager");
    assertThat(props.getProperty("manager.impl")).isEqualTo("ManagerOverloaded");
  }
}
