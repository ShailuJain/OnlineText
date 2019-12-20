package com.onlinetext.webscraping;

import com.onlinetext.core.Target;

import java.io.IOException;

public class Helper {
    public static String get(String siteResourceName){
        try {
            Target site = new Shrib(siteResourceName);
            return site.getText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
