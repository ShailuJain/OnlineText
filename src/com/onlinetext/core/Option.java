package com.onlinetext.core;

import java.util.List;

abstract public class Option extends CLIEntity{
    private int option;

    public Option(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
}
