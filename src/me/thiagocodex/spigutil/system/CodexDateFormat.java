package me.thiagocodex.spigutil.system;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CodexDateFormat {

    private String hours;
    private char separator;
    private String minutes;
    private String amPm;
    private String timeZoneID;
    private TimeZone timeZone;
    private Locale locale;

    public String getHours() {
        return hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public String getAmPm() {
        return amPm;
    }

    public void setAmPm(String amPm) {
        this.amPm = amPm;
    }

    public String getTimeZoneID() {
        return timeZoneID;
    }

    public void setTimeZoneID(String timeZoneID) {
        this.timeZoneID = timeZoneID;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }


    public Locale getLocale() {
        return locale;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public enum FORMAT {
        TINY, SMALL, NORMAL
    }


    private CodexDateFormat(TimeZone timeZone, Locale locale) {
        this.setTimeZone(timeZone);
        this.setLocale(locale);
    }

    public static CodexDateFormat getInstance(TimeZone timeZone, Locale locale) {
        return new CodexDateFormat(timeZone, locale);
    }


    public final String format(Date date, FORMAT format) {

        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT, getLocale());
        dateFormat.setTimeZone(getTimeZone());

        DateFormat dateFormat2 = DateFormat.getTimeInstance(DateFormat.FULL, getLocale());
        dateFormat2.setTimeZone(getTimeZone());

        List<String> zoneIds = new ArrayList<>(Arrays.asList(dateFormat2.format(date).split(" ")));

        String temp = dateFormat.format(date);

        char hourSeparator = ':';

        if (temp.contains(".")) {
            hourSeparator = '.';
        }

        setHours(temp.substring(0, temp.indexOf(hourSeparator)));
        setSeparator(hourSeparator);
        String minuteTemp = temp.substring(temp.indexOf(hourSeparator) + 1);
        setMinutes(minuteTemp.substring(0, 2));
        setTimeZoneID(zoneIds.get(zoneIds.size() - 1));


        String amPmOld;
        if (minuteTemp.contains(" AM")) {
            amPmOld = "AM";
        } else if (minuteTemp.contains(" PM")) {
            amPmOld = "PM";
        } else if (minuteTemp.contains(" a.m.")) {
            amPmOld = "a.m.";
        } else if (minuteTemp.contains(" p.m")) {
            amPmOld = "p.m.";
        } else {
            amPmOld = "";
        }


        if (temp.contains("a.m.") || temp.contains("p.m.") || temp.contains("AM") || temp.contains("PM")) {
            switch (getLocale().toLanguageTag()) {
                case "en-US":
                    setAmPm(amPmOld.equalsIgnoreCase("AM") ? " am" : " pm");
                    break;
                //more here
                case "any":
                    break;
                default:
                    setAmPm("");

            }
        } else {
            setAmPm("");
        }

        switch (format) {
            case TINY:
                return getHours() + getSeparator() + getMinutes();
            case SMALL:
                return getHours() + getSeparator() + getMinutes() + getAmPm();
            case NORMAL:
                return getHours() + getSeparator() + getMinutes() + getAmPm() + " " + getTimeZoneID();
        }
        return "Error";
    }
}
