package com.onlinetext.target;

import java.io.IOException;

public interface Target {
    public String getText() throws IOException;
    public boolean putText(String text) throws IOException;
    public boolean appendText(String text);
    public String getDescription();
}
