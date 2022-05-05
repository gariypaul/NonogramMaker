package edu.ou.cs2334.project5.handlers;

import javafx.stage.FileChooser;
import javafx.stage.Window;
/**
 * This is an abstract class which implements the base constructor for a fileChooser handler
 * @author gariy
 *
 */
public abstract class AbstractBaseHandler {

	protected Window window;
	protected FileChooser fileChooser;
	
	protected AbstractBaseHandler(Window window, FileChooser fileChooser) {
		this.window=window;
		this.fileChooser=fileChooser;
	}

}
