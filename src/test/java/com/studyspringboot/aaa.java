package com.studyspringboot;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * @author Vetch
 * @Description
 * @create 2020-12-12-9:49
 */
public class aaa {
    public static void main(String[] args) throws IOException {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = LocalDate.now().plus(1, ChronoUnit.MONTHS);    //使用变量赋值
        System.out.println("Today is : " + today);
        System.out.println("Date after 1 week : " + nextWeek);

    }
}
