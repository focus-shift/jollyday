package de.focus_shift.jollyday.pojo.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.focus_shift.jollyday.core.HolidayCalendar;

class JavaGeneratorTest {

  @Test
  @Disabled("No actual test currently used to generate Java source classes. Not really pretty but it works for now...")
  void generateJavaConfigurationService() {

    try (FileWriter fileWriter = new FileWriter("src/main/java/de/focus_shift/jollyday/java/JavaConfigurationService.java", false)) {
      JavaGenerator generator = new JavaGenerator();
      generator.generateConfigurationSource(fileWriter);

      for (HolidayCalendar cal : HolidayCalendar.values()) {
        String calendarId = cal.getId().toLowerCase(Locale.ROOT);
        try (FileWriter holidayFileWriter = new FileWriter("src/main/java/de/focus_shift/jollyday/java/holidays/Holiday_" + calendarId + ".java", false)) {
          generator.generateHolidaySource(cal, holidayFileWriter);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}

