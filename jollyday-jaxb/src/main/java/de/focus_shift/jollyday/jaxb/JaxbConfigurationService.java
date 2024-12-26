package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.spi.Configuration;
import de.focus_shift.jollyday.core.spi.ConfigurationService;

import java.io.InputStream;
import java.net.URL;

/**
 * see {@link ConfigurationService}
 */
public class JaxbConfigurationService implements ConfigurationService {

  private static final JaxbXMLMapper xmlUtil = new JaxbXMLMapper();

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Configuration getConfiguration(ManagerParameter parameter) {
    final URL resourceUrl = parameter.createResourceUrl();
    try (final InputStream inputStream = resourceUrl.openStream()) {
      return new JaxbConfiguration(xmlUtil.unmarshallConfiguration(inputStream));
    } catch (Exception e) {
      throw new IllegalStateException("Cannot instantiate configuration from URL '" + resourceUrl + "'.", e);
    }
  }
}
