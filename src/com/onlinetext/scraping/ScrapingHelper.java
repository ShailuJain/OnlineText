package com.onlinetext.scraping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ScrapingHelper {
    public static URLConnection getConnection(String url, String method) throws IOException {
        URL siteUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) siteUrl.openConnection();
        httpURLConnection.setRequestMethod(method);
        return httpURLConnection;
    }
    public static String getContent(URLConnection connection) throws IOException {
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = "";
        StringBuilder stringBuilder = new StringBuilder();

        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }
        return stringBuilder.toString();
    }
}
