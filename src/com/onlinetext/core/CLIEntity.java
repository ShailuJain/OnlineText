package com.onlinetext.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a basic entity of the CLI
 * A CLI entity can be a command, option, etc
 */
abstract public class CLIEntity {
    private String description;
    private List<String> aliases;
    private List<ArgumentType> requiredArgumentTypes;
    private Map<ArgumentType, String> appliedArguments;

    protected CLIEntity(){
        this.aliases = new ArrayList<>();
        this.requiredArgumentTypes = new ArrayList<>();
        this.appliedArguments = new HashMap<>();
    }

    public Map<ArgumentType, String> getAppliedArguments() {
        return appliedArguments;
    }

    public void addAppliedArguments(ArgumentType appliedArgumentType, String argument) {
        this.appliedArguments.put(appliedArgumentType, argument);
        this.argumentAdded(appliedArgumentType, argument);
    }

    protected boolean addAlias(String alias) {
        return this.aliases.add(alias);
    }
    protected List<String> getAliases() {
        return aliases;
    }
    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }
    public boolean addRequiredArgumentType(ArgumentType argumentType){
        return this.requiredArgumentTypes.add(argumentType);
    }

    public boolean removeRequiredArgumentType(ArgumentType argumentType){
        return this.requiredArgumentTypes.remove(argumentType);
    }

    public List<ArgumentType> getRequiredArgumentTypes() {
        return requiredArgumentTypes;
    }

    protected boolean inAliases(String obj){
        if(obj == null)
            return false;
        for (String alias: this.aliases) {
            if(alias.equals(obj)){
                return true;
            }
        }
        return false;
    }
    public boolean isValid(){
        if(requiredArgumentTypes != null && appliedArguments != null){
            return requiredArgumentTypes.size() == appliedArguments.size();
        }
        return false;
    }
    protected abstract void argumentAdded(ArgumentType argumentType, String argument);
}
