package com.myalarm.morning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myalarm.morning.bikeSeoul.BikeSeoulHandler;
import com.myalarm.morning.busStop.BusStopHandler;



@SpringBootApplication
public class MorningApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MorningApplication.class, args);
		BikeSeoulHandler bikeSeoulHandler = new BikeSeoulHandler();
		BusStopHandler busStopHandler = new BusStopHandler();
		//bikeSeoulHandler.getBikeStationInfoAPI();
		//busStopHandler.sendBusMessage(busStopHandler.getBusStationInfo());


		//알람 타이머 맞추기  1=일요일, 7=토요일;
		int[] arr = {2, 4, 5, 6};
		AlarmTimer alarmTimer = new AlarmTimer(arr, 11, 5, 2);
		alarmTimer.start();
	}
} 

