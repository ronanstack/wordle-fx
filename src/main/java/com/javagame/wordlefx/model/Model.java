package com.javagame.wordlefx.model;

public interface Model {

    void addObserver(ModelObserver observer);

    void removeObserver(ModelObserver observer);

}
