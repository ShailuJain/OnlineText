package com.onlinetext.core;

import static com.onlinetext.core.Constants.CLIPBOARD;

public class ClipboardOption extends Option {
    private StringBuilder help;

    public ClipboardOption() {
        super(CLIPBOARD);
        super.addAlias("-c");
        super.addAlias("--clipboard");

        this.buildHelp();
    }

    private void buildHelp() {
        this.help = new StringBuilder();
        this.help.append(super.help());
//        this.help.append(String.format("%5s%s", "","This option uses clipboard instead of file\n"));
//        this.help.append(String.format("%" + (super.help().length() + 5) + "s%s", "","usage: text <command> -c <remote_uri> \n"));
        this.help.append("This option uses clipboard instead of file\n");
        this.help.append(String.format("%30s%s", "","usage: text <command> -c <remote_uri> \n"));
    }

    @Override
    protected void argumentAdded(ArgumentType argumentType, String argument) {

    }

    @Override
    protected String help() {
        return this.help.toString();
    }
}
