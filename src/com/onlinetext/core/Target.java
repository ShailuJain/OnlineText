package com.onlinetext.core;

import java.io.IOException;

public interface Target {
    public String getText() throws IOException;
    public boolean putText(String text);
    public boolean appendText(String text);
    public String getDescription();
}
