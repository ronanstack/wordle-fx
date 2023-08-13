package com.javagame.wordlefx;

import com.javagame.wordlefx.view.AppLauncher;
import javafx.application.Application;

public class Main {
    /* The puzzle game Wordle (minus the once-per-day restriction) recreated in JavaFX with an MVC design. */

    // Future improvements: add validation for whether submitted words are real

    public static void main(String[] args) {
        Application.launch(AppLauncher.class);
    }
}