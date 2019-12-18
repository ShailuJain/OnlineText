package com.onlinetext.core;

import java.io.IOException;

public interface ScrappingSite {
    public String getText() throws IOException;
    public int putText(String text);
    public int appendText(String text);
}
