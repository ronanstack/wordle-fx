package com.javagame.wordlefx.view;

import com.javagame.wordlefx.controller.Controller;
import com.javagame.wordlefx.controller.ControllerImpl;
import com.javagame.wordlefx.model.Model;
import com.javagame.wordlefx.model.ModelImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {

    @Override
    public void start(Stage stage) {

        // MVC
        Model model = new ModelImpl();
        Controller controller = new ControllerImpl(model);
        FXComponent view = new View(controller, model);

        // scene
        Scene scene = new Scene(view.render(), 800, 600);
        scene.getStylesheets().add("main.css");
        stage.setScene(scene);

        // stage
        stage.setTitle("Wordle-fx");
        stage.show();
    }
}
