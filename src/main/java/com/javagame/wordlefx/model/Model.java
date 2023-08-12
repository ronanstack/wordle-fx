package com.javagame.wordlefx.model;

import javafx.scene.paint.Color;

import java.util.List;

public interface Model {

//    void addLetter(String letter);

    void addObserver(ModelObserver observer);

    void removeObserver(ModelObserver observer);

    List<Color> checkWord(List<String> word);

}
