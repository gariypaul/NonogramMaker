package edu.ou.cs2334.project5.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonogramModel {

	private static final String DELIMITER = " ";
	private static final int IDX_NUM_ROWS = 0;
	private static final int IDX_NUM_COLS = 1;

	private int[][] rowClues;
	private int[][] colClues;
	private CellState[][] cellStates;
	
	public NonogramModel(int[][] rowClues, int[][] colClues) {
		// TODO: Implement deepCopy. 
		// This is simple, and you should not ask about this on Discord.
		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);

		cellStates = initCellStates(getNumRows(), getNumCols());
	}
	
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

	public NonogramModel(String filename) throws IOException {
		this(new File(filename));
	}
	
	public int getNumCols() {
		return this.colClues.length;
	}
	
	public int getNumRows() {
		return this.rowClues.length;
	}
	
	public CellState getCellState(int rowIdx, int colIdx) {
		return this.cellStates[rowIdx][colIdx];
	}
	
	public boolean getCellStateasBoolean (int rowIdx, int colIdx) {
		return CellState.toBoolean(this.cellStates[rowIdx][colIdx]);
	}
	
	public boolean setCellState(int rowIdx, int colIdx, CellState state) {
		if(state==null||isSolved()||this.getCellState(rowIdx, colIdx)==state) {
			return false;
		}
		else {
			this.cellStates[rowIdx][colIdx] = state;
			return true;
		}
	}
	
	public int[][] getRowClues(){
		return deepCopy(this.rowClues);
	}
	
	public int[][] getColClues(){
		return deepCopy(this.colClues);
	}
	
	public int[] getRowClue(int rowIdx) {
		return Arrays.copyOf(this.rowClues[rowIdx], this.rowClues[rowIdx].length);
	}
	
	public int[] getColClue(int colIdx) {
		return Arrays.copyOf(this.colClues[colIdx], this.colClues[colIdx].length);
	}
	
	public boolean isRowSolved(int rowIdx) {
		int[] row = projectCellStatesRow(rowIdx);
		boolean isSolved = Arrays.equals(row,this.rowClues[rowIdx]);
		return isSolved;
	}
	
	public boolean isColSolved(int colIdx) {
		int[] col = projectCellStatesCol(colIdx);
		boolean isSolved = Arrays.equals(col,this.colClues[colIdx]);
		return isSolved;
	}
	
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
	/* Helper methods */
	
	// This is implemented for you
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
	
	
	
	// TODO: Implement this method
	private static int[][] deepCopy(int[][] array) {
		// You can do this in under 10 lines of code. If you ask the internet
		// "how do I do a deep copy of a 2d array in Java," be sure to cite
		// your source.
		// Note that if we used a 1-dimensional array to store our arrays,
		// we could simply use Arrays.copyOf directly without this helper
		// method.
		// Do not ask about this on Discord. You can do this on your own. :)
		return null;
	}
	
	// This method is implemented for you. You need to figure out how it is useful.
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