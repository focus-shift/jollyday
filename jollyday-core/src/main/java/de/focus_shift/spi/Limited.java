package de.focus_shift.spi;

import java.time.Year;

public interface Limited {
  Year validFrom();

  Year validTo();

  YearCycle cycle();
}
