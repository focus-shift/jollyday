package de.focus_shift.jollyday.core.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static de.focus_shift.jollyday.core.configuration.URLConfigurationProvider.CONFIG_URLS_PROPERTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class URLConfigurationProviderTest {

  private URLConfigurationProvider sut;

  @BeforeEach
  void setUp() {
    sut = new URLConfigurationProvider();
  }

  @Test
  void ensuresToDoNothingIfConfigProvidersPropertyIsNotSet() {
    System.clearProperty(CONFIG_URLS_PROPERTY);

    final Properties properties = sut.getProperties();
    assertThat(properties).isEmpty();
  }

  @Test
  void testPutConfigurationWithPropertySetEmpty() {
    System.setProperty(CONFIG_URLS_PROPERTY, "");
    final Properties props = sut.getProperties();
    assertThat(props).isEmpty();
    System.clearProperty(CONFIG_URLS_PROPERTY);
  }

  @Test
  void testPutConfigurationWithPropertyWithIllegalURL() {
    System.setProperty(CONFIG_URLS_PROPERTY, "TestIllegalData");
    final Properties props = sut.getProperties();
    assertThat(props).isEmpty();
    System.clearProperty(CONFIG_URLS_PROPERTY);
  }

  @Test
  void ensuresThatCorrectUrlSyntaxWithNotAvailablePropertiesThrowsIllegalStateException() {
    System.setProperty(CONFIG_URLS_PROPERTY, "file:./src/test/resources/NotAvailable.properties");
    assertThrows(IllegalStateException.class, () -> sut.getProperties());
    System.clearProperty(CONFIG_URLS_PROPERTY);
  }

  @Test
  void testPutConfigurationWithPropertyWithCorrectURL() {
    System.setProperty(CONFIG_URLS_PROPERTY, "file:./src/test/resources/URLConfigurationProviderTest.properties");
    final Properties props = sut.getProperties();
    assertThat(props.getProperty("manager.impl.url")).isEqualTo("UrlConfigurationProviderOverloaded.url");
    assertThat(props.getProperty("manager.impl")).isEqualTo("UrlConfigurationProviderOverloaded");
    System.clearProperty(CONFIG_URLS_PROPERTY);
  }
}
