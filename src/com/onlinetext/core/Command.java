package com.onlinetext.core;

import java.util.ArrayList;

abstract public class Command {
    private ArrayList<String> aliases;
    private String commandName;
    private ArrayList<Option> availableOptions;
    private ArrayList<Option> appliedOptions;
    private ArrayList<Argument> requiredArguments;

    public Command(ArrayList<String> aliases, String commandName, ArrayList<Option> availableOptions, ArrayList<Option> appliedOptions, ArrayList<Argument> requiredArguments) {
        this.aliases = aliases;
        this.commandName = commandName;
        this.availableOptions = availableOptions;
        this.appliedOptions = appliedOptions;
        this.requiredArguments = requiredArguments;
    }

    public ArrayList<String> getAliases() {
        return aliases;
    }

    public void setAliases(ArrayList<String> aliases) {
        this.aliases = aliases;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public ArrayList<Option> getAvailableOptions() {
        return availableOptions;
    }

    public void setAvailableOptions(ArrayList<Option> availableOptions) {
        this.availableOptions = availableOptions;
    }

    public ArrayList<Option> getAppliedOptions() {
        return appliedOptions;
    }

    public void setAppliedOptions(ArrayList<Option> appliedOptions) {
        this.appliedOptions = appliedOptions;
    }

    public ArrayList<Argument> getRequiredArguments() {
        return requiredArguments;
    }

    public void setRequiredArguments(ArrayList<Argument> requiredArguments) {
        this.requiredArguments = requiredArguments;
    }

    public boolean addAppliedOption(Option option){
        return this.appliedOptions.add(option);
    }

    public boolean removeAppliedOption(Option option){
        return this.appliedOptions.remove(option);
    }
    public boolean addRequiredArgument(Argument argument){
        return this.requiredArguments.add(argument);
    }

    public boolean removeRequiredArgument(Argument argument){
        return this.requiredArguments.remove(argument);
    }

    public abstract boolean isValid();
    public abstract String execute();
}
