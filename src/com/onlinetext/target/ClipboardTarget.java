package com.onlinetext.target;

import com.onlinetext.exception.TargetException;

import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.File;
import java.io.PrintStream;

public class ClipboardTarget implements Target
{
	@Override
	public boolean putText(String text) throws TargetException {
		try{
			Toolkit.getDefaultToolkit().getSystemClipboard()
					.setContents(new StringSelection(text), null);
			return true;
		}catch (Exception e){
			System.out.println("Something is bugging me in ClipboardTarget" + e.getMessage());
            throw new TargetException("Currently only clipboard text is supported!");
		}
	}
	@Override
	public String getText() throws TargetException {
		try {
			System.setErr(new PrintStream(System.getProperty("java.io.tmpdir") + File.separator + "error.log"));
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			String text = clipboard.getData(DataFlavor.stringFlavor).toString();
            return text;
        }
		catch (Exception e) {
                System.out.println( "Something is bugging me in ClipboardTarget" + e.getMessage());
                throw new TargetException("Currently only clipboard text is supported!");
		}
	}

	@Override
	public String getDescription() {
		return "Clipboard";
	}
}