package de.focus_shift.parser.impl;

import de.focus_shift.Holiday;
import de.focus_shift.parser.HolidayParser;
import de.focus_shift.spi.Holidays;

import java.util.List;

/**
 * <p>HinduHolidayParser class.</p>
 *
 * @author Sven
 * @version $Id: $
 */
public class HinduHolidayParser implements HolidayParser {

  @Override
  public List<Holiday> parse(int year, Holidays holidays) {
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
