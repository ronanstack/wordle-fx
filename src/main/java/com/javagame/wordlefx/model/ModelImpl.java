package com.javagame.wordlefx.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;

public class ModelImpl implements Model {
    private List<ModelObserver> observers;
    private List<String> letters;
    private List<String> words;

    public ModelImpl() {
        this.observers = new ArrayList<>();
        this.letters = new ArrayList<>();
        this.words = new ArrayList<>();

        this.generateWordsList();
        this.generateWord();
        System.out.println(this.letters);
    }

//    public void addLetter(String letter) {
//        // notifyObservers();
//    }

    private void generateWordsList() {
        String file = "src/main/resources/com/javagame/wordlefx/words.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            String[] words = line.split(", ");
            this.words.addAll(Arrays.asList(words));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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

    public String getWord() {
        String word = "";
        for (String letter : this.letters) {
            word = word.concat(letter);
        }
        return word;
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
