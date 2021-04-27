package com.ruoyi.project.system.util;

/**
 * @author Vetch
 * @Description
 * @create 2021-04-09-9:19
 */

import com.ruoyi.framework.config.RuoYiConfig;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 切割时间字符串
 * *
 */
public class CutTimeUtil {


    /**
     * Description: 获取在指定时间内以指定分钟段分割的时间集合
     *
     * @param time         指定时间
     * @param minItem      指定分钟段
     * @param disableTimes 不包含的时间
     * @Return: java.util.List<java.lang.String>
     * @Date: 2021/04/25 10:45
     */
    public static List<String> cutTime(String time, int minItem, List<String> disableTimes) {
        List<String> result = new ArrayList<>();

        String[] minItems = getMin(minItem);
        Map<String, String> times = getTime(time);
        int startHour = Integer.parseInt(times.get("startHour"));
        int endHour = Integer.parseInt(times.get("endHour"));
        int startMin = Integer.parseInt(times.get("startMin"));
        String endTime = times.get("endTime");
        for (int i = startHour; i <= endHour; i++) {
            for (int j = 0; j < minItems.length; j++) {
                String temp;
                if (i == startHour && j == 0) {
                    temp = (startHour == 0 ? "00" : startHour) + ":" + (startMin == 0 ? "00" : startMin);
                    j = startMin / minItem + 1;
                } else {
                    temp = (i == 0 ? "00" : i) + ":" + minItems[j];
                }
                if (disableTimes != null) {
                    if (!disableTimes.contains(temp)) {
                        result.add(temp);
                        if (temp.equals(endTime)) {
                            break;
                        }
                    } else {
                        if (temp.equals(endTime)) {
                            break;
                        }
                    }
                } else {
                    result.add(temp);
                    if (temp.equals(endTime)) {
                        break;
                    }
                }
            }
        }
        return result;
    }


    /**
     * Description: 获取每小时中指定分钟段分割的分钟集合
     *
     * @param item 指定分钟段
     * @Return: java.lang.String[]
     * @Date: 2021/04/25 10:46
     */
    public static String[] getMin(int item) {
        int num = 60 / item;
        String[] result = new String[num];
        result[0] = "00";
        for (int i = 1; i < num; i++) {
            result[i] = String.valueOf(item * i);
        }
        return result;
    }

    /**
     * Description: 分割指定时间，获取时间信息
     *
     * @param time 指定时间
     * @Return: java.util.Map<java.lang.String, java.lang.String>
     * @Date: 2021/04/25 10:47
     */
    public static Map<String, String> getTime(String time) {
        Map<String, String> result = new HashMap<>();
        String regex = "[: -]";
        String[] splitTime = time.split(regex);
        result.put("startTime", splitTime[0] + ":" + splitTime[1]);
        result.put("endTime", splitTime[2] + ":" + splitTime[3]);
        result.put("startHour", splitTime[0]);
        result.put("startMin", splitTime[1]);
        result.put("endHour", splitTime[2]);
        result.put("endMin", splitTime[3]);
        return result;
    }
}
