module com.javagame.wordlefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.javagame.wordlefx to javafx.fxml;
    exports com.javagame.wordlefx;
    exports com.javagame.wordlefx.controller;
    opens com.javagame.wordlefx.controller to javafx.fxml;
    exports com.javagame.wordlefx.view;
    opens com.javagame.wordlefx.view to javafx.graphics;
}