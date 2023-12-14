package de.focus_shift.jollyday.tests;

import com.vitorsvieira.iso.CountrySubdivision;
import com.vitorsvieira.iso.ISOCountry;
import com.vitorsvieira.iso.ISOCountrySubdivision;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import scala.collection.JavaConverters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static de.focus_shift.jollyday.core.HolidayManager.getInstance;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class CalendarHierarchyTest {

  private static final List<String> noneIso3166Countries = List.of("DJ_STOXX", "LME", "NYSE", "TARGET", "XK", "FR");
  private static final List<String> okSubdivisions = List.of("FI-01");

  @Test
  void ensureThatAllCountryAreISO3166Conform() {
    Arrays.stream(HolidayCalendar.values())
      .filter(holidayCalendar -> !noneIso3166Countries.contains(holidayCalendar.getId()))
      .forEach(holidayCalendar -> {

        final ISOCountry.EnumVal enumVal = ISOCountry.from(holidayCalendar.getId()).get();
        final Collection<CountrySubdivision.EnumVal> subdivisionVector = JavaConverters.asJavaCollection(ISOCountrySubdivision.fromCountry(enumVal));
        final List<String> subdivisions = subdivisionVector.stream()
          .map(CountrySubdivision.EnumVal::toString)
          .collect(toList());

        final HolidayManager holidayManager = getInstance(create(holidayCalendar));
        holidayManager.getCalendarHierarchy().getChildren().keySet()
          .forEach(subdivision -> {
            final String subdivisionWithCountry = holidayCalendar.getId() + "-" + subdivision.toUpperCase();
            if(!okSubdivisions.contains(subdivisionWithCountry)) {
              assertThat(subdivisionWithCountry).isIn(subdivisions);
            }
          });
      });
  }
}
