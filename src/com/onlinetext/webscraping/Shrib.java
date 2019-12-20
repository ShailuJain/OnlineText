package com.onlinetext.webscraping;

import com.onlinetext.core.Target;
import org.apache.commons.text.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shrib implements Target {
    public String baseUrlString = "https://alt.shrib.com/";
    private String siteResourceName;
    private URL siteUrl;
    private HttpURLConnection httpURLConnection;
    public Shrib(String siteResourceName) throws IOException {
        this.siteResourceName = siteResourceName;
        siteUrl = new URL(baseUrlString + this.siteResourceName);
        httpURLConnection = (HttpURLConnection) siteUrl.openConnection();
    }
    @Override
    public String getText() throws IOException {
        System.out.println(this.siteUrl);
        this.httpURLConnection.setRequestMethod("GET");
        InputStream is = this.httpURLConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = "", text = "";
        StringBuilder stringBuilder = new StringBuilder();

        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }
        System.out.println(this.httpURLConnection.getHeaderFields());
//        Pattern pattern = Pattern.compile("<textarea(.*?)id=\"igob\"(.*?)>(.*?)</textarea>");
        Pattern pattern = Pattern.compile("<textarea(.*?)id=\"igob\"(.*?)>((.*)([\r\n\t].*)+)</textarea>");

        Matcher matcher = pattern.matcher(stringBuilder);

        if(matcher.find()){
            text = matcher.group(3);
        }
        //Converting encoded text to normal text
        return StringEscapeUtils.unescapeHtml4(text);
    }

    @Override
    public boolean putText(String text) {
        return false;
    }

    @Override
    public boolean appendText(String text) {
        return false;
    }

    @Override
    public String getDescription() {
        return "shrib.com/" + siteResourceName;
    }
}
