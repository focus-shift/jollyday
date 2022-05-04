package de.jollyday.jaxb;

import de.jollyday.HolidayType;
import de.jollyday.spi.Fixed;
import de.jollyday.spi.FixedWeekdayBetweenFixed;
import de.jollyday.spi.YearCycle;

import java.time.DayOfWeek;
import java.time.Year;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbFixedWeekdayBetweenFixed implements FixedWeekdayBetweenFixed {

  private final de.jollyday.jaxb.mapping.FixedWeekdayBetweenFixed fixedWeekdayBetweenFixed;

  public JaxbFixedWeekdayBetweenFixed(de.jollyday.jaxb.mapping.FixedWeekdayBetweenFixed fixedWeekdayInMonth) {
    this.fixedWeekdayBetweenFixed = fixedWeekdayInMonth;
  }

  @Override
  public Fixed from() {
    return new JaxbFixed(fixedWeekdayBetweenFixed.getFrom());
  }

  @Override
  public Fixed to() {
    return new JaxbFixed(fixedWeekdayBetweenFixed.getTo());
  }

  @Override
  public DayOfWeek weekday() {
    return DayOfWeek.valueOf(fixedWeekdayBetweenFixed.getWeekday().name());
  }

  @Override
  public String descriptionPropertiesKey() {
    return fixedWeekdayBetweenFixed.getDescriptionPropertiesKey();
  }

  @Override
  public HolidayType officiality() {
    return fixedWeekdayBetweenFixed.getLocalizedType() == null
      ? HolidayType.OFFICIAL_HOLIDAY
      : HolidayType.valueOf(fixedWeekdayBetweenFixed.getLocalizedType().name());
  }

  @Override
  public Year validFrom() {
    return Year.of(fixedWeekdayBetweenFixed.getValidFrom());
  }

  @Override
  public Year validTo() {
    return Year.of(fixedWeekdayBetweenFixed.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return fixedWeekdayBetweenFixed.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixedWeekdayBetweenFixed.getEvery());
  }
}
