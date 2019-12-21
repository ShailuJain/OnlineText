package com.onlinetext.core;

import static com.onlinetext.core.Constants.CLIPBOARD;

public class ClipboardOption extends Option {
    public ClipboardOption() {
        super(CLIPBOARD);
        super.addAlias("-c");
        super.addAlias("--clipboard");
    }

    @Override
    protected void argumentAdded(ArgumentType argumentType, String argument) {

    }
}
