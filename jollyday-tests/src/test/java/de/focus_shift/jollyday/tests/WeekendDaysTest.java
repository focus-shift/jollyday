package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.Year;
import java.util.Set;
import java.util.stream.Stream;

import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the weekend days of every calendar whose weekend differs from the default
 * Saturday/Sunday, per the sources cited in each calendar's Holidays_xx.xml.
 */
class WeekendDaysTest {

  private static Stream<Arguments> weekends() {
    return Stream.of(
      // United Arab Emirates: Friday/Saturday until 2021, Saturday/Sunday from 2022
      Arguments.of(HolidayCalendar.UNITED_ARAB_EMIRATES, Year.of(2021), Set.of(FRIDAY, SATURDAY)),
      Arguments.of(HolidayCalendar.UNITED_ARAB_EMIRATES, Year.of(2022), Set.of(SATURDAY, SUNDAY)),

      // Saudi Arabia: Thursday/Friday until 2012, Friday/Saturday from 2013
      Arguments.of(HolidayCalendar.SAUDI_ARABIA, Year.of(2012), Set.of(THURSDAY, FRIDAY)),
      Arguments.of(HolidayCalendar.SAUDI_ARABIA, Year.of(2013), Set.of(FRIDAY, SATURDAY)),

      // Qatar: Friday/Saturday
      Arguments.of(HolidayCalendar.QATAR, Year.of(2024), Set.of(FRIDAY, SATURDAY)),

      // Bahrain: Thursday/Friday until 2005, Friday/Saturday from 2006
      Arguments.of(HolidayCalendar.BAHRAIN, Year.of(2005), Set.of(THURSDAY, FRIDAY)),
      Arguments.of(HolidayCalendar.BAHRAIN, Year.of(2006), Set.of(FRIDAY, SATURDAY)),

      // Kuwait: Thursday/Friday until 2006, Friday/Saturday from 2007
      Arguments.of(HolidayCalendar.KUWAIT, Year.of(2006), Set.of(THURSDAY, FRIDAY)),
      Arguments.of(HolidayCalendar.KUWAIT, Year.of(2007), Set.of(FRIDAY, SATURDAY)),

      // Oman: Friday/Saturday
      Arguments.of(HolidayCalendar.OMAN, Year.of(2024), Set.of(FRIDAY, SATURDAY)),

      // Jordan: Friday/Saturday
      Arguments.of(HolidayCalendar.JORDAN, Year.of(2024), Set.of(FRIDAY, SATURDAY)),

      // Iraq: Friday/Saturday
      Arguments.of(HolidayCalendar.IRAQ, Year.of(2024), Set.of(FRIDAY, SATURDAY)),

      // Syria: Friday/Saturday
      Arguments.of(HolidayCalendar.SYRIA, Year.of(2024), Set.of(FRIDAY, SATURDAY)),

      // Libya: Friday/Saturday
      Arguments.of(HolidayCalendar.LIBYA, Year.of(2024), Set.of(FRIDAY, SATURDAY)),

      // Algeria: default Saturday/Sunday before 1976, Thursday/Friday 1976-2008, Friday/Saturday from 2009
      Arguments.of(HolidayCalendar.ALGERIA, Year.of(1975), Set.of(SATURDAY, SUNDAY)),
      Arguments.of(HolidayCalendar.ALGERIA, Year.of(2008), Set.of(THURSDAY, FRIDAY)),
      Arguments.of(HolidayCalendar.ALGERIA, Year.of(2009), Set.of(FRIDAY, SATURDAY)),

      // Egypt: Friday/Saturday
      Arguments.of(HolidayCalendar.EGYPT, Year.of(2024), Set.of(FRIDAY, SATURDAY)),

      // Bangladesh: Friday only until 2004, Friday/Saturday from 2005
      Arguments.of(HolidayCalendar.BANGLADESH, Year.of(2004), Set.of(FRIDAY)),
      Arguments.of(HolidayCalendar.BANGLADESH, Year.of(2005), Set.of(FRIDAY, SATURDAY)),

      // Maldives: Friday/Saturday
      Arguments.of(HolidayCalendar.MALDIVES, Year.of(2024), Set.of(FRIDAY, SATURDAY)),

      // Palestine: Friday/Saturday
      Arguments.of(HolidayCalendar.PALESTINE, Year.of(2024), Set.of(FRIDAY, SATURDAY)),

      // Mexico: Sunday only
      Arguments.of(HolidayCalendar.MEXICO, Year.of(2024), Set.of(SUNDAY)),

      // Djibouti: Friday only
      Arguments.of(HolidayCalendar.DJIBOUTI, Year.of(2024), Set.of(FRIDAY)),

      // Sudan: Friday only until 2007, Friday/Saturday from 2008
      Arguments.of(HolidayCalendar.SUDAN, Year.of(2007), Set.of(FRIDAY)),
      Arguments.of(HolidayCalendar.SUDAN, Year.of(2008), Set.of(FRIDAY, SATURDAY)),

      // Yemen: Thursday/Friday until 2011, Friday/Saturday from 2012
      Arguments.of(HolidayCalendar.YEMEN, Year.of(2011), Set.of(THURSDAY, FRIDAY)),
      Arguments.of(HolidayCalendar.YEMEN, Year.of(2012), Set.of(FRIDAY, SATURDAY)),

      // Iran: Friday only
      Arguments.of(HolidayCalendar.IRAN, Year.of(2024), Set.of(FRIDAY)),

      // A calendar with no explicit weekend configuration defaults to Saturday/Sunday
      Arguments.of(HolidayCalendar.GERMANY, Year.of(2024), Set.of(SATURDAY, SUNDAY))
    );
  }

  @ParameterizedTest(name = "{0} in {1} has weekend {2}")
  @MethodSource("weekends")
  void ensureWeekendDaysAreCorrect(final HolidayCalendar calendar, final Year year, final Set<DayOfWeek> expectedWeekendDays) {
    final HolidayManager sut = HolidayManager.getInstance(create(calendar));
    assertThat(sut.getWeekendDays(year)).isEqualTo(expectedWeekendDays);
  }
}
