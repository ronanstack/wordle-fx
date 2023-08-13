package com.javagame.wordlefx.model;

import javafx.scene.paint.Color;

import java.util.List;

public interface Model {

    void addLetter(String letter);

    void delLetter();

    String getNextLetter();

    String getGridIndex(int row, int col);

    void addObserver(ModelObserver observer);

    void removeObserver(ModelObserver observer);

    List<Color> checkWord(List<String> word);

    String getWord();

    int[] getCurrentGrid();

}
