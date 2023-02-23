package com.myalarm.morning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MorningApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MorningApplication.class, args);
		System.out.println("test");

		BikeSeoulHandler bikeSeoulHandler = new BikeSeoulHandler();
		BusStopHandler busStopHandler = new BusStopHandler();
		//bikeSeoulHandler.getBikeStationInfoAPI();
		busStopHandler.sendBusMessage(busStopHandler.getBusStationInfo());
	}

} 

