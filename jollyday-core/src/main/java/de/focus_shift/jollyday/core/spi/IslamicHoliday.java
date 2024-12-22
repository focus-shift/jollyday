package de.focus_shift.jollyday.core.spi;

/**
 * Represents a islamic holiday that can be
 *
 * <ul>
 *   <li>Movable</li>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 *
 * and has a special {@link IslamicHolidayType}.
 * The {@link de.focus_shift.jollyday.core.parser.impl.IslamicHolidayParser} is used.
 */
public interface IslamicHoliday extends Described, Limited, Movable {

  IslamicHolidayType type();

}
