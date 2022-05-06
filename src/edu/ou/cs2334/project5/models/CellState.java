package edu.ou.cs2334.project5.models;
/**
 * This class is an enumeration of the States that cells can be in in the Nonorgram
 * @author gariy
 *
 */
public enum CellState {
	/**
	 * This is an empty Cellstate
	 */
	EMPTY,
	/**
	 *This is a filled CellState 
	 */
	FILLED,
	/**
	 * This is a marked CellState
	 */
	MARKED;
	/**
	 * This method converts the states to boolean values
	 * @param state this is the state to be converted into boolean form 
	 * @return this is the boolean value of the State
	 */
	public static boolean toBoolean (CellState state) {
		
		return (state.equals(CellState.FILLED)) ? true : false ;
	}
	
}
