package com.javagame.wordlefx.view;

import com.javagame.wordlefx.controller.Controller;
import com.javagame.wordlefx.model.Model;
import com.javagame.wordlefx.model.ModelObserver;
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


public class Board implements FXComponent, ModelObserver {
    private Controller controller;
    private VBox layout;
    private Model model;

    public Board(Controller controller, Model model) {
        this.controller = controller;
        this.layout = new VBox();
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public Parent render() {
        GridPane grid = new GridPane();
        grid.setMaxHeight(360);
        grid.setMaxWidth(300);
        int[] currentGrid = this.controller.getCurrentGrid();

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                Rectangle square = new Rectangle(60, 60);
                square.setFill(Color.WHITE);
                square.setStroke(Color.BLACK);

                Label letter = new Label("");
                StackPane stack = new StackPane(square, letter);
                stack.setFocusTraversable(true);

                this.addKeyHandlers(stack, grid, currentGrid);

                grid.add(stack, col, row);
                if (currentGrid[0] == 0 && currentGrid[1] == 0) {
                    stack.requestFocus();
                }
            }
        }
        this.layout.getStyleClass().add("board");
        this.layout.getChildren().add(grid);

        return this.layout;
    }

    private void addKeyHandlers(StackPane stack, GridPane grid, int[] currentGrid) {
        Label letter = (Label) stack.getChildren().get(1);
        stack.setOnKeyPressed(
                (KeyEvent press) -> {
                    KeyCode keyCode = press.getCode();
                    if (keyCode.isLetterKey()) {
                        if (keyCode == KeyCode.SPACE) {
                            throw new IllegalArgumentException("Space is not a valid input");
                        } else {
                            System.out.println(keyCode);
                            if (letter.getText().equals("")) {
                                this.controller.addLetter(keyCode.toString());
                            }

                            if (currentGrid[1] < 6 && currentGrid[0] < 5) {
                                this.focusNext();
                            }
                        }
                    } else if (keyCode == KeyCode.BACK_SPACE) {
                        if (currentGrid[0] > 0) {
                            this.controller.delLetter();
                            StackPane prevStack = (StackPane) grid.getChildren().get(currentGrid[1] * 5 + currentGrid[0]);
                            prevStack.requestFocus();
                        }
                    } else if (keyCode == KeyCode.ENTER) {
                        List<String> word = new ArrayList<>();
                        for (int i = 0; i < 5; i++) {
                            StackPane stackPane = (StackPane) grid.getChildren().get(currentGrid[1] * 5 + i);
                            Label label = (Label) stackPane.getChildren().get(1);
                            if (label.getText().equals("")) {
                                throw new IllegalArgumentException("Row is not full");
                            }
                            word.add(label.getText());
                        }

                        System.out.println("Submitted: " + word);
                        List<Color> tileColors = this.controller.submitWord(word);
                        for (int i = 0; i < 5; i++) {
                            StackPane stackPane = (StackPane) grid.getChildren().get(currentGrid[1] * 5 + i);
                            Rectangle rect = (Rectangle) stackPane.getChildren().get(0);
                            rect.setFill(tileColors.get(i));
                        }
                        if (!tileColors.contains(Color.YELLOW) && !tileColors.contains(Color.DARKGRAY)) {
                            this.displayEndScreen(1, currentGrid[1]+1);
                            return;
                        }

                        if (currentGrid[1] == 5) {
                            this.displayEndScreen(0, currentGrid[1]+1);
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
                        this.focusNext();
                    } else {
                        throw new IllegalArgumentException("Key pressed is not a letter");
                    }

                });
    }

    private void displayEndScreen(int result, int guesses) {
        VBox endScreen = new VBox();
        endScreen.setMinHeight(360);
        endScreen.setMinWidth(300);
        Label endText = new Label();
        if (result == 1) {
            endText.setText("You won in " + guesses + " guesses!");
            endScreen.setStyle("-fx-border-color: green;");
        } else {
            endText.setText("You lost. The word was " + this.controller.getWord() + ".");
            endScreen.setStyle("-fx-border-color: red;");
        }
        endText.getStyleClass().add("end-text");
        endScreen.getChildren().add(endText);

        this.layout.getChildren().remove(0);
        this.layout.getChildren().add(endScreen);
    }

    public void focusNext() {
        GridPane grid = (GridPane) this.layout.getChildren().get(0);

        for (int i = 0; i < grid.getChildren().size(); i++) {
            StackPane stack = (StackPane) grid.getChildren().get(i);
            Label letter = (Label) stack.getChildren().get(1);

            if (letter.getText().equals("")) {
                stack.requestFocus();
                return;
            }
        }
    }

    @Override
    public void update(Model model) {
        this.model = model;
        GridPane grid = (GridPane) this.layout.getChildren().get(0);

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                StackPane stack = (StackPane) grid.getChildren().get(row * 5 + col);
                Label letter = (Label) stack.getChildren().get(1);

                String text = model.getGridIndex(row, col);
                letter.setText(text);
            }
        }
    }

}
