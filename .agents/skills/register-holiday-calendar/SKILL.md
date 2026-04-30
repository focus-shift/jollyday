---
name: register-holiday-calendar
description: Register a new holiday calendar in the HolidayCalendar enum
---

# How to Register a Holiday Calendar

This guide explains how to register a new country calendar in the `HolidayCalendar` enum.

## File Location

Edit: `jollyday-core/src/main/java/de/focus_shift/jollyday/core/HolidayCalendar.java`

## Organization Pattern

The `HolidayCalendar` enum follows a strict organization pattern:

1. Calendars are listed in **alphabetical order** by their ISO country code
2. Each line starts with calendars beginning with a specific letter (A, B, C, etc.)
3. Multiple calendars sharing the same starting letter are grouped on the same line alphabetically

## Example Structure

```java
public enum HolidayCalendar {
  ANTARCTICA("AQ"), ALBANIA("AL"), ANDORRA("AD"), ARGENTINA("AR"), ARMENIA("AM"), AUSTRALIA("AU"), AUSTRIA("AT"),
  BAHAMAS("BS"), BELARUS("BY"), BELGIUM("BE"), BERMUDA("BM"), BOLIVIA("BO"), BRAZIL("BR"), ...
  CANADA(Locale.CANADA.getCountry()), CAYMAN_ISLANDS("KY"), ...
  GERMANY("DE"), GREECE("GR"), ...
  UNITED_STATES(Locale.US.getCountry()), ...
}
```

## Steps to Add Your Calendar

### 1. Find the Correct Line

Identify which line your country code should appear on based on the first letter:

| First Letter | Example Calendars                                  |
|--------------|----------------------------------------------------|
| A            | ANTARCTICA, ALBANIA, ANDORRA, ARGENTINA, AUSTRALIA |
| B            | BELARUS, BELGIUM, BERMUDA, BRAZIL                  |
| D            | DENMARK, DOMINICAN, ECUADAR (E starts D line)      |

### 2. Insert in Alphabetical Order

Add your calendar in alphabetical position within that letter group:

**Before:**
```java
ALBANIA("AL"), ANDORRA("AD"), ARGENTINA("AR"),
```

**Adding Antarctica (AQ) - after ARGENTINA:**
```java
ALBANIA("AL"), ANDORRA("AD"), ARGENTINA("AR"), ANTARCTICA("AQ"),
```

### 3. Use Correct Format

Format: `COUNTRY_NAME("ISO_CODE")`

**Examples:**
```java
GERMANY("DE"),                          // Direct ISO code
CANADA(Locale.CANADA.getCountry()),     // Java Locale for special cases
UNITED_STATES(Locale.US.getCountry()),  // Java Locale for US
```

## Complete Example

Adding a fictional "Zebra" country with code "ZB":

**Before:**
```java
  ZIMBABWE("ZW");
}
```

**After:**
```java
  ZEBRA("ZB"), ZIMBABWE("ZW");
}
```

## Special Cases

Some countries use Java Locale instead of string codes:

```java
CANADA(Locale.CANADA.getCountry()),  // Returns "CA"
UNITED_STATES(Locale.US.getCountry()),  // Returns "US"
```

## Best Practices

1. **Alphabetical order**: Always maintain alphabetical order by ISO code
2. **Proper naming**: Use full country name in uppercase with underscores
3. **ISO code**: Use the official ISO 3166-1 alpha-2 code
4. **Comma placement**: Add comma after each entry except the last
5. **Semicolon**: Last entry ends with semicolon before closing brace

## Verification

After adding your calendar, verify:
1. The calendar is in the correct alphabetical position
2. The ISO code matches the XML file's hierarchy attribute
3. The Java file compiles without errors
