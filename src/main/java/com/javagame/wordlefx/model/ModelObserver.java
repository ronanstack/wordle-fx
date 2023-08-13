package com.javagame.wordlefx.model;

public interface ModelObserver {
    /** When a model value is changed, the model updates all active ModelObserver objects */
    void update(Model model);
}
