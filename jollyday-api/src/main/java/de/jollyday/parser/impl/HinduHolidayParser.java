package de.jollyday.parser.impl;

import de.jollyday.Holiday;
import de.jollyday.spi.HinduHoliday;

import java.util.List;
import java.util.function.Function;

/**
 * <p>HinduHolidayParser class.</p>
 *
 * @author Sven
 * @version $Id: $
 */
public class HinduHolidayParser implements Function<Integer, List<Holiday>> {

  private final List<HinduHoliday> hinduHolidays;

  public HinduHolidayParser(List<HinduHoliday> hinduHolidays) {
    this.hinduHolidays = hinduHolidays;
  }

  @Override
  public List<Holiday> apply(Integer year) {
    return List.of();
		/* TODO: Implement
		return hinduHolidayStream
				.filter(new ValidLimitation(year))

		for (HinduHoliday hh : config.getHinduHoliday()) {
			if (!isValid(hh, year))
				continue;
			switch (hh.getType()) {
			case HOLI:
				// 20 February and ending on 21 March (20th march in leap years)
				// TODO: Calculate with hindu calendar.
				break;
			default:
				throw new IllegalArgumentException("Unknown hindu holiday "
						+ hh.getType());
			}
		}
		 */
  }

}
