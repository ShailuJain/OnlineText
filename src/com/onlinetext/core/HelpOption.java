package com.onlinetext.core;

import static com.onlinetext.core.Constants.CLIPBOARD;
import static com.onlinetext.core.Constants.HELP;

public class HelpOption extends Option {
    public HelpOption() {
        super(HELP);
        super.addAlias("-h");
        super.addAlias("--help");
    }
    @Override
    protected void argumentAdded(ArgumentType argumentType, String argument) {

    }

    @Override
    protected String help() {
        StringBuilder help = new StringBuilder();
        help.append(super.help());
//        help.append(String.format("%5s%s", "", "Get help of the command\n"));
        help.append("Get help of the command\n");
        return help.toString();
    }
}
