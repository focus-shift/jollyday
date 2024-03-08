# Document empty subdivision

## Status

Accepted

## Context

This decision arises from the discussion on https://github.com/focus-shift/jollyday/pull/388, where the identification for subdivisions was changed to 'ISO 3166-2'.
There was uncertainty regarding whether to include subdivisions that do not have additional holidays compared to the country.

During the discussion, it was noted that having numerous subdivisions for a country like India while missing a few could lead to confusion and require significant time investment to identify the missing ones.

## Decision

The decision has been made to include all subdivisions, regardless of whether they have distinct holidays compared to the country.

## Consequences

For subdivisions without separate holidays, an empty `SubConfigurations` will still be created. For example, for `Ladākh`, it will appear as follows in the configuration:

```xml
<SubConfigurations hierarchy="la" description="Ladākh">
  <Holidays/>
</SubConfigurations>
```

This ensures consistency and completeness in the documentation of subdivisions.
