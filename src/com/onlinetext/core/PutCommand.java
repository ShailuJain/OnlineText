package com.onlinetext.core;

import com.onlinetext.client.ClipboardTarget;
import com.onlinetext.client.FileTarget;
import com.onlinetext.webscraping.Shrib;

import java.io.IOException;
import java.util.ArrayList;

import static com.onlinetext.core.Constants.*;

public class PutCommand extends Command {
    private Target source;
    private Target destination;

    public PutCommand() {
        super(GET);
        super.addAlias(GET_ALIAS_1);
        super.addAlias(GET_ALIAS_2);
        super.addRequiredArgumentType(CoreHelper.STRING_ARGUMENT_TYPE);
        super.addRequiredArgumentType(CoreHelper.FILE_NAME_ARGUMENT_TYPE);
        super.addAvailableOption(CoreHelper.CLIPBOARD_OPTION);
    }
    public Target getSource() {
        return source;
    }

    public void setSource(Target source) {
        this.source = source;
    }

    public Target getDestination() {
        return destination;
    }

    public void setDestination(Target destination) {
        this.destination = destination;
    }

    @Override
    public void processOption(Option option) {
        if(option.getOption() == CLIPBOARD){
            setDestination(new ClipboardTarget());
            this.removeRequiredArgumentType(CoreHelper.FILE_NAME_ARGUMENT_TYPE);
        }
    }

    @Override
    public boolean isValid() {
        boolean flag = super.isValid();
        for (Option op : getAppliedOptions()) {
            if(!op.isValid()){
                return false;
            }
        }
        return flag;
    }

    @Override
    protected void argumentAdded(ArgumentType argumentType, String argument) {
        try {
            switch (argumentType.getArgumentType()){
                case STRING:
                    setSource(new Shrib(argument));
                    break;
                case FILE_NAME:
                    setDestination(new FileTarget(argument));
                    break;
                default:
                    System.out.println("Default argument added");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String execute() {
        return "I am executing";
    }
}
