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
    private String[][] grid;
    private int[] currentGrid;

    public ModelImpl() {
        this.observers = new ArrayList<>();
        this.letters = new ArrayList<>();
        this.words = new ArrayList<>();
        this.grid = new String[6][5];
        this.currentGrid = new int[]{0, 0};

        this.generateGrid();
        this.generateWordsList();
        this.generateWord();
        System.out.println(this.letters);
    }

    @Override
    public void addLetter(String letter) {
        for (int row = 0; row < this.grid.length; row++) {
            for (int col = 0; col < this.grid[row].length; col++) {
                if (this.grid[row][col].equals("")) {
                    this.grid[row][col] = letter;

                    this.updateCurrentGrid(0);

                    this.notifyObservers();
                    return;
                }
            }
        }
    }

    @Override
    public void delLetter() {
        for (int row = this.grid.length - 1; row >= 0; row--) {
            for (int col = this.grid[row].length - 1; col >= 0; col--) {
                if (!this.grid[row][col].equals("")) {
                    this.grid[row][col] = "";
                    this.updateCurrentGrid(1);
                    this.notifyObservers();
                    return;
                } else if (row == 0 && col == 0) {
                    throw new IllegalArgumentException("No letters to delete");
                }
            }
        }
    }

    /** Updates the current grid index */
    private void updateCurrentGrid(int i) {
        if (i == 0) {
            if (this.currentGrid[0] < 5) {
                this.currentGrid[0]++;
            }
        } else if (i == 1) {
            this.currentGrid[0]--;
        }
    }

    @Override
    public String getGridIndex(int row, int col) {
        if (row < 0 || row > 5 || col < 0 || col > 4) {
            throw new IllegalArgumentException("Invalid grid index");
        } else {
            return this.grid[row][col];
        }
    }

    /** Generates a new grid */
    private void generateGrid() {
        for (String[] strings : this.grid) {
            Arrays.fill(strings, "");
        }
    }

    /** Retrieves the list of words from words.txt */
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

    /** Generates a new solution word */
    private void generateWord() {
        letters.clear();
        String word = this.words.get(new Random().nextInt(words.size()));
        for (int i = 0; i < word.length(); i++) {
            letters.add(String.valueOf(word.charAt(i)));
        }
    }

    @Override
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
    public String getWord() {
        String word = "";
        for (String letter : this.letters) {
            word = word.concat(letter);
        }
        return word;
    }

    @Override
    public int[] getCurrentGrid() {
        return this.currentGrid;
    }

    @Override
    public void addObserver(ModelObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        this.observers.remove(observer);
    }

    /** Updates all observers */
    private void notifyObservers() {
        for (ModelObserver observer : this.observers) {
            observer.update(this);
        }
    }
}
