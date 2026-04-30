---
name: add-country-descriptions
description: Add country description properties for localization
---

# How to Add Country Description Properties

This guide explains how to add country descriptions to the properties files in Jollyday.

## Base English Country Descriptions

Add entries to `jollyday-core/src/main/resources/descriptions/country_descriptions.properties`:

```properties
country.description.de = Germany
country.description.us = United States
country.description.fr = France
country.description.gb = United Kingdom
country.description.it = Italy
country.description.es = Spain
```

## Key Naming Conventions

- Use lowercase ISO 3166-1 alpha-2 country codes: `de`, `us`, `fr`, `gb`
- Always prefix with `country.description.`

## Localized Country Descriptions

Add entries to locale-specific files:

| File                                 | Language   |
|--------------------------------------|------------|
| `country_descriptions_de.properties` | German     |
| `country_descriptions_el.properties` | Greek      |
| `country_descriptions_fr.properties` | French     |
| `country_descriptions_nl.properties` | Dutch      |
| `country_descriptions_pt.properties` | Portuguese |
| `country_descriptions_sv.properties` | Swedish    |

**Example - German:**

```properties
country.description.de = Deutschland
country.description.us = Vereinigte Staaten
country.description.fr = Frankreich
country.description.gb = Vereinigtes Königreich
country.description.it = Italien
country.description.es = Spanien
```

## Property File Locations

| File                                                    | Purpose                 |
|---------------------------------------------------------|-------------------------|
| `descriptions/country_descriptions.properties`          | English country names   |
| `descriptions/country_descriptions_[locale].properties` | Localized country names |

## Complete Example

For adding a new country code `ch` (Switzerland):

**1. Base properties:**
```properties
# country_descriptions.properties
country.description.ch = Switzerland
```

**2. German localization:**
```properties
# country_descriptions_de.properties
country.description.ch = Schweiz
```

**3. French localization:**
```properties
# country_descriptions_fr.properties
country.description.ch = Suisse
```

## Best Practices

1. **ISO 3166-1 alpha-2 codes**: Use standard two-letter country codes in lowercase
2. **English first**: Always add the English description first
3. **Localized when available**: Add translations for supported locales
4. **No empty values**: Leave out the line entirely if translation unavailable
5. **Follow existing patterns**: Review existing descriptions for style consistency
