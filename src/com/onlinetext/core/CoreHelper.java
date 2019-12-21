package com.onlinetext.core;

import com.onlinetext.exception.TargetException;
import com.onlinetext.target.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.onlinetext.core.Constants.*;
import static com.onlinetext.core.Constants.STRING;

public class CoreHelper {
    static final List<Command> AVAILABLE_COMMANDS = new ArrayList<>();
    static final List<ArgumentType> AVAILABLE_ARGUMENT_TYPES = new ArrayList<>();
    static final ArgumentType STRING_ARGUMENT_TYPE = new ArgumentType(STRING);
    static final ArgumentType FILE_NAME_ARGUMENT_TYPE = new ArgumentType(FILE_NAME);
    static final Option CLIPBOARD_OPTION = new ClipboardOption();
    static final Option HELP_OPTION = new HelpOption();
    static {
        /**
         * Adding AVAILABLE_COMMANDS
         */
        AVAILABLE_COMMANDS.add(new GetCommand());
        AVAILABLE_COMMANDS.add(new PutCommand());
        AVAILABLE_COMMANDS.add(new AppendCommand());

    }

    static boolean isLast(int i, int length){
        return i == length - 1;
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
    static String exec(Command command, Target source, Target destination){
        if(command.isValid()){
            try {
                String onlineText = source.getText();
                if(command.getCommandType() == APPEND){
                    onlineText = destination.getText() + "\n" + onlineText;
                }
                if(destination.putText(onlineText)){
                    return "Copied text from " + source.getDescription() + "\nSuccessfully saved the text to " + destination.getDescription();
                }
            } catch (IOException | TargetException e) {
//                System.out.println(e.getMessage());
                return e.getMessage();
            }
        }
        return "You missed the syntax: \n" + command.help();
    }

    public static String help() {
        StringBuilder help = new StringBuilder();
        help.append("\nAvailable Commands :\n");
        for (Command availableCommand : AVAILABLE_COMMANDS) {
            help.append(availableCommand.getCommandName() + ": " + availableCommand.getDescription());
        }
        return help.toString();
    }
}
