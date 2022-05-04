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
import de.jollyday.parser.functions.CreateHoliday;
import de.jollyday.parser.functions.FindWeekDayInMonth;
import de.jollyday.parser.predicates.ValidLimitation;
import de.jollyday.spi.Relation;
import de.jollyday.spi.RelativeToWeekdayInMonth;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * RelativeToWeekdayInMonthParser class.
 * </p>
 *
 * @author Sven
 * @version $Id: $
 */
public class RelativeToWeekdayInMonthParser implements Function<Integer, List<Holiday>> {

  private final List<RelativeToWeekdayInMonth> relativeToWeekdayInMonths;

  public RelativeToWeekdayInMonthParser(List<RelativeToWeekdayInMonth> relativeToWeekdayInMonths) {
    this.relativeToWeekdayInMonths = relativeToWeekdayInMonths;
  }

  @Override
  public List<Holiday> apply(Integer year) {
    return relativeToWeekdayInMonths.stream()
      .filter(new ValidLimitation(year))
      .map(rwm -> {
        LocalDate date = new FindWeekDayInMonth(year).apply(rwm.weekdayInMonth()).plusDays(1);
        int direction = (rwm.when() == Relation.BEFORE ? -1 : 1);
        while (date.getDayOfWeek() != rwm.weekday()) {
          date = date.plusDays(direction);
        }
        return new CreateHoliday(date).apply(rwm);
      })
      .collect(toList());
  }
}
