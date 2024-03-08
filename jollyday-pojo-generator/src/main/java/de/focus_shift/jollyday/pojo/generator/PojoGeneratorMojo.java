package de.focus_shift.jollyday.pojo.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Locale;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import de.focus_shift.jollyday.core.HolidayCalendar;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class PojoGeneratorMojo extends AbstractMojo {

    /**
     *  The directory where the pojos will be generated to
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-sources/pojo", property = "outputDir", required = true)
    private File outputDirectory;

    public PojoGeneratorMojo() { }

    public void execute() throws MojoExecutionException {
      final Log log = getLog();

      final File genDir = Paths.get(outputDirectory.getAbsolutePath(), "de/focus_shift/jollyday/pojo/holidays").toFile();
      if (!genDir.exists() && !genDir.mkdirs()) {
          throw new MojoExecutionException("Target directory doesn't exist or cannot be generated: "+ genDir.getAbsolutePath());
      }

      final File configurationService = Paths.get(outputDirectory.getAbsolutePath(), "de/focus_shift/jollyday/pojo/JavaConfigurationService.java").toFile();

      try (FileWriter fileWriter = new FileWriter(configurationService, false)) {
        PojoGenerator generator = new PojoGenerator();
        generator.generateConfigurationSource(fileWriter);

        for (HolidayCalendar cal : HolidayCalendar.values()) {
          String calendarId = cal.getId().toLowerCase(Locale.ROOT);

          final File holidayFile = Paths.get(outputDirectory.getAbsolutePath(), "de/focus_shift/jollyday/pojo/holidays/Holiday_" + calendarId + ".java").toFile();
          try (FileWriter holidayFileWriter = new FileWriter(holidayFile, false)) {
            generator.generateHolidaySource(cal, holidayFileWriter);
          }
        }
      } catch (IOException e) {
        throw new MojoExecutionException(e.getLocalizedMessage());
      }

      log.info("Holiday files created at "+outputDirectory.getAbsolutePath());
    }
}
