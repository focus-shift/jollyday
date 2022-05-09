package de.jollyday.spi;

import org.threeten.extra.Days;

import java.time.chrono.Chronology;

/**
 * @author sdiedrichsen
 * @version $
 * @since 03.11.19
 */
public interface RelativeToEasterSunday extends Described, Limited {
  Chronology chronology();

  Days days();
}
