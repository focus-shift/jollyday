package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.MovingCondition;
import de.focus_shift.jollyday.core.spi.YearCycle;

import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class JacksonFixed implements Fixed {

  private final XMLUtil xmlUtil = new XMLUtil();

  private final de.focus_shift.jollyday.jackson.mapping.Fixed fixed;

  public JacksonFixed(de.focus_shift.jollyday.jackson.mapping.Fixed fixed) {
    this.fixed = fixed;
  }

  @Override
  public MonthDay day() {
    return MonthDay.of(xmlUtil.getMonth(fixed.getMonth()), fixed.getDay());
  }

  @Override
  public String descriptionPropertiesKey() {
    return fixed.getDescriptionPropertiesKey();
  }

  @Override
  public HolidayType officiality() {
    return fixed.getLocalizedType() == null
      ? HolidayType.OFFICIAL_HOLIDAY
      : HolidayType.valueOf(fixed.getLocalizedType().name());
  }

  @Override
  public Year validFrom() {
    return fixed.getValidFrom() == null
      ? null
      : Year.of(fixed.getValidFrom());
  }

  @Override
  public Year validTo() {
    return fixed.getValidTo() == null
      ? null
      : Year.of(fixed.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return fixed.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixed.getEvery().name());
  }

  @Override
  public List<MovingCondition> conditions() {
    return fixed.getMovingCondition().stream()
      .map(JacksonMovingCondition::new)
      .collect(toList());
  }
}
