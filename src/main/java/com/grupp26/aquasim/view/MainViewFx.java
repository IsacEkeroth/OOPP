package com.grupp26.aquasim.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class MainViewFx extends Application {
    private static com.grupp26.aquasim.model.ModelFacade ModelFacade;

    public static void setAquarium(com.grupp26.aquasim.model.ModelFacade Facade) {
        ModelFacade = Facade;
    }

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                ModelFacade.getEntities();
            }
        }.start();

    }

    public static void main(String[] args) {
        new Thread(() -> launch(args)).start();
    }

}