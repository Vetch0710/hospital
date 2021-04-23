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
        String[] appointTime ="17:30-23:00".split("-");
        String []t1=new String[]{"00","30"};
        String[] startTime = appointTime[0].split(":");
        int startHour=Integer.parseInt(startTime[0]) ;
        int startMin=Integer.parseInt(startTime[1]) ;
        String[] endTime = appointTime[1].split(":");
        int endHour=Integer.parseInt(endTime[0]) ;
        int endMin=Integer.parseInt(endTime[1]) ;

    }
}
