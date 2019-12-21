package com.onlinetext.target;

import com.onlinetext.exception.TargetException;

import java.io.IOException;

public interface Target {
    public String getText() throws IOException, TargetException;
    public boolean putText(String text) throws IOException, TargetException;
    public String getDescription();
}
