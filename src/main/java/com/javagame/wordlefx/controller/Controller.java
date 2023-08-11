package com.javagame.wordlefx.controller;

import javafx.scene.input.KeyCode;

import java.util.List;

public interface Controller {

    void pressKey(KeyCode keyCode);

    void clickLetter(String letter);

    boolean submitWord(List<String> word);

}
