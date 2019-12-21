package com.onlinetext.target;

import com.onlinetext.exception.TargetException;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

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
            return Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor).toString();
        }
		catch (UnsupportedFlavorException | IOException e) {
                System.out.println( "Something is bugging me in ClipboardTarget" + e.getMessage());
                throw new TargetException("Currently only clipboard text is supported!");
		}
	}

	@Override
	public String getDescription() {
		return "Clipboard";
	}
}