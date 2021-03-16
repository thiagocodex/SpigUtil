package me.thiagocodex.spigutil.system.datetime;

import me.thiagocodex.spigutil.SpigUtil;

import java.util.Calendar;

public class DateTime {


    public static String date() {
        Calendar calendar = Calendar.getInstance(SpigUtil.timeZone, SpigUtil.locale);
        CodexDateFormat codexDateFormat = CodexDateFormat.getInstance(SpigUtil.timeZone, SpigUtil.locale);

        return codexDateFormat.format(calendar.getTime(), CodexDateFormat.FORMAT.valueOf( SpigUtil.timeFormat));
    }
}
