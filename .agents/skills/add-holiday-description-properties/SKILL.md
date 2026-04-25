---
name: add-holiday-description-properties
description: Add holiday and country description properties for localization
---

# How to Add Holiday Description Properties

This guide explains how to add holiday descriptions to the properties files in Jollyday.

## Holiday Descriptions

### Base English Descriptions

Add entries to `jollyday-core/src/main/resources/descriptions/holiday_descriptions.properties`:

```properties
holiday.description.NEW_YEAR                    = New Year's Day
holiday.description.LABOUR_DAY                  = Labour Day
holiday.description.CHRISTMAS                   = Christmas
holiday.description.INDEPENDENCE_DAY            = Independence Day
holiday.description.NATIONAL_DAY                = National Day
holiday.description.GOOD_FRIDAY                 = Good Friday
holiday.description.EASTER_MONDAY               = Easter Monday
holiday.description.FIRST_CHRISTMAS_DAY         = First Day of Christmas
holiday.description.SECOND_CHRISTMAS_DAY        = Second Day of Christmas
```

### Key Naming Conventions

- Use **UPPERCASE** with underscores: `NEW_YEAR`, `LABOUR_DAY`, `GOOD_FRIDAY`
- For multi-word holidays: `FIRST_CHRISTMAS_DAY`, `SECOND_CHRISTMAS_DAY`
- Country-specific: `REFORMATION_DAY`, `UNIFICATION_GERMANY`, `MARTIN_LUTHER_KING`
- Always prefix with `holiday.description.`

### Localized Descriptions

Add entries to locale-specific files:

| File                                 | Language   |
|--------------------------------------|------------|
| `holiday_descriptions_de.properties` | German     |
| `holiday_descriptions_el.properties` | Greek      |
| `holiday_descriptions_fr.properties` | French     |
| `holiday_descriptions_nl.properties` | Dutch      |
| `holiday_descriptions_pt.properties` | Portuguese |
| `holiday_descriptions_sv.properties` | Swedish    |

**Example - German:**

```properties
holiday.description.NEW_YEAR            = Neujahr
holiday.description.LABOUR_DAY          = Tag der Arbeit
holiday.description.CHRISTMAS           = 1. Weihnachtstag
holiday.description.GOOD_FRIDAY         = Karfreitag
holiday.description.EASTER_MONDAY       = Ostermontag
```

## Country Descriptions

### Base English Country Descriptions

Add entries to `jollyday-core/src/main/resources/descriptions/country_descriptions.properties`:

```properties
country.description.de = Germany
country.description.us = United States
country.description.fr = France
```

### Localized Country Descriptions

Add entries to locale-specific files:

```properties
# country_descriptions_de.properties
country.description.de = Deutschland
country.description.us = Vereinigte Staaten
country.description.fr = Frankreich
```

## Property File Locations

| File                                                    | Purpose                 |
|---------------------------------------------------------|-------------------------|
| `descriptions/holiday_descriptions.properties`          | English holiday names   |
| `descriptions/holiday_descriptions_[locale].properties` | Localized holiday names |
| `descriptions/country_descriptions.properties`          | English country names   |
| `descriptions/country_descriptions_[locale].properties` | Localized country names |

## Complete Example

For adding a new holiday key `REFORMATION_DAY`:

**1. Base properties:**
```properties
# holiday_descriptions.properties
holiday.description.REFORMATION_DAY = Reformation Day

# country_descriptions.properties
country.description.xx = Your Country Name
```

**2. German localization:**
```properties
# holiday_descriptions_de.properties
holiday.description.REFORMATION_DAY = Reformationsfest

# country_descriptions_de.properties
country.description.xx = Ihr Landname
```

**3. French localization:**
```properties
# holiday_descriptions_fr.properties
holiday.description.REFORMATION_DAY = Jour de la Réformation

# country_descriptions_fr.properties
country.description.xx = Votre nom de pays
```

## Best Practices

1. **Consistent naming**: Use the same key across all locale files
2. **English first**: Always add the English description first
3. **Localized when available**: Add translations for supported locales
4. **No empty values**: Leave out the line entirely if translation unavailable
5. **Follow existing patterns**: Review existing descriptions for style consistency

## Common Holiday Keys

| Key                          | Description             |
|-------------------------------|---------------- --------|
| `NEW_YEAR`                   | New Year's Day          |
| `CHRISTMAS`                  | Christmas Day           |
| `FIRST_CHRISTMAS_DAY`        | First Day of Christmas  |
| `SECOND_CHRISTMAS_DAY`       | Second Day of Christmas |
| `GOOD_FRIDAY`                | Good Friday             |
| `EASTER_MONDAY`              | Easter Monday           |
| `LABOUR_DAY`                 | Labour/May Day          |
| `NATIONAL_DAY`               | National Day            |
| `INDEPENDENCE_DAY`           | Independence Day        |
| `ASCENSION_DAY`              | Ascension Day           |
| `WHIT_MONDAY`                | Whit Monday/Pentecost   |
| `CORPUS_CHRISTI`             | Corpus Christi          |
| `EPIPHANY`                   | Epiphany/Bright Tuesday |
| `REPELLENCE_PRAYER`          | Repentance Prayer       |
