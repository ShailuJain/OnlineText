package com.onlinetext.scraping;

import com.onlinetext.exception.TargetException;
import com.onlinetext.target.FileTarget;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

public class PersistentCookieStore implements CookieStore, Runnable {
    CookieStore cookieStore;
    File file;
    String filename;
    public PersistentCookieStore() throws IOException, TargetException {
        cookieStore = new CookieManager().getCookieStore();
        String cookie;

        filename = System.getProperty("java.io.tmpdir") + File.separator + "shrib.cookie";
        if(!new File(filename).createNewFile()){
            FileTarget fileTarget = new FileTarget(filename);
            cookie = fileTarget.getText();
            String[] cookiesStrings = cookie.split(";");
            for (String cookiesString : cookiesStrings) {
                //Checking if the cookie string contains only \n as from getText() we are appending new line by \n
                if(!cookiesString.matches("[\n]")){
                    String[] keyValue = cookiesString.split("=");
                    this.cookieStore.add(null, new HttpCookie(keyValue[0], keyValue[1]));
                }
            }
        }

        //Calling this.run method when the jvm is shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(this));
    }
    @Override
    public void add(URI uri, HttpCookie cookie) {
        this.cookieStore.add(uri, cookie);
    }

    @Override
    public List<HttpCookie> get(URI uri) {
        return this.cookieStore.get(uri);
    }

    @Override
    public List<HttpCookie> getCookies() {
        return this.cookieStore.getCookies();
    }

    @Override
    public List<URI> getURIs() {
        return this.cookieStore.getURIs();
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        return this.cookieStore.remove(uri, cookie);
    }

    @Override
    public boolean removeAll() {
        return this.cookieStore.removeAll();
    }

    /**
     * Why: This is used to store the cookie in persistent storage
     * How: using a file to store cookie
     */
    @Override
    public void run() {
        String cookiesToWrite = "";
        FileTarget fileTarget = new FileTarget(this.filename);
        try {
            if(!(fileTarget.getText().length() > 0)){
                List<HttpCookie> cookies = this.cookieStore.getCookies();
                if(cookies != null)
                    for (HttpCookie cookie : cookies) {
                        cookiesToWrite += cookie.toString() + ";";
                    }
                fileTarget.putText(cookiesToWrite);
            }
        } catch (TargetException e) {
            e.printStackTrace();
        }

    }
}
