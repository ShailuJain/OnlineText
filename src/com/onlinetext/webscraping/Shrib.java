package com.onlinetext.webscraping;

import com.onlinetext.core.PersistentCookieStore;
import com.onlinetext.core.ScrapingHelper;
import com.onlinetext.core.Target;
import org.apache.commons.text.StringEscapeUtils;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shrib implements Target {
    private String siteResourceName;

    public Shrib(String siteResourceName) {
        this.siteResourceName = siteResourceName;
    }

    @Override
    public String getText() throws IOException {
        String baseUrlString = "https://alt.shrib.com/", text = "";
        HttpURLConnection httpURLConnection = (HttpURLConnection) ScrapingHelper.getConnection(baseUrlString, "GET");
        String content = ScrapingHelper.getContent(httpURLConnection);
//        Pattern pattern = Pattern.compile("<textarea(.*?)id=\"igob\"(.*?)>(.*?)</textarea>");
        Pattern pattern = Pattern.compile("<textarea(.*?)id=\"igob\"(.*?)>((.*)([\r\n\t].*)+)</textarea>");
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            text = matcher.group(3);
        }
        //Converting encoded text to normal text
        return StringEscapeUtils.unescapeHtml4(text);
    }

    @Override
    public boolean putText(String text) throws IOException {
        PersistentCookieStore myPersistentCookieStore = new PersistentCookieStore();
        CookieHandler.setDefault(new CookieManager(myPersistentCookieStore, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        String baseUrlString = "https://shrib.com/zuex/api.php";
        if(myPersistentCookieStore.getCookies().size() < 1){
            System.out.println("Default Cookie not set.\n Setting default cookie");
            String connectionUrl = "https://shrib.com/zuex/api.0.80155438379768.svg";
            URLConnection urlConnection = ScrapingHelper.getConnection(connectionUrl, "GET");
            urlConnection.getContent();
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) ScrapingHelper.getConnection(baseUrlString, "POST");
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setDoOutput(true);
        List<HttpCookie> cookies = myPersistentCookieStore.getCookies();
        for (HttpCookie cookie: cookies) {
            httpURLConnection.addRequestProperty("cookie", cookie.getName() + "=" + cookie.getValue() + ";");
        }

        String urlParams = "";
        try {
            urlParams = "note=" + this.siteResourceName + "&ssc=1&text=" + URLEncoder.encode(text, "UTF-8");
            byte[] postData = urlParams.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.write(postData);
            wr.flush();
            wr.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println(httpURLConnection.getResponseMessage());
        return httpURLConnection.getResponseCode() == 200;
    }

    @Override
    public boolean appendText(String text) {
        return false;
    }

    @Override
    public String getDescription() {
        return "https://shrib.com/" + siteResourceName;
    }
}
