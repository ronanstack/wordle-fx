package com.javagame.wordlefx.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.List;

public interface Controller {

//    void pressKey(KeyCode keyCode);
//
//    void clickLetter(String letter);

    List<Color> submitWord(List<String> word);

    String getWord();

}
