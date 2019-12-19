package clipboard;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
class Copy
{
	public void setTextToClipboard(String text){
		Toolkit.getDefaultToolkit().getSystemClipboard()
			.setContents(new StringSelection(text), null);
	}
	public String getTextFromClipboard(){
		try
		{
			return (String)Toolkit.getDefaultToolkit()
            .getSystemClipboard().getData(DataFlavor.stringFlavor);
		}
		catch (UnsupportedFlavorException e)
		{
			System.out.println(e);
		}
		catch(IOException e){
			System.out.println(e);
			
		}
		return null;

		
	}
	public static void main(String args[]){
		Copy c = new Copy();
		c.setTextToClipboard("hello bc jo padh rha hau");
		System.out.println(c.getTextFromClipboard());
	}
	
}