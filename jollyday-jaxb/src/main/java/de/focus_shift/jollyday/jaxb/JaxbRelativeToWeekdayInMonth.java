package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonthHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonthHolidayConfiguration;
import de.focus_shift.jollyday.jaxb.mapping.RelativeToWeekdayInMonth;
import java.time.DayOfWeek;
import java.time.Year;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/** see {@link RelativeToWeekdayInMonthHolidayConfiguration} */
class JaxbRelativeToWeekdayInMonth implements RelativeToWeekdayInMonthHolidayConfiguration {

  private final RelativeToWeekdayInMonth relativeToWeekdayInMonth;

  JaxbRelativeToWeekdayInMonth(RelativeToWeekdayInMonth relativeToWeekdayInMonth) {
    this.relativeToWeekdayInMonth = relativeToWeekdayInMonth;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull FixedWeekdayInMonthHolidayConfiguration weekdayInMonth() {
    return new JaxbFixedWeekdayInMonth(relativeToWeekdayInMonth.getFixedWeekday());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull DayOfWeek weekday() {
    return DayOfWeek.valueOf(relativeToWeekdayInMonth.getWeekday().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Relation when() {
    return Relation.valueOf(relativeToWeekdayInMonth.getWhen().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull String descriptionPropertiesKey() {
    return relativeToWeekdayInMonth.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull HolidayType holidayType() {
    return relativeToWeekdayInMonth.getLocalizedType() == null
        ? HolidayType.PUBLIC_HOLIDAY
        : HolidayType.valueOf(relativeToWeekdayInMonth.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @Nullable Year validFrom() {
    return relativeToWeekdayInMonth.getValidFrom() == null
        ? null
        : Year.of(relativeToWeekdayInMonth.getValidFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @Nullable Year validTo() {
    return relativeToWeekdayInMonth.getValidTo() == null
        ? null
        : Year.of(relativeToWeekdayInMonth.getValidTo());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull YearCycle cycle() {
    return relativeToWeekdayInMonth.getEvery() == null
        ? YearCycle.EVERY_YEAR
        : YearCycle.valueOf(relativeToWeekdayInMonth.getEvery().name());
  }
}
