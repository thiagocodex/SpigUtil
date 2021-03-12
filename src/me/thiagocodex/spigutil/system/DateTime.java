package me.thiagocodex.spigutil.system;

import me.thiagocodex.spigutil.SpigUtil;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateTime {



    public static String date() {
        Calendar calendar = Calendar.getInstance(SpigUtil.timeZone, SpigUtil.locale);
        CodexDateFormat dateFormat = CodexDateFormat.getInstance(SpigUtil.timeZone, SpigUtil.locale);

        return dateFormat.format(calendar.getTime(), CodexDateFormat.FORMAT.valueOf(SpigUtil.timeFormat));
    }
}
