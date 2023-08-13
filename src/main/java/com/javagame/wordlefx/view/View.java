package com.javagame.wordlefx.view;

import com.javagame.wordlefx.controller.Controller;
import com.javagame.wordlefx.model.Model;
import com.javagame.wordlefx.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
    private Controller controller;
    private VBox layout;
    private Model model;

    public View(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;
    }

    @Override
    public Parent render() {
        this.layout = new VBox();
        // this.layout.getStyleClass().add("view");

        Header header = new Header(this.controller); // Header text
        Board board = new Board(this.controller, this.model); // Game board
        KeyboardDisplay keyboardDisplay = new KeyboardDisplay(this.controller, board); // On-screen keyboard

        this.layout.getChildren().add(header.render());
        this.layout.getChildren().add(board.render());
        this.layout.getChildren().add(keyboardDisplay.render());

        return this.layout;
    }
}
