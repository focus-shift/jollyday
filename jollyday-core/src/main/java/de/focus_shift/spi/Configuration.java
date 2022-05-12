package de.focus_shift.spi;

import java.util.stream.Stream;

/**
 * Represents the configuration.
 */
public interface Configuration {

  Holidays holidays();

  Stream<Configuration> subConfigurations();

  String hierarchy();

  String description();

}
