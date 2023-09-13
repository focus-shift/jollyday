package de.focus_shift.jollyday.core;

/**
 * Type of holiday. Each holiday can be placed in a category and this is
 * represented by this type. The categories can mark a holiday as a official
 * holiday or not.
 *
 * @author tboven
 * @version $Id: $
 */
public enum HolidayType {

  OFFICIAL_HOLIDAY {
    @Override
    public boolean isOfficialHoliday() {
      return true;
    }
  },

  UNOFFICIAL_HOLIDAY {
    @Override
    public boolean isOfficialHoliday() {
      return false;
    }
  };

  public abstract boolean isOfficialHoliday();

}
