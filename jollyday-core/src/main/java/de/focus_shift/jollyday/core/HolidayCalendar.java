package de.focus_shift.jollyday.core;

import java.util.Locale;

/**
 * This enum provides a list of all supported holiday calendars.
 *
 * @author Sven Diedrichsen (sven.diedrichsen@gmail.com)
 * @version $Id$
 */
public enum HolidayCalendar {

  ALBANIA("AL"), ARGENTINA("AR"), AUSTRALIA("AU"), AUSTRIA("AT"),
  BAHAMAS("BS"), BELARUS("BY"), BELGIUM("BE"), BERMUDA("BM"), BOLIVIA("BO"), BOSNIA_HERZIGOWINA("BA"), BRAZIL("BR"), BRITISH_VIRGIN_ISLANDS("VG"), BULGARIA("BG"),
  CANADA(Locale.CANADA.getCountry()), CAYMAN_ISLANDS("KY"), CHILE("CL"), COLOMBIA("CO"), COSTA_RICA("CR"), CROATIA("HR"), CZECH_REPUBLIC("CZ"),
  DENMARK("DK"), DOW_JONES_STOXX("DJ_STOXX"),
  ECUADOR("EC"), EGYPT("EG"), ESTONIA("EE"), ETHIOPIA("ET"),
  FINLAND("FI"), FRANCE(Locale.FRANCE.getCountry()),
  GERMANY(Locale.GERMANY.getCountry()), GUERNSEY("GG"), GREECE("GR"),
  HUNGARY("HU"),
  ICELAND("IS"), IRELAND("IE"), ISLE_OF_MAN("IM"), ITALY(Locale.ITALY.getCountry()),
  JAPAN("JP"), JERSEY("JE"),
  KAZAKHSTAN("KZ"), KOSOVO("XK"),
  LATVIA("LV"), LIECHTENSTEIN("LI"), LITHUANIA("LT"), LONDON_METAL_EXCHANGE("LME"), LUXEMBOURG("LU"),
  MACEDONIA("MK"), MALTA("MT"), MAURITIUS("MU"), MEXICO("MX"), MOLDOVA("MD"), MONTENEGRO("ME"),
  NETHERLANDS("NL"), NEW_ZEALAND("NZ"), NICARAGUA("NI"), NIGERIA("NG"), NORWAY("NO"), NYSE("NYSE"),
  PANAMA("PA"), PARAGUAY("PY"), PERU("PE"), POLAND("PL"), PORTUGAL("PT"),
  ROMANIA("RO"), RUSSIA("RU"),
  SAUDI_ARABIA("SA"), SERBIA("RS"), SINGAPORE("SG"), SLOWAKIA("SK"), SLOWENIA("SI"), SOUTH_AFRICA("ZA"), SPAIN("ES"), SWEDEN("SE"), SWITZERLAND("CH"),
  TARGET("TARGET"), TURKEY("TR"),
  UKRAINE("UA"), UNITED_ARAB_EMIRATES("AE"), UNITED_KINGDOM(Locale.UK.getCountry()), UNITED_STATES(Locale.US.getCountry()), URUGUAY("UY"),
  VENEZUELA("VE");

  private final String id;

  HolidayCalendar(String id) {
    this.id = id;
  }

  /**
   * <p>
   * Getter for the field <code>id</code>.
   * </p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getId() {
    return id;
  }

}
