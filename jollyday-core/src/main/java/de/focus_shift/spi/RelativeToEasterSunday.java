package de.focus_shift.spi;

import org.threeten.extra.Days;

import java.time.chrono.Chronology;

public interface RelativeToEasterSunday extends Described, Limited {
  Chronology chronology();

  Days days();
}
