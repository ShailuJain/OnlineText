package com.onlinetext.routing;

import com.onlinetext.core.Commands;
import com.onlinetext.core.ScrappingSite;
import com.onlinetext.webscraping.Helper;
import com.onlinetext.webscraping.Shrib;

public class Route {
    public static String call(String[] args) {
        if(Commands.isCommand(args[0]) || args.length == 2){
            String keyCommand = Commands.DEFAULT_COMMAND;
            String siteResourceName = args[0];
            String fileName = args[1];

            if(Commands.isCommand(args[0])){
                keyCommand = Commands.getKeyCommand(args[0]);
                siteResourceName = args[1];
                fileName = args[2];
            }

            switch (keyCommand){
                case Commands.GET:
                    String string = Helper.get(siteResourceName);
                    System.out.println(string);
                    break;
                case Commands.PUT:
                    System.out.println(siteResourceName);
                    System.out.println(fileName);
                    break;
                case Commands.APPEND:
                    System.out.println(siteResourceName);
                    System.out.println(fileName);
                    break;
                default:
                    System.out.println(siteResourceName);
                    System.out.println(fileName);
                    break;
            }
        }
        return "Error: Command Not Found";
    }
}
