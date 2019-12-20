package com.onlinetext.core;

import java.util.ArrayList;

import static com.onlinetext.core.Constants.*;

public class PutCommand extends Command {
    public PutCommand() {
        super(GET);
        super.addAlias(GET_ALIAS_1);
        super.addAlias(GET_ALIAS_2);
        super.addRequiredArgumentType(CoreHelper.STRING_ARGUMENT_TYPE);
        super.addRequiredArgumentType(CoreHelper.FILE_NAME_ARGUMENT_TYPE);
        super.addAvailableOption(CoreHelper.CLIPBOARD_OPTION);
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
