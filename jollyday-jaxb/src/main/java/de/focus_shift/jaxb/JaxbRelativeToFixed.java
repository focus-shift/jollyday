package de.focus_shift.jaxb;

import de.focus_shift.HolidayType;
import de.focus_shift.spi.Fixed;
import de.focus_shift.spi.Relation;
import de.focus_shift.spi.RelativeToFixed;
import de.focus_shift.spi.YearCycle;
import org.threeten.extra.Days;

import java.time.DayOfWeek;
import java.time.Year;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbRelativeToFixed implements RelativeToFixed {

  private final de.focus_shift.jaxb.mapping.RelativeToFixed relativeToFixed;

  public JaxbRelativeToFixed(de.focus_shift.jaxb.mapping.RelativeToFixed relativeToFixed) {
    this.relativeToFixed = relativeToFixed;
  }

  @Override
  public Fixed date() {
    return new JaxbFixed(relativeToFixed.getDate());
  }

  @Override
  public DayOfWeek weekday() {
    return relativeToFixed.getWeekday() == null
      ? null
      : DayOfWeek.valueOf(relativeToFixed.getWeekday().name());
  }

  @Override
  public Relation when() {
    return relativeToFixed.getWhen() == null
      ? null
      : Relation.valueOf(relativeToFixed.getWhen().name());
  }

  @Override
  public Days days() {
    return relativeToFixed.getDays() == null
      ? null
      : Days.of(relativeToFixed.getDays());
  }

  @Override
  public String descriptionPropertiesKey() {
    return relativeToFixed.getDescriptionPropertiesKey();
  }

  @Override
  public HolidayType officiality() {
    return relativeToFixed.getLocalizedType() == null
      ? HolidayType.OFFICIAL_HOLIDAY
      : HolidayType.valueOf(relativeToFixed.getLocalizedType().name());
  }

  @Override
  public Year validFrom() {
    return relativeToFixed.getValidFrom() == null
      ? null
      : Year.of(relativeToFixed.getValidFrom());
  }

  @Override
  public Year validTo() {
    return relativeToFixed.getValidTo() == null
      ? null
      : Year.of(relativeToFixed.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return relativeToFixed.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(relativeToFixed.getEvery());
  }
}
