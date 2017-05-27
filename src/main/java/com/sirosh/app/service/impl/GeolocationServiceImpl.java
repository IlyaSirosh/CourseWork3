package com.sirosh.app.service.impl;

import com.sirosh.app.service.GeolocationService;
import com.sirosh.app.service.GeolocationService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Illya on 5/27/17.
 */
@Service
public class GeolocationServiceImpl implements GeolocationService {


    private static final String IP_API_URL = "http://ip-api.com/json/";

    public String getStateByIp(String ip)  {
        JSONObject json = null;

        try {
            json = readJsonFromUrl(IP_API_URL+ip);

        }catch (Throwable e){}

        return json.getString("status").equals("success")?json.getString("country"):null;
    }



    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
