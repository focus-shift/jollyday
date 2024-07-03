package de.focus_shift.jollyday.pojo;

import java.util.ArrayList;
import java.util.List;

import de.focus_shift.jollyday.core.spi.ChristianHoliday;
import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHoliday;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.core.spi.IslamicHoliday;
import de.focus_shift.jollyday.core.spi.RelativeToEasterSunday;
import de.focus_shift.jollyday.core.spi.RelativeToFixed;
import de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonth;

public class PojoHolidays implements de.focus_shift.jollyday.core.spi.Holidays {

  protected List<ChristianHoliday> christianHoliday = new ArrayList<>();
  protected List<IslamicHoliday> islamicHoliday = new ArrayList<>();
  protected List<EthiopianOrthodoxHoliday> ethiopianOrthodoxHoliday = new ArrayList<>();

  protected List<Fixed> fixed = new ArrayList<>();
  protected List<FixedWeekdayInMonth> fixedWeekday= new ArrayList<>();
  protected List<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed= new ArrayList<>();
  protected List<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed= new ArrayList<>();

  protected List<RelativeToFixed> relativeToFixed = new ArrayList<>();
  protected List<RelativeToWeekdayInMonth> relativeToWeekdayInMonth = new ArrayList<>();
  protected List<RelativeToEasterSunday> relativeToEasterSunday= new ArrayList<>();

  public PojoHolidays() {

  }

  public PojoHolidays(List<ChristianHoliday> christianHoliday, List<IslamicHoliday> islamicHoliday, List<EthiopianOrthodoxHoliday> ethiopianOrthodoxHoliday, List<Fixed> fixed, List<FixedWeekdayInMonth> fixedWeekday, List<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed, List<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed, List<RelativeToFixed> relativeToFixed, List<RelativeToWeekdayInMonth> relativeToWeekdayInMonth, List<RelativeToEasterSunday> relativeToEasterSunday) {
    this.christianHoliday = christianHoliday;
    this.islamicHoliday = islamicHoliday;
    this.ethiopianOrthodoxHoliday = ethiopianOrthodoxHoliday;
    this.fixed = fixed;
    this.fixedWeekday = fixedWeekday;
    this.fixedWeekdayBetweenFixed = fixedWeekdayBetweenFixed;
    this.fixedWeekdayRelativeToFixed = fixedWeekdayRelativeToFixed;
    this.relativeToFixed = relativeToFixed;
    this.relativeToWeekdayInMonth = relativeToWeekdayInMonth;
    this.relativeToEasterSunday = relativeToEasterSunday;
  }

  @Override
  public List<Fixed> fixed() {
    return fixed;
  }

  public PojoHolidays addFixed(Fixed value) {
    this.fixed.add(value);
    return this;
  }

  @Override
  public List<RelativeToFixed> relativeToFixed() {
    return relativeToFixed;
  }

  public PojoHolidays addRelativeToFixed(RelativeToFixed value) {
    this.relativeToFixed.add(value);
    return this;
  }

  @Override
  public List<RelativeToWeekdayInMonth> relativeToWeekdayInMonth() {
    return relativeToWeekdayInMonth;
  }

  public PojoHolidays addRelativeToWeekdayInMonth(RelativeToWeekdayInMonth value) {
    this.relativeToWeekdayInMonth.add(value);
    return this;
  }

  @Override
  public List<FixedWeekdayInMonth> fixedWeekdays() {
    return fixedWeekday;
  }

  public PojoHolidays addFixedWeekday(FixedWeekdayInMonth value) {
    this.fixedWeekday.add(value);
    return this;
  }

  @Override
  public List<ChristianHoliday> christianHolidays() {
    return christianHoliday;
  }

  public PojoHolidays addChristianHoliday(ChristianHoliday value) {
    this.christianHoliday.add(value);
    return this;
  }

  @Override
  public List<IslamicHoliday> islamicHolidays() {
    return islamicHoliday;
  }

  public PojoHolidays addIslamicHoliday(IslamicHoliday value) {
    this.islamicHoliday.add(value);
    return this;
  }

  @Override
  public List<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed() {
    return fixedWeekdayBetweenFixed;
  }

  public PojoHolidays addFixedWeekdayBetweenFixed(FixedWeekdayBetweenFixed value) {
    this.fixedWeekdayBetweenFixed.add(value);
    return this;
  }

  @Override
  public List<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed() {
    return fixedWeekdayRelativeToFixed;
  }

  public PojoHolidays addFixedWeekdayRelativeToFixed(FixedWeekdayRelativeToFixed value) {
    this.fixedWeekdayRelativeToFixed.add(value);
    return this;
  }

  @Override
  public List<EthiopianOrthodoxHoliday> ethiopianOrthodoxHolidays() {
    return ethiopianOrthodoxHoliday;
  }

  public PojoHolidays addEthiopianOrthodoxHoliday(EthiopianOrthodoxHoliday value) {
    this.ethiopianOrthodoxHoliday.add(value);
    return this;
  }

  @Override
  public List<RelativeToEasterSunday> relativeToEasterSunday() {
    return relativeToEasterSunday;
  }

  public PojoHolidays addRelativeToEasterSunday(RelativeToEasterSunday value) {
    this.relativeToEasterSunday.add(value);
    return this;
  }
}
