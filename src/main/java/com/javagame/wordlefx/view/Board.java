package com.javagame.wordlefx.view;

import com.javagame.wordlefx.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Board implements FXComponent {
    private Controller controller;

    public Board(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        GridPane grid = new GridPane();

        // Make a 5x6 grid of squares
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 6; col++) {
                Rectangle square = new Rectangle(60, 60);
                square.setFill(Color.WHITE);
                square.setStroke(Color.BLACK);
                grid.add(square, row, col);
            }
        }

        return grid;
    }
}
