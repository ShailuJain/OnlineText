package com.onlinetext;

import com.onlinetext.routing.Route;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(Route.call(args));
    }
}