package com.onlinetext.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Commands {
    /*
    * GET
    * PUT
    * APPEND
    */
    public static final String GET = "GET";
    public static final String PUT = "PUT";
    public static final String APPEND = "APPEND";
    public static final String DEFAULT_COMMAND = GET;
    public static final HashMap<String, ArrayList<String>> commands = new HashMap<>();
    static {
        commands.put(GET, new ArrayList<String>(){{ add("get"); add("r"); }});
        commands.put(PUT, new ArrayList<String>(){{ add("put"); add("w"); }});
        commands.put(APPEND, new ArrayList<String>(){{ add("append"); add("a");}});
    }

    public static boolean isCommand(String command){
        for (String key : commands.keySet()) {
            if(commands.get(key).contains(command)){
                return true;
            }
        }
        return false;
    }

    public static String getKeyCommand(String command) {
        for (String key : commands.keySet()) {
            if(commands.get(key).contains(command)){
                return key;
            }
        }
        return "";
    }
}
