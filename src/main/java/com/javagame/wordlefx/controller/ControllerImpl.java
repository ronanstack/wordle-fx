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

    @Override
    public void addLetter(String letter) {
        this.model.addLetter(letter);
    }

    @Override
    public void delLetter() {
        this.model.delLetter();
    }

    @Override
    public String getLetter() {
        return this.model.getNextLetter();
    }

    @Override
    public List<Color> submitWord(List<String> word) {
        return this.model.checkWord(word);
    }

    @Override
    public String getWord() {
        return this.model.getWord();
    }

    @Override
    public int[] getCurrentGrid() {
        return this.model.getCurrentGrid();
    }

}