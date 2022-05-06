package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
/**
 *This class is for CellGridView in the Nonogram 
 *
 */
public class CellGridView extends GridPane{
	private static final String STYLE_CLASS = "cell-grid-view";
	private CellView[][] cellViews;
	/**
	 * This method is the constructor for a new CellGridView
	 * @param numRows this is the int value of  number of rows in the grid
	 * @param numCols this is the int value of the number of cols in the grid
	 * @param cellLength this is the int value of the cellLength in pixels
	 */
	public CellGridView(int numRows, int numCols, int cellLength) {
		initCells(numRows, numCols, cellLength);
		this.getStyleClass().add(STYLE_CLASS);
		
	}
	/**
	 * This method initializes the cells in the grid
	 * @param numRows this is the number of rows in the grid
	 * @param numCols this is the number of columns in the grid
 	 * @param cellLength this is the int value of the cell lengths in pixels
	 */
	public void initCells(int numRows, int numCols, int cellLength) {
		this.getChildren().clear();
		cellViews = new CellView[numRows][numCols];
		for(int i=0; i<numRows;i++) {
			for(int j=0;j<numCols;j++) {
				cellViews[i][j] = new CellView(cellLength);
				add(cellViews[i][j], j, i);
			}
		}
	}
	/**
	 * This method gets the CellView object in the specified index
	 * @param rowIdx this is the row the CellView is in 
	 * @param colIdx this is the column the CellView is in 
	 * @return this is the CellView object in the specified index
	 */
	public CellView getCellView (int rowIdx, int colIdx) {
		return this.cellViews[rowIdx][colIdx];
	}
	
	/**
	 * This methods sets the CellState of the CellView object in the specified index
	 * @param rowIdx this is the row the CellView is in 
	 * @param colIdx this is the column the CellView is in 
	 * @param state this is the state that the CellView is to be set to
	 */
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		this.cellViews[rowIdx][colIdx].setState(state);
	}
	
	
}
