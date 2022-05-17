package de.focus_shift.parser;

import de.focus_shift.Holiday;
import de.focus_shift.spi.Holidays;

import java.util.List;

public interface HolidayParser {

  /**
   * Parses for the provided year using the {@link Holidays} config and adds
   * to the set of holidays.
   *
   * @param year   the year to parse the holiday for
   * @param config the {@link Holidays} config to use for parsing
   */
  List<Holiday> parse(Integer year, Holidays config);
}
