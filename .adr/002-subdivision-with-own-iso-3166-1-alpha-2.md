# Subdivisions with Their Own ISO 3166-1 Alpha-2 Codes as Separate Files

## Status

Accepted

## Context

Jollyday provides holiday information about subdivisions. Subdivisions described in [ISO 3166-2] can have both an [ISO 3166-1 alpha-2] code and an ISO 3166-2 code. In these cases, we want to declare the subdivision under their ISO 3166-2 code and **not** under their ISO 3166-1 alpha-2 code.

For example, [Guadeloupe] is an overseas department of France in the Caribbean and has the ISO 3166-2 code [GP] and the ISO 3166-1 alpha-2 code [FR-971].

## Decision

The decision is to only publish subdivisions that have an ISO 3166-2 code under their ISO 3166-2 code and not under their ISO 3166-1 alpha-2 code.

## Consequences

The subdivision will **not** be configured and will also **not** be left empty like this:

```xml
<SubConfigurations hierarchy="971" description="Guadeloupe">
  <Holidays/>
</SubConfigurations>
```

Instead, the subdivision will be added separately in its own file, e.g., in `Holidays_gp.xml`.

[ISO 3166-1 alpha-2]: https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
[ISO 3166-2]: https://en.wikipedia.org/wiki/ISO_3166-2
[Guadeloupe]: https://en.wikipedia.org/wiki/Guadeloupe
[FR-971]: https://www.iso.org/obp/ui/#iso:code:3166:FR
[GP]: https://www.iso.org/obp/ui/#iso:code:3166:GP
