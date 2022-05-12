package de.focus_shift.tests.parsers;

import java.io.Serializable;
import java.util.Comparator;

import de.focus_shift.Holiday;

/**
 * Compares two {@link Holiday} instances upon their dates.
 *
 * @author Sven Diedrichsen
 */
public class HolidayComparator implements Comparator<Holiday>, Serializable {

  private static final long serialVersionUID = 7346124638993088797L;

  @Override
  public int compare(Holiday o1, Holiday o2) {
    return o1.getDate().compareTo(o2.getDate());
  }

}
