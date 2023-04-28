package com.myalarm.morning;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myalarm.morning.bikeSeoul.BikeSeoulFactory;
import com.myalarm.morning.bikeSeoul.BikeSeoulHandler;
import com.myalarm.morning.bikeSeoul.BikeSeoulInfo;
import com.myalarm.morning.busStop.BusStopHandler;
import com.myalarm.morning.message.MessageFactory;



@SpringBootApplication
public class MorningApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MorningApplication.class, args);
		BikeSeoulHandler bikeSeoulHandler = new BikeSeoulHandler();
		BusStopHandler busStopHandler = new BusStopHandler();
		BikeSeoulFactory bikeSeoulFactory = new BikeSeoulFactory();

		
		busStopHandler.sendBusMessage(busStopHandler.getBusStationInfo());
		bikeSeoulHandler.sendBikeMessage(bikeSeoulHandler.getBikeStationInfo());
		//busStopHandler.sendBusMessage(busStopHandler.getBusStationInfo());


		//알람 타이머 맞추기  1=일요일, 7=토요일;
	//	int[] arr = {2, 3, 4, 5, 6};
	//	AlarmTimer alarmTimer = new AlarmTimer(arr, 8, 25, 3);
	//	alarmTimer.start();
	}
} 

