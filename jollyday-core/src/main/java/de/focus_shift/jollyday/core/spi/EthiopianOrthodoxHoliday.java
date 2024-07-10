package de.focus_shift.jollyday.core.spi;

public interface EthiopianOrthodoxHoliday extends Described, Limited {

  EthiopianOrthodoxHolidayType type();

  @Override
  default String descriptionPropertiesKeyPrefix() {
    return "ethiopian.";
  }
}
