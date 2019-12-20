package com.onlinetext.core;

import java.util.ArrayList;

import static com.onlinetext.core.Constants.CLIPBOARD;

public class ClipboardOption extends Option {
    public ClipboardOption() {
        super(CLIPBOARD);
        super.addAlias("-c");
        super.addAlias("--clipboard");
    }
}
