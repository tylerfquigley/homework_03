package com.example.homework_03;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
        //player maze 1
        Player p = new Player();
        p.loadSprite("robot.png");
        p.setMaze(new Image("maze.png"));
        p.setGraphicsContext(controller.gc);
        p.setTabNumber(1);
        controller.addToWorld(p);
        //player maze 2
        Player p2 = new Player();
        p2.loadSprite("car.png");
        p2.setMaze(new Image("maze2.png"));
        p2.setGraphicsContext(controller.gc2);
        controller.addToWorld(p2);
        p2.setTabNumber(2);
        //begin animation and thread
        controller.startRendering();

        controller.bindToScene(scene);
       stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                controller.stopRendering();
            }
        });
    }
    public void focusCanvas(Canvas canvas){
        canvas.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }


}