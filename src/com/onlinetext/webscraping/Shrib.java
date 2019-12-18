package com.onlinetext.webscraping;

import com.onlinetext.core.ScrappingSite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shrib implements ScrappingSite {
    public String baseUrlString = "https://shrib.com/zuex/api.php";
    private String siteResourceName;
    private URL siteUrl;
    private HttpURLConnection httpURLConnection;
    public Shrib(String siteResourceName) throws IOException {
        this.siteResourceName = siteResourceName;
        siteUrl = new URL(baseUrlString);
        httpURLConnection = (HttpURLConnection) siteUrl.openConnection();
        this.httpURLConnection.setRequestMethod("POST");
    }
    @Override
    public String getText() throws IOException {
        System.out.println(this.siteUrl);

        InputStream is = this.httpURLConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = "", text = "";
        StringBuilder stringBuilder = new StringBuilder();

        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
        }
//        Pattern pattern = Pattern.compile("<textarea(.*?)name=\"t\"(.*?)>(.*?)</textarea>");
//        Matcher matcher = pattern.matcher(stringBuilder);
//
//        if(matcher.find()){
//            text = matcher.group(3);
//        }
        return stringBuilder.toString();
    }

    @Override
    public int putText(String text) {
        return 0;
    }

    @Override
    public int appendText(String text) {
        return 0;
    }
}
