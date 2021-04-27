package com.studyspringboot;

import com.ruoyi.project.system.util.CutTimeUtil;
import io.swagger.models.auth.In;

import java.io.*;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Vetch
 * @Description
 * @create 2020-12-12-9:49
 */
public class aaa {
    public static void main(String[] args) throws IOException, ParseException {
//        ArrayList<String> result = new ArrayList<>();
//        ArrayList<String> times = new ArrayList<>();
//        times.add("19:00");
//        times.add("20:30");
//        times.add("17:30");
//        times.add("18:00");
DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        Date date1=format.parse("2021-04-30-11:30");
        Date date2=format.parse("2021-04-30-08:00");
        Calendar ca1 = Calendar.getInstance();
        Calendar ca2 = Calendar.getInstance();
        ca1.setTime(date1);
        ca2.setTime(date2);
//        long distanceMin =( ca1.getTimeInMillis()- ca2.getTimeInMillis())/(1000*60*60*24);
        long distanceMin =( ca1.getTimeInMillis()- ca2.getTimeInMillis())/(1000*60);
        System.out.println(distanceMin);
//        List<String> cutTime = CutTimeUtil. cutTime("17:30-23:00", 30, times);
//        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH：mm"));
//        String banTime = LocalDateTime.now().plus(1, ChronoUnit.MONTHS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH：mm"));
//        System.out.println( banTime);
    }
    public static List<String> cutTime(String time,int minItem,List<String> disableTimes) {
       List<String> result = new ArrayList<>();

        String[] minItems = getMin(minItem);
        Map<String, String> times = getTime(time);
        int startHour= Integer.parseInt(times.get("startHour")) ;
        int endHour=Integer.parseInt(times.get("endHour"));
        int startMin=Integer.parseInt(times.get("startMin"));
        String endTime=times.get("endHour");
        for (int i =startHour ; i <= endHour; i++) {
            for (int j = 0; j < minItems.length; j++) {
                String temp;
                if (i == startHour && j == 0) {
                    temp = (startHour == 0 ? "00" : startHour) + ":" + (startMin == 0 ? "00" : startMin);
                    j = startMin / minItem + 1;
                } else {
                    temp = (i == 0 ? "00" : i) + ":" + minItems[j];
                }
                if (!disableTimes.contains(temp)) {
                    if (temp.equals(endTime)) {
                        result.add(temp);
                        break;
                    }
                    result.add(temp);
                }
            }
        }
        return result;
    }

    public static String[] getMin(int item) {
        int num = 60 / item;
        String[] result = new String[num];
        result[0] = "00";
        for (int i = 1; i < num; i++) {
            result[i] = String.valueOf(item * i);
        }
        return result;
    }

    public static Map<String, String> getTime(String time) {
        Map<String, String> result = new HashMap<>();
        String regex="[: -]";
        String[] splitTime = time.split(regex);
        result.put("startTime", splitTime[0]+":"+splitTime[1]);
        result.put("endTime", splitTime[2]+":"+splitTime[3]);
        result.put("startHour", splitTime[0]);
        result.put("startMin", splitTime[1]);
        result.put("endHour", splitTime[2]);
        result.put("endMin", splitTime[3]);
        return result;
    }
}
