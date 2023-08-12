package com.javagame.wordlefx.view;

import com.javagame.wordlefx.controller.Controller;
import javafx.event.ActionEvent;
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

        Button reset = new Button("Reset"); // TODO: change text for symbol
        reset.setOnAction(
                (ActionEvent click) -> {
                    // this.controller.reset();
                });

        Button playAgain = new Button("Play Again"); // TODO: change text for symbol
        playAgain.setOnAction(
                (ActionEvent click) -> {
                    // this.controller.playAgain();
                });

        Button giveUp = new Button("Give Up"); // TODO: change text for symbol
        giveUp.setOnAction(
                (ActionEvent click) -> {
                    // this.controller.giveUp();
                });

        layout.getChildren().addAll(reset, playAgain, giveUp);

        return layout;
    }
}
