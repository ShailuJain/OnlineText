package com.onlinetext.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static com.onlinetext.core.Constants.*;

public class Utils {
    public static final HashMap<String, Command> commands = new HashMap<>();
    static {
        /**
         * Adding available commands
         */
        commands.put(GET, new GetCommand());
        commands.put(PUT, new PutCommand());
        commands.put(APPEND, new AppendCommand());


    }

    public static boolean isCommand(String command){
        for (String key : commands.keySet()) {
            if(commands.get(key).inAliases(command)){
                return true;
            }
        }
        return false;
    }

    public static String getKeyCommand(String command) {
        for (String key : commands.keySet()) {
            if(commands.get(key).inAliases(command)){
                return key;
            }
        }
        return "";
    }
}
