package de.focus_shift.datasource;

import de.focus_shift.ManagerParameter;
import de.focus_shift.spi.Configuration;

/**
 * The interface for all holiday data providing datasources.
 *
 * @author sdiedrichsen
 */
public interface ConfigurationDataSource {
  Configuration getConfiguration(ManagerParameter parameter);
}
