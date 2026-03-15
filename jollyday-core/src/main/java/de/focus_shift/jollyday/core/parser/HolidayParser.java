package de.focus_shift.jollyday.core.parser;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import java.time.Year;
import java.util.List;
import org.jspecify.annotations.NonNull;

public interface HolidayParser {

  /**
   * Parses for the provided year using the {@link HolidayConfigurations} config and adds to the set
   * of holidays.
   *
   * @param year the year to parse the holiday for
   * @param config the {@link HolidayConfigurations} config to use for parsing
   * @return the list of holidays
   */
  @NonNull List<Holiday> parse(
      @NonNull final Year year, @NonNull final HolidayConfigurations config);
}
