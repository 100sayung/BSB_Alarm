package com.myalarm.morning;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

import com.myalarm.morning.busStop.BusStopHandler;

public class AlarmTimer {
    private final Timer timer;
    private final int hour;
    private final int minute;
    private final int[] daysOfWeek;
    private final int numRings;
    private int numRingsSoFar;

    public AlarmTimer(int[] daysOfWeek, int hour, int minute, int numRings) {
        this.timer = new Timer();
        this.hour = hour;
        this.minute = minute;
        this.daysOfWeek = daysOfWeek;
        this.numRings = numRings;
        this.numRingsSoFar = 0;
    }

    public void start() {
        // Schedule the first ring for the next available day
        Calendar cal = getNextAlarmTime();
        timer.schedule(new RingTask(), cal.getTime());
System.out.println(cal.getTime());
        // Schedule subsequent rings at 3-minute intervals
        for (int i = 1; i < numRings; i++) {
            cal.add(Calendar.MINUTE, 3);
            timer.schedule(new RingTask(), cal.getTime());
        }
    }

    private Calendar getNextAlarmTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        System.out.println(cal.get(Calendar.DAY_OF_WEEK));
        // Find the next day of the week that the alarm should ring on
        int today = cal.get(Calendar.DAY_OF_WEEK);
        int daysToAdd = 0;
        for (int i = 0; i < daysOfWeek.length; i++) {
            int alarmDay = daysOfWeek[i];
            if (alarmDay >= today) {
                daysToAdd = alarmDay - today;
                break;
            }
        }
        if (daysToAdd == 0) {
//            daysToAdd = 7 - today + daysOfWeek[0];
            System.out.println(Calendar.getInstance());
            if(hour<=Calendar.getInstance().get(Calendar.HOUR_OF_DAY)&&minute<=Calendar.getInstance().get(Calendar.MINUTE)){
                daysToAdd =7;
            }
        }
        cal.add(Calendar.DAY_OF_WEEK, daysToAdd);

        return cal;
    }

    private class RingTask extends TimerTask {
        public void run() {
            System.out.println("RING!");
            BusStopHandler busStopHandler = new BusStopHandler();
            System.out.println("알람이 울립니다!");
            try {
                System.out.println(busStopHandler.getBusStationInfo());
    //           busStopHandler.sendBusMessage(jobj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            numRingsSoFar++;
            if (numRingsSoFar >= numRings) {
                timer.cancel();
                System.out.println("Alarm canceled.");
            } else {
                // Schedule the next ring
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, 3);
                timer.schedule(new RingTask(), cal.getTime());
            }
        }
    }
}