package com.javagame.wordlefx.view;

import com.javagame.wordlefx.controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class Board implements FXComponent {
    private Controller controller;
    private GridPane grid;

    public Board(Controller controller) {

        this.controller = controller;
        this.grid = new GridPane();
    }

    @Override
    public Parent render() {
        // Make a 5x6 grid of squares
        int[] currentGrid = {0, 0};

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                Rectangle square = new Rectangle(60, 60);
                square.setFill(Color.WHITE);
                square.setStroke(Color.BLACK);

                Label letter = new Label("");
                StackPane stack = new StackPane(square, letter);
                stack.setFocusTraversable(true);

                stack.setOnKeyPressed(
                        (KeyEvent press) -> {
                            KeyCode keyCode = press.getCode();
                            if (keyCode.isLetterKey()) {
                                if (keyCode == KeyCode.SPACE) {
                                    throw new IllegalArgumentException("Space is not a valid input");
                                } else {
                                    System.out.println(keyCode);
                                    if (letter.getText().equals("")) {
                                        letter.setText(keyCode.toString());
                                    }

                                    // this.controller.pressKey(keyCode);

                                    if (currentGrid[0] < 5) {
                                        currentGrid[0]++;
                                    }
                                    if (currentGrid[1] < 6 && currentGrid[0] < 5) {
                                        StackPane nextStack = (StackPane) this.grid.getChildren().get(currentGrid[1] * 5 + currentGrid[0]);
                                        nextStack.requestFocus();
                                    }
                                }
                            } else if (keyCode == KeyCode.BACK_SPACE) {
                                if (currentGrid[0] > 0 || currentGrid[1] > 0) {
                                    currentGrid[0]--;
                                    if (currentGrid[0] < 0) {
                                        currentGrid[0] = 4;
                                        currentGrid[1]--;
                                    }
                                    StackPane prevStack = (StackPane) this.grid.getChildren().get(currentGrid[1] * 5 + currentGrid[0]);
                                    Label prevLetter = (Label) prevStack.getChildren().get(1);
                                    prevLetter.setText("");
                                    prevStack.requestFocus();
                                }
                            } else if (keyCode == KeyCode.ENTER) {
                                List<String> word = new ArrayList<>();
                                for (int i = 0; i < 5; i++) {
                                    StackPane stackPane = (StackPane) this.grid.getChildren().get(currentGrid[1] * 5 + i);
                                    Label label = (Label) stackPane.getChildren().get(1);
                                    word.add(label.getText());
                                }

                                System.out.println("Submitted: " + word);
                                List<Color> tileColors = this.controller.submitWord(word);
                                for (int i = 0; i < 5; i++) {
                                    StackPane stackPane = (StackPane) this.grid.getChildren().get(currentGrid[1] * 5 + i);
                                    Rectangle rect = (Rectangle) stackPane.getChildren().get(0);
                                    rect.setFill(tileColors.get(i));
                                }
                                if (!tileColors.contains(Color.YELLOW) && !tileColors.contains(Color.DARKGRAY)) {
                                    return;
                                }

                                if (currentGrid[1] == 5) {
                                    return;
                                }
                                if (currentGrid[0] == 5) {
                                    currentGrid[0] = 0;
                                    currentGrid[1]++;
                                    if (currentGrid[1] >= 6) {
                                        return;
                                    }
                                } else {
                                    throw new IllegalArgumentException("Row is not full");
                                }
                                StackPane nextStack = (StackPane) this.grid.getChildren().get(currentGrid[1] * 5 + currentGrid[0]);
                                nextStack.requestFocus();
                            } else {
                                throw new IllegalArgumentException("Key pressed is not a letter");
                            }

                        });

                this.grid.add(stack, col, row);
                if (currentGrid[0] == 0 && currentGrid[1] == 0) {
                    stack.requestFocus();
                }
            }
        }

        return this.grid;
    }

}
