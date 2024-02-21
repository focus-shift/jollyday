package de.focus_shift.jollyday.core;

/**
 * Type of holiday. Each holiday can be placed in a category and this is
 * represented by this type. The categories can mark a holiday as an official
 * holiday or not.
 */
public enum HolidayType {

  OFFICIAL_HOLIDAY {
    @Override
    public boolean isOfficialHoliday() {
      return true;
    }
  },

  BANK_HOLIDAY {
    @Override
    public boolean isOfficialHoliday() {
      return false;
    }
  },

  SCHOOL_HOLIDAY {
    @Override
    public boolean isOfficialHoliday() {
      return false;
    }
  },

  AUTHORITIES_HOLIDAY {
    @Override
    public boolean isOfficialHoliday() {
      return false;
    }
  },

  @Deprecated(since = "0.26.0", forRemoval = true)
  UNOFFICIAL_HOLIDAY {
    @Override
    public boolean isOfficialHoliday() {
      return false;
    }
  };

  public abstract boolean isOfficialHoliday();

}
