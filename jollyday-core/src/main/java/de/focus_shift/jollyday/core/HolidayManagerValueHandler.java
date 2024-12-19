package de.focus_shift.jollyday.core;

import de.focus_shift.jollyday.core.caching.Cache;
import de.focus_shift.jollyday.core.datasource.ConfigurationServiceManager;
import de.focus_shift.jollyday.core.spi.ConfigurationService;
import de.focus_shift.jollyday.core.util.ClassLoadingUtil;

/**
 * Creates the {@link Cache.ValueHandler} which constructs and caches a {@link HolidayManager}.
 */
class HolidayManagerValueHandler implements Cache.ValueHandler<HolidayManager> {

  private final ManagerParameter parameter;
  private final String managerImplClassName;
  private final String configurationServiceImplClassName;
  private final ConfigurationServiceManager configurationServiceManager;

  HolidayManagerValueHandler(final ManagerParameter parameter,
                             final String managerImplClassName,
                             final String configurationServiceImplClassName,
                             final ConfigurationServiceManager configurationServiceManager) {
    this.parameter = parameter;
    this.managerImplClassName = managerImplClassName;
    this.configurationServiceImplClassName = configurationServiceImplClassName;
    this.configurationServiceManager = configurationServiceManager;
  }

  @Override
  public String getKey() {
    return parameter.createCacheKey();
  }

  @Override
  public HolidayManager createValue() {
    final HolidayManager manager = instantiateManagerImpl(managerImplClassName);

    final ConfigurationService configurationService = configurationServiceManager.getConfigurationService(configurationServiceImplClassName);
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
      final Class<?> managerImplClass = ClassLoadingUtil.loadClass(managerImplClassName);
      return (HolidayManager) managerImplClass.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new IllegalStateException("Cannot create manager class " + managerImplClassName, e);
    }
  }
}
