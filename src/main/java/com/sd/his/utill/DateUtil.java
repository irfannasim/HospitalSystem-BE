package com.sd.his.utill;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/*
 * @author    : irfan nasim
 * @Date      : 15-May-18
 * @version   : ver. 1.0.0
 *
 * ________________________________________________________________________________________________
 *
 *  Developer				Date		     Version		Operation		Description
 * ________________________________________________________________________________________________
 *
 *
 * ________________________________________________________________________________________________
 *
 * @Project   : HIS
 * @Package   : com.sd.ap.util
 * @FileName  : DateUtil
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public class DateUtil {

    public static String convertDateToUFDateFormat(Date d, long millis) {
        if (!HISCoreUtil.isValidObject(d)) {
            d = new Date(millis);
        }

        String str = "";
        if (d != null) {

            Calendar start = Calendar.getInstance();
            start.setTime(d);
            Calendar end = Calendar.getInstance();
            end.setTime(new Date());

            SimpleDateFormat sameYrDateFormat = new SimpleDateFormat(HISConstants.DATE_PATTERN_SAME_YEAR),
                    preYrDateFormat = new SimpleDateFormat(HISConstants.DATE_FORMATE_ONE),
                    stf = new SimpleDateFormat(HISConstants.TIME_PATTERN);

            long diff = end.getTimeInMillis() - start.getTimeInMillis();

            if ((diff / 1000) <= 60) {
                str = "few seconds ago";
            } else if ((diff / 60000) <= 60) {
                str = (diff / 60000) + " minutes ago";
            } else if ((diff / 3600000) <= 24) {
                str = (diff / 3600000) + " hours ago";
            } else if ((diff / 3600000) > 24 && (diff / 3600000) < 48) {
                str = "Yesterday at " + stf.format(d).toLowerCase();
            } else if (start.getTime().getYear() != end.getTime().getYear()) {
                str = preYrDateFormat.format(d) + " at " + stf.format(d).toLowerCase();
            } else if ((diff / 31536000000L) <= 12) {
                str = sameYrDateFormat.format(d) + " at " + stf.format(d).toLowerCase();
            }
            //System.out.println("Date::: " + str);
        }
        return str;
    }

    public static long addDaysInMilliDays(long millisDate, int days) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millisDate);
        c.add(Calendar.DATE, days);

        return c.getTimeInMillis();
    }

    public static long getMillisFromStringDate(String date, String format) {
        Date parsedDate = null;
        try {
            SimpleDateFormat sameYrDateFormat = new SimpleDateFormat(format);
            parsedDate = sameYrDateFormat.parse(date);
        } catch (Exception ex) {

        }
        return parsedDate.getTime();
    }

    public static String getDateFromMillis(long dateMillis, String format) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sameYrDateFormat = null;
        try {
            calendar.setTimeInMillis(dateMillis);
            sameYrDateFormat = new SimpleDateFormat(format);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sameYrDateFormat.format(calendar.getTime());
    }

    public static Date getDateFromString(String dateString, String dateFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        Date date = null;
        date = format.parse(dateString);
        return date;
    }

    public static String getFormattedDateFromDate(Date date, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        return format.format(date);
    }
}
