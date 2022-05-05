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
	private void bindCellViews() {
		// TODO Auto-generated method stub
		for(int r=0;r<model.getNumRows();r++) {
			for(int c=0;c<model.getNumCols();c++) {
				final int row = r;
				final int col = c;
				view.getCellView(row, col).setOnMouseClicked(e->{
					if(e.getButton()==MouseButton.PRIMARY) {
						handleLeftClick(row,col);
					}
					else {
						handleRightClick(row,col);
					}
				});
			}	
		}
		
	}
	
	private void handleRightClick(int rowIdx, int colIdx) {
		// TODO Auto-generated method stub
		if((model.getCellState(rowIdx,colIdx)==CellState.EMPTY)||(model.getCellState(rowIdx, colIdx)==CellState.FILLED)) {
			updateCellState(rowIdx,colIdx,CellState.MARKED);
		}
		else {
			updateCellState(rowIdx,colIdx,CellState.EMPTY);
		}
	}
	
	private void handleLeftClick(int rowIdx, int colIdx) {
		// TODO Auto-generated method stub
		if((model.getCellState(rowIdx,colIdx)==CellState.EMPTY)||(model.getCellState(rowIdx, colIdx)==CellState.MARKED)) {
			updateCellState(rowIdx,colIdx,CellState.FILLED);
		}
		else {
			updateCellState(rowIdx,colIdx,CellState.EMPTY);
		}
	}
	
	private void updateCellState(int rowIdx, int colIdx, CellState state) {
		// TODO Auto-generated method stub
		if(model.getCellState(rowIdx, colIdx)!=state&&!model.isSolved()) {
			model.setCellState(rowIdx, colIdx, state);
			view.setCellState(rowIdx, colIdx, state);
			view.setRowClueState(rowIdx, model.isRowSolved(rowIdx));
			view.setColClueState(colIdx, model.isColSolved(colIdx));
			view.setPuzzleState(model.isSolved());
			if(model.isSolved()) {
				processVictory();
			}
		}
		
	}
	
	private void processVictory() {
		// TODO Auto-generated method stub
		removeCellViewMarks();
		view.setPuzzleState(model.isSolved());
		view.showVictoryAlert();
		
	}
	

	private void removeCellViewMarks() {
		// TODO Auto-generated method stub
		for(int r=0;r<model.getNumRows();r++) {
			for(int c=0;c<model.getNumCols();c++) {
				if(model.getCellState(r, c)==CellState.MARKED) {
						model.setCellState(r, c, CellState.EMPTY);
						view.setCellState(r, c, CellState.EMPTY);
						view.setRowClueState(r, model.isRowSolved(r));
						view.setColClueState(c, model.isColSolved(c));
				}
			}
		}	
		
	}


	public Window getWindow() {
		// TODO Auto-generated method stub
		try {
			return view.getScene().getWindow();
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public Pane getPane() {
		return this.view;
	}
	
	private void synchronize() {
		// TODO Auto-generated method stub
		for(int r=0;r<model.getNumRows();r++) {
			for(int c=0;c<model.getNumCols();c++) {
				view.setCellState(r, c, model.getCellState(r, c));
			}
		}
		for(int r=0;r<model.getNumRows();r++) {
			view.setRowClueState(r, model.isRowSolved(r));
		}
		for(int c=0;c<model.getNumCols();c++) {
			view.setColClueState(c, model.isColSolved(c));
		}
		view.setPuzzleState(model.isSolved());
	}
	
	private void configureButtons() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
		view.getLoadButton().setOnAction(new OpenHandler(getWindow(), fileChooser, this));
		view.getResetButton().setOnAction(e-> resetPuzzle());
	}
}
