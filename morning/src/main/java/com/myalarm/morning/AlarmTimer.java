package com.myalarm.morning;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.myalarm.morning.bikeSeoul.BikeSeoulHandler;
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
        System.out.println("First Alarm Time" + cal.getTime());
        timer.schedule(new RingTask(), cal.getTime());
        // Schedule subsequent rings at 3-minute intervals
        //for (int i = 1; i < numRings; i++) {
           // cal.add(Calendar.MINUTE, 3);
        //timer.schedule(new RingTask(), cal.getTime());
       // }
    }

    private Calendar getNextAlarmTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        System.out.println(cal.get(Calendar.DAY_OF_WEEK) + "!!");
        // Find the next day of the week that the alarm should ring on
        int today = cal.get(Calendar.DAY_OF_WEEK);
        int daysToAdd = 0;
        for (int i = 0; i < daysOfWeek.length; i++) {
            int alarmDay = daysOfWeek[i];
            if (alarmDay > today) {
                daysToAdd = alarmDay - today;
                break;
            }
            if (alarmDay == today) {
              if (hour >= Calendar.getInstance().get(Calendar.HOUR_OF_DAY)){
                 if (minute >= Calendar.getInstance().get(Calendar.MINUTE)) {
                    daysToAdd = 0;
                    break;
                   }
               }
             }
            if(i==daysOfWeek.length-1){
              if(alarmDay<=today){
               daysToAdd=7-(today-daysOfWeek[0]);
                }
            }
        }


//         if (daysToAdd == 0) {
// //            daysToAdd = 7 - today + daysOfWeek[0];
//             System.out.println(Calendar.getInstance());
//             if(hour<=Calendar.getInstance().get(Calendar.HOUR_OF_DAY)&&minute<=Calendar.getInstance().get(Calendar.MINUTE)){
//                 daysToAdd =7-(today-daysOfWeek[0]);
//             }
//         }
        cal.add(Calendar.DAY_OF_WEEK, daysToAdd);

        return cal;
    }

    private class RingTask extends TimerTask {
        public void run() {
            System.out.println("RING!");
            BusStopHandler busStopHandler = new BusStopHandler();
            BikeSeoulHandler bikeSeoulHandler = new BikeSeoulHandler();
            System.out.println("알람이 울립니다!");
            try {
                busStopHandler.sendBusMessage(busStopHandler.getBusStationInfo());
                bikeSeoulHandler.sendBikeMessage(bikeSeoulHandler.getBikeStationInfo());
            } catch (Exception e) {
                e.printStackTrace();
            }


            numRingsSoFar++;
            System.out.println(numRingsSoFar);
            if (numRingsSoFar >= numRings) {
                Calendar nextCal = getNextAlarmTime();
                System.out.println(nextCal.getTime());
                System.out.println("Alarm(NextDay)");
                numRingsSoFar=0;
                try {
                    Thread.sleep(59500); //sleep 안하면 1분동안 계속반복해서 울림..
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                timer.schedule(new RingTask(), nextCal.getTime()) ;
//                timer.cancel();
            } else {
                // Schedule the next ring
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, 2); //기다리는 시간 : amount 2.
                System.out.println("Here is else.");
                System.out.println("Re-Alarm in same day" + cal.getTime());
                timer.schedule(new RingTask(), cal.getTime());
            }
        }
    }
}