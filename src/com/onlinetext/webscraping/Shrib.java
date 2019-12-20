package com.onlinetext.webscraping;

import com.onlinetext.core.Target;
import org.apache.commons.text.StringEscapeUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shrib implements Target {
    private String siteResourceName;
    private URL siteUrl;
    private HttpURLConnection httpURLConnection;
    public Shrib(String siteResourceName) {
        this.siteResourceName = siteResourceName;
    }
    @Override
    public String getText() throws IOException {
        String baseUrlString = "https://alt.shrib.com/";
        siteUrl = new URL(baseUrlString + this.siteResourceName);
        httpURLConnection = (HttpURLConnection) siteUrl.openConnection();
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
    public boolean putText(String text) throws IOException {
        // Instantiate CookieManager;
        // make sure to set CookiePolicy
//        CookieManager manager = new CookieManager();
//        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
//        CookieHandler.setDefault(manager);
        // your first request that does the authentication
        URL authUrl = new URL("https://shrib.com/");
        HttpURLConnection authCon = (HttpURLConnection) authUrl.openConnection();
        authCon.setInstanceFollowRedirects(false);
        authCon.connect();

// temporary to build request cookie header
        StringBuilder sb = new StringBuilder();

// find the cookies in the response header from the first request
        List<String> cookies = authCon.getHeaderFields().get("Set-Cookie");
        if (cookies != null) {
            for (String cookie : cookies) {
                if (sb.length() > 0) {
                    sb.append("; ");
                }

                // only want the first part of the cookie header that has the value
                String value = cookie.split(";")[0];
                sb.append(value);
            }
        }

// build request cookie header to send on all subsequent requests
        String cookieHeader = sb.toString();
        System.out.println(cookieHeader);

        String baseUrlString = "https://shrib.com/zuex/api.php";
//        String baseUrlString = "https://alt.shrib.com/user";
        siteUrl = new URL(baseUrlString);
        httpURLConnection = (HttpURLConnection) siteUrl.openConnection();
        httpURLConnection.setInstanceFollowRedirects(false);
        this.httpURLConnection.setRequestMethod("POST");
        this.httpURLConnection.setDoOutput(true);
        this.httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        this.httpURLConnection.setRequestProperty("charset", "utf-8");
        this.httpURLConnection.setRequestProperty("cookie","guetsli=MKV1tltVLou69bZRb31jrC2V4PL4aXwJTZL7XMqr;");

        String urlParams = null;
        try {
            urlParams = "note="+this.siteResourceName+"&ssc=1&text="+ URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            return false;
        }

        System.out.println(urlParams);
        byte[] postData = urlParams.getBytes( StandardCharsets.UTF_8 );
        int postDataLength = postData.length;
        this.httpURLConnection.setRequestProperty("Content-Length", Integer.toString( postDataLength ));
        this.httpURLConnection.connect();
        try( DataOutputStream wr = new DataOutputStream( this.httpURLConnection.getOutputStream())) {
            wr.write(postData);
            wr.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return this.httpURLConnection.getResponseCode() == 200;
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
