# Jollyday [![Build Status](https://github.com/focus-shift/jollyday/workflows/Build/badge.svg)](https://github.com/focus-shift/jollyday/actions/workflows/build.yml) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=focus-shift_jollyday&metric=coverage)](https://sonarcloud.io/summary/overall?id=focus-shift_jollyday) [![Maven Central](https://img.shields.io/maven-central/v/de.focus-shift/jollyday-core.svg)](https://maven-badges.herokuapp.com/maven-central/de.focus-shift/jollyday-core) 

Jollyday is a java library to query public holidays. Currently, we support **over 70 countries**.

## How to use it

**Jollyday** is based on **Java 11** and can be used directly as dependency via maven or gradle e.g.
The calculation basis of the public holidays for each country is based on a xml file and will be mapped via Jakarta XML Binding.
If you already use one of these libraries in your project than just use the specific jollyday dependency.

<details>
  <summary>Maven (click to expand)</summary>

  You need the core library, that defines all functionality and the api for you as developer.

  ```xml
  <dependency>
    <groupId>de.focus-shift</groupId>
    <artifactId>jollyday-core</artifactId>
    <version>${version}</version>
  </dependency>
  ```

  ### XML-Binding libraries

  Additionally, the XML-Binding library of your choice. At the moment we do only support JAXB, 
  but in the future there should be more that one.

  **Jakarta XML Binding (JAXB)**

  ```xml
  <dependency>
    <groupId>de.focus-shift</groupId>
    <artifactId>jollyday-jaxb</artifactId>
    <version>${version}</version>
  </dependency>
  ```
</details>

<details>
  <summary>Gradle (click to expand)</summary>

  You need the core library, that defines all functionality and the api for you as developer.

  ```gradle
  implementation group: 'de.focus-shift', name: 'jollyday-core', version: '${version}'
  ```

  ### XML-Binding libraries

  Additionally, the XML-Binding library of your choice. At the moment we do only support JAXB,
  but in the future there should be more that one.

  **Jakarta XML Binding (JAXB)**

  ```gradle
  implementation group: 'de.focus-shift', name: 'jollyday-jaxb', version: '${version}'
  ```
</details>

<details>
  <summary>with the Java Platform Module System (click to expand)</summary>

  If you want to use Jollyday in a project that is modularized via java modules you need to require the `de.focus_shift.jollyday.core` module via
  
  ```java
  module your.application {
    ...
    requires de.focus_shift.jollyday.core;
    ...
  }
```
</details>

### Examples

<details>
  <summary>Retrieve public holidays for a year (click to expand)</summary>

  Returns all **german** public holidays in **2022**
  ```java
  import de.focus_shift.jolldayday.core.Holiday;
  import de.focus_shift.jolldayday.core.HolidayCalendar.GERMANY;
  import de.focus_shift.jolldayday.core.HolidayManager;
  import de.focus_shift.jolldayday.core.ManagerParameters;
  import java.time.LocalDate;

  final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(GERMANY));
  final Set<Holiday> holidays = holidayManager.getHolidays(2022);
  ```
</details>

<details>
  <summary>Retrieve public holidays for a period of days (click to expand)</summary>

  Returns all german public holidays from the **15th of april in 2022** until the **31st of may in 2023**
  ```java
  import de.focus_shift.jolldayday.core.Holiday;
  import de.focus_shift.jolldayday.core.HolidayCalendar.GERMANY;
  import de.focus_shift.jolldayday.core.HolidayManager;
  import de.focus_shift.jolldayday.core.ManagerParameters;
  import java.time.LocalDate;

  final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(GERMANY));
  final Set<Holiday> holidays = holidayManager.getHolidays(LocalDate.of(2022, 4, 15), LocalDate.of(2023, 5, 31));
  ```
</details>

<details>
  <summary>Check if a specific date is a public holiday (click to expand)</summary>

  Returns true or false if a date is a public holidays in germany.
  ```java
  import de.focus_shift.jolldayday.core.Holiday;
  import de.focus_shift.jolldayday.core.HolidayCalendar.GERMANY;
  import de.focus_shift.jolldayday.core.HolidayManager;
  import de.focus_shift.jolldayday.core.ManagerParameters;
  import java.time.LocalDate;

  final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(GERMANY));
  final boolean isHoliday = holidayManager.isHoliday(LocalDate.of(2022, 6, 6));
  ```

  Returns true or false if a date is a public holidays in Baden-Württemberg in germany.
  ```java
  import de.focus_shift.jolldayday.core.Holiday;
  import de.focus_shift.jolldayday.core.HolidayCalendar.GERMANY;
  import de.focus_shift.jolldayday.core.HolidayManager;
  import de.focus_shift.jolldayday.core.ManagerParameters;
  import java.time.LocalDate;

  final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(GERMANY));
  final boolean isHoliday = holidayManager.isHoliday(LocalDate.of(2022, 6, 6), "bw");
  ```
</details>

<details>
  <summary>Override an existing country (click to expand)</summary>
  
  If you want to override the public holidays of a provided country like **germany**, you need to put a holiday file
  with the name `Holiday_de.xml` on your classpath. Jollyday will pick up yours at first. The File and the hierarchy needs
  to be identical to the one you want to override.

  The holiday file structure needs to look like the one below. The XML Schema Definition file can be viewed [here](jollyday-core/src/main/resources/focus_shift.de/jollyday/schema/holiday/holiday.xsd)

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  
  <Configuration hierarchy="de" description="Germany"
                 xmlns="https://focus_shift.de/jollyday/schema/holiday"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="https://focus_shift.de/jollyday/schema/holiday https://focus_shift.de/jollyday/schema/holiday/holiday.xsd">
    <Holidays>
      <!-- Add the holidays here-->
    </Holidays>
  
    ...
    
    <SubConfigurations hierarchy="bw" description="Baden-Württemberg">
      <Holidays>
      ...
      </Holidays>
    </SubConfigurations>
  </Configuration>
  ```
</details>

## ISO 3166

To retrieve the public holidays of a country the [ISO 3166-1 alpha-2] standard is used. An [list of current ISO 3166-1 alpha-2 codes] is available at wikipedia.

To access the public holidays of a subdivision of a country, e.g. Baden-Württemberg of Germany the [ISO 3166-2] standard is used. A [list of current ISO 3166-2 codes] is available at wikipedia.

### Data precision

| Precision                      | Supported |
|--------------------------------|-----------|
| Country ([ISO 3166-1 alpha-2]) | Yes       |
| Subdivisions ([ISO 3166-2])    | Yes       |
| City Holiday                   | Yes       |

## Development

If you want to **support** us at the development on **jollyday** than take a look at [Contributing to jollyday](./CONTRIBUTING.md).
If you have any kind of **questions** please go to [Discussions] and see if there are already answers and if not please open a discussion with your question. 
If you want to raise an **issue or bug** you can create a [new issue](https://github.com/focus-shift/jollyday/issues/new/choose)

### Requirements

* [JDK 11](https://openjdk.java.net/install/)

### Clone the repository

```bash
git clone git@github.com:focus-shift/jollyday.git
```

### Build jollyday

```bash
./mvnw clean verify
```

or for Windows user:

```bash
./mvnw.cmd clean verify
```

## License

[Apache License, Version 2.0](LICENSE.md)

[Discussions]: https://github.com/focus-shift/jollyday/discussions
[ISO 3166-1 alpha-2]: https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
[list of current ISO 3166-1 alpha-2 codes]: https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2#Current_codes
[ISO 3166-2]: https://en.wikipedia.org/wiki/ISO_3166-2
[list of current ISO 3166-2 codes]: https://en.wikipedia.org/wiki/ISO_3166-2#Current_codes
