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
    }
}