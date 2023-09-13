package de.focus_shift.jollyday.core.parser;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.spi.Holidays;

import java.util.List;

public interface HolidayParser {

  /**
   * Parses for the provided year using the {@link Holidays} config and adds
   * to the set of holidays.
   *
   * @param year   the year to parse the holiday for
   * @param config the {@link Holidays} config to use for parsing
   * @return the list of holidays
   */
  List<Holiday> parse(int year, Holidays config);
}
