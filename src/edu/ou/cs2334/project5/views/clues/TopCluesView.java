package edu.ou.cs2334.project5.views.clues;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;

/**
 * Represents a view containing all column clues displayed above the grid.
 * 
 * @author It's you
 * @version 0.1
 */
public class TopCluesView extends AbstractGroupCluesView {

	/**
	 * The style class for this view.
	 */
	private static final String STYLE_CLASS = "top-clues-view";
	
	/**
	 * Constructs a LeftCluesView given a set of clues, cell length, and height.
	 * 
	 * @param colClues a set of column clues
	 * @param cellLength the length of a cell
	 * @param height the maximum number of numbered clues among all columns
	 */
	public TopCluesView(int[][] colClues, int cellLength, int height) {
		super(Orientation.HORIZONTAL, STYLE_CLASS, colClues, cellLength, height);
		setMaxWidth(colClues.length * cellLength);
		
		// TODO: Possibly add something here. Do not directly discuss your
		// solution on Discord.
		setPrefWrapLength(Double.MAX_VALUE);
	}
	
	@Override
	protected AbstractOrientedClueView makeClue(int[] clue, int cellLength, int numClueUnits) {
		// TODO Auto-generated method stub
		VerticalClueView madeClue = new VerticalClueView(clue,cellLength,numClueUnits);
		return madeClue;
	}
	
	/**
	 * This method sets the state in which the column is in 
	 * @param colIdx this is the int value of the specified column's index
	 * @param solved this is true if it is solved and false if not
	 */
	public void setColState(int colIdx, boolean solved) {
		// TODO Auto-generated method stub
		super.setState(colIdx, solved);
		
	}

}
