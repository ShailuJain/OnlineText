package com.onlinetext.core;

import com.onlinetext.target.ClipboardTarget;
import com.onlinetext.target.FileTarget;
import com.onlinetext.target.ShribTarget;
import com.onlinetext.target.Target;

import static com.onlinetext.core.Constants.*;

public class AppendCommand extends Command {
    private Target source;
    private Target destination;
    private StringBuilder help;

    public AppendCommand() {
        super("append", APPEND);
        super.addAlias(APPEND_ALIAS_1);
        super.addAlias(APPEND_ALIAS_2);
        super.addRequiredArgumentType(CoreHelper.STRING_ARGUMENT_TYPE);
        super.addRequiredArgumentType(CoreHelper.FILE_NAME_ARGUMENT_TYPE);
        super.addAvailableOption(CoreHelper.CLIPBOARD_OPTION);
        super.setDescription("This command is used to append data to remote site such as shrib.com, etc and paste to local file or clipboard\nusage: text append <remote_uri> <filename>\n\n");

        this.buildHelp();
    }

    private void buildHelp() {
        this.help = new StringBuilder();
        this.help.append(this.getDescription());
        this.help.append(super.help());
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
        }else if(option.getOption() == HELP){
            setDestination(null);
            setSource(null);
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
                setDestination(new ShribTarget(argument));
                break;
            case FILE_NAME:
                setSource(new FileTarget(argument));
                break;
            default:
                System.out.println("Default argument added");
        }
    }

    @Override
    protected String help() {
        return this.help.toString();
    }

    @Override
    public String execute() {
        if(this.source == null && this.destination == null)
            return help();
        return CoreHelper.exec(this, this.source, this.destination);
    }
}
