package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.spi.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JacksonConfigurationServiceTest {

  private JacksonConfigurationService service;

  @BeforeEach
  void setUp() {
    service = new JacksonConfigurationService();
  }

  @Test
  void ensureToGetConfiguration() throws Exception {
    // Arrange
    final ManagerParameter parameter = mock(ManagerParameter.class);
    final URL url = mock(URL.class);
    when(parameter.createResourceUrl()).thenReturn(url);
    // Provide a minimal valid XML for unmarshalling with correct namespace
    final String xml = "<Configuration xmlns=\"https://focus_shift.de/jollyday/schema/holiday\"></Configuration>";
    final InputStream stream = new ByteArrayInputStream(xml.getBytes());
    when(url.openStream()).thenReturn(stream);

    // Act & Assert
    final Configuration config = service.getConfiguration(parameter);
    assertThat(config).isNotNull();
  }

  @Test
  void ensureToGetConfigurationThrowsException() throws Exception {
    final ManagerParameter parameter = mock(ManagerParameter.class);
    final URL url = mock(URL.class);
    when(parameter.createResourceUrl()).thenReturn(url);
    when(url.openStream()).thenThrow(new RuntimeException("IO error"));

    assertThatThrownBy(() -> service.getConfiguration(parameter))
      .isInstanceOf(IllegalStateException.class)
      .hasMessageContaining("Cannot instantiate configuration from URL");
  }
}
