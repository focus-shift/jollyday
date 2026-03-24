package de.focus_shift.jollyday.core.parameter;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.net.URL;
import java.util.Properties;

import static de.focus_shift.jollyday.core.util.ResourceUtil.getResource;

public class CalendarPartManagerParameter extends BaseManagerParameter {

  private static final String FILE_PREFIX = "holidays/Holidays";
  private static final String FILE_SUFFIX = ".xml";

  private final String calendarPart;

  public CalendarPartManagerParameter(@NonNull final String calendarPart, @Nullable final Properties properties) {
    super(properties);
    this.calendarPart = calendarPart;
  }

  @Override
  public @NonNull String createCacheKey() {
    return calendarPart;
  }

  @Override
  public @NonNull String getDisplayName() {
    return calendarPart;
  }

  @Override
  public @NonNull URL createResourceUrl() {
    final String configurationFileName = getConfigurationFileName(calendarPart);
    return getResource(configurationFileName)
      .orElseThrow(() -> new IllegalStateException("Cannot find resource '" + configurationFileName + "'."));
  }

  @Override
  public @NonNull String getManagerImplClassName() {
    return getProperty(MANAGER_IMPL_CLASS_PREFIX + "." + calendarPart)
      .orElse(super.getManagerImplClassName());
  }

  /**
   * Returns the configuration file name for the country.
   *
   * @param country a {@link java.lang.String} object.
   * @return file name
   */
  public static @NonNull String getConfigurationFileName(final String country) {
    return FILE_PREFIX + "_" + country + FILE_SUFFIX;
  }

  @Override
  public @NonNull String toString() {
    return this.getClass().getSimpleName() + " - " + this.calendarPart;
  }
}
