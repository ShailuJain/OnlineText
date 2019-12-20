package com.onlinetext.webscraping;

import com.onlinetext.core.Target;
import org.apache.commons.text.StringEscapeUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        String baseUrlString = "https://shrib.com/zuex/api.php";
        siteUrl = new URL(baseUrlString);
        httpURLConnection = (HttpURLConnection) siteUrl.openConnection();
        this.httpURLConnection.setRequestMethod("POST");
        this.httpURLConnection.setDoOutput(true);
        this.httpURLConnection.setInstanceFollowRedirects(false);
        this.httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        this.httpURLConnection.setRequestProperty("charset", "utf-8");
        String urlParams = null;
        try {
            urlParams = "note="+this.siteResourceName+"&ssc=1&text="+ URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            return false;
        }

        System.out.println(urlParams);
        byte[] postData = urlParams.getBytes( StandardCharsets.UTF_8 );
        int postDataLength = postData.length;
        this.httpURLConnection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
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
