package de.focus_shift.jollyday.core;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import static de.focus_shift.jollyday.core.util.ResourceUtil.getHolidayDescription;

/**
 * Represents the holiday and contains the actual date and an localized
 * description.
 */
public final class Holiday implements Comparable<Holiday> {
  /**
   * The calculated hashcode cached for performance.
   */
  private int hashCode;
  /**
   * The date the holiday occurs.
   */
  private final LocalDate actualDate;
  /**
   * The observed date of the holiday.
   */
  private final LocalDate observedDate;
  /**
   * The properties key to retrieve the description with.
   */
  private final String propertiesKey;
  /**
   * The type of holiday. e.g. official holiday or not.
   */
  private final HolidayType type;

  /**
   * Constructs a holiday for a date using the provided properties key to
   * retrieve the description with.
   *
   * @param actualDate    a {@link LocalDate} object.
   * @param propertiesKey a {@link java.lang.String} object.
   * @param type          a {@link HolidayType} object.
   */
  public Holiday(final LocalDate actualDate, final String propertiesKey, final HolidayType type) {
    this(actualDate, null, propertiesKey, type);
  }

  /**
   * Constructs a holiday for a date using the provided properties key to
   * retrieve the description with.
   *
   * @param actualDate    a {@link LocalDate} object.
   * @param observedDate  a {@link LocalDate} object.
   * @param propertiesKey a {@link java.lang.String} object.
   * @param type          a {@link HolidayType} object.
   */
  public Holiday(final LocalDate actualDate, final LocalDate observedDate, final String propertiesKey, final HolidayType type) {
    super();
    this.type = type;
    this.actualDate = actualDate;
    this.observedDate = observedDate;
    this.propertiesKey = propertiesKey == null ? "" : propertiesKey;
  }

  /**
   * Returns the calculated holiday date.
   * <p>
   * If the holiday is {@link de.focus_shift.jollyday.core.spi.Movable} and the holiday was moved, then:
   * <ul>
   *  <li>the observed holiday date is given</li>
   *  <li>otherwise, the actual holiday date</li>
   * </ul>
   *  @return if holiday was moved the observed date, otherwise the actual date
   */
  public LocalDate getDate() {
    return Optional.ofNullable(observedDate).orElse(actualDate);
  }

  /**
   * <p>
   * Returns the actual date.
   * <p>
   * If you want the observed holiday date then use {@link #getObservedDate()}
   * </p>
   *
   * @return the actual holiday date
   */
  public LocalDate getActualDate() {
    return actualDate;
  }

  /**
   * <p>
   * Getter for the field <code>observedDate</code>.
   * </p>
   *
   * @return the observed holiday date as optional
   */
  public Optional<LocalDate> getObservedDate() {
    return Optional.ofNullable(observedDate);
  }

  /**
   * <p>
   * Getter for the field <code>propertiesKey</code>.
   * </p>
   *
   * @return the holidays properties key
   */
  public String getPropertiesKey() {
    return propertiesKey;
  }

  /**
   * The description read with the default locale.
   *
   * @return Description for this holiday
   */
  public String getDescription() {
    return getHolidayDescription(getPropertiesKey());
  }

  /**
   * The description read with the provided locale.
   *
   * @param locale a {@link java.util.Locale} object.
   * @return Description for this holiday
   */
  public String getDescription(Locale locale) {
    return getHolidayDescription(locale, getPropertiesKey());
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    final Holiday holiday = (Holiday) obj;
    return Objects.equals(getDate(), holiday.getDate()) &&
      Objects.equals(propertiesKey, holiday.propertiesKey) &&
      type == holiday.type;
  }

  @Override
  public int hashCode() {
    if (hashCode == 0) {
      hashCode = Objects.hash(getDate(), propertiesKey, type);
    }
    return hashCode;
  }

  @Override
  public String toString() {
    return getDate().toString() + " (" + getDescription() + ")";
  }

  /**
   * Gets the type holiday.
   *
   * @return the type holiday
   */
  public HolidayType getType() {
    return type;
  }

  /**
   * Compares this holiday to another holiday.
   * <p>
   * The comparison is primarily based on the date, from earliest to latest by using the LocalDate comparator.
   *
   * @param other the other holiday to compare to, not null
   * @return the comparator value, negative if less, positive if greater
   */
  @Override
  public int compareTo(final Holiday other) {
    return this.getDate().compareTo(other.getDate());
  }
}
