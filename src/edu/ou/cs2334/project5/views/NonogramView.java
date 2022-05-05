package edu.ou.cs2334.project5.views;

import java.util.Collections;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.views.clues.*;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class NonogramView extends BorderPane{
	private final static String STYLE_CLASS="nonogram-view";
	private final static String SOLVED_STYLE_CLASS="nonogram-view-solved";
	private LeftCluesView leftCluesView;
	private TopCluesView topCluesView;
	private CellGridView cellGridView;
	private HBox bottomHBox;
	private Button loadBtn;
	private Button resetBtn;
	
	public NonogramView() {
		this.getStyleClass().add(STYLE_CLASS);
	}
	
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
	
	public CellView getCellView(int rowIdx, int colIdx) {
		return cellGridView.getCellView(rowIdx, colIdx);
	}
	
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		cellGridView.setCellState(rowIdx, colIdx, state);
	}
	
}
