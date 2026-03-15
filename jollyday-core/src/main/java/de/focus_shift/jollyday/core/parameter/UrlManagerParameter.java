package de.focus_shift.jollyday.core.parameter;

import java.net.URL;
import java.util.Properties;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class UrlManagerParameter extends BaseManagerParameter {

  private final URL calendarFileUrl;

  public UrlManagerParameter(
      @NonNull final URL calendarFileUrl, @Nullable final Properties properties) {
    super(properties);
    this.calendarFileUrl = calendarFileUrl;
  }

  @Override
  public @NonNull String createCacheKey() {
    return calendarFileUrl.toString();
  }

  @Override
  public @NonNull String getDisplayName() {
    return calendarFileUrl.toString();
  }

  @Override
  public @NonNull URL createResourceUrl() {
    return calendarFileUrl;
  }

  @Override
  public @NonNull String toString() {
    return getClass().getSimpleName() + " - " + this.calendarFileUrl.toString();
  }
}
