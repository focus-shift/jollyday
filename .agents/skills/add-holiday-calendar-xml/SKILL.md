---
name: Add a new Holiday Calendar
description: Create XML holiday calendar configuration files for a new country or region
---

# How to Add Holiday Calendar XML

This guide explains how to create XML holiday calendar files for countries in Jollyday.

## File Location

Create a new file at: `jollyday-core/src/main/resources/holidays/Holidays_[country_code].xml`

## Root Structure

```xml
<?xml version="1.0" encoding="UTF-8"?>

<Configuration hierarchy="[country_code]" description="[Country Name]"
               xmlns="https://focus_shift.de/jollyday/schema/holiday"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="https://focus_shift.de/jollyday/schema/holiday https://focus_shift.de/jollyday/schema/holiday/holiday.xsd">
  <Holidays>
    <!-- Add your holidays here -->
  </Holidays>

  <Sources>
    <Source>https://en.wikipedia.org/wiki/Public_holidays_in_[Country Name]</Source>
    <Source of="ISO 3166">https://www.iso.org/obp/ui/#iso:code:3166:[country_code]</Source>
    <Source of="ISO 3166-2">https://en.wikipedia.org/wiki/ISO_3166-2:[country_code]</Source>
  </Sources>
</Configuration>
```

### Root Element Attributes

| Attribute     | Description                                              |
|---------------|----------------------------------------------------------|
| `hierarchy`   | ISO 3166-1 alpha-2 country code (e.g., "de", "us", "fr") |
| `description` | Full country name in English                             |

## Holiday Types

### Fixed Date Holidays

For holidays on a fixed calendar date:

```xml
<Fixed month="JANUARY" day="1" descriptionPropertiesKey="NEW_YEAR"/>
```

With validity period:

```xml
<Fixed month="OCTOBER" day="31" validFrom="2017" descriptionPropertiesKey="REFORMATION_DAY"/>
<Fixed month="JUNE" day="19" validFrom="2021" descriptionPropertiesKey="JUNETEENTH"/>
```

### Fixed Weekday Holidays

For holidays on a specific weekday occurrence in a month:

```xml
<FixedWeekday which="FIRST" weekday="MONDAY" month="SEPTEMBER" descriptionPropertiesKey="LABOUR_DAY"/>
<FixedWeekday which="THIRD" weekday="MONDAY" month="JANUARY" descriptionPropertiesKey="MARTIN_LUTHER_KING"/>
<FixedWeekday which="FOURTH" weekday="THURSDAY" month="NOVEMBER" descriptionPropertiesKey="THANKSGIVING"/>
<FixedWeekday which="LAST" weekday="MONDAY" month="MAY" descriptionPropertiesKey="MEMORIAL_DAY"/>
```

**`which` values**: `FIRST`, `SECOND`, `THIRD`, `FOURTH`, `LAST`

### Christian (Easter-based) Holidays

For Christian holidays that depend on Easter:

```xml
<ChristianHoliday type="GOOD_FRIDAY"/>
<ChristianHoliday type="EASTER_MONDAY"/>
<ChristianHoliday type="ASCENSION_DAY"/>
<ChristianHoliday type="WHIT_MONDAY"/>
<ChristianHoliday type="CORPUS_CHRISTI"/>
<ChristianHoliday type="PENTECOST"/>
```

With optional `localizedType`:

```xml
<ChristianHoliday type="GOOD_FRIDAY" localizedType="OBSERVANCE"/>
```

### Islamic Calendar Holidays

For Islamic calendar-based holidays:

```xml
<IslamicHoliday type="ID_AL_FITR"/>
<IslamicHoliday type="ID_AL_FITR_2"/>
<IslamicHoliday type="ID_AL_FITR_3"/>
<IslamicHoliday type="ID_UL_ADHA"/>
<IslamicHoliday type="ARAFAAT"/>
<IslamicHoliday type="NEWYEAR"/>
<IslamicHoliday type="MAWLID_AN_NABI"/>
```

### Relative to Fixed Date

For holidays calculated relative to a fixed date:

```xml
<RelativeToFixed validFrom="1990" descriptionPropertiesKey="REPENTANCE_PRAYER">
  <Weekday>WEDNESDAY</Weekday>
  <When>BEFORE</When>
  <Date month="NOVEMBER" day="23"/>
</RelativeToFixed>
```

**`When` values**: `BEFORE`, `AFTER`

### Moving Conditions

For holidays that shift to weekdays when falling on weekends:

```xml
<Fixed month="JANUARY" day="1" descriptionPropertiesKey="NEW_YEAR">
  <MovingCondition substitute="SATURDAY" with="PREVIOUS" weekday="FRIDAY"/>
  <MovingCondition substitute="SUNDAY" with="NEXT" weekday="MONDAY"/>
</Fixed>
```

## Subdivision Holidays

For countries with subdivision holidays (states, provinces):

```xml
<SubConfigurations hierarchy="[subdivision_code]" description="[Subdivision Name]">
  <Holidays>
    <Fixed month="MARCH" day="8" descriptionPropertiesKey="INTERNATIONAL_WOMAN"/>
  </Holidays>
</SubConfigurations>
```


| Attribute     | Description                        |
|---------------|------------------------------------|
| `hierarchy`   | ISO 3166-2 code (e.g., "by", "bw") |
| `description` | Full subdivision name in English   |

Example with nested subdivisions:

```xml
<SubConfigurations hierarchy="by" description="Bavaria">
  <Holidays>
    <Fixed month="JANUARY" day="6" descriptionPropertiesKey="EPIPHANY"/>
  </Holidays>

  <SubConfigurations hierarchy="mu" description="Munich">
    <Holidays>
      <Fixed day="15" month="AUGUST" descriptionPropertiesKey="ASSUMPTION_DAY"/>
    </Holidays>
  </SubConfigurations>
</SubConfigurations>
```

## Common Attributes Reference

| Attribute                  | Description                | Example                  |
|----------------------------|----------------------------|--------------------------|
| `month`                    | Month (English, uppercase) | `JANUARY`, `DECEMBER`    |
| `day`                      | Day of month               | `1`, `25`                |
| `weekday`                  | Day of week                | `MONDAY`, `FRIDAY`       |
| `which`                    | Which occurrence           | `FIRST`, `LAST`, `THIRD` |
| `validFrom`                | Start year                 | `1990`, `2021`           |
| `validTo`                  | End year                   | `1990`, `2020`           |
| `descriptionPropertiesKey` | Key for description        | `NEW_YEAR`               |
| `localizedType`            | Holiday type variant       | `OBSERVANCE`             |

## Available Values

### Months
`JANUARY`, `FEBRUARY`, `MARCH`, `APRIL`, `MAY`, `JUNE`, `JULY`, `AUGUST`, `SEPTEMBER`, `OCTOBER`, `NOVEMBER`, `DECEMBER`

### Weekdays
`MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY`

### ChristianHoliday Types
- `GOOD_FRIDAY`, `EASTER`, `EASTER_MONDAY`, `EASTER_SATURDAY`, `EASTER_TUESDAY`
- `ASCENSION_DAY`, `WHIT_MONDAY`, `PENTECOST`, `PENTECOST_MONDAY`
- `CORPUS_CHRISTI`, `CARNIVAL`, `MARDI_GRAS`, `MAUNDY_THURSDAY`, `ASH_WEDNESDAY`

### IslamicHoliday Types
- `NEWYEAR`, `MAWLID_AN_NABI`, `LAILAT_AL_BARAT`, `LAILAT_AL_MIRAJ`, `LAILAT_AL_QADR`
- `ASCHURA`, `RAMADAN`, `RAMADAN_END`
- `ID_AL_FITR`, `ID_AL_FITR_2`, `ID_AL_FITR_3`
- `ARAFAAT`, `ID_UL_ADHA`, `ID_UL_ADHA_2`, `ID_UL_ADHA_3`

## Complete Example: Germany (Simplified)

```xml
<?xml version="1.0" encoding="UTF-8"?>

<Configuration hierarchy="de" description="Germany"
               xmlns="https://focus_shift.de/jollyday/schema/holiday"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="https://focus_shift.de/jollyday/schema/holiday https://focus_shift.de/jollyday/schema/holiday/holiday.xsd">
  <Holidays>
    <Fixed month="JANUARY" day="1" descriptionPropertiesKey="NEW_YEAR"/>
    <Fixed month="MAY" day="1" descriptionPropertiesKey="LABOUR_DAY"/>
    <Fixed month="DECEMBER" day="25" descriptionPropertiesKey="FIRST_CHRISTMAS_DAY"/>
    <Fixed month="DECEMBER" day="26" descriptionPropertiesKey="SECOND_CHRISTMAS_DAY"/>
    <ChristianHoliday type="GOOD_FRIDAY"/>
    <ChristianHoliday type="EASTER_MONDAY"/>
    <ChristianHoliday type="ASCENSION_DAY"/>
    <ChristianHoliday type="WHIT_MONDAY"/>
  </Holidays>

  <Sources>
    <Source>https://en.wikipedia.org/wiki/Public_holidays_in_Germany</Source>
    <Source of="ISO 3166">https://www.iso.org/obp/ui/#iso:code:3166:DE</Source>
    <Source of="ISO 3166-2">https://en.wikipedia.org/wiki/ISO_3166-2:DE</Source>
  </Sources>
</Configuration>
```

## References

- **Schema XSD**: `jollyday-core/src/main/resources/focus_shift.de/jollyday/schema/holiday/holiday.xsd`
- **Online Schema**: `https://focus_shift.de/jollyday/schema/holiday/holiday.xsd`
- **Examples**: Review existing `Holidays_*.xml` files in `jollyday-core/src/main/resources/holidays/`
