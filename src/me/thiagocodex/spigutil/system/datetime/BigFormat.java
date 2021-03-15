package me.thiagocodex.spigutil.system.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class BigFormat {

    public static String formatTypes(TimeZone timeZone, Locale locale, Date date) {

        SimpleDateFormat simpleDateFormat = CodexDateFormat.getSdfInstance(timeZone, locale);

        switch (locale.toLanguageTag()) {
            case "en-US":
                simpleDateFormat.applyPattern("EEEE, MMMM d, y\nh:mm:ss a z 'GMT'XXX");
                break;
            case "pt-BR":
                simpleDateFormat.applyPattern("EEEE, dd 'de' MMMM 'de' y\nHH:mm:ss z 'GMT'XXX");
                break;
        }
        return simpleDateFormat.format(date).replaceAll("AM", "am").replaceAll("PM", "pm");
    }
}
