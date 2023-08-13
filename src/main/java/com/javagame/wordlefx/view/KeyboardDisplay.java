package com.javagame.wordlefx.view;

import com.javagame.wordlefx.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

public class KeyboardDisplay implements FXComponent {
    private Controller controller;
    private Board board;

    public KeyboardDisplay(Controller controller, Board board) {
        this.controller = controller;
        this.board = board;
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

            this.addClickHandlers(letterButton);

            if (i < 10) {
                upperRow.getChildren().add(letterButton);
            } else if (i < 19) {
                middleRow.getChildren().add(letterButton);
            } else {
                lowerRow.getChildren().add(letterButton);
            }
        }

        upperRow.getStyleClass().add("upper-row");
        keyboard.getChildren().add(upperRow);
        middleRow.getStyleClass().add("middle-row");
        keyboard.getChildren().add(middleRow);
        lowerRow.getStyleClass().add("lower-row");
        keyboard.getChildren().add(lowerRow);

        return keyboard;
    }

    /** Adds a click handler to the given button.
     * On click, the button adds its corresponding letter
     * to the board. */
    private void addClickHandlers(Button button) {
        button.setOnAction(
                (ActionEvent click) -> {
                    System.out.println("Clicked " + button.getText());
                    this.controller.addLetter(button.getText());
                    this.board.focusNext();
                });
    }

}
