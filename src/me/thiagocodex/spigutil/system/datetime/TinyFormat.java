package me.thiagocodex.spigutil.system.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TinyFormat {


    public static String formatTypes(TimeZone timeZone, Locale locale, Date date) {

        SimpleDateFormat simpleDateFormat = CodexDateFormat.getSdfInstance(timeZone, locale);

        switch (locale.toLanguageTag()) {
            case "en-US":
                simpleDateFormat.applyPattern("M/d/yy h:mm a");
                break;
            case "pt-BR":
                simpleDateFormat.applyPattern("dd/MM/yy HH:mm");
                break;
        }
        return simpleDateFormat.format(date).replaceAll("AM", "am").replaceAll("PM", "pm");
    }
}
