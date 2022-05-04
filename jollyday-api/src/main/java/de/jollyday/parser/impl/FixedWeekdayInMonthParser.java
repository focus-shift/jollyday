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
import de.jollyday.parser.functions.FindWeekDayInMonth;
import de.jollyday.parser.predicates.ValidLimitation;
import de.jollyday.spi.FixedWeekdayInMonth;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * The Class FixedWeekdayInMonthParser.
 *
 * @author tboven
 * @version $Id: $
 */
public class FixedWeekdayInMonthParser implements Function<Integer, List<Holiday>> {

  private final List<FixedWeekdayInMonth> fixedWeekdayInMonths;

  public FixedWeekdayInMonthParser(List<FixedWeekdayInMonth> fixedWeekdayInMonths) {
    this.fixedWeekdayInMonths = fixedWeekdayInMonths;
  }

  @Override
  public List<Holiday> apply(Integer year) {
    return fixedWeekdayInMonths.stream()
      .filter(new ValidLimitation(year))
      .map(fwm -> new DescribedDateHolder(fwm, new FindWeekDayInMonth(year).apply(fwm)))
      .map(holder -> new CreateHoliday(holder.getDate()).apply(holder.getDescribed()))
      .collect(toList());
  }
}
