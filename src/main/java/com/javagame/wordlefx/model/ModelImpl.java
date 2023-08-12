package com.javagame.wordlefx.model;

import javafx.scene.paint.Color;

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
        this.words.add("hello");
        this.words.add("world");
        this.generateWord();
        System.out.println(this.letters);
    }

//    public void addLetter(String letter) {
//        // notifyObservers();
//    }

    public void generateWord() {
        letters.clear();
        String word = this.words.get(new Random().nextInt(words.size()));
        for (int i = 0; i < word.length(); i++) {
            letters.add(String.valueOf(word.charAt(i)));
        }
    }

    public List<Color> checkWord(List<String> word) {
        List<Color> tileColors = new ArrayList<>();
        for (int i = 0; i < word.size(); i++) {
            String lowerLetter = word.get(i).toLowerCase();
            if (lowerLetter.equals(letters.get(i))) {
                tileColors.add(Color.GREEN);
            } else if (letters.contains(lowerLetter)) {
                tileColors.add(Color.YELLOW);
            } else {
                tileColors.add(Color.DARKGRAY);
            }
        }
        if (!tileColors.contains(Color.YELLOW) && !tileColors.contains(Color.DARKGRAY)) {
            System.out.println("Word found");
        } else {
            System.out.println("Submission incorrect");
        }
        return tileColors;
    }

    @Override
    public void addObserver(ModelObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        this.observers.remove(observer);
    }

//    private void notifyObservers() {
//        for (ModelObserver observer : this.observers) {
//            observer.update(this);
//        }
//    }
}
