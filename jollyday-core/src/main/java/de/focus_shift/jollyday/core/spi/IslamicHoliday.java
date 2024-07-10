package de.focus_shift.jollyday.core.spi;

public interface IslamicHoliday extends Described, Limited, Movable {

  IslamicHolidayType type();

  @Override
  default String descriptionPropertiesKeyPrefix() {
    return "islamic.";
  }
}
