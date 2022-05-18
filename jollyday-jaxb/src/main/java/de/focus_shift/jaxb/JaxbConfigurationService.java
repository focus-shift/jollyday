package de.focus_shift.jaxb;

import de.focus_shift.ManagerParameter;
import de.focus_shift.spi.ConfigurationService;

import java.io.InputStream;
import java.net.URL;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbConfigurationService implements ConfigurationService {

  private final XMLUtil xmlUtil = new XMLUtil();

  @Override
  public de.focus_shift.spi.Configuration getConfiguration(ManagerParameter parameter) {
    final URL resourceUrl = parameter.createResourceUrl();
    try (final InputStream inputStream = resourceUrl.openStream()) {
      return new JaxbConfiguration(xmlUtil.unmarshallConfiguration(inputStream));
    } catch (Exception e) {
      throw new IllegalStateException("Cannot instantiate configuration from URL '" + resourceUrl + "'.", e);
    }
  }
}
