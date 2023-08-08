package com.javagame.wordlefx.view;

import com.javagame.wordlefx.controller.Controller;
import com.javagame.wordlefx.model.Model;
import com.javagame.wordlefx.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class View implements FXComponent, ModelObserver {
    private Controller controller;
    private Model model;
    private VBox layout;

    public View(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Model model) {
        this.model = model;
        this.layout.getChildren().setAll(this.render());
    }

    @Override
    public Parent render() {
        this.layout = new VBox();

        Header header = new Header(this.controller);
        Board board = new Board(this.controller);
        KeyboardDisplay keyboardDisplay = new KeyboardDisplay(this.controller);
        Controls controls = new Controls(this.controller);

        layout.getChildren().add(header.render());
        layout.getChildren().add(board.render());
        layout.getChildren().add(keyboardDisplay.render());
        layout.getChildren().add(controls.render());

        return layout;
    }
}
