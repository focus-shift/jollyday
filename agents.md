---
name: jollyday
description: A Java library for querying public holidays supporting 130+ countries with XML-based holiday calendar configuration
---

# Jollyday - Public Holiday Library

## Project Overview

**Jollyday** is a Java library to query public holidays. It currently supports **over 130 countries**. The holiday data is stored in XML files (one for each country) and will be read from the classpath.

### Key Features

- Supports 130+ countries with public holiday data
- ISO 3166-1 alpha-2 country code support
- ISO 3166-2 subdivision support (states, provinces, regions)
- Multiple holiday types: Fixed dates, moving holidays (Easter-based, Islamic calendar, etc.)
- XML-based configuration with validation via XSD
- Supports JAXB and Jackson for XML unmarshalling
- Java 17+ required

### Modules

| Module             | Description                                    |
|--------------------|------------------------------------------------|
| `jollyday-core`    | Core API, holiday data, parser implementations |
| `jollyday-jackson` | Jackson XML binding implementation             |
| `jollyday-jaxb`    | Jakarta XML Binding implementation             |
| `jollyday-tests`   | Integration and country-specific tests         |

## Architecture

```
jollyday/
├── jollyday-core/
│   ├── src/main/java/de/focus_shift/jollyday/core/
│   │   ├── Holiday.java                 # Holiday value object
│   │   ├── HolidayManager.java          # Main API entry point
│   │   ├── HolidayCalendar.java         # Enum of all supported countries
│   │   ├── ManagerParameters.java       # Configuration builder
│   │   ├── impl/                        # Manager implementations
│   │   ├── parser/                      # Holiday parsing API
│   │   │   └── impl/                    # Parser implementations
│   │   └── spi/                         # Holiday configuration interfaces
│   └── src/main/resources/
│       ├── holidays/                    # Country holiday XML files
│       ├── descriptions/                # Localized holiday descriptions
│       ├── focus_shift.de/jollyday/     # XSD schema definition
│       └── jollyday.properties          # Default configuration
├── jollyday-jackson/                    # Jackson XML unmarshalling
├── jollyday-jaxb/                       # JAXB XML unmarshalling
└── jollyday-tests/                      # Test suite
```

## Key Concepts

### Holiday Types

| Type                         | Description                          | Examples                                        |
|------------------------------|--------------------------------------|-------------------------------------------------|
| **Fixed**                    | Same calendar date every year        | New Year (Jan 1), Christmas (Dec 25)            |
| **FixedWeekday**             | Specific weekday occurrence in month | Labor Day (first Monday in Sept)                |
| **ChristianHoliday**         | Easter-based moving holidays         | Good Friday, Easter Monday, Ascension           |
| **IslamicHoliday**           | Islamic calendar holidays            | Eid al-Fitr, Eid al-Adha                        |
| **EthiopianOrthodoxHoliday** | Ethiopian Orthodox holidays          | Timkat, Meskel                                  |
| **RelativeToFixed**          | Relative to a fixed date             | German Repentance Day (Wednesday before Nov 23) |
| **RelativeToWeekdayInMonth** | Relative to a moving holiday         | Various country-specific holidays               |

### Holiday Categories

| Category           | Description                                 |
|--------------------|---------------------------------------------|
| **Public Holiday** | Official non-working days (paid time off)   |
| **Bank Holiday**   | Days when financial institutions are closed |
| **Observance**     | Celebrations without official time off      |

### ISO Standards

- **ISO 3166-1 alpha-2**: Country codes (e.g., `DE`, `US`, `FR`)
- **ISO 3166-2**: Subdivision codes (e.g., `DE-BY` for Bavaria, Germany)

## XML Holiday Configuration

### Root Structure

```xml
<?xml version="1.0" encoding="UTF-8"?>

<Configuration hierarchy="[country_code]" description="[Country Name]"
               xmlns="https://focus_shift.de/jollyday/schema/holiday"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="https://focus_shift.de/jollyday/schema/holiday https://focus_shift.de/jollyday/schema/holiday/holiday.xsd">
  <Holidays>
    <!-- National holidays -->
  </Holidays>

  <Sources>
    <Source>https://en.wikipedia.org/wiki/Public_holidays_in_[Country]</Source>
    <Source of="ISO 3166">https://www.iso.org/obp/ui/#iso:code:3166:[CODE]</Source>
  </Sources>
</Configuration>
```

### Holiday Elements

#### Fixed Date
```xml
<Fixed month="JANUARY" day="1" descriptionPropertiesKey="NEW_YEAR"/>
<Fixed month="DECEMBER" day="25" validFrom="1990" descriptionPropertiesKey="CHRISTMAS"/>
```

#### Fixed Weekday in Month
```xml
<FixedWeekday which="FIRST" weekday="MONDAY" month="SEPTEMBER" descriptionPropertiesKey="LABOUR_DAY"/>
<FixedWeekday which="LAST" weekday="MONDAY" month="MAY" descriptionPropertiesKey="MEMORIAL_DAY"/>
```
- `which`: FIRST, SECOND, THIRD, FOURTH, LAST

#### Christian (Easter-based)
```xml
<ChristianHoliday type="GOOD_FRIDAY"/>
<ChristianHoliday type="EASTER_MONDAY"/>
<ChristianHoliday type="ASCENSION_DAY"/>
<ChristianHoliday type="WHIT_MONDAY"/>
<ChristianHoliday type="CORPUS_CHRISTI"/>
```

#### Islamic Calendar
```xml
<IslamicHoliday type="ID_AL_FITR"/>
<IslamicHoliday type="ID_UL_ADHA"/>
<IslamicHoliday type="NEWYEAR"/>
```

#### Relative to Fixed Date
```xml
<RelativeToFixed descriptionPropertiesKey="REPENTANCE_PRAYER">
  <Weekday>WEDNESDAY</Weekday>
  <When>BEFORE</When>
  <Date month="NOVEMBER" day="23"/>
</RelativeToFixed>
```

#### Subdivisions (Regional Holidays)
```xml
<SubConfigurations hierarchy="bw" description="Baden-Württemberg">
  <Holidays>
    <Fixed month="MARCH" day="19" descriptionPropertiesKey="ST_JOSEPH"/>
  </Holidays>
</SubConfigurations>
```

### Common Attributes

| Attribute                  | Description     | Values                             |
|----------------------------|-----------------|------------------------------------|
| `month`                    | Month name      | JANUARY, FEBRUARY, ... DECEMBER    |
| `day`                      | Day of month    | 1-31                               |
| `weekday`                  | Day of week     | MONDAY, TUESDAY, ... SUNDAY        |
| `which`                    | Occurrence      | FIRST, SECOND, THIRD, FOURTH, LAST |
| `validFrom`                | Start year      | e.g., 1990                         |
| `validTo`                  | End year        | e.g., 2020                         |
| `descriptionPropertiesKey` | Description key | UPPERCASE_WITH_UNDERSCORES         |

## API Usage

### Basic Usage

```java
import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.HolidayCalendar;

import java.util.Set;
import java.time.Year;

// Get all German holidays for 2024
HolidayManager manager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.GERMANY));
Set<Holiday> holidays = manager.getHolidays(Year.of(2024));

// Check if a date is a holiday
boolean isHoliday = manager.isHoliday(LocalDate.of(2024, 12, 25));

// Get holidays for a subdivision (Baden-Württemberg)
boolean isHolidayInBW = manager.isHoliday(LocalDate.of(2024, 3, 19), "bw");
```

### Holiday Object

```java
Holiday holiday = holidays.iterator().next();
holiday.getDate();           // LocalDate
holiday getDescription();    // Holiday name
holiday.getDescription(Locale.GERMAN);  // Localized name
holiday getType();           // HolidayType (PUBLIC_HOLIDAY, BANK_HOLIDAY, OBSERVANCE)
```

## Configuration

### Default Properties

Located in `jollyday-core/src/main/resources/jollyday.properties`:

```properties
manager.impl=de.focus_shift.jollyday.core.impl.DefaultHolidayManager
configuration.service.impl=de.focus_shift.jollyday.jackson.JacksonConfigurationService
```

### Parser Implementations

```properties
parser.impl.de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration = de.focus_shift.jollyday.core.parser.impl.FixedParser
parser.impl.de.focus_shift.jollyday.core.spi.ChristianHolidayConfiguration = de.focus_shift.jollyday.core.parser.impl.ChristianHolidayParser
parser.impl.de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration = de.focus_shift.jollyday.core.parser.impl.IslamicHolidayParser
```

### Override Configuration

```java
ManagerParameters params = ManagerParameters.create(HolidayCalendar.GERMANY);
params.setProperty("manager.impl", "de.focus_shift.jollyday.core.impl.SpecialHolidayManager");
HolidayManager manager = HolidayManager.getInstance(params);
```

## Code Structure Details

### HolidayCalendar Enum

Located in `jollyday-core/src/main/java/de/focus_shift/jollyday/core/HolidayCalendar.java`:

- Organized alphabetically by ISO country code
- Each entry: `COUNTRY_NAME("ISO_CODE")`
- Grouped by starting letter on same line

```java
public enum HolidayCalendar {
  ANTARCTICA("AQ"), ALBANIA("AL"), ANDORRA("AD"), ARGENTINA("AR"), ...
  GERMANY("DE"), GREECE("GR"), ...
  UNITED_STATES("US"), ...
}
```

### Parser Interface

All parsers implement `HolidayParser`:

```java
public interface HolidayParser {
  Set<Holiday> parse(HolidayConfiguration configuration);
}
```

### Directory Organization

| Directory                                         | Purpose                                                  |
|---------------------------------------------------|----------------------------------------------------------|
| `holidays/`                                       | Country XML files (`Holidays_de.xml`, `Holidays_us.xml`) |
| `descriptions/`                                   | Localized holiday and country names                      |
| `descriptions/holiday_descriptions.properties`    | English holiday names                                    |
| `descriptions/holiday_descriptions_de.properties` | German holiday names                                     |
| `descriptions/country_descriptions.properties`    | English country names                                    |

## Skills

### add-holiday-calendar-xml

**Location**: `.agents/skills/add-holiday-calendar-xml/SKILL.md`

Create XML holiday calendar configuration files for a new country or region. Covers:
- Root structure with namespace declarations
- Holiday types: Fixed, FixedWeekday, Christian, Islamic, RelativeToFixed
- Moving conditions and validity periods
- Regional/subdivision configurations

### add-holiday-description-properties

**Location**: `.agents/skills/add-holiday-description-properties/SKILL.md`

Add holiday and country description properties for localization. Covers:
- Base English descriptions in `holiday_descriptions.properties`
- Localized descriptions (de, el, fr, nl, pt, sv)
- Country descriptions in `country_descriptions.properties`
- Key naming conventions

### register-holiday-calendar

**Location**: `.agents/skills/register-holiday-calendar/SKILL.md`

Register a new holiday calendar in the `HolidayCalendar` enum. Covers:
- Alphabetical organization by ISO country code
- File location: `jollyday-core/src/main/java/de/focus_shift/jollyday/core/HolidayCalendar.java`
- Entry format: `COUNTRY_NAME("ISO_CODE")`

### write-holiday-tests

**Location**: `.agents/skills/write-holiday-tests/SKILL.md`

Write country-specific holiday tests using `CalendarCheckerApi`. Covers:
- Test file location and naming conventions
- Assertion methods for all holiday types
- Testing regional/subdivision holidays
- Testing edge cases (moving conditions, historical changes)

### add-subdivision

**Location**: `.agents/skills/add-subdivision/SKILL.md`

Add regional/subdivision holiday configurations based on ISO 3166-2 codes. Covers:
- ISO 3166-2 subdivision codes and format
- SubConfigurations XML structure
- Nested subdivisions (cities within states)
- Validity periods for regional holidays
- Testing subdivisions with CalendarCheckerApi

## Best Practices

### Adding New Countries

1. Follow existing XML file structure
2. Use official sources for holiday data
3. Include `validFrom`/`validTo` for historical accuracy
4. Add comprehensive test coverage
5. Register in `HolidayCalendar` enum alphabetically

### Localization

- Use `descriptionPropertiesKey` for all holidays
- Provide English descriptions in base properties file
- Add localized descriptions for supported languages: de, el, fr, nl, pt, sv

### Holiday Type Conventions

| Type           | Usage                             |
|----------------|-----------------------------------|
| `NEW_YEAR`     | January 1st                       |
| `CHRISTMAS`    | December 25th                     |
| `GOOD_FRIDAY`  | Easter Friday                     |
| `LABOUR_DAY`   | May 1st                           |
| `NATIONAL_DAY` | Country-specific national holiday |

## Version Requirements

| Component    | Version           |
|--------------|-------------------|
| Java         | 17+               |
| Maven        | 3.8.5+            |
| JUnit        | 6.0.3+            |
| Jackson/JAXB | As defined in POM |

## External Resources

- **Schema XSD**: `jollyday-core/src/main/resources/focus_shift.de/jollyday/schema/holiday/holiday.xsd`
- **GitHub**: https://github.com/focus-shift/jollyday
- **Maven Central**: https://central.sonatype.com/artifact/de.focus-shift/jollyday-core
- **Javadoc**: https://www.javadoc.io/doc/de.focus-shift/jollyday-core
- **Issues**: https://github.com/focus-shift/jollyday/issues
- **Discussions**: https://github.com/focus-shift/jollyday/discussions

## License

Apache License, Version 2.0
