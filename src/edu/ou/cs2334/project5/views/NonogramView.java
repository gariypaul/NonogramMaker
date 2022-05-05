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
	
}
