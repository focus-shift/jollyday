package de.focus_shift.jollyday.core.spi;

import de.focus_shift.jollyday.core.ManagerParameter;

/**
 * Service to provide the serialised configuration from XML files.
 */
public interface ConfigurationService {
  Configuration getConfiguration(ManagerParameter parameter);
}
