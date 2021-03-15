package me.thiagocodex.spigutil.system.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ExtendedFormat {

    public static String formatTypes(TimeZone timeZone, Locale locale, Date date) {

        SimpleDateFormat simpleDateFormat = CodexDateFormat.getSdfInstance(timeZone, locale);

        switch (locale.toLanguageTag()) {
            case "en-US":
                simpleDateFormat.applyPattern("zzzz 'GMT'XXX\nEEEE, MMMM d, y, h:mm:ss.S a z");
                break;
            case "pt-BR":
                simpleDateFormat.applyPattern("zzzz 'GMT'XXX\nEEEE, dd 'de' MMMM 'de' y, HH:mm:ss.S z");
                break;
        }
        return simpleDateFormat.format(date).replaceAll("AM", "am").replaceAll("PM", "pm");
    }

}
