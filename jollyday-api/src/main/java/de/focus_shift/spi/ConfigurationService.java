package de.focus_shift.spi;

import de.focus_shift.ManagerParameter;

/**
 * Service to provide the serialised configuration from XML files.
 */
public interface ConfigurationService {
  Configuration getConfiguration(ManagerParameter parameter);
}
