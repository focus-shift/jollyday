package de.focus_shift.jollyday.jackson.test;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.impl.ChristianHolidayParser;
import de.focus_shift.jollyday.core.parser.impl.RelativeToEasterSundayParser;
import de.focus_shift.jollyday.jackson.JacksonHolidays;
import de.focus_shift.jollyday.jackson.mapping.ChristianHoliday;
import de.focus_shift.jollyday.jackson.mapping.ChristianHolidayType;
import de.focus_shift.jollyday.jackson.mapping.ChronologyType;
import de.focus_shift.jollyday.jackson.mapping.Holidays;
import de.focus_shift.jollyday.jackson.mapping.RelativeToEasterSunday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.focus_shift.jollyday.jackson.mapping.ChristianHolidayType.CLEAN_MONDAY;
import static de.focus_shift.jollyday.jackson.mapping.ChristianHolidayType.EASTER;
import static de.focus_shift.jollyday.jackson.mapping.ChristianHolidayType.EASTER_SATURDAY;
import static de.focus_shift.jollyday.jackson.mapping.ChristianHolidayType.EASTER_TUESDAY;
import static de.focus_shift.jollyday.jackson.mapping.ChristianHolidayType.GENERAL_PRAYER_DAY;
import static de.focus_shift.jollyday.jackson.mapping.ChristianHolidayType.PENTECOST;
import static de.focus_shift.jollyday.jackson.mapping.ChristianHolidayType.SACRED_HEART;
import static org.assertj.core.api.Assertions.assertThat;

class ChristianHolidayParserTest {


  private ChristianHolidayParser sut;

  @BeforeEach
  void setUp() {
    sut = new ChristianHolidayParser();
  }

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(Year.of(2010), new JacksonHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testEaster() {
    final Holidays config = createConfig(EASTER);
    final List<Holiday> holidays = sut.parse(Year.of(2011), new JacksonHolidays(config));
    assertThat(holidays).hasSize(1);
    final Holiday easterDate = holidays.iterator().next();
    final LocalDate ed = LocalDate.of(2011, 4, 24);
    assertThat(easterDate.getDate()).isEqualTo(ed);
  }

  @Test
  void testChristianInvalidDate() {
    final Holidays config = createConfig(EASTER);
    config.getChristianHoliday().get(0).setValidTo(2010);
    final List<Holiday> holidays = sut.parse(Year.of(2011), new JacksonHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testRelativeToEasterSunday() {
    final Holidays config = createConfig(1);
    final RelativeToEasterSundayParser p = new RelativeToEasterSundayParser();
    final List<Holiday> holidays = p.parse(Year.of(2011), new JacksonHolidays(config));
    final List<LocalDate> expected = new ArrayList<>();
    expected.add(LocalDate.of(2011, 4, 25));
    assertThat(holidays).hasSameSizeAs(expected);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(expected.get(0));
  }

  @Test
  void testChristianDates() {
    final Holidays config = createConfig(EASTER, CLEAN_MONDAY, EASTER_SATURDAY, EASTER_TUESDAY,
      GENERAL_PRAYER_DAY, PENTECOST, SACRED_HEART);
    final List<Holiday> holidays = sut.parse(Year.of(2011), new JacksonHolidays(config));
    final List<LocalDate> expected = new ArrayList<>();
    expected.add(LocalDate.of(2011, 3, 7));
    expected.add(LocalDate.of(2011, 4, 23));
    expected.add(LocalDate.of(2011, 4, 24));
    expected.add(LocalDate.of(2011, 4, 26));
    expected.add(LocalDate.of(2011, 5, 20));
    expected.add(LocalDate.of(2011, 6, 12));
    expected.add(LocalDate.of(2011, 7, 1));

    assertThat(holidays).hasSameSizeAs(expected);

    Collections.sort(expected);
    final List<Holiday> found = new ArrayList<>(holidays);
    Collections.sort(found);

    for (int i = 0; i < expected.size(); i++) {
      assertThat(found.get(i).getDate()).isEqualTo(expected.get(i));
    }
  }

  @Test
  void testCustomPropertiesKey() {
    final Holidays config = createConfig(ChristianHolidayType.EASTER_TUESDAY);
    config.getChristianHoliday().get(0).setDescriptionPropertiesKey("CUSTOM_KEY");
    final List<Holiday> holidays = sut.parse(Year.of(2019), new JacksonHolidays(config));

    final String expectedPropertiesKey = "CUSTOM_KEY";
    final LocalDate expectedDate = LocalDate.of(2019, 4, 23);

    assertThat(holidays).hasSize(1);

    final Holiday easterTuesday = holidays.iterator().next();
    assertThat(easterTuesday.getDate()).isEqualTo(expectedDate);
    assertThat(easterTuesday.getPropertiesKey()).isEqualTo(expectedPropertiesKey);
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
