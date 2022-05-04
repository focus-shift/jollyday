package de.jollyday.jaxb;

import de.jollyday.HolidayType;
import de.jollyday.spi.FixedWeekdayInMonth;
import de.jollyday.spi.Relation;
import de.jollyday.spi.RelativeToWeekdayInMonth;
import de.jollyday.spi.YearCycle;

import java.time.DayOfWeek;
import java.time.Year;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbRelativeToWeekdayInMonth implements RelativeToWeekdayInMonth {

  private final de.jollyday.jaxb.mapping.RelativeToWeekdayInMonth relativeToWeekdayInMonth;

  public JaxbRelativeToWeekdayInMonth(de.jollyday.jaxb.mapping.RelativeToWeekdayInMonth relativeToWeekdayInMonth) {
    this.relativeToWeekdayInMonth = relativeToWeekdayInMonth;
  }

  @Override
  public FixedWeekdayInMonth weekdayInMonth() {
    return new JaxbFixedWeekdayInMonth(relativeToWeekdayInMonth.getFixedWeekday());
  }

  @Override
  public DayOfWeek weekday() {
    return DayOfWeek.valueOf(relativeToWeekdayInMonth.getWeekday().name());
  }

  @Override
  public Relation when() {
    return Relation.valueOf(relativeToWeekdayInMonth.getWhen().name());
  }

  @Override
  public String descriptionPropertiesKey() {
    return relativeToWeekdayInMonth.getDescriptionPropertiesKey();
  }

  @Override
  public HolidayType officiality() {
    return relativeToWeekdayInMonth.getLocalizedType() == null
      ? HolidayType.OFFICIAL_HOLIDAY
      : HolidayType.valueOf(relativeToWeekdayInMonth.getLocalizedType().name());
  }

  @Override
  public Year validFrom() {
    return Year.of(relativeToWeekdayInMonth.getValidFrom());
  }

  @Override
  public Year validTo() {
    return Year.of(relativeToWeekdayInMonth.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return relativeToWeekdayInMonth.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(relativeToWeekdayInMonth.getEvery());
  }
}
