/**
 * Copyright 2010-2019 Sven Diedrichsen
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
import de.jollyday.parser.functions.FixedToLocalDate;
import de.jollyday.parser.predicates.ValidLimitation;
import de.jollyday.spi.Relation;
import de.jollyday.spi.RelativeToFixed;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * The Class RelativeToFixedParser.
 *
 * @author tboven
 * @version $Id: $
 */
public class RelativeToFixedParser implements Function<Integer, List<Holiday>> {

  private final List<RelativeToFixed> relativeToFixed;

  public RelativeToFixedParser(List<RelativeToFixed> relativeToFixed) {
    this.relativeToFixed = relativeToFixed;
  }

  @Override
  public List<Holiday> apply(Integer year) {
    return relativeToFixed.stream()
      .filter(new ValidLimitation(year))
      .map(rf -> {
        LocalDate fixed = new FixedToLocalDate(year).apply(rf.date());
        if (rf.weekday() != null) {
          // if weekday is set -> move to weekday
          DayOfWeek day = rf.weekday();
          int direction = (rf.when() == Relation.BEFORE ? -1 : 1);
          // don't test start day
          fixed = fixed.plusDays(direction);
          while (fixed.getDayOfWeek() != day) {
            fixed = fixed.plusDays(direction);
          }
        } else if (rf.days() != null) {
          // if number of days set -> move number of days
          fixed = fixed.plus(rf.when() == Relation.BEFORE ? rf.days().negated() : rf.days());
        }
        return new CreateHoliday(fixed).apply(rf);
      })
      .collect(toList());
  }
}
