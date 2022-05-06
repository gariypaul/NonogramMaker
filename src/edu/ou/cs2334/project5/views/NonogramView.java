package edu.ou.cs2334.project5.views;

import java.util.Collections;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.views.clues.*;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
/**
 * This is the NonogramView Class it contains all the UI objects of the Nonogram
 * @author gariy
 *
 */

public class NonogramView extends BorderPane{
	private final static String STYLE_CLASS="nonogram-view";
	private final static String SOLVED_STYLE_CLASS="nonogram-view-solved";
	private LeftCluesView leftCluesView;
	private TopCluesView topCluesView;
	private CellGridView cellGridView;
	private HBox bottomHBox;
	private Button loadBtn;
	private Button resetBtn;
	/**
	 * This is the constructor for a new NonogramView Object
	 */
	public NonogramView() {
		this.getStyleClass().add(STYLE_CLASS);
	}
	/**
	 * This is the method that initialises the objects inside the NonogramView
	 * @param rowClues this is the rowClues int array 
	 * @param colClues this is the colClues array
	 * @param cellLength this is the int value of the cellLength in pixels
	 */
	public void initialize(int[][] rowClues, int[][] colClues, int cellLength) {
		leftCluesView = new LeftCluesView(rowClues,cellLength,CluesWidth(rowClues));
		topCluesView = new TopCluesView(colClues,cellLength,CluesWidth(colClues));
		cellGridView = new CellGridView(rowClues.length,colClues.length,cellLength);
		initBottomHBox();
		this.setLeft(leftCluesView);
		this.setTop(topCluesView);
		this.setCenter(cellGridView);
		this.setBottom(bottomHBox);
		BorderPane.setAlignment(leftCluesView, Pos.CENTER_LEFT);
		BorderPane.setAlignment(topCluesView, Pos.TOP_RIGHT);
		BorderPane.setAlignment(cellGridView, Pos.CENTER);
	}
	
	private void initBottomHBox() {
		bottomHBox = new HBox();
		bottomHBox.setAlignment(Pos.CENTER);
		loadBtn = new Button("Load");
		resetBtn = new Button("Reset");
		bottomHBox.getChildren().addAll(loadBtn,resetBtn);
	}
	/**
	 * This method gets the CellView from the specified index
	 * @param rowIdx this is the int value of the row the CellView is in 
	 * @param colIdx this is the int value of the column the CellView is in
	 * @return this is the CellView object that is returned
	 */
	public CellView getCellView(int rowIdx, int colIdx) {
		return cellGridView.getCellView(rowIdx, colIdx);
	}
	
	/**
	 * This method sets the cellState of the CellView that is in the specified index
	 * @param rowIdx this is the int value of the row the CellView is in 
	 * @param colIdx this is the int value of the column the CellView is in
	 * @param state this is the  CellState state that the CelView is to be set in 
	 */ 
	
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		cellGridView.setCellState(rowIdx, colIdx, state);
	}
	/**
	 * This method sets the state of the rowClues on the nonogram depending on the state of it being solved 
	 * @param rowIdx this is the int value of the row  
	 * @param solved this is true if solves and false if not solved
	 */
	public void setRowClueState(int rowIdx, boolean solved) {
		leftCluesView.setRowState(rowIdx,solved);
	}
	
	/**
	 *This method sets the state of the colClues on the nonogram depending on the state of it being solved 
	 * @param colIdx this is the int value of the row the column  is in 
	 * @param solved this is true if solves and false if not solved
	 */
	public void setColClueState(int colIdx, boolean solved) {
		topCluesView.setColState(colIdx,solved);
	}
	/**
	 * This method sets the state of the puzzle
	 * @param solved this is the boolean value of whether it is solved or not
	 */
	public void setPuzzleState(boolean solved) {
		if(solved==true) {
			this.getStyleClass().add(SOLVED_STYLE_CLASS);
		}
		else {
			this.getStyleClass().removeAll(Collections.singleton(SOLVED_STYLE_CLASS));
		}
		
	}
	/**
	 * This method returns the loadButton in the View
	 * @return this is the button object of the load button
	 */
	public Button getLoadButton() {
		return loadBtn;
	}
	
	/**
	 * /*
	 * This method returns the Reset button of the view
	 * @return this is the button object that is to be returned
	 */
	public Button getResetButton() {
		return resetBtn;
	}
	/**
	 * This method makes the program show a victory alert if puzzle is solved
	 */
	public void showVictoryAlert() {
		Alert victoryAlert = new Alert(Alert.AlertType.INFORMATION);
		victoryAlert.setTitle("Puzzle Solved");
		victoryAlert.setContentText("You win!");
		victoryAlert.show();
		
	}
	/*Helper Methods*/
	
	private int CluesWidth(int[][] cluesView) {
		int width = 0;
		for(int i=0; i<cluesView.length; i++) {
			if(cluesView[i].length>width) {
				width=cluesView[i].length;
			}
		}
		
		return width;
	}
	
}
