package com.onlinetext.core;

import java.io.IOException;
import java.net.ProtocolException;

public interface Target {
    public String getText() throws IOException;
    public boolean putText(String text) throws IOException;
    public boolean appendText(String text);
    public String getDescription();
}
