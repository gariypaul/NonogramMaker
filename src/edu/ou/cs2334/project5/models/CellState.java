package edu.ou.cs2334.project5.models;

public enum CellState {

	EMPTY,
	FILLED,
	MARKED;
	
	public static boolean toBoolean (CellState state) {
		
		return (state.equals(CellState.FILLED)) ? true : false ;
	}
	
}
