package me.thiagocodex.spigutil.system.datetime;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CodexDateFormat {
    private static Locale locale;
    private static TimeZone timeZone;
    private static String nomeDiaDoMesCurto;
    private static String nomeDiaDoMes;
    private static String diaDoMes1digito;
    private static String diaDoMes2digitos;
    private static String mes1Digito;
    private static String mes2Digitos;
    private static String nomeDoMesCurto;
    private static String nomeDoMes;
    private static String anoCurto;
    private static String ano;
    private static String horas1Digito;
    private static String horas2Digitos;
    private static String minutos1Digito;
    private static String minutos2Digitos;
    private static String segundos1Digito;
    private static String segundos2Digitos;
    private static String amPm;
    private static String zoneId;

    enum FORMAT {
        TINY, SMALL, NORMAL, BIG, EXTENDED
    }

    private static CodexDateFormat instance;

    public static CodexDateFormat getInstance(TimeZone timeZone, Locale locale) {
        if (instance == null) {
            instance = new CodexDateFormat();
        }
        CodexDateFormat.timeZone = timeZone;
        CodexDateFormat.locale = locale;
        return instance;
    }

    private static SimpleDateFormat sdfInstance;

    static SimpleDateFormat getSdfInstance(TimeZone timeZone, Locale locale) {
        if (sdfInstance == null) {
            sdfInstance = new SimpleDateFormat("", locale);
        } else {
            try {
                Field privateLocaleField = SimpleDateFormat.class.getDeclaredField("locale");
                privateLocaleField.setAccessible(true);
                privateLocaleField.set(sdfInstance, locale);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sdfInstance.setTimeZone(timeZone);
        return sdfInstance;
    }

    String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = getSdfInstance(timeZone, locale);
        simpleDateFormat.applyPattern("E");
        nomeDiaDoMesCurto = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("EEEE");
        nomeDiaDoMes = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("d");
        diaDoMes1digito = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("dd");
        diaDoMes2digitos = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("M");
        mes1Digito = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("MM");
        mes2Digitos = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("MMM");
        nomeDoMesCurto = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("MMMM");
        nomeDoMes = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("yy");
        anoCurto = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("y");
        ano = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("H");
        horas1Digito = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("HH");
        horas2Digitos = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("m");
        minutos1Digito = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("mm");
        minutos2Digitos = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("s");
        segundos1Digito = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("ss");
        segundos2Digitos = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("a");
        amPm = simpleDateFormat.format(date);
        simpleDateFormat.applyPattern("z");
        zoneId = simpleDateFormat.format(date);
        return pattern
                .replaceAll("<dddd>", nomeDiaDoMes)
                .replaceAll("<ddd>", nomeDiaDoMesCurto)
                .replaceAll("<dd>", diaDoMes2digitos)
                .replaceAll("<d>", diaDoMes1digito)
                .replaceAll("<MMMM>", nomeDoMes)
                .replaceAll("<MMM>", nomeDoMesCurto)
                .replaceAll("<MM>", mes2Digitos)
                .replaceAll("<M>", mes1Digito)
                .replaceAll("<yy>", ano)
                .replaceAll("<y>", anoCurto)
                .replaceAll("<hh>", horas2Digitos)
                .replaceAll("<h>", horas1Digito)
                .replaceAll("<mm>", minutos2Digitos)
                .replaceAll("<m>", minutos1Digito)
                .replaceAll("<ss>", segundos2Digitos)
                .replaceAll("<s>", segundos1Digito)
                .replaceAll("<ampm>", amPm
                        .replaceAll("AM", "am")
                        .replaceAll("PM", "pm"))
                .replaceAll("<AMPM>", amPm)
                .replaceAll("<z>", zoneId);
    }

    String format(Date date, FORMAT format) {
        switch (format) {
            case TINY:
                return TinyFormat.formatTypes(timeZone, locale, date);
            case SMALL:
                return SmallFormat.formatTypes(timeZone, locale, date);
            case NORMAL:
                return NormalFormat.formatTypes(timeZone, locale, date);
            case BIG:
                return BigFormat.formatTypes(timeZone, locale, date);
            case EXTENDED:
                return ExtendedFormat.formatTypes(timeZone, locale, date);
            default:
                return "error";
        }
    }
}