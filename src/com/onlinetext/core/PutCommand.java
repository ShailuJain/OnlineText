package com.onlinetext.core;

import java.util.List;

public class GetCommand extends Command {
    public GetCommand(List<String> aliases, String commandName, List<Option> availableOptions, List<Option> appliedOptions, List<Argument> requiredArguments) {
        super(aliases, commandName, availableOptions, appliedOptions, requiredArguments);
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
