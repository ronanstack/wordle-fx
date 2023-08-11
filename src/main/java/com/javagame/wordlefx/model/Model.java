package com.javagame.wordlefx.model;

public interface Model {

    void addLetter(String letter);

    void addObserver(ModelObserver observer);

    void removeObserver(ModelObserver observer);

}
