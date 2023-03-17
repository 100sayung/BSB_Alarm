package com.myalarm.morning.message;

import com.myalarm.morning.busStop.BusStopInfo;

public class MessageFactory {
    
    public static String makeBusStopMsgString(BusStopInfo busStopInfo){

        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append("["+busStopInfo.getBusRouteAbrv()+"]");
        msgBuilder.append("("+busStopInfo.getStNm()+") ");
        msgBuilder.append(busStopInfo.getArrmgs1()+","+busStopInfo.getArrmgs2());
        msgBuilder.append(" 도착합니다.");
        return msgBuilder.toString();
    }
}
