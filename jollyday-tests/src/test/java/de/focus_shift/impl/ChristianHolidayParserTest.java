package de.focus_shift.impl;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.JaxbHolidays;
import de.focus_shift.jaxb.mapping.ChristianHoliday;
import de.focus_shift.jaxb.mapping.ChristianHolidayType;
import de.focus_shift.jaxb.mapping.ChronologyType;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.jaxb.mapping.RelativeToEasterSunday;
import de.focus_shift.parser.impl.ChristianHolidayParser;
import de.focus_shift.parser.impl.RelativeToEasterSundayParser;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.focus_shift.jaxb.mapping.ChristianHolidayType.CLEAN_MONDAY;
import static de.focus_shift.jaxb.mapping.ChristianHolidayType.EASTER;
import static de.focus_shift.jaxb.mapping.ChristianHolidayType.EASTER_SATURDAY;
import static de.focus_shift.jaxb.mapping.ChristianHolidayType.EASTER_TUESDAY;
import static de.focus_shift.jaxb.mapping.ChristianHolidayType.GENERAL_PRAYER_DAY;
import static de.focus_shift.jaxb.mapping.ChristianHolidayType.PENTECOST;
import static de.focus_shift.jaxb.mapping.ChristianHolidayType.SACRED_HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChristianHolidayParserTest {

  private final CalendarUtil calendarUtil = new CalendarUtil();

  private ChristianHolidayParser sut;

  @BeforeEach
  void setUp() {
    sut = new ChristianHolidayParser();
  }

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(2010, new JaxbHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testEaster() {
    final Holidays config = createConfig(EASTER);
    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertEquals(1, holidays.size(), "Wrong number of holidays.");
    final Holiday easterDate = holidays.iterator().next();
    final LocalDate ed = calendarUtil.create(2011, 4, 24);
    assertEquals(ed, easterDate.getDate(), "Wrong easter date.");
  }

  @Test
  void testChristianInvalidDate() {
    final Holidays config = createConfig(EASTER);
    config.getChristianHoliday().get(0).setValidTo(2010);
    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertEquals(0, holidays.size(), "Wrong number of holidays.");
  }

  @Test
  void testRelativeToEasterSunday() {
    final Holidays config = createConfig(1);
    final RelativeToEasterSundayParser p = new RelativeToEasterSundayParser();
    final List<Holiday> holidays = p.parse(2011, new JaxbHolidays(config));
    final List<LocalDate> expected = new ArrayList<>();
    expected.add(calendarUtil.create(2011, 4, 25));
    assertEquals(expected.size(), holidays.size(), "Wrong number of holidays.");
    assertEquals(expected.get(0), holidays.iterator().next().getDate(), "Wrong holiday.");
  }

  @Test
  void testChristianDates() {
    final Holidays config = createConfig(EASTER, CLEAN_MONDAY, EASTER_SATURDAY, EASTER_TUESDAY,
      GENERAL_PRAYER_DAY, PENTECOST, SACRED_HEART);
    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    final List<LocalDate> expected = new ArrayList<>();
    expected.add(calendarUtil.create(2011, 3, 7));
    expected.add(calendarUtil.create(2011, 4, 23));
    expected.add(calendarUtil.create(2011, 4, 24));
    expected.add(calendarUtil.create(2011, 4, 26));
    expected.add(calendarUtil.create(2011, 5, 20));
    expected.add(calendarUtil.create(2011, 6, 12));
    expected.add(calendarUtil.create(2011, 7, 1));

    assertEquals(expected.size(), holidays.size(), "Wrong number of holidays.");

    Collections.sort(expected);
    final List<Holiday> found = new ArrayList<>(holidays);
    Collections.sort(found);

    for (int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i), found.get(i).getDate(), "Wrong date.");
    }
  }

  @Test
  void testCustomPropertiesKey() {
    final Holidays config = createConfig(ChristianHolidayType.EASTER_TUESDAY);
    config.getChristianHoliday().get(0).setDescriptionPropertiesKey("CUSTOM_KEY");
    final List<Holiday> holidays = sut.parse(2019, new JaxbHolidays(config));

    final String expectedPropertiesKey = "CUSTOM_KEY";
    final LocalDate expectedDate = calendarUtil.create(2019, 4, 23);

    assertEquals(1, holidays.size(), "Wrong number of holidays.");

    Holiday easterTuesday = holidays.iterator().next();
    assertEquals(expectedDate, easterTuesday.getDate(), "Wrong holiday date.");
    assertEquals(expectedPropertiesKey, easterTuesday.getPropertiesKey(), "Wrong holiday key.");
  }

  private Holidays createConfig(int... days) {
    final Holidays config = new Holidays();
    for (int day : days) {
      final RelativeToEasterSunday relativeToEasterSunday = new RelativeToEasterSunday();
      relativeToEasterSunday.setDays(day);
      relativeToEasterSunday.setChronology(ChronologyType.GREGORIAN);
      config.getRelativeToEasterSunday().add(relativeToEasterSunday);
    }
    return config;
  }

  private Holidays createConfig(ChristianHolidayType... types) {
    final Holidays config = new Holidays();
    for (ChristianHolidayType type : types) {
      final ChristianHoliday christianHoliday = new ChristianHoliday();
      christianHoliday.setType(type);
      config.getChristianHoliday().add(christianHoliday);
    }
    return config;
  }

}
