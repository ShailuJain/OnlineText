package com.onlinetext.core;

import com.onlinetext.client.ClipboardTarget;
import static com.onlinetext.core.Constants.*;

public class GetCommand extends Command {
    private Target source;
    private Target destination;
    public GetCommand() {
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
        }
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String execute() {
        return null;
    }
}
