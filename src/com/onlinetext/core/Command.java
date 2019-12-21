package com.onlinetext.core;

import java.util.ArrayList;
import java.util.List;

abstract public class Command extends CLIEntity{
    private int commandName;
    private List<Option> availableOptions;
    private List<Option> appliedOptions;

    protected Command(int commandName) {
        this.commandName = commandName;
        this.availableOptions = new ArrayList<>();
        this.appliedOptions = new ArrayList<>();
    }

    public int getCommandName() {
        return commandName;
    }

    public void setCommandName(int commandName) {
        this.commandName = commandName;
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

    public boolean removeAppliedOption(Option option){
        return this.appliedOptions.remove(option);
    }

    public abstract void processOption(Option option);
    public abstract String execute();
}
