package com.javagame.wordlefx.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.List;

public interface Controller {

    /** Handles addition of a letter */
    void addLetter(String letter);

    /** Handles deletion of a letter */
    void delLetter();

    /** Handles submission of a word */
    List<Color> submitWord(List<String> word);

    /** Returns the puzzle solution */
    String getWord();

    /** Returns the current grid index */
    int[] getCurrentGrid();

}
