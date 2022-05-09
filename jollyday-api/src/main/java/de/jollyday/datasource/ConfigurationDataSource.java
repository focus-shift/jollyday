package de.jollyday.datasource;

import de.jollyday.ManagerParameter;
import de.jollyday.spi.Configuration;

/**
 * The interface for all holiday data providing datasources.
 *
 * @author sdiedrichsen
 */
public interface ConfigurationDataSource {
  Configuration getConfiguration(ManagerParameter parameter);
}
