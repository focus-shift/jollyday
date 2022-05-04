package de.jollyday.jaxb;

import de.jollyday.HolidayType;
import de.jollyday.spi.YearCycle;

import java.time.MonthDay;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbFixed implements de.jollyday.spi.Fixed {

  private final XMLUtil xmlUtil = new XMLUtil();
  private final de.jollyday.jaxb.mapping.Fixed fixed;

  public JaxbFixed(de.jollyday.jaxb.mapping.Fixed fixed) {
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
    return Year.of(fixed.getValidFrom());
  }

  @Override
  public Year validTo() {
    return Year.of(fixed.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return fixed.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixed.getEvery());
  }

  @Override
  public List<de.jollyday.spi.MovingCondition> conditions() {
    return fixed.getMovingCondition().stream().map(JaxbMovingCondition::new).collect(Collectors.toList());
  }
}
