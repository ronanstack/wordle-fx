package com.javagame.wordlefx.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
    private List<ModelObserver> observers;

    public ModelImpl() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(ModelObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        this.observers.remove(observer);
    }

    private void notifyObservers() {
        for (ModelObserver observer : this.observers) {
            observer.update(this);
        }
    }
}
