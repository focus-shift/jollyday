package de.focus_shift.jollyday.pojo;

import java.util.List;
import java.util.stream.Stream;

import de.focus_shift.jollyday.core.spi.Configuration;
import de.focus_shift.jollyday.core.spi.Holidays;

public class PojoConfiguration implements Configuration {

  private PojoHolidays javaHolidays;
  private List<Configuration> subConfigurations;
  private String hierarchy;
  private String description;

  public PojoConfiguration() {
  }

  public PojoConfiguration(PojoHolidays javaHolidays, List<Configuration> subConfigurations, String hierarchy, String description) {
    this.javaHolidays = javaHolidays;
    this.subConfigurations = subConfigurations;
    this.hierarchy = hierarchy;
    this.description = description;
  }

  @Override
  public Holidays holidays() {
    return javaHolidays;
  }

  @Override
  public Stream<Configuration> subConfigurations() {
    return subConfigurations != null ? subConfigurations.stream() : Stream.empty();
  }

  @Override
  public String hierarchy() {
    return hierarchy;
  }

  @Override
  public String description() {
    return description;
  }

}
