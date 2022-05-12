package de.focus_shift.jaxb;

import de.focus_shift.HolidayType;
import de.focus_shift.spi.EthiopianOrthodoxHoliday;
import de.focus_shift.spi.EthiopianOrthodoxHolidayType;
import de.focus_shift.spi.YearCycle;

import java.time.Year;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbEthiopianOrthodoxHoliday implements EthiopianOrthodoxHoliday {

  private final de.focus_shift.jaxb.mapping.EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday;

  public JaxbEthiopianOrthodoxHoliday(de.focus_shift.jaxb.mapping.EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday) {
    this.ethiopianOrthodoxHoliday = ethiopianOrthodoxHoliday;
  }

  @Override
  public String descriptionPropertiesKey() {
    return ethiopianOrthodoxHoliday.getDescriptionPropertiesKey();
  }

  @Override
  public HolidayType officiality() {
    return ethiopianOrthodoxHoliday.getLocalizedType() == null
      ? HolidayType.OFFICIAL_HOLIDAY
      : HolidayType.valueOf(ethiopianOrthodoxHoliday.getLocalizedType().name());
  }

  @Override
  public EthiopianOrthodoxHolidayType type() {
    return EthiopianOrthodoxHolidayType.valueOf(ethiopianOrthodoxHoliday.getType().name());
  }

  @Override
  public Year validFrom() {
    return Year.of(ethiopianOrthodoxHoliday.getValidFrom());
  }

  @Override
  public Year validTo() {
    return Year.of(ethiopianOrthodoxHoliday.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return ethiopianOrthodoxHoliday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(ethiopianOrthodoxHoliday.getEvery());
  }
}
