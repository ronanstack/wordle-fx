package com.javagame.wordlefx.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.List;

public interface Controller {

    void addLetter(String letter);

    void delLetter();

    String getLetter();

    List<Color> submitWord(List<String> word);

    String getWord();

    int[] getCurrentGrid();

}
