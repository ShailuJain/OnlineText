package com.onlinetext;

import com.onlinetext.routing.Route;

public class Main {

    public static void main(String[] args) {
        /**
         * routing
         * webscrapping
         * file operation
         */
        if(args.length < 2){
            System.exit(0);
        }
        System.out.println(Route.call(args));
    }
}
