package com.onlinetext.webscraping;

import com.onlinetext.core.ScrappingSite;

import java.io.IOException;

public class Helper {
    public static String get(String siteResourceName){
        try {
            ScrappingSite site = new Shrib(siteResourceName);
            return site.getText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
