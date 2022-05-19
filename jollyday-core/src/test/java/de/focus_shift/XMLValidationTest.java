package de.focus_shift;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xmlunit.validation.ValidationResult;
import org.xmlunit.validation.Validator;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.xmlunit.validation.Languages.W3C_XML_SCHEMA_NS_URI;

class XMLValidationTest {

  private File schemaFile;
  private Validator validator;

  @BeforeEach
  void setUp() {
    schemaFile = new File("src/main/resources/Holiday.xsd");
    validator = Validator.forLanguage(W3C_XML_SCHEMA_NS_URI);
    validator.setSchemaSource(new StreamSource(schemaFile));
  }

  @Test
  void testSchemaIsValid() {
    assertThat(schemaFile).exists();
    final ValidationResult validationResult = validator.validateSchema();
    assertThat(validationResult.isValid()).isTrue();
  }

  @Test
  void testHolidayFilesAreValid() throws Exception {
    final Path holidaysFolder = Paths.get("src/main/resources/holidays/");
    Files.list(holidaysFolder).forEach(this::validateHolidayFile);
  }

  private void validateHolidayFile(Path path) {
    try {
      final ValidationResult validationResult = validator.validateInstance(new StreamSource(new FileInputStream(path.toFile())));
      assertThat(validationResult.isValid()).isTrue();
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Cannot validate holiday file " + path);
    }
  }
}
