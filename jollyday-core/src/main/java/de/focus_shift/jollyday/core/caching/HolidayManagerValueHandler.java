package de.focus_shift.jollyday.core.caching;

import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.datasource.ConfigurationServiceManager;
import de.focus_shift.jollyday.core.ConfigurationService;
import de.focus_shift.jollyday.core.util.ClassLoadingUtil;

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
  private final ConfigurationServiceManager configurationServiceManager = new ConfigurationServiceManager();

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
    final ConfigurationService configurationService = configurationServiceManager.getConfigurationService(parameter);
    manager.setConfigurationService(configurationService);
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
      return (HolidayManager) managerImplClass.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new IllegalStateException("Cannot create manager class " + managerImplClassName, e);
    }
  }
}
