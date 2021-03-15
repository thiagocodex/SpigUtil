package me.thiagocodex.spigutil.system.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class NormalFormat {

    public static String formatTypes(TimeZone timeZone, Locale locale, Date date) {

        SimpleDateFormat simpleDateFormat = CodexDateFormat.getSdfInstance(timeZone, locale);

        switch (locale.toLanguageTag()) {
            case "en-US":
                simpleDateFormat.applyPattern("EEEE, M/d/yy h:mm a z");
                break;
            case "pt-BR":
                simpleDateFormat.applyPattern("EEEE, dd/MM/yy HH:mm z");
                break;
        }
        return simpleDateFormat.format(date).replaceAll("AM", "am").replaceAll("PM", "pm");
    }

}
