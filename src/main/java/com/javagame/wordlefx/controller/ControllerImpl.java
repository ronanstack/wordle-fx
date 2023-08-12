package com.javagame.wordlefx.controller;

import com.javagame.wordlefx.model.Model;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.List;

public class ControllerImpl implements Controller {
    private Model model;

    public ControllerImpl(Model model) {
        this.model = model;
    }

//    @Override
//    public void pressKey(KeyCode keyCode) {
//        this.model.addLetter(keyCode.getChar());
//    }
//
//    @Override
//    public void clickLetter(String letter) {
//        this.model.addLetter(letter);
//    }

    @Override
    public List<Color> submitWord(List<String> word) {
        return this.model.checkWord(word);
    }

    @Override
    public String getWord() {
        return this.model.getWord();
    }

}