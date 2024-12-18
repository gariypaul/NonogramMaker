package edu.ou.cs2334.project5;

import edu.ou.cs2334.project5.presenters.NonogramPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * This is the Main class of the Nonogram Application
 * @author gariy
 *
 */
public class Main extends Application{
	private static final int IDX_CELL_SIZE = 0;
	private static final int DEFAULT_CELL_SIZE = 30;
	/**
	 * This is where the application is launched
	 * @param args these are the arguments passed to the application
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * This method Starts the Nonogram application 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		int cellSize;
		
		if(getParameters().getUnnamed()==null||getParameters().getUnnamed().size()==0) {
			cellSize = Main.DEFAULT_CELL_SIZE;
		}
		else {
			cellSize = Integer.parseInt(getParameters().getUnnamed().get(IDX_CELL_SIZE));
		}
		
		NonogramPresenter nonogramPresenter = new NonogramPresenter(cellSize);
		Scene scene = new Scene(nonogramPresenter.getPane());
		scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("Nonogram++");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
}
