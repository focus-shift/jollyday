package de.focus_shift.jackson;

import de.focus_shift.ManagerParameter;
import de.focus_shift.spi.Configuration;
import de.focus_shift.spi.ConfigurationService;

import java.io.InputStream;
import java.net.URL;


public class JacksonConfigurationService implements ConfigurationService {

  private final XMLUtil xmlUtil = new XMLUtil();

  @Override
  public Configuration getConfiguration(ManagerParameter parameter) {
    final URL resourceUrl = parameter.createResourceUrl();
    try (final InputStream inputStream = resourceUrl.openStream()) {
      return new JacksonConfiguration(xmlUtil.unmarshallConfiguration(inputStream));
    } catch (Exception e) {
      throw new IllegalStateException("Cannot instantiate configuration from URL '" + resourceUrl + "'.", e);
    }
  }
}
