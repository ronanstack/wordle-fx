package com.javagame.wordlefx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModelImpl implements Model {
    private List<ModelObserver> observers;
    private List<String> letters;
    private List<String> words;

    public ModelImpl() {
        this.observers = new ArrayList<>();
        this.letters = new ArrayList<>();
        this.words = new ArrayList<>();
        // TODO: make txt file with words; add words to list
        this.words.add("apple"); // test words
        this.words.add("smile");
        this.generateWord();
        System.out.println(this.letters);
    }

    public void addLetter(String letter) {
        // notifyObservers();
    }

    public void generateWord() {
        letters.clear();
        String word = this.words.get(new Random().nextInt(words.size()));
        for (int i = 0; i < word.length(); i++) {
            letters.add(String.valueOf(word.charAt(i)));
        }
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
