package com.myalarm.morning;

import org.json.*;

public class DataParser {
    public String xmlToJson(String xmlString) throws Exception{

        JSONObject jsonObject = XML.toJSONObject(xmlString);
        String jsonString = jsonObject.toString(4);

        return jsonString;
    }
}
