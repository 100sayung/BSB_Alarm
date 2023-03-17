package com.myalarm.morning.busStop;

import org.json.JSONObject;


public class BusStopFactory {
    
    public static JSONObject extractObj(String jsonData){

        JSONObject busOjbect = new JSONObject(jsonData).getJSONObject("ServiceResult")
                             .getJSONObject("msgBody")
                             .getJSONObject("itemList");
        return busOjbect;
    }

    public static BusStopInfo jsonObjtoBusObj(JSONObject busObject){

        BusStopInfo busStopInfo = new BusStopInfo();

        busStopInfo.setStNm(busObject.getString("stNm").toString());
        busStopInfo.setStId(Integer.toString(busObject.getInt("stId")));
        busStopInfo.setBusRouteAbrv(Integer.toString(busObject.getInt("busRouteAbrv")));
        busStopInfo.setBusRoutId(Integer.toString(busObject.getInt("busRouteId")));
        busStopInfo.setArrmgs1(busObject.getString("arrmsg1"));
        busStopInfo.setArrmgs2(busObject.getString("arrmsg2"));

        return busStopInfo;
    }
}
