package de.focus_shift.jollyday.pojo.generator;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.chrono.Chronology;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.threeten.extra.Days;

import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.spi.ChristianHoliday;
import de.focus_shift.jollyday.core.spi.Configuration;
import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHoliday;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.IslamicHoliday;
import de.focus_shift.jollyday.core.spi.MovingCondition;
import de.focus_shift.jollyday.core.spi.RelativeToEasterSunday;
import de.focus_shift.jollyday.core.spi.RelativeToFixed;
import de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.jackson.JacksonConfiguration;
import de.focus_shift.jollyday.jackson.XMLUtil;

class PojoGenerator {

  void generateHolidaySource(HolidayCalendar cal, Writer writer) throws IOException{
    XMLUtil xmlUtil = new XMLUtil();

    String calendarId = cal.getId().toLowerCase();
    String holidayFileName = "Holidays_" + calendarId + ".xml";


    InputStream inputStream = PojoGenerator.class.getClassLoader().getResourceAsStream("holidays/" + holidayFileName);
    if (inputStream == null) {
      System.err.println("No input found for " + holidayFileName);
      return;
    }
    JacksonConfiguration jacksonConfiguration = new JacksonConfiguration(xmlUtil.unmarshallConfiguration(inputStream));

    writer.write("package de.focus_shift.jollyday.pojo.holidays;\n\n");
    writeImports(writer);
    writer.write("import de.focus_shift.jollyday.pojo.*;\n\n");
    writer.write("public class Holiday_"+calendarId+" {\n\n");


    writer.write("  public static JavaConfiguration configuration;\n\n");
    StringBuilder sb = new StringBuilder();
    sb.append("  static {\n");
    sb.append("    configuration = ");
    sb.append(configuration(jacksonConfiguration));
    sb.append(";\n");
    sb.append("  }\n");
    sb.append("}\n");

    writer.write(sb.toString());
  }



  void generateConfigurationSource(Writer writer) throws IOException {


    writeHeader(writer);


    writer.append("  static Map<String,JavaConfiguration> configurations = new HashMap<>();\n");

    writer.append("  static {\n");
    for (HolidayCalendar cal : HolidayCalendar.values()) {
      String calendarId = cal.getId().toLowerCase();
      writer.write(String.format("    configurations.put(\"%s\",Holiday_%s.configuration);\n", calendarId, calendarId));
    }
    writer.write("  }\n");
    writeFooter(writer);
  }



  private void writeImports(Writer writer) throws IOException{
    StringBuilder sb = new StringBuilder();
    sb.append("import java.time.DayOfWeek;\n");
    sb.append("import java.time.Month;\n");
    sb.append("import java.time.MonthDay;\n");
    sb.append("import java.time.Year;\n");
    sb.append("import java.time.chrono.Chronology;\n");
    sb.append("import java.util.HashMap;\n");
    sb.append("import java.util.List;\n");
    sb.append("import java.util.Map;\n\n");

    sb.append("import de.focus_shift.jollyday.core.HolidayType;\n");
    sb.append("import de.focus_shift.jollyday.core.ManagerParameter;\n");
    sb.append("import de.focus_shift.jollyday.core.spi.ChristianHolidayType;\n");
    sb.append("import de.focus_shift.jollyday.core.spi.Configuration;\n");
    sb.append("import de.focus_shift.jollyday.core.spi.ConfigurationService;\n");
    sb.append("import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHolidayType;\n");
    sb.append("import de.focus_shift.jollyday.core.spi.IslamicHolidayType;\n");
    sb.append("import de.focus_shift.jollyday.core.spi.Occurrance;\n");
    sb.append("import de.focus_shift.jollyday.core.spi.Relation;\n");
    sb.append("import de.focus_shift.jollyday.core.spi.With;\n");
    sb.append("import de.focus_shift.jollyday.core.spi.YearCycle;\n");

    writer.write(sb.toString());
  }

  private void writeHeader(Writer writer) throws IOException{
    writer.write("package de.focus_shift.jollyday.pojo;\n\n");

    writer.write("import java.util.HashMap;\n");
    writer.write("import java.util.Map;\n\n");

    writer.write("import de.focus_shift.jollyday.core.ManagerParameter;\n");
    writer.write("import de.focus_shift.jollyday.core.spi.Configuration;\n");
    writer.write("import de.focus_shift.jollyday.core.spi.ConfigurationService;\n");
    writer.write("import de.focus_shift.jollyday.pojo.holidays.*;\n\n");

    writer.write("public class JavaConfigurationService implements ConfigurationService {\n\n");
  }

  private  void writeFooter(Writer writer) throws IOException{
    StringBuilder sb = new StringBuilder();

    sb.append("\n");
    sb.append("  @Override\n");
    sb.append("  public Configuration getConfiguration(ManagerParameter parameter) {\n");
    sb.append("    final String cacheKey = parameter.createCacheKey();\n");
    sb.append("\n");
    sb.append("    JavaConfiguration configuration = configurations.get(cacheKey);\n");
    sb.append("    return configuration;\n");
    sb.append("  }\n");
    sb.append("}");

    writer.write(sb.toString());
  }
  // public JavaConfiguration(JavaHolidays javaHolidays, List<Configuration> subConfigurations, String hierarchy, String description)
  private String configuration(Configuration configuration) {
    return constructor("JavaConfiguration", holidays(configuration.holidays()), configurations(configuration.subConfigurations()), string(configuration.hierarchy()), string(configuration.description()));
  }

  private String configurations(Stream<Configuration> configurations) {
    String result;
    if (configurations != null) {
      result = configurations.map(c -> configuration(c)).collect(Collectors.joining(",", "List.of(", ")"));
      if ("List.of()".equals(result)) {
        result = "null";
      }
    } else {
      result = "null";
    }

    return result;
  }

  // public JavaHolidays(List<ChristianHoliday> christianHoliday, List<IslamicHoliday> islamicHoliday, List<EthiopianOrthodoxHoliday> ethiopianOrthodoxHoliday, List<Fixed> fixed, List<FixedWeekdayInMonth> fixedWeekday, List<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed, List<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed, List<RelativeToFixed> relativeToFixed, List<RelativeToWeekdayInMonth> relativeToWeekdayInMonth, List<RelativeToEasterSunday> relativeToEasterSunday)
  private String holidays(Holidays holidays) {
    if (holidays == null) {
      return "null";
    }

    StringBuilder sb = new StringBuilder();
    sb.append(String.format("new JavaHolidays()\n"));
    for (de.focus_shift.jollyday.core.spi.Fixed fixed : holidays.fixed()) {
      sb.append(String.format("      .addFixed(%s)\n", fixed(fixed)));
    }

    for (ChristianHoliday christianHoliday : holidays.christianHolidays()) {
      sb.append(String.format("      .addChristianHoliday(%s)\n", christianHoliday(christianHoliday)));
    }

    for (EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday : holidays.ethiopianOrthodoxHolidays()) {
      sb.append(String.format("      .addEthiopianOrthodoxHoliday(%s)\n", ethiopianOrthodoxHoliday(ethiopianOrthodoxHoliday)));
    }

    for (IslamicHoliday islamicHoliday : holidays.islamicHolidays()) {
      sb.append(String.format("      .addIslamicHoliday(%s)\n", islamicHoliday(islamicHoliday)));
    }

    for (FixedWeekdayBetweenFixed fixedWeekdayBetweenFixed : holidays.fixedWeekdayBetweenFixed()) {
      sb.append(String.format("      .addFixedWeekdayBetweenFixed(%s)\n", fixedWeekdayBetweenFixed(fixedWeekdayBetweenFixed)));
    }

    for (FixedWeekdayInMonth fixedWeekdayInMonth : holidays.fixedWeekdays()) {
      sb.append(String.format("      .addFixedWeekday(%s)\n", fixedWeekdayInMonth(fixedWeekdayInMonth)));
    }

    for (FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed : holidays.fixedWeekdayRelativeToFixed()) {
      sb.append(String.format("      .addFixedWeekdayRelativeToFixed(%s)\n", fixedWeekdayRelativeToFixed(fixedWeekdayRelativeToFixed)));
    }

    for (RelativeToEasterSunday relativeToEasterSunday : holidays.relativeToEasterSunday()) {
      sb.append(String.format("      .addRelativeToEasterSunday(%s)\n", relativeToEasterSunday(relativeToEasterSunday)));
    }

    for (RelativeToFixed relativeToFixed : holidays.relativeToFixed()) {
      sb.append(String.format("      .addRelativeToFixed(%s)\n", relativeToFixed(relativeToFixed)));
    }

    for (RelativeToWeekdayInMonth relativeToWeekdayInMonth : holidays.relativeToWeekdayInMonth()) {
      sb.append(String.format("      .addRelativeToWeekdayInMonth(%s)\n", relativeToWeekdayInMonth(relativeToWeekdayInMonth)));
    }

    return sb.toString();
  }

  // public JavaFixed(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, List<MovingCondition> conditions, MonthDay day)
  private String fixed(Fixed fixed) {
    return constructor("JavaFixed", string(fixed.descriptionPropertiesKey()), enums(fixed.officiality()), year(fixed.validFrom()), year(fixed.validTo()), yearCycle(fixed.cycle()), movingConditions(fixed.conditions()), monthDay(fixed.day()));
  }

  //public JavaChristianHoliday(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, List<MovingCondition> conditions, ChristianHolidayType type, Chronology chronology)
  private String christianHoliday(ChristianHoliday christianHoliday) {
    return constructor("JavaChristianHoliday", string(christianHoliday.descriptionPropertiesKey()), enums(christianHoliday.officiality()), year(christianHoliday.validFrom()), year(christianHoliday.validTo()), yearCycle(christianHoliday.cycle()), movingConditions(christianHoliday.conditions()), enums(christianHoliday.type()), chronology(christianHoliday.chronology()));
  }

  // public JavaEthiopianOrthodoxHoliday(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, EthiopianOrthodoxHolidayType type)
  private String ethiopianOrthodoxHoliday(EthiopianOrthodoxHoliday hol) {
    return constructor("JavaEthiopianOrthodoxHoliday", string(hol.descriptionPropertiesKey()), enums(hol.officiality()), year(hol.validFrom()), year(hol.validTo()), yearCycle(hol.cycle()), enums(hol.type()));
  }

  // public JavaIslamicHoliday(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, List<MovingCondition> conditions, IslamicHolidayType type)
  private String islamicHoliday(IslamicHoliday hol) {
    return constructor("JavaIslamicHoliday", string(hol.descriptionPropertiesKey()), enums(hol.officiality()), year(hol.validFrom()), year(hol.validTo()), yearCycle(hol.cycle()), movingConditions(hol.conditions()), enums(hol.type()));
  }

  // public JavaFixedWeekdayBetweenFixed(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, Fixed from, Fixed to, DayOfWeek weekday)
  private String fixedWeekdayBetweenFixed(FixedWeekdayBetweenFixed hol) {
    return constructor("JavaFixedWeekdayBetweenFixed", string(hol.descriptionPropertiesKey()), enums(hol.officiality()), year(hol.validFrom()), year(hol.validTo()), yearCycle(hol.cycle()), fixed(hol.from()), fixed(hol.to()), dayOfWeek(hol.weekday()));
  }

  // public JavaFixedWeekdayInMonth(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, DayOfWeek weekday, Month month, Occurrance which)
  private String fixedWeekdayInMonth(FixedWeekdayInMonth hol) {
    return constructor("JavaFixedWeekdayInMonth", string(hol.descriptionPropertiesKey()), enums(hol.officiality()), year(hol.validFrom()), year(hol.validTo()), yearCycle(hol.cycle()), dayOfWeek(hol.weekday()), month(hol.month()), enums(hol.which()));
  }

  // public JavaFixedWeekdayRelativeToFixed(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, DayOfWeek weekday, Relation when, Fixed day, Occurrance which)
  private String fixedWeekdayRelativeToFixed(FixedWeekdayRelativeToFixed hol) {
    return constructor("JavaFixedWeekdayRelativeToFixed", string(hol.descriptionPropertiesKey()), enums(hol.officiality()), year(hol.validFrom()), year(hol.validTo()), yearCycle(hol.cycle()), dayOfWeek(hol.weekday()), enums(hol.when()), fixed(hol.day()), enums(hol.which()));
  }

  // public JavaRelativeToEasterSunday(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, Chronology chronology, Days days)
  private String relativeToEasterSunday(RelativeToEasterSunday hol) {
    return constructor("JavaRelativeToEasterSunday", string(hol.descriptionPropertiesKey()), enums(hol.officiality()), year(hol.validFrom()), year(hol.validTo()), yearCycle(hol.cycle()), chronology(hol.chronology()), days(hol.days()));
  }

  // public JavaRelativeToFixed(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, Fixed date, DayOfWeek weekday, Relation when, Days days) {
  private String relativeToFixed(RelativeToFixed hol) {
    return constructor("JavaRelativeToFixed", string(hol.descriptionPropertiesKey()), enums(hol.officiality()), year(hol.validFrom()), year(hol.validTo()), yearCycle(hol.cycle()), fixed(hol.date()), dayOfWeek(hol.weekday()), enums(hol.when()), days(hol.days()));
  }

  // public JavaRelativeToWeekdayInMonth(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, FixedWeekdayInMonth weekdayInMonth, DayOfWeek weekday, Relation when)
  private String relativeToWeekdayInMonth(RelativeToWeekdayInMonth hol) {
    return constructor("JavaRelativeToWeekdayInMonth", string(hol.descriptionPropertiesKey()), enums(hol.officiality()), year(hol.validFrom()), year(hol.validTo()), yearCycle(hol.cycle()), fixedWeekdayInMonth(hol.weekdayInMonth()), dayOfWeek(hol.weekday()), enums(hol.when()));
  }

  private String constructor(Object... arguments) {
    StringBuilder sb = new StringBuilder();
    sb.append("new %s(");
    for (int i = 0; i < arguments.length - 1; i++) {
      sb.append("%s,");
    }
    sb.setLength(sb.length() - 1);
    sb.append(")");

    return String.format(sb.toString(), arguments);
  }

  private String string(String string) {
    return String.format("\"%s\"", string);
  }

  private String year(Year year) {
    if (year != null) {
      return String.format("Year.of(%s)", year.getValue());
    } else {
      return "null";
    }
  }

  private String enums(Enum enumz) {
    return enumz.getDeclaringClass().getSimpleName() + "." + enumz.name();
  }

  private String yearCycle(YearCycle yearCycle) {
    return enums(yearCycle);
  }

  private String days(Days days) {
    if (days != null) {
      return String.format("Days.of(%s)", days.getAmount());
    } else {
      return "null";
    }
  }

  private String monthDay(MonthDay monthDay) {
    if (monthDay != null) {
      return String.format("MonthDay.of(%s,%s)", monthDay.getMonthValue(), monthDay.getDayOfMonth());
    } else {
      return "null";
    }
  }

  private String month(Month monthDay) {
    if (monthDay != null) {
      return String.format("Month.of(%s)", monthDay.getValue());
    } else {
      return "null";
    }
  }

  private String dayOfWeek(DayOfWeek dayOfWeek) {

    if (dayOfWeek != null) {
      return String.format("DayOfWeek.of(%s)", dayOfWeek.getValue());
    } else {
      return "null";
    }
  }

  private String chronology(Chronology chronology) {
    if (chronology != null) {
      return String.format("Chronology.of(\"%s\")", chronology.getId());
    } else {
      return "null";
    }
  }

  private String movingConditions(List<MovingCondition> movingConditions) {
    if (movingConditions.isEmpty()) {
      return "null";
    } else {
      StringBuilder sb = new StringBuilder();
      sb.append("List.of(");
      movingConditions.forEach(m -> sb.append(movingCondition(m)).append(","));
      sb.setLength(sb.length() - 1);
      sb.append(")");
      return sb.toString();
    }
  }

  // public JavaMovingCondition(DayOfWeek substitute, With with, DayOfWeek weekday) {
  private String movingCondition(MovingCondition movingCondition) {
    if (movingCondition == null) {
      return "null";
    } else {
      return constructor("JavaMovingCondition", dayOfWeek(movingCondition.substitute()), enums(movingCondition.with()), dayOfWeek(movingCondition.weekday()));
    }
  }
}
