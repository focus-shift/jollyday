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
import de.jollyday.parser.functions.CalculateRelativeDatesFromChronologyWithinGregorianYear;
import de.jollyday.parser.functions.CreateHoliday;
import de.jollyday.parser.predicates.ValidLimitation;
import de.jollyday.spi.IslamicHoliday;

import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * This parser calculates gregorian dates for the different islamic holidays.
 *
 * @author Sven Diedrichsen
 * @version $Id: $
 */
public class IslamicHolidayParser implements Function<Integer, List<Holiday>> {

  private final List<IslamicHoliday> islamicHolidayStream;

  public IslamicHolidayParser(List<IslamicHoliday> islamicHolidayStream) {
    this.islamicHolidayStream = islamicHolidayStream;
  }

  @Override
  public List<Holiday> apply(Integer year) {
    return islamicHolidayStream.stream()
      .filter(new ValidLimitation(year))
      .flatMap(ih -> {
        Stream<LocalDate> islamicHolidays;
        switch (ih.type()) {
          case NEWYEAR:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 1, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ASCHURA:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 10, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case RAMADAN_END:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 1, HijrahChronology.INSTANCE, -1).apply(year);
            break;
          case ID_AL_FITR:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 1, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ID_AL_FITR_2:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 2, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ID_AL_FITR_3:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 3, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ARAFAAT:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 9, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ID_UL_ADHA:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 10, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ID_UL_ADHA_2:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 11, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ID_UL_ADHA_3:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 12, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case LAILAT_AL_BARAT:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(8, 15, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case LAILAT_AL_MIRAJ:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(7, 27, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case LAILAT_AL_QADR:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(9, 27, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case MAWLID_AN_NABI:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(3, 12, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case RAMADAN:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(9, 1, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          default:
            throw new IllegalArgumentException("Unknown islamic holiday " + ih.type());
        }
        return islamicHolidays.map(date -> new CreateHoliday(date).apply(ih));
      })
      .collect(toList());
  }
}
