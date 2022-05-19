package de.focus_shift.impl;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.JaxbHolidays;
import de.focus_shift.jaxb.mapping.EthiopianOrthodoxHoliday;
import de.focus_shift.jaxb.mapping.EthiopianOrthodoxHolidayType;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.parser.impl.EthiopianOrthodoxHolidayParser;
import de.focus_shift.util.CalendarUtil;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static de.focus_shift.jaxb.mapping.EthiopianOrthodoxHolidayType.ENKUTATASH;
import static de.focus_shift.jaxb.mapping.EthiopianOrthodoxHolidayType.MESKEL;
import static de.focus_shift.jaxb.mapping.EthiopianOrthodoxHolidayType.TIMKAT;
import static org.assertj.core.api.Assertions.assertThat;


class EthiopianOrthodoxHolidayParserTest {

  private final EthiopianOrthodoxHolidayParser sut = new EthiopianOrthodoxHolidayParser();
  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(2010, new JaxbHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testAllHolidays() {
    final Holidays config = new Holidays();
    config.getEthiopianOrthodoxHoliday().add(createHoliday(ENKUTATASH));
    config.getEthiopianOrthodoxHoliday().add(createHoliday(MESKEL));
    config.getEthiopianOrthodoxHoliday().add(createHoliday(TIMKAT));

    final List<Holiday> holidays = sut.parse(2010, new JaxbHolidays(config));
    assertThat(holidays).hasSize(3);
    assertContains(calendarUtil.create(2010, 1, 18), Sets.newHashSet(holidays));
    assertContains(calendarUtil.create(2010, 9, 11), Sets.newHashSet(holidays));
    assertContains(calendarUtil.create(2010, 9, 27), Sets.newHashSet(holidays));
  }

  private void assertContains(LocalDate date, Set<Holiday> holidays) {
    assertThat(calendarUtil.contains(holidays, date)).isTrue();
  }

  private EthiopianOrthodoxHoliday createHoliday(EthiopianOrthodoxHolidayType type) {
    final EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday = new EthiopianOrthodoxHoliday();
    ethiopianOrthodoxHoliday.setType(type);
    return ethiopianOrthodoxHoliday;
  }
}
