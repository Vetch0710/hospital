package com.ruoyi.project.system.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Vetch
 * @Description
 * @create 2021-04-02-13:56
 */
public class CalculateTime {
    // 时间戳 --- 日期 --- Date --- 年龄


    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timedate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 调用此方法输入所要转换的字符串输入例如"2014-06-14  16:09:00"输出Date类型的数据
     *
     * @param time
     * @return
     */
    public static Date getDate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date date = null;
        try {
            date = sdr.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 根据用户生日Date数据计算年龄
     */
    public static int getAgeByBirthday(Object birthday) {
        Date date;
        if (birthday instanceof String) {
            date=getDate(birthday.toString());
//            date = new Date(new Long(birthday.toString()));
        } else {
            date = (Date) birthday;
        }
//        System.out.println(date);
        Calendar cal = Calendar.getInstance();

        if (cal.before(date)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(date);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;
//        if (age == 0) {
//            return age;
//        }
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }

    /**
     * 根据毕业时间数据计算工龄
     */
    public static String getWorkDay(Object workday) {
        Date date;
        if (workday instanceof String) {
            date=getDate(workday.toString());

//            date = new Date(new Long(workday.toString()));
        } else {
            date = (Date) workday;
        }
        System.out.println(date);
        Calendar cal = Calendar.getInstance();

        if (cal.before(date)) {
            throw new IllegalArgumentException(
                    "The workday is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;

        cal.setTime(date);
        int yearWorkday = cal.get(Calendar.YEAR);
        int monthWorkday = cal.get(Calendar.MONTH) + 1;

        DecimalFormat df = new DecimalFormat("0.00");
        double time = yearNow - yearWorkday;
        String result;
        if (monthNow > monthWorkday) {
            result = df.format((time + (monthNow - monthWorkday) / 12.00));

        } else {
            double v = (float) (12 - monthWorkday + monthNow) / 12.00;
//            System.out.println(v);
            result = df.format(--time + v);
//            time = Double.valueOf(format);
        }
        return result;
    }
}
