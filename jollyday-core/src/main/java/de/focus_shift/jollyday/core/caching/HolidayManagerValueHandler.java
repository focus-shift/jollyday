package de.focus_shift.jollyday.core.caching;

import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.datasource.ConfigurationServiceManager;
import de.focus_shift.jollyday.core.spi.ConfigurationService;
import de.focus_shift.jollyday.core.util.ClassLoadingUtil;

/**
 * Creates the {@link Cache.ValueHandler} which constructs a {@link HolidayManager}.
 */
public class HolidayManagerValueHandler implements Cache.ValueHandler<HolidayManager> {

  private final ManagerParameter parameter;
  private final String managerImplClassName;
  private final ConfigurationServiceManager configurationServiceManager;

  /**
   * Utility to load classes.
   */
  private final ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

  public HolidayManagerValueHandler(final ManagerParameter parameter, final String managerImplClassName, final ConfigurationServiceManager configurationServiceManager) {
    this.parameter = parameter;
    this.managerImplClassName = managerImplClassName;
    this.configurationServiceManager = configurationServiceManager;
  }

  @Override
  public String getKey() {
    return parameter.createCacheKey();
  }

  @Override
  public HolidayManager createValue() {
    final HolidayManager manager = instantiateManagerImpl(managerImplClassName);

    final ConfigurationService configurationService = configurationServiceManager.getConfigurationService();
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
