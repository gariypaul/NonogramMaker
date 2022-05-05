package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class CellGridView extends GridPane{
	private static final String STYLE_CLASS = "cell-grid-view";
	private CellView[][] cellViews;
	
	public CellGridView(int numRows, int numCols, int cellLength) {
		initCells(numRows, numCols, cellLength);
		this.getStyleClass().add(STYLE_CLASS);
		
	}
	
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
	
	public CellView getCellView (int rowIdx, int colIdx) {
		return this.cellViews[rowIdx][colIdx];
	}
	
}
