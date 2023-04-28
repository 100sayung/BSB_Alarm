package com.myalarm.morning.bikeSeoul;

import org.json.JSONArray;
import org.json.JSONObject;


public class BikeSeoulFactory {
    
    public static JSONObject extractObj(String jsonData){

        JSONArray bikeArray = new JSONObject(jsonData).getJSONObject("rentBikeStatus")
                             .getJSONArray("row");
        return bikeArray.getJSONObject(0);
    }

    public static BikeSeoulInfo jsonObjtoBikeObj(JSONObject bikeObject){

        BikeSeoulInfo bikeSeoulInfo = new BikeSeoulInfo();

        bikeSeoulInfo.setStationId(bikeObject.getString("stationId"));
        bikeSeoulInfo.setParkingBikeTotCnt(bikeObject.getString("parkingBikeTotCnt"));

        return bikeSeoulInfo;
    }
}
