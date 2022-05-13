package de.focus_shift.jaxb;

import de.focus_shift.HolidayType;
import de.focus_shift.spi.Fixed;
import de.focus_shift.spi.MovingCondition;
import de.focus_shift.spi.YearCycle;

import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbFixed implements Fixed {

  private final XMLUtil xmlUtil = new XMLUtil();

  private final de.focus_shift.jaxb.mapping.Fixed fixed;

  public JaxbFixed(de.focus_shift.jaxb.mapping.Fixed fixed) {
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
      : YearCycle.valueOf(fixed.getEvery());
  }

  @Override
  public List<MovingCondition> conditions() {
    return fixed.getMovingCondition().stream()
      .map(JaxbMovingCondition::new)
      .collect(toList());
  }
}
