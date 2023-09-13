package de.focus_shift.jollyday.core;

/**
 * Service to provide the serialised configuration from XML files.
 */
public interface ConfigurationService {
  Configuration getConfiguration(ManagerParameter parameter);
}
