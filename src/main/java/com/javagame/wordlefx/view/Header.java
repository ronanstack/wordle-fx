package com.javagame.wordlefx.view;

import com.javagame.wordlefx.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Header implements FXComponent {
    private Controller controller;

    public Header(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        HBox layout = new HBox();

        layout.getStyleClass().add("header-layout");
        Text text = new Text("Wordle-fx");
        layout.getChildren().add(text);

        return layout;
    }
}
