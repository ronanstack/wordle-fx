package com.javagame.wordlefx.model;

import javafx.scene.paint.Color;

import java.util.List;

public interface Model {

    /** Adds a letter to the next empty slot in the grid */
    void addLetter(String letter);

    /** Deletes the last letter in the grid */
    void delLetter();

    /** Returns the letter at the given row and column */
    String getGridIndex(int row, int col);

    /** Adds an observer to the model */
    void addObserver(ModelObserver observer);

    /** Removes an observer from the model */
    void removeObserver(ModelObserver observer);

    /** Compares the submitted word with the solution word.
     * Returns a list of colors corresponding to each letter
     * where green indicates a match, yellow indicates a letter
     * in the wrong spot, and gray indicates a wrong letter. */
    List<Color> checkWord(List<String> word);

    /** Returns the solution word */
    String getWord();

    /** Returns the current grid */
    int[] getCurrentGrid();

}
