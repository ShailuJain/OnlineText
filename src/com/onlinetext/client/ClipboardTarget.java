package com.onlinetext.client;
import com.onlinetext.core.Target;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardTarget implements Target
{
	@Override
	public boolean putText(String text){
		try{
			Toolkit.getDefaultToolkit().getSystemClipboard()
					.setContents(new StringSelection(text), null);
			return true;
		}catch (Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	@Override
	public String getText(){
		try {
			return (String)Toolkit.getDefaultToolkit()
            .getSystemClipboard().getData(DataFlavor.stringFlavor);
		}
		catch (UnsupportedFlavorException | IOException e) {
			return e.getMessage();
		}
	}

	@Override
	public boolean appendText(String text) {
		return false;
	}

	@Override
	public String getDescription() {
		return "Clipboard";
	}
}