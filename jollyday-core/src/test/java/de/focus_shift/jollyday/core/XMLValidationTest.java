package de.focus_shift.jollyday.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xmlunit.validation.ValidationResult;
import org.xmlunit.validation.Validator;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.xmlunit.validation.Languages.W3C_XML_SCHEMA_NS_URI;

class XMLValidationTest {

  private File schemaFile;
  private Validator validator;

  @BeforeEach
  void setUp() {
    schemaFile = new File("src/main/resources/focus_shift.de/jollyday/schema/holiday/holiday.xsd");
    validator = Validator.forLanguage(W3C_XML_SCHEMA_NS_URI);
    validator.setSchemaSource(new StreamSource(schemaFile));
  }

  @Test
  void ensureThatHolidaySchemaIsValid() {
    assertThat(schemaFile).exists();
    assertThat(validator.validateSchema().isValid()).isTrue();
  }

  @Test
  void testHolidayFilesAreValid() throws Exception {
    final Path holidaysFolder = Paths.get("src/main/resources/holidays/");
    try (final Stream<Path> list = Files.list(holidaysFolder)) {
      list.forEach(this::validateHolidayFile);
    }
  }

  private void validateHolidayFile(Path path) {
    try (final FileInputStream inputStream = new FileInputStream(path.toFile())) {
      final ValidationResult validationResult = validator.validateInstance(new StreamSource(inputStream));
      assertThat(validationResult.isValid())
        .withFailMessage(validationResult.getProblems().toString())
        .isTrue();
    } catch (IOException e) {
      throw new IllegalStateException("Cannot validate holiday file " + path);
    }
  }
}
