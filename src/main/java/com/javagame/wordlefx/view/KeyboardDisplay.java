package com.javagame.wordlefx.view;

import com.javagame.wordlefx.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

public class KeyboardDisplay implements FXComponent {
    private Controller controller;

    public KeyboardDisplay(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        VBox keyboard = new VBox();

        HBox upperRow = new HBox();
        HBox middleRow = new HBox();
        HBox lowerRow = new HBox();

        for (int i = 0; i < 26; i++) {
            char letter = (char) (i + 65);
            Button letterButton = new Button();
            letterButton.setText(String.valueOf(letter));
            letterButton.setOnAction(
                    (ActionEvent click) -> {
                        // TODO: button clicks to operate the same as keys
                        this.controller.clickLetter(letterButton.getText());
                    });
            if (i < 10) {
                upperRow.getChildren().add(letterButton);
            } else if (i < 19) {
                middleRow.getChildren().add(letterButton);
            } else {
                lowerRow.getChildren().add(letterButton);
            }
        }

        keyboard.getChildren().add(upperRow);
        keyboard.getChildren().add(middleRow);
        keyboard.getChildren().add(lowerRow);

        return keyboard;
    }
}
