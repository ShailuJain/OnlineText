package com.onlinetext;

import com.onlinetext.core.PersistentCookieStore;
import com.onlinetext.routing.Route;

import java.io.IOException;
import java.net.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        /**
         * routing
         * web-scrapping
         * file operation
         */
        if (args.length < 2) {
            System.exit(0);
        }

        System.out.println(Route.call(args));
//        getCookieUsingCookieHandler();
    }

    public static void getCookieUsingCookieHandler() {
        try {
            // Instantiate CookieManager;
            // make sure to set CookiePolicy

            PersistentCookieStore persistentCookieStore = new PersistentCookieStore();
            CookiePolicy cookiePolicy;
            CookieManager manager = new CookieManager(persistentCookieStore, CookiePolicy.ACCEPT_ORIGINAL_SERVER);
            CookieHandler.setDefault(manager);

            // get content from URLConnection;
            // cookies are set by web site
            URL url = new URL("https://shrib.com/zuex/api.php");
//            URL url = new URL("");
            URLConnection connection = url.openConnection();
            connection.getContent();
//            System.out.println(connection.getHeaderFields().get("Set-Cookie"));

            // get cookies from underlying
            // CookieStore
            CookieStore cookieJar =  manager.getCookieStore();
            List<HttpCookie> cookies =
                    cookieJar.getCookies();
            for (HttpCookie cookie: cookies) {
                System.out.println("CookieHandler retrieved cookie: " + cookie.getValue());
            }
        } catch (Exception e) {
            System.out.println("Unable to get cookie using CookieHandler");
            e.printStackTrace();
        }
    }
}