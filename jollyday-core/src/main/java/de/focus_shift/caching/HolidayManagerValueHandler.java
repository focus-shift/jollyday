package de.focus_shift.caching;

import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameter;
import de.focus_shift.datasource.ConfigurationDataSource;
import de.focus_shift.datasource.ConfigurationDataSourceManager;
import de.focus_shift.util.Cache;
import de.focus_shift.util.ClassLoadingUtil;

/**
 * Creates the {@link Cache.ValueHandler} which constructs a {@link HolidayManager}.
 */
public class HolidayManagerValueHandler implements Cache.ValueHandler<HolidayManager> {

  private final ManagerParameter parameter;
  private final String managerImplClassName;

  /**
   * Manager for providing configuration data sources which return the holiday
   * data.
   */
  private final ConfigurationDataSourceManager configurationDataSourceManager = new ConfigurationDataSourceManager();

  /**
   * Utility to load classes.
   */
  private final ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

  public HolidayManagerValueHandler(final ManagerParameter parameter, final String managerImplClassName) {
    this.parameter = parameter;
    this.managerImplClassName = managerImplClassName;
  }

  @Override
  public String getKey() {
    return parameter.createCacheKey();
  }

  @Override
  public HolidayManager createValue() {
    final HolidayManager manager = instantiateManagerImpl(managerImplClassName);
    final ConfigurationDataSource configurationDataSource = configurationDataSourceManager.getConfigurationDataSource(parameter);
    manager.setConfigurationDataSource(configurationDataSource);
    manager.init(parameter);
    return manager;
  }

  /**
   * Instantiates the manager implementing class.
   *
   * @param managerImplClassName the managers class name
   * @return the implementation class instantiated
   */
  private HolidayManager instantiateManagerImpl(String managerImplClassName) {
    try {
      final Class<?> managerImplClass = classLoadingUtil.loadClass(managerImplClassName);
      final Object managerImplObject = managerImplClass.getDeclaredConstructor().newInstance();
      return HolidayManager.class.cast(managerImplObject);
    } catch (Exception e) {
      throw new IllegalStateException("Cannot create manager class " + managerImplClassName, e);
    }
  }

}
