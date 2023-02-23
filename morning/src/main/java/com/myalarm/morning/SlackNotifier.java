package com.myalarm.morning;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SlackNotifier {
    
    public void sendSlackMsg(String msg) throws Exception{
        String webhookUrl = "https://hooks.slack.com/services/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";

      // Encode the message in UTF-8 format
       String encodedMessage = URLEncoder.encode(msg, "UTF-8");

    // Create the POST request
    URL obj = new URL(webhookUrl);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("POST");
    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

    // Set the payload data
    String data = "payload={\"channel\": \"#test\", \"username\": \"버스알림이\",\"text\": \""+encodedMessage+"\", \"icon_emoji\": \":bus:\"}";
    // Send the POST request
    con.setDoOutput(true);
    OutputStream os = con.getOutputStream();
    os.write(data.getBytes("UTF-8"));
    os.flush();
    os.close();

    // Get the response from the server
    int responseCode = con.getResponseCode();
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    
    // Print the response from the server
        System.out.println("Response Code : " + responseCode);
        System.out.println("Response Message : " + response.toString());
    }
}