package de.jollyday.jaxb;

import de.jollyday.HolidayType;
import de.jollyday.spi.Fixed;
import de.jollyday.spi.FixedWeekdayRelativeToFixed;
import de.jollyday.spi.Occurrance;
import de.jollyday.spi.Relation;
import de.jollyday.spi.YearCycle;

import java.time.DayOfWeek;
import java.time.Year;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbFixedWeekdayRelativeToFixed implements FixedWeekdayRelativeToFixed {

  private final de.jollyday.jaxb.mapping.FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed;

  public JaxbFixedWeekdayRelativeToFixed(de.jollyday.jaxb.mapping.FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed) {
    this.fixedWeekdayRelativeToFixed = fixedWeekdayRelativeToFixed;
  }

  @Override
  public DayOfWeek weekday() {
    return DayOfWeek.valueOf(fixedWeekdayRelativeToFixed.getWeekday().name());
  }

  @Override
  public Relation when() {
    return Relation.valueOf(fixedWeekdayRelativeToFixed.getWhen().name());
  }

  @Override
  public Fixed day() {
    return new JaxbFixed(fixedWeekdayRelativeToFixed.getDay());
  }

  @Override
  public Occurrance which() {
    return Occurrance.valueOf(fixedWeekdayRelativeToFixed.getWhich().name());
  }

  @Override
  public String descriptionPropertiesKey() {
    return fixedWeekdayRelativeToFixed.getDescriptionPropertiesKey();
  }

  @Override
  public HolidayType officiality() {
    return fixedWeekdayRelativeToFixed.getLocalizedType() == null
      ? HolidayType.OFFICIAL_HOLIDAY
      : HolidayType.valueOf(fixedWeekdayRelativeToFixed.getLocalizedType().name());
  }

  @Override
  public Year validFrom() {
    return Year.of(fixedWeekdayRelativeToFixed.getValidFrom());
  }

  @Override
  public Year validTo() {
    return Year.of(fixedWeekdayRelativeToFixed.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return fixedWeekdayRelativeToFixed.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixedWeekdayRelativeToFixed.getEvery());
  }
}
