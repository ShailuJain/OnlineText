package com.onlinetext.routing;

import com.onlinetext.core.ArgumentType;
import com.onlinetext.core.Command;
import com.onlinetext.core.CoreHelper;
import com.onlinetext.core.Option;

import java.util.List;

public class Route {
    public static String call(String[] args) {
        int index = 0, i = 0;
        Command command = CoreHelper.getCommand(args[0]);
        if(command != null){
            while(++index < args.length){
                if(CoreHelper.isOption(args[index])){
                    Option op = CoreHelper.getOption(args[index]);
                    if(op == null){
                        return "Option " + args[index] + " is not a valid option";
                    }
                    i = 0;
                    List<ArgumentType> requiredArgumentTypes = op.getRequiredArgumentTypes();
                    int requiredArguments = op.getRequiredArgumentTypes().size();
                    while(requiredArguments > 0 && index < args.length){
                        index++;
                        ArgumentType argumentType = requiredArgumentTypes.get(i++);
                        String arg = args[index];
                        if(arg.matches(argumentType.getArgumentType())){
                            op.addAppliedArguments(argumentType, arg);
                        }else{
                            return "This option requires argument(s)";
                        }
                        requiredArguments--;
                    }
                    command.addAppliedOption(op);
                }else{
                    i = 0;
                    List<ArgumentType> requiredArgumentTypes = command.getRequiredArgumentTypes();
                    int requiredArguments = command.getRequiredArgumentTypes().size();
                    while(requiredArguments > 0 && index < args.length){
                        ArgumentType argumentType = requiredArgumentTypes.get(i++);
                        String arg = args[index];
                        if(arg.matches(argumentType.getArgumentType())){
                            command.addAppliedArguments(argumentType, arg);
                        }else{
                            return "This command requires valid argument(s)";
                        }
                        index++;
                        requiredArguments--;
                    }
                }
            }
            return command.execute();
        }
        return "Error: Command Not Found";
    }
}
