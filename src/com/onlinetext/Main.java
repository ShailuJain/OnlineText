package com.onlinetext;

import com.onlinetext.routing.Route;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        /**
         * routing
         * web-scrapping
         * file operation
         */
        if(args.length < 2){
             System.exit(0);
        }
        System.out.println(Route.call(args));
//        getCookieUsingCookieHandler();
//        System.out.println(post("https://shrib.com/zuex/api.php", "note=shailu&ssc=1&text=hello"));
    }
    public static void getCookieUsingCookieHandler() {
        try {
            // Instantiate CookieManager;
            // make sure to set CookiePolicy
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);

            // get content from URLConnection;
            // cookies are set by web site
            URL url = new URL("https://alt.shrib.com/user");
            URLConnection connection = url.openConnection();
            connection.getContent();

            // get cookies from underlying
            // CookieStore
            CookieStore cookieJar =  manager.getCookieStore();
            List<HttpCookie> cookies =
                    cookieJar.getCookies();
            for (HttpCookie cookie: cookies) {
                System.out.println("CookieHandler retrieved cookie: " + cookie);
            }
        } catch(Exception e) {
            System.out.println("Unable to get cookie using CookieHandler");
            e.printStackTrace();
        }
    }
//    static String post(String url, String data) throws IOException {
//        CookieManager cookieManager = new CookieManager();
//        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
//        MediaType JSON = MediaType.parse("multipart/form-data");
//        OkHttpClient client = new OkHttpClient.Builder()
//                .cookieJar(new CookieJar() {
//                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
//
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                        cookieStore.put(url, cookies);
//                    }
//
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        List<Cookie> cookies = cookieStore.get(url);
//                        return cookies != null ? cookies : new ArrayList<Cookie>();
//                    }
//                })
//                .build();
//        RequestBody body = FormBody.create(JSON, data);
//        Request request = new Request.Builder()
//                .url(url)
//                .addHeader("accept:",  "*/*")
//                .addHeader("accept-encoding", "gzip, deflate, br")
//                .addHeader("accept-language", "en-US,en;q=0.9")
//                .addHeader("origin", "https://shrib.com")
//                .addHeader("referer", "https://shrib.com/")
//                .addHeader("sec-fetch-mode", "cors")
//                .addHeader("sec-fetch-site", "same-origin")
//                .post(body)
//                .build();
//        try (Response response = client.newCall(request).execute()) {
//            return response.body().string();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
}
