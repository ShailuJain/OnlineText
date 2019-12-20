package com.onlinetext.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.onlinetext.core.Constants.*;
import static com.onlinetext.core.Constants.STRING;

public class CoreHelper {
    static final List<Command> AVAILABLE_COMMANDS = new ArrayList<>();
    static final List<Option> AVAILABLE_OPTIONS = new ArrayList();
    static final List<ArgumentType> AVAILABLE_ARGUMENT_TYPES = new ArrayList<>();
    static final ArgumentType STRING_ARGUMENT_TYPE = new ArgumentType(STRING);
    static final ArgumentType FILE_NAME_ARGUMENT_TYPE = new ArgumentType(FILE_NAME);
    static final Option CLIPBOARD_OPTION = new ClipboardOption();
    static {
        /**
         * Adding AVAILABLE_COMMANDS
         */
        AVAILABLE_COMMANDS.add(new GetCommand());
        AVAILABLE_COMMANDS.add(new PutCommand());
        AVAILABLE_COMMANDS.add(new AppendCommand());

        /**
         * Adding AVAILABLE_OPTIONS
         */
        AVAILABLE_OPTIONS.add(new ClipboardOption());
    }

    public static Command getCommand(String arg) {
        for (Command command : AVAILABLE_COMMANDS) {
            if(command.inAliases(arg)){
                return command;
            }
        }
        return null;
    }

    public static Command getCommand(int command) {
        switch (command){
            case GET:
                return AVAILABLE_COMMANDS.get(0);
            case PUT:
                return AVAILABLE_COMMANDS.get(1);
            case APPEND:
                return AVAILABLE_COMMANDS.get(2);
            default:
                return null;
        }
    }

    public static boolean isOption(String arg){
        if(arg == null || "".equals(arg))
            return false;
        return arg.startsWith("-");
    }

    public static Option getOption(String arg) {
        for (Option option : AVAILABLE_OPTIONS) {
            if(option.inAliases(arg)){
                return option;
            }
        }
        return null;
    }
    static String exec(Command command, Target source, Target destination){
        if(command.isValid()){
            try {
                String onlineText = source.getText();
                if(destination.putText(onlineText)){
                    return "Copied text from " + source.getDescription() + "\nSuccessfully saved the text to " + destination.getDescription();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Not a valid command";
    } 
}
