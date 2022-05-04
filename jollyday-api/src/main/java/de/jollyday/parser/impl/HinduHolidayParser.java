/**
 * Copyright 2010 Sven Diedrichsen
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
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
