package edu.ou.cs2334.project5.handlers;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project5.interfaces.Openable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
/**
 * This class handles opening of text files
 * @author gariy
 *
 */
public class OpenHandler extends AbstractBaseHandler implements EventHandler<ActionEvent>  {
	private Openable opener;
	/**
	 * This is the constructor method of the OpenHandler object
	 * @param window this is the window for which the fileOpener is to opened for
	 * @param fileChooser this is a FileChooser object to retrieve the file name of the selected file 
	 * @param opener this is the object which will open the file chosen
	 */
	public OpenHandler(Window window, FileChooser fileChooser, Openable opener) {
		super(window,fileChooser);
		this.opener = opener;
	}
	/**
	 * This method handles the event that triggers the opening of a file and opens the FileChooser window.
	 */
	@Override
	public void handle(ActionEvent e) {
		FileChooser fileOpener = new FileChooser();
		fileOpener.setTitle("Open");
		fileOpener.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		File selectedfile = fileOpener.showOpenDialog(window);
		if(selectedfile!=null) {
			try {
				opener.open(selectedfile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}	

}
