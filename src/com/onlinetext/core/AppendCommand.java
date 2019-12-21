package com.onlinetext.core;

import com.onlinetext.target.ClipboardTarget;
import com.onlinetext.target.FileTarget;
import com.onlinetext.target.Shrib;
import com.onlinetext.target.Target;

import static com.onlinetext.core.Constants.*;

public class AppendCommand extends Command {
    private Target source;
    private Target destination;

    public AppendCommand() {
        super(APPEND);
        super.addAlias(APPEND_ALIAS_1);
        super.addAlias(APPEND_ALIAS_2);
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
            setSource(new ClipboardTarget());
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
        switch (argumentType.getArgumentType()){
            case STRING:
                setDestination(new Shrib(argument));
                break;
            case FILE_NAME:
                setSource(new FileTarget(argument));
                break;
            default:
                System.out.println("Default argument added");
        }
    }

    @Override
    public String execute() {
        return CoreHelper.exec(this, this.source, this.destination);
    }
}
