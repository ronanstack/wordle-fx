package com.javagame.wordlefx.view;

import com.javagame.wordlefx.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Controls implements FXComponent {
    private Controller controller;

    public Controls(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        HBox layout = new HBox();

        Button reset = new Button("Reset"); // TODO: change text for arrow symbol
        Button playAgain = new Button("Play Again"); // TODO: change text for next symbol

        layout.getChildren().addAll(reset, playAgain);

        return layout;
    }
}
