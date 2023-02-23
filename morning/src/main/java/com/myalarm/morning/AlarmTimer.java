package com.myalarm.morning;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.Date;

public class AlarmTimer {
    private TimerTask task;

    public void AlarmTimer(){
        Timer timer = new Timer();

        // get the current date and time
        Calendar now = Calendar.getInstance();

        // set the alarm time to 8:00 am
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.set(Calendar.HOUR_OF_DAY, 15);
        alarmTime.set(Calendar.MINUTE, 55);
        alarmTime.set(Calendar.SECOND, 0);

        // calculate the delay until the alarm time
        long delay = alarmTime.getTimeInMillis() - now.getTimeInMillis();

        // schedule a TimerTask to send the alarm at the specified time
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("test");
            }
        }, delay);
    }

    public TimerTask getTask() {
        return task;
    }

    private Date getNextMonday(int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (cal.getTimeInMillis() < System.currentTimeMillis()) {
            cal.add(Calendar.WEEK_OF_YEAR, 1);
        }
        return cal.getTime();
    }
}