package edu.ou.cs2334.project5.presenters;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project5.handlers.OpenHandler;
import edu.ou.cs2334.project5.interfaces.Openable;
import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.NonogramView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class NonogramPresenter implements Openable{
	
	private NonogramView view;
	private NonogramModel model;
	private int cellLength;
	private final static String DEFAULT_PUZZLE = "puzzles/space-invader.txt";
	
	public NonogramPresenter (int cellLength) {
		this.cellLength=cellLength;
		try {
			model = new NonogramModel(this.DEFAULT_PUZZLE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		view = new NonogramView();
		initializePresenter();
		
	}
	

	private void initializePresenter() {
		initializeView();
		bindCellViews();
		synchronize();
		configureButtons();
	}
	
	private void initializeView() {
		// TODO Auto-generated method stub
		view.getChildren().clear();
		view.initialize(model.getRowClues(),model.getColClues(),this.cellLength);
		view.setPuzzleState(model.isSolved());
		if(this.getWindow()!=null) {
			this.getWindow().sizeToScene();
		}
	}
}
