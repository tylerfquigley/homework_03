package com.example.homework_03;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MazeApp extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MazeApp.class.getResource("hello-view.fxml"));
        Controller controller = new Controller();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Maze");
        stage.setScene(scene);
        stage.show();
        Player p = new Player();
        p.bindToScene(scene);
        p.loadSprite("robot.png");
        p.setMaze(new Image("maze.png"));
        controller.addToWorld(p);
        controller.startRendering();
       stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                controller.stopRendering();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }


}