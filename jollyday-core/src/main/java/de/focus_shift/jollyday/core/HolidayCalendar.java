package de.focus_shift.jollyday.core;

import org.jspecify.annotations.NonNull;

import java.util.Locale;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableSet;

/**
 * This enum provides a list of all supported holiday calendars.
 */
public enum HolidayCalendar {

  ANTARCTICA("AQ"), AFGHANISTAN("AF"), ALBANIA("AL"), ALGERIA("DZ"), ANDORRA("AD"), ANGUILLA("AI"), ANTIGUA_AND_BARBUDA("AG"), ANGOLA("AO"), ARGENTINA("AR"), ARMENIA("AM"), AZERBAIJAN("AZ"), AUSTRALIA("AU"), AUSTRIA("AT"),
  BAHAMAS("BS"), BANGLADESH("BD"), BAHRAIN("BH"), BARBADOS("BB"), BELARUS("BY"), BELGIUM("BE"), BELIZE("BZ"), BENIN("BJ"), BERMUDA("BM"), BOLIVIA("BO"), BOSNIA_HERZIGOWINA("BA"), BOTSWANA("BW"), BRAZIL("BR"), BRITISH_VIRGIN_ISLANDS("VG"), BULGARIA("BG"), BURUNDI("BI"), BURKINA_FASO("BF"),
  CAMEROON("CM"), CANADA(Locale.CANADA.getCountry()), CAPE_VERDE("CV"), CAYMAN_ISLANDS("KY"), CURACAO("CW"), CENTRAL_AFRICAN_REPUBLIC("CF"), CHAD("TD"), CHILE("CL"), COLOMBIA("CO"), CONGO("CD"), CONGO_REPUBLIC("CG"), COMOROS("KM"), COSTA_RICA("CR"), CROATIA("HR"), CUBA("CU"), CZECH_REPUBLIC("CZ"), CYPRUS("CY"),
  DENMARK("DK"), DJIBOUTI("DJ"), DOMINICA("DM"), DOW_JONES_STOXX("DJ_STOXX"),
  ECUADOR("EC"), EGYPT("EG"), EL_SALVADOR("SV"), ERITREA("ER"), ESTONIA("EE"), ETHIOPIA("ET"), EQUATORIAL_GUINEA("GQ"),
  FINLAND("FI"), FAROE_ISLANDS("FO"), FALKLAND_ISLANDS("FK"), FRANCE(Locale.FRANCE.getCountry()), FRENCH_GUIANA("GF"),
  GEORGIA("GE"), GERMANY(Locale.GERMANY.getCountry()), GAMBIA("GM"), GABON("GA"), GHANA("GH"), GIBRALTAR("GI"), GRENADA("GD"), GUADELOUPE("GP"), GUYANA("GY"), GUATEMALA("GT"), GUAM("GU"), GUERNSEY("GG"), GREECE("GR"), GREENLAND("GL"), GUINEA("GN"), GUINEA_BISSAU("GW"),
  HAITI("HT"), HONDURAS("HN"), HONG_KONG("HK"), HUNGARY("HU"),
  ICELAND("IS"), INDIA("IN"), IVORY_COAST("CI"), IRELAND("IE"), IRAQ("IQ"), ISLE_OF_MAN("IM"), ITALY(Locale.ITALY.getCountry()),
  JAMAICA("JM"), JAPAN("JP"), JERSEY("JE"), JORDAN("JO"),
  KAZAKHSTAN("KZ"), KENYA("KE"), KOSOVO("XK"), KUWAIT("KW"), KYRGYZSTAN("KG"),
  LIBERIA("LR"), LEBANON("LB"), LESOTHO("LS"), LIBYA("LY"),
  LATVIA("LV"), LIECHTENSTEIN("LI"), LITHUANIA("LT"), LONDON_METAL_EXCHANGE("LME"), LUXEMBOURG("LU"),
  MACEDONIA("MK"), MADAGASCAR("MG"), MALAWI("MW"), MALI("ML"), MALTA("MT"), MARTINIQUE("MQ"), MAURITANIA("MR"), MONACO("MC"), MOROCCO("MA"), MAURITIUS("MU"), MEXICO("MX"), MICRONESIA("FM"), MOLDOVA("MD"), MONTENEGRO("ME"), MONTSERRAT("MS"), MOZAMBIQUE("MZ"),
  OMAN("OM"),
  NAMIBIA("NA"), NAURU("NR"), NETHERLANDS("NL"), NEW_CALEDONIA("NC"), NEW_ZEALAND("NZ"), NICARAGUA("NI"), NIGER("NE"), NIGERIA("NG"), NORWAY("NO"), NYSE("NYSE"), NYSE_EURONEXT("NYSE_EURONEXT"),
  PANAMA("PA"), PAKISTAN("PK"), PALAU("PW"), PAPUA_NEW_GUINEA("PG"), PALESTINE("PS"), PARAGUAY("PY"), PERU("PE"), PHILIPPINES("PH"), POLAND("PL"), PORTUGAL("PT"),
  ROMANIA("RO"), RUSSIA("RU"), RWANDA("RW"),
  SAN_MARINO("SM"), SAO_TOME_AND_PRINCIPE("ST"), SAUDI_ARABIA("SA"), SENEGAL("SN"), SERBIA("RS"), SIERRA_LEONE("SL"), SEYCHELLES("SC"), SINGAPORE("SG"), SINT_MAARTEN("SX"), SLOWAKIA("SK"), SLOWENIA("SI"), SAMOA("WS"), SOLOMON_ISLANDS("SB"), SOMALIA("SO"), SUDAN("SD"), SOUTH_AFRICA("ZA"), SOUTH_SUDAN("SS"), SPAIN("ES"), SVALBARD_AND_JAN_MAYEN("SJ"), SAINT_BARTHELEMY("BL"), SAINT_KITTS_AND_NEVIS("KN"), SAINT_LUCIA("LC"), SAINT_VINCENT_AND_THE_GRENADINES("VC"), SURINAME("SR"), SYRIA("SY"), SWEDEN("SE"), SWITZERLAND("CH"),
  TARGET("TARGET"), TARGET2_SECURITIES("TARGET2_SECURITIES"), TAJIKISTAN("TJ"), TANZANIA("TZ"), TIMOR_LESTE("TL"), TOGO("TG"), TONGA("TO"), TRINIDAD_AND_TOBAGO("TT"), TUNISIA("TN"), TUVALU("TV"), TURKMENISTAN("TM"), TURKEY("TR"), TURKS_AND_CAICOS_ISLANDS("TC"),
  UGANDA("UG"), UKRAINE("UA"), UNITED_ARAB_EMIRATES("AE"), UNITED_KINGDOM(Locale.UK.getCountry()), UNITED_STATES(Locale.US.getCountry()), URUGUAY("UY"), UZBEKISTAN("UZ"),
  VATICAN_CITY("VA"), VANUATU("VU"), VENEZUELA("VE"), VIETNAM("VN"), WESTERN_SAHARA("EH"), YEMEN("YE"),
  ZAMBIA("ZM"), ZIMBABWE("ZW");

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
  public @NonNull String getId() {
    return id;
  }

  /**
   * Returns a set of all currently supported ISO 3166-1 alpha-2 codes.
   *
   * @return Set of supported calendar codes.
   */
  public static @NonNull Set<String> getSupportedCalendarCodes() {
    return stream(HolidayCalendar.values())
      .map(HolidayCalendar::getId)
      .collect(toUnmodifiableSet());
  }
}
