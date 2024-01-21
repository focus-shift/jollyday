package de.focus_shift.jollyday.jackson.test;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.impl.EthiopianOrthodoxHolidayParser;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import de.focus_shift.jollyday.jackson.JacksonHolidays;
import de.focus_shift.jollyday.jackson.mapping.EthiopianOrthodoxHoliday;
import de.focus_shift.jollyday.jackson.mapping.EthiopianOrthodoxHolidayType;
import de.focus_shift.jollyday.jackson.mapping.Holidays;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static de.focus_shift.jollyday.jackson.mapping.EthiopianOrthodoxHolidayType.ENKUTATASH;
import static de.focus_shift.jollyday.jackson.mapping.EthiopianOrthodoxHolidayType.MESKEL;
import static de.focus_shift.jollyday.jackson.mapping.EthiopianOrthodoxHolidayType.TIMKAT;
import static org.assertj.core.api.Assertions.assertThat;

class EthiopianOrthodoxHolidayParserTest {

  private final EthiopianOrthodoxHolidayParser sut = new EthiopianOrthodoxHolidayParser();

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(2010, new JacksonHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testAllHolidays() {
    final Holidays config = new Holidays();
    config.getEthiopianOrthodoxHoliday().add(createHoliday(ENKUTATASH));
    config.getEthiopianOrthodoxHoliday().add(createHoliday(MESKEL));
    config.getEthiopianOrthodoxHoliday().add(createHoliday(TIMKAT));

    final List<Holiday> holidays = sut.parse(2010, new JacksonHolidays(config));
    assertThat(holidays).hasSize(3);
    assertContains(CalendarUtil.create(2010, 1, 18), Sets.newHashSet(holidays));
    assertContains(CalendarUtil.create(2010, 9, 11), Sets.newHashSet(holidays));
    assertContains(CalendarUtil.create(2010, 9, 27), Sets.newHashSet(holidays));
  }

  private void assertContains(LocalDate date, Set<Holiday> holidays) {
    assertThat(CalendarUtil.contains(holidays, date)).isTrue();
  }

  private EthiopianOrthodoxHoliday createHoliday(EthiopianOrthodoxHolidayType type) {
    final EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday = new EthiopianOrthodoxHoliday();
    ethiopianOrthodoxHoliday.setType(type);
    return ethiopianOrthodoxHoliday;
  }
}
