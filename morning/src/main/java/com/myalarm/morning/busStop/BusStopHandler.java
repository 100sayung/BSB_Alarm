package com.myalarm.morning.busStop;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

import com.myalarm.morning.DataParser;
import com.myalarm.morning.message.MessageFactory;
import com.myalarm.morning.message.SlackNotifier;

import java.io.BufferedReader;


public class BusStopHandler {

    final DataParser dataParser = new DataParser();

    public void sendBusMessage(JSONObject busObject) throws Exception{

        String msg = MessageFactory.makeBusStopMsgString(BusStopFactory.jsonObjtoBusObj(busObject));
        System.out.println(msg);
        //SlackNotifier slackNotifier = new SlackNotifier();
        //slackNotifier.sendSlackMsg(msg);

    }

    public JSONObject getBusStationInfo() throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRoute"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=SQ9ZTtVc9M5q9lNUqvihEcpqRxsVzJJM9bx0yZ1Br8q7wOToP2l3uOH6cKMMcpe%2B50rm3m3fgyTXNoq1y434DA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("stId","UTF-8") + "=" + URLEncoder.encode("118000142", "UTF-8"));  //새마을금고 StId
        urlBuilder.append("&" + URLEncoder.encode("busRouteId","UTF-8") + "=" + URLEncoder.encode("100100057", "UTF-8")); //360번 RouteId
        urlBuilder.append("&" + URLEncoder.encode("ord","UTF-8") + "=" + URLEncoder.encode("40", "UTF-8")); //새마을금고 40번째
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return BusStopFactory.extractObj(dataParser.xmlToJson(sb.toString()));
    }
}

