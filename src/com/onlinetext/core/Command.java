package com.onlinetext.core;

import java.util.ArrayList;
import java.util.List;

abstract public class Command extends CLIEntity{
    private String commandName;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    private int commandType;
    private List<Option> availableOptions;
    private List<Option> appliedOptions;

    protected Command(String commandName, int commandType) {
        this.commandType = commandType;
        this.commandName = commandName;
        this.availableOptions = new ArrayList<>();
        this.appliedOptions = new ArrayList<>();

        this.addAvailableOption(CoreHelper.HELP_OPTION);
    }

    public int getCommandType() {
        return commandType;
    }

    public void setCommandType(int commandType) {
        this.commandType = commandType;
    }

    public List<Option> getAvailableOptions() {
        return availableOptions;
    }

    public boolean addAvailableOption(Option availableOption) {
        return this.availableOptions.add(availableOption);
    }

    public List<Option> getAppliedOptions() {
        return appliedOptions;
    }

    public boolean addAppliedOption(Option appliedOption) {
        this.processOption(appliedOption);
        return this.appliedOptions.add(appliedOption);
    }

    public Option getOption(String arg) {
        for (Option option : this.getAvailableOptions()) {
            if(option.inAliases(arg)){
                return option;
            }
        }
        return null;
    }
    public boolean isAppliedOption(Option option){
        return this.appliedOptions.contains(option);
    }

    @Override
    protected String help() {
        StringBuilder help = new StringBuilder();
        help.append("Aliases: \n");
        help.append(super.help() + "\n\n");
        help.append("Options: \n");
        List<Option> options = this.getAvailableOptions();
        for (int i = 0; i < options.size(); i++) {
            help.append(options.get(i).help());
        }
        return help.toString();
    }

    public boolean removeAppliedOption(Option option){
        return this.appliedOptions.remove(option);
    }

    public abstract void processOption(Option option);
    public abstract String execute();
}
