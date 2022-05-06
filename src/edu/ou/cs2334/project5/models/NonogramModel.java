package edu.ou.cs2334.project5.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * This class creates the base model that the Nonogram is to follow
 * @author gariy
 *
 */
public class NonogramModel {

	private static final String DELIMITER = " ";
	private static final int IDX_NUM_ROWS = 0;
	private static final int IDX_NUM_COLS = 1;

	private int[][] rowClues;
	private int[][] colClues;
	private CellState[][] cellStates;
	/**
	 * This method is the constructor of a new NonngramModel object
	 * @param rowClues this is int array representing the clues for the rows in the nonogram
	 * @param colClues this is the int array representing the clues for the columns in the nonogram
	 */
	public NonogramModel(int[][] rowClues, int[][] colClues) {
		// TODO: Implement deepCopy. 
		// This is simple, and you should not ask about this on Discord.
		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);

		cellStates = initCellStates(getNumRows(), getNumCols());
	}
	/**
	 * This method is the constructor of a new NonogramModel object
	 * @param file this is the file that has the data to initialise a new NonogramModel
	 * @throws IOException this exception is throw if there is any error in accessing the file specified
	 */
	public NonogramModel(File file) throws IOException {
		// Number of rows and columns
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String header = reader.readLine();
		String[] fields = header.split(DELIMITER);
		int numRows = Integer.parseInt(fields[IDX_NUM_ROWS]);
		int numCols = Integer.parseInt(fields[IDX_NUM_COLS]);

		
		this.cellStates= initCellStates(numRows,numCols);
		this.rowClues=deepCopy(readClueLines(reader,numRows));
		this.colClues=deepCopy(readClueLines(reader,numCols));
		
		reader.close();
	}
	/**
	 * This method is the constructor of a new NonogramModel object
	 * @param filename this is the string that represents the file to be used 
	 * @throws IOException this ception is throw if there is any error in accessing the file specified by the string
	 */
	public NonogramModel(String filename) throws IOException {
		this(new File(filename));
	}
	/**
	 * This method return the number of columns in the nonogram 
	 * @return this is the int value of the number of columns
	 */
	public int getNumCols() {
		return this.colClues.length;
	}
	/**
	 * This method returns the number of rows in the nonogram
	 * @return this is the int value of the number of columns
	 */
	public int getNumRows() {
		return this.rowClues.length;
	}
	/**
	 * This method gets the Cellstate of a cell in the specified index
	 * @param rowIdx this is the index of the row the cell is in 
	 * @param colIdx this is the index of the column the cell is in 
	 * @return  this is the CellState of the cell in the specified index
	 */
	public CellState getCellState(int rowIdx, int colIdx) {
		return this.cellStates[rowIdx][colIdx];
	}
	/**
	 * This method gets the Cellstate of a cell in the specified index in boolean form 
	 * @param rowIdx this is the index of the row the cell is in 
	 * @param colIdx this is the index of the column the cell is in 
	 * @return this is state of the specified index in boolean form
	 */
	public boolean getCellStateasBoolean (int rowIdx, int colIdx) {
		return CellState.toBoolean(this.cellStates[rowIdx][colIdx]);
	}
	/**
	 * This method sets the CellState of the specified index and returns true if changed and false if nothing changes
	 * @param rowIdx this is the index of the row the cell is in 
	 * @param colIdx this is the index of the column the cell is in 
	 * @param state this is the CellState the index is to be set to 
	 * @return this is the boolean value to indicate if the state has been changed
	 */
	public boolean setCellState(int rowIdx, int colIdx, CellState state) {
		if(state==null||isSolved()||this.getCellState(rowIdx, colIdx)==state) {
			return false;
		}
		else {
			this.cellStates[rowIdx][colIdx] = state;
			return true;
		}
	}
	/**
	 * This method gets the row Clues
	 * @return this is the int array of the row clues
	 */
	public int[][] getRowClues(){
		return deepCopy(this.rowClues);
	}
	/**
	 * This method gets the column Clues
	 * @return this is the int array of the column clues
	 */
	public int[][] getColClues(){
		return deepCopy(this.colClues);
	}
	/**
	 * This method gets the int value of the clue for a specific row
	 * @param rowIdx this is the index of the specified row
	 * @return this is the integer array of the clue
	 */
	public int[] getRowClue(int rowIdx) {
		return Arrays.copyOf(this.rowClues[rowIdx], this.rowClues[rowIdx].length);
	}
	/**
	 * This method gets the int value of the clue for a specific clue
	 * @param colIdx this is the index of the specified row
	 * @return this is the integer array of the clue
	 */
	public int[] getColClue(int colIdx) {
		return Arrays.copyOf(this.colClues[colIdx], this.colClues[colIdx].length);
	}
	/**
	 * This method returns a boolean value true if the row specified is solved and false otherwise
	 * @param rowIdx this is the index value of the index of the row specified
	 * @return this is the boolean value returned if solved or not
	 */
	public boolean isRowSolved(int rowIdx) {
		int[] row = projectCellStatesRow(rowIdx);
		boolean isSolved = Arrays.equals(row,this.rowClues[rowIdx]);
		return isSolved;
	}
	/**
	  * This method returns a boolean value true if the column specified is solved and false otherwise
	 * @param colIdx this is the index value of the index of the column specified
	 * @return this is the boolean value returned if solved or not
	 */
	public boolean isColSolved(int colIdx) {
		int[] col = projectCellStatesCol(colIdx);
		boolean isSolved = Arrays.equals(col,this.colClues[colIdx]);
		return isSolved;
	}
	/**
	 * This method return true if the puzzle is solved and false otherwise
	 * @return this is the boolean value returned if solved or not
	 */
	public boolean isSolved() {
		boolean isSolved=true;
		for(int r=0;r<this.getNumRows()&&isSolved!=false;r++) {
			isSolved = isRowSolved(r);
		}
		for(int c=0;c<this.getNumCols()&&isSolved!=false;c++) {
			isSolved = isColSolved(c);
		}
		return isSolved;
	}
	/**
	 * This method resets the cells in the model back to an EMPTY CellState
	 */
	public void resetCells() {
		for(int r=0;r<this.getNumRows();r++) {
		 for(int c=0;c<this.getNumCols();c++) {
			// this.setCellState(r, c, CellState.EMPTY);
			 this.cellStates[r][c] = CellState.EMPTY;
		 }
		}
	}
	/* Helper methods */
	/**
	 * This method projects the boolean array passed 
	 * @param cells this is the boolean array to be projected 
	 * @return this is the List of integer values of the projected boolean array
	 */
	public static List<Integer> project(boolean[] cells){
		ArrayList<Integer> projection = new ArrayList<Integer>();	
		int counter = 0;
		for(int i=0;i<cells.length;i++) {
			if(cells[i]==true) {
				counter++;	
			}
			else if(counter>0){
				projection.add(counter);
				counter=0;
			}
		}
		if(counter>0) {
			projection.add(counter);
		}
		if(projection.size()==0) {
			projection.add(0);
		}
		return projection;
		
	}
	
	
	private static CellState[][] initCellStates(int numRows, int numCols) {
		// Create a 2D array to store numRows * numCols elements
		CellState[][] cellStates = new CellState[numRows][numCols];
		
		// Set each element of the array to empty
		for (int rowIdx = 0; rowIdx < numRows; ++rowIdx) {
			for (int colIdx = 0; colIdx < numCols; ++colIdx) {
				cellStates[rowIdx][colIdx] = CellState.EMPTY;
			}
		}
		
		// Return the result
		return cellStates;
	}
	/**
	 * This method projects the cellStates of the column of the specified index
	 * @param colIdx this is the index of the column to be projected
	 * @return this is the int array of the column's projection
	 */
	public int[] projectCellStatesCol(int colIdx) {
		// TODO Auto-generated method stub
		boolean[] colStates = new boolean [this.getNumRows()];
		for(int i=0;i<this.getNumRows();i++) {
			colStates[i] = CellState.toBoolean(this.cellStates[i][colIdx]);
		}
		int[] projectedCol = project(colStates).stream().mapToInt(Integer::intValue).toArray();
		return projectedCol;
	}
	/**
	* This method projects the cellStates of the row of the specified index
	 * @param rowIdx this is the index of the row to be projected
	 * @return this is the int array of the row's projection
	 */
	public int[] projectCellStatesRow(int rowIdx) {
		// TODO Auto-generated method stub
		boolean[] rowStates = new boolean[this.getNumCols()];
		for(int i=0; i<this.getNumCols();i++) {
			rowStates[i] = CellState.toBoolean(this.cellStates[rowIdx][i]);
		}
		int[] projectedRow = project(rowStates).stream().mapToInt(Integer::intValue).toArray();
		return projectedRow;
	}
	
	
	private static int[][] deepCopy(int[][] array) {
		if(array==null) {
			return null;
		}
		final int[][] copy = new int[array.length][array[0].length];
		for (int r=0;r<array.length;r++) {
			copy[r] = Arrays.copyOf(array[r], array[r].length);
				
		}
		
		return copy;
	}
	
	
	
	private static int[][] readClueLines(BufferedReader reader, int numLines)
			throws IOException {
		// Create a new 2D array to store the clues
		int[][] clueLines = new int[numLines][];
		
		// Read in clues line-by-line and add them to the array
		for (int lineNum = 0; lineNum < numLines; ++lineNum) {
			// Read in a line
			String line = reader.readLine();
			
			// Split the line according to the delimiter character
			String[] tokens = line.split(DELIMITER);
			
			// Create new int array to store the clues in
			int[] clues = new int[tokens.length];
			for (int idx = 0; idx < tokens.length; ++idx) {
				clues[idx] = Integer.parseInt(tokens[idx]);
			}
			
			// Store the processed clues in the resulting 2D array
			clueLines[lineNum] = clues;
		}
		
		// Return the result
		return clueLines;
	}
	
}