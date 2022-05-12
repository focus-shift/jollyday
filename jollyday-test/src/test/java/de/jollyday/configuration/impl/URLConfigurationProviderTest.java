package de.focus_shift.configuration.impl;

import java.util.Properties;

import de.focus_shift.configuration.ConfigurationProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class URLConfigurationProviderTest {

  private ConfigurationProvider urlConfigurationProvider = new URLConfigurationProvider();

  @Test
  public void testPutConfigurationWithPropertyNotSet() {
    Properties props = urlConfigurationProvider.getProperties();
    assertTrue(props.isEmpty());
  }

  @AfterEach
  public void teardown() {
    System.clearProperty(ConfigurationProvider.CONFIG_URLS_PROPERTY);
  }

  @Test
  public void testPutConfigurationWithPropertySetEmpty() {
    System.setProperty(ConfigurationProvider.CONFIG_URLS_PROPERTY, "");
    Properties props = urlConfigurationProvider.getProperties();
    assertTrue(props.isEmpty());
  }

  @Test
  public void testPutConfigurationWithPropertyWithIllegalURL() {
    System.setProperty(ConfigurationProvider.CONFIG_URLS_PROPERTY, "TestIllegalData");
    Properties props = urlConfigurationProvider.getProperties();
    assertTrue(props.isEmpty());
  }

  @Test
  public void testPutConfigurationWithPropertyWithCorrectURL() {
    System.setProperty(ConfigurationProvider.CONFIG_URLS_PROPERTY, "file:./src/test/resources/url.load.properties");
    Properties props = urlConfigurationProvider.getProperties();
    assertFalse(props.isEmpty());
    assertEquals("de.focus_shift.impl.DefaultHolidayManager", props.getProperty("manager.impl.test"), "Wrong new property.");
    assertEquals("ManagerOverloaded", props.getProperty("manager.impl"), "Wrong overloaded property.");
  }

}
