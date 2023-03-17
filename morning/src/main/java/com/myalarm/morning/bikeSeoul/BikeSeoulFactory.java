package com.myalarm.morning.bikeSeoul;

import org.json.JSONObject;


public class BikeSeoulFactory {
    
    public static JSONObject extractObj(String jsonData){

        JSONObject busOjbect = new JSONObject(jsonData).getJSONObject("ServiceResult")
                             .getJSONObject("msgBody")
                             .getJSONObject("itemList");
        return busOjbect;
    }

    public static BikeSeoulInfo jsonObjtoBusObj(JSONObject busObject){

        BikeSeoulInfo bikeSeoulInfo = new BikeSeoulInfo();
        
        return bikeSeoulInfo;
    }
}
