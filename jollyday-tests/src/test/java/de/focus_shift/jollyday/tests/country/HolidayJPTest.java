package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static de.focus_shift.jollyday.core.HolidayCalendar.JAPAN;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayJPTest {

  /**
   * List of national holidays and holidays published by the Cabinet Office,
   * https://www8.cao.go.jp/chosei/shukujitsu/syukujitsu.csv (converted from Shift_JIS to UTF-8).
   */
  private static final String CABINET_OFFICE_HOLIDAYS = "/country/holidays_jp_cabinet_office.csv";

  /**
   * Vernal Equinox Day (春分の日) and Autumnal Equinox Day (秋分の日) are not modelled yet (see #1510).
   */
  private static final Set<String> NOT_MODELLED_HOLIDAY_NAMES = Set.of("春分の日", "秋分の日");

  /**
   * Substitute holidays for an equinox day on a Sunday (see #1510).
   */
  private static final List<String> MISSING_EQUINOX_SUBSTITUTE_HOLIDAYS = List.of(
    "1973-09-24", "1982-03-22", "1984-09-24", "1988-03-21", "1990-09-24", "1999-03-22", "2001-09-24",
    "2005-03-21", "2007-09-24", "2010-03-22", "2016-03-21", "2018-09-24", "2024-09-23", "2027-03-22"
  );

  /**
   * Citizens' holidays between Respect for the Aged Day and Autumnal Equinox Day (see #1510).
   */
  private static final List<String> MISSING_EQUINOX_CITIZENS_HOLIDAYS = List.of(
    "2009-09-22", "2015-09-22", "2026-09-22"
  );

  /**
   * Substitute holidays since 2007, the next day that is not a national holiday (see #1511).
   * Only the substitute holiday for Culture Day is calculated so far.
   */
  private static final List<String> MISSING_SUBSTITUTE_HOLIDAYS_SINCE_2007 = List.of(
    "2007-02-12", "2007-04-30", "2007-12-24", "2008-05-06", "2008-11-24", "2009-05-06", "2012-01-02",
    "2012-04-30", "2012-12-24", "2013-05-06", "2014-05-06", "2014-11-24", "2015-05-06", "2017-01-02",
    "2018-02-12", "2018-04-30", "2018-12-24", "2019-05-06", "2019-08-12", "2020-02-24", "2020-05-06",
    "2021-08-09", "2023-01-02", "2024-02-12", "2024-05-06", "2024-08-12", "2025-02-24", "2025-05-06",
    "2025-11-24", "2026-05-06"
  );

  private static final List<String> KNOWN_MISSING = Stream.of(
      MISSING_EQUINOX_SUBSTITUTE_HOLIDAYS,
      MISSING_EQUINOX_CITIZENS_HOLIDAYS,
      MISSING_SUBSTITUTE_HOLIDAYS_SINCE_2007
    )
    .flatMap(List::stream)
    .toList();

  @Test
  void ensuresHolidays() {
    assertFor(JAPAN)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(Year.of(1948), Year.of(1973)).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(1974), Year.of(2006)).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validFrom(Year.of(2007)).and()
      .hasFixedHoliday("COMING_OF_AGE", JANUARY, 15)
        .validBetween(Year.of(1948), Year.of(1973)).and()
      .hasFixedHoliday("COMING_OF_AGE", JANUARY, 15)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(1974), Year.of(1999)).and()
      .hasFixedWeekdayHoliday("COMING_OF_AGE", SECOND, MONDAY, JANUARY)
        .validFrom(Year.of(2000)).and()
      .hasFixedHoliday("FOUNDATION", FEBRUARY, 11)
        .validBetween(Year.of(1967), Year.of(1973)).and()
      .hasFixedHoliday("FOUNDATION", FEBRUARY, 11)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(1974), Year.of(2006)).and()
      .hasFixedHoliday("FOUNDATION", FEBRUARY, 11)
        .validFrom(Year.of(2007)).and()
      .hasFixedHoliday("EMPERORS_BIRTHDAY", APRIL, 29)
        .validBetween(Year.of(1948), Year.of(1972)).and()
      .hasFixedHoliday("EMPERORS_BIRTHDAY", APRIL, 29)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(1973), Year.of(1988)).and()
      .hasFixedHoliday("GREENERY_DAY", APRIL, 29)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(1989), Year.of(2006)).and()
      .hasFixedHoliday("SHOWA_DAY", APRIL, 29)
        .validFrom(Year.of(2007)).and()
      .hasFixedHoliday("CONSTITUTION_DAY", MAY, 3)
        .validBetween(Year.of(1948), Year.of(1972)).and()
      .hasFixedHoliday("CONSTITUTION_DAY", MAY, 3)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(1973), Year.of(2006)).and()
      .hasFixedHoliday("CONSTITUTION_DAY", MAY, 3)
        .validFrom(Year.of(2007)).and()
      .hasFixedHoliday("GREENERY_DAY", MAY, 4)
        .validFrom(Year.of(2007)).and()
      .hasFixedHoliday("CHILDRENS_DAY", MAY, 5)
        .validBetween(Year.of(1948), Year.of(1972)).and()
      .hasFixedHoliday("CHILDRENS_DAY", MAY, 5)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(1973), Year.of(2006)).and()
      .hasFixedHoliday("CHILDRENS_DAY", MAY, 5)
        .validFrom(Year.of(2007)).and()
      .hasFixedHoliday("MARINE_DAY", JULY, 20)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(1996), Year.of(2002)).and()
      .hasFixedWeekdayHoliday("MARINE_DAY", THIRD, MONDAY, JULY)
        .validBetween(Year.of(2003), Year.of(2019)).and()
      .hasFixedHoliday("MARINE_DAY", JULY, 23)
        .validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("MARINE_DAY", JULY, 22)
        .validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedWeekdayHoliday("MARINE_DAY", THIRD, MONDAY, JULY)
        .validFrom(Year.of(2022)).and()
      .hasFixedHoliday("MOUNTAIN_DAY", AUGUST, 11)
        .validBetween(Year.of(2016), Year.of(2019)).and()
      .hasFixedHoliday("MOUNTAIN_DAY", AUGUST, 10)
        .validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("MOUNTAIN_DAY", AUGUST, 8)
        .validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("MOUNTAIN_DAY", AUGUST, 11)
        .validFrom(Year.of(2022)).and()
      .hasFixedHoliday("RESPECT_AGED_DAY", SEPTEMBER, 15)
        .validBetween(Year.of(1966), Year.of(1972)).and()
      .hasFixedHoliday("RESPECT_AGED_DAY", SEPTEMBER, 15)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(1973), Year.of(2002)).and()
      .hasFixedWeekdayHoliday("RESPECT_AGED_DAY", THIRD, MONDAY, SEPTEMBER)
        .validFrom(Year.of(2003)).and()
      .hasFixedHoliday("HEALTH_SPORTS", OCTOBER, 10)
        .validBetween(Year.of(1966), Year.of(1972)).and()
      .hasFixedHoliday("HEALTH_SPORTS", OCTOBER, 10)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(1973), Year.of(1999)).and()
      .hasFixedWeekdayHoliday("HEALTH_SPORTS", SECOND, MONDAY, OCTOBER)
        .validBetween(Year.of(2000), Year.of(2019)).and()
      .hasFixedHoliday("SPORTS_DAY", JULY, 24)
        .validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("SPORTS_DAY", JULY, 23)
        .validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedWeekdayHoliday("SPORTS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2022)).and()
      .hasFixedHoliday("CULTURE_DAY", NOVEMBER, 3)
        .validBetween(Year.of(1948), Year.of(1972)).and()
      .hasFixedHoliday("CULTURE_DAY", NOVEMBER, 3)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validFrom(Year.of(1973)).and()
      .hasFixedHoliday("LABOUR_THANKSGIVING_DAY", NOVEMBER, 23)
        .validBetween(Year.of(1948), Year.of(1972)).and()
      .hasFixedHoliday("LABOUR_THANKSGIVING_DAY", NOVEMBER, 23)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(1973), Year.of(2006)).and()
      .hasFixedHoliday("LABOUR_THANKSGIVING_DAY", NOVEMBER, 23)
        .validFrom(Year.of(2007)).and()
      .hasFixedHoliday("EMPERORS_BIRTHDAY", DECEMBER, 23)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(1989), Year.of(2006)).and()
      .hasFixedHoliday("EMPERORS_BIRTHDAY", DECEMBER, 23)
        .validBetween(Year.of(2007), Year.of(2018)).and()
      .hasFixedHoliday("EMPERORS_BIRTHDAY", FEBRUARY, 23)
        .validFrom(Year.of(2020)).and()
      .hasFixedHoliday("IMPERIAL_DAY", APRIL, 10)
        .validBetween(Year.of(1959), Year.of(1959)).and()
      .hasFixedHoliday("IMPERIAL_DAY", FEBRUARY, 24)
        .validBetween(Year.of(1989), Year.of(1989)).and()
      .hasFixedHoliday("IMPERIAL_DAY", NOVEMBER, 12)
        .validBetween(Year.of(1990), Year.of(1990)).and()
      .hasFixedHoliday("IMPERIAL_DAY", JUNE, 9)
        .validBetween(Year.of(1993), Year.of(1993)).and()
      .hasFixedHoliday("IMPERIAL_DAY", MAY, 1)
        .validBetween(Year.of(2019), Year.of(2019)).and()
      .hasFixedHoliday("IMPERIAL_DAY", OCTOBER, 22)
        .validBetween(Year.of(2019), Year.of(2019))
      .check();
  }

  @Test
  void ensuresHolidaysMatchCabinetOfficeList() throws IOException {
    final List<CabinetOfficeHoliday> cabinetOfficeHolidays = readCabinetOfficeHolidays();
    final Set<LocalDate> knownMissing = KNOWN_MISSING.stream().map(LocalDate::parse).collect(toSet());

    assertThat(cabinetOfficeHolidays)
      .extracting(CabinetOfficeHoliday::date)
      .containsAll(knownMissing);

    final Set<LocalDate> expected = cabinetOfficeHolidays.stream()
      .filter(holiday -> !NOT_MODELLED_HOLIDAY_NAMES.contains(holiday.name()))
      .map(CabinetOfficeHoliday::date)
      .filter(date -> !knownMissing.contains(date))
      .collect(toCollection(TreeSet::new));

    final int firstYear = cabinetOfficeHolidays.get(0).date().getYear();
    final int lastYear = cabinetOfficeHolidays.get(cabinetOfficeHolidays.size() - 1).date().getYear();
    final HolidayManager holidayManager = HolidayManager.getInstance(create(JAPAN));
    final Set<LocalDate> actual = new TreeSet<>();
    for (int year = firstYear; year <= lastYear; year++) {
      for (final Holiday holiday : holidayManager.getHolidays(Year.of(year))) {
        // a national holiday on a Sunday stays a holiday on its actual date, its substitute holiday is the observed date
        actual.add(holiday.getActualDate());
        actual.add(holiday.getDate());
      }
    }

    assertThat(actual).containsExactlyElementsOf(expected);
  }

  private static List<CabinetOfficeHoliday> readCabinetOfficeHolidays() throws IOException {
    final DateTimeFormatter csvDate = DateTimeFormatter.ofPattern("yyyy/M/d");
    try (final InputStream inputStream = HolidayJPTest.class.getResourceAsStream(CABINET_OFFICE_HOLIDAYS);
         final BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(inputStream), UTF_8))) {
      return reader.lines()
        .filter(line -> !line.isBlank() && !line.startsWith("#"))
        .skip(1) // header
        .map(line -> line.split(","))
        .map(columns -> new CabinetOfficeHoliday(LocalDate.parse(columns[0], csvDate), columns[1]))
        .toList();
    }
  }

  private record CabinetOfficeHoliday(LocalDate date, String name) {
  }
}
