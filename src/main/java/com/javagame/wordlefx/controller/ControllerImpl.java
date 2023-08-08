package com.javagame.wordlefx.controller;

import com.javagame.wordlefx.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerImpl implements Controller {
    private Model model;

    public ControllerImpl(Model model) {
        this.model = model;
    }

//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}