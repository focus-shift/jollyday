# Jollyday [![Build Status](https://github.com/focus-shift/jollyday/workflows/Build/badge.svg)](https://github.com/focus-shift/jollyday/actions/workflows/build.yml) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=focus-shift_jollyday&metric=coverage)](https://sonarcloud.io/summary/overall?id=focus-shift_jollyday) [![Crowdin](https://badges.crowdin.net/jollyday/localized.svg)](https://crowdin.com/project/jollyday)

Jollyday is a java library to query public holidays. Currently, we support **over 70 countries**.

## How to use it

**Jollyday** is based on **Java 11** and can be used directly as dependency via maven or gradle e.g.
The calculation basis of the public holidays for each country is based on a xml file and will be mapped via Jakarta XML Binding.
If you already use one of these libraries in your project than just use the specific jollyday dependency.

<details>
  <summary>Maven (click to expand)</summary>

  #### Jakarta XML Binding (JAXB)
  
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

  #### Jakarta XML Binding (JAXB)
  
  ```gradle
  implementation group: 'de.focus-shift', name: 'jollyday-jaxb', version: '${version}'
  ```
</details>

### Examples

<details>
  <summary>Retrieve public holidays for a year (click to expand)</summary>

  Returns all **german** public holidays in **2022**
  ```java
  final HolidayManager holidayManager = HolidayManager.getInstance(HolidayCalendar.GERMANY);
  final Set<Holiday> holidays = holidayManager.getHolidays(2022, "de");
  ```
</details>

<details>
  <summary>Retrieve public holidays for a period of days (click to expand)</summary>

  Returns all german public holidays from the **15th of april in 2022** until the **31st of may in 2023**
  ```java
  final HolidayManager holidayManager = HolidayManager.getInstance(HolidayCalendar.GERMANY);
  final Set<Holiday> holidays = holidayManager.getHolidays(LocalDate.of(2022, 4, 15), LocalDate.of(2023, 5, 31), "de");
  ```
</details>

<details>
  <summary>Check if a specific date is a public holiday (click to expand)</summary>

  Returns true or false if a date is a public holidays in germany.
  ```java
  final HolidayManager holidayManager = HolidayManager.getInstance(HolidayCalendar.GERMANY);
  final boolean isHoliday = holidayManager.isHoliday(LocalDate.of(2022, 6, 6), "de");
  ```

  Returns true or false if a date is a public holidays in Baden-Württemberg in germany.
  ```java
  final HolidayManager holidayManager = HolidayManager.getInstance(HolidayCalendar.GERMANY);
  final boolean isHoliday = holidayManager.isHoliday(LocalDate.of(2022, 6, 6), "de", "bw");
  ```
</details>

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
