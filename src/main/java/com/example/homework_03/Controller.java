package com.example.homework_03;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller implements Runnable,inputHandler{
    private static boolean up;
    private static boolean down;

    private static boolean left;

    private static boolean right;
    private boolean r =true;
    private Scene s;

    private int frameRate=60;
    public GraphicsContext gc;

    public GraphicsContext gc2;
    private Thread t1;
    private Image maze1;
    private  Image maze2;
    private AnimationTimer a1;
    private ArrayList<GameObject> world;
    private static int tabNumber=1;

    @FXML
    Tab tab1;
    @FXML
    Tab tab2;
    @FXML
    TabPane tabPane;
    @FXML
    Canvas can;
    @FXML
    Canvas can2;
    @FXML
    public void initialize(){
        gc = can.getGraphicsContext2D();
        gc2 = can2.getGraphicsContext2D();
        maze1 = new Image("maze.png");
        maze2= new Image("maze2.png");
        world = new ArrayList<GameObject>();
        //check for tab change and rrequest focus
        tabPane.getSelectionModel().selectedItemProperty().addListener(((observableValue, ov, nv) -> {
                if(nv.equals(tab1)){
                    tabNumber=1;

                }
            if(nv.equals(tab2)){
                tabNumber=2;

            }
        }));
    }

    public void startRendering(){

        a1 = new AnimationTimer() {
            @Override
            public void handle(long now) {

                //first maze
                gc.clearRect(0,0,can.getWidth(),can.getHeight());
                gc.drawImage(maze1,0,0);
                //second maze
                gc2.clearRect(0,0,can2.getWidth(),can2.getHeight());
                gc2.drawImage(maze2,0,0);
                for(int i=0;i<world.size();i++){world.get(i).draw();}
            }
        };
        a1.start();
        //start backend logic when rending begins
       t1 = new Thread(this);
        t1.start();
    }

    public void stopRendering(){
        if (a1!=null){a1.stop();}
        if (t1!=null){
            t1.stop();
        }
    }
    public void addToWorld(GameObject gameObject){
        world.add(gameObject);
    }
    @Override
    //backend logic
    public void run() {

        long c = System.currentTimeMillis();
        long s = System.currentTimeMillis();
        while(r){
            c=System.currentTimeMillis();
            if((c-s)>(1000/frameRate)){
                c = System.currentTimeMillis();
                s = System.currentTimeMillis();
                iterateThroughAll();

            }
        }
    }
    private void iterateThroughAll(){
        for (int i = 0;i<world.size();i++){
            world.get(i).toDo();
        }
    }
    @Override
    public void setInputs(KeyEvent e) {
        switch (e.getCode()) {
            case W -> up=true;
            case D -> right=true;
            case S -> down=true;
            case A -> left=true;
            case UP -> up=true;
            case RIGHT -> right=true;
            case DOWN -> down=true;
            case LEFT -> left=true;

        }
    }

    @Override
    public void unSetInputs(KeyEvent e) {
        switch (e.getCode()){
            case W -> up=false;
            case D -> right=false;
            case S -> down=false;
            case A -> left=false;
            case UP -> up=false;
            case RIGHT -> right=false;
            case DOWN -> down=false;
            case LEFT -> left=false;
        }
    }

    @Override
    public void bindToScene(Scene scene) {
        scene.setOnKeyReleased(this::unSetInputs);
        scene.setOnKeyPressed(this::setInputs);
    }
    public static boolean isRight(){
        return right;
    };
    public static boolean isLeft(){
        return left;
    };
    public static boolean isUp(){
        return up;
    };
    public static boolean isDown(){
        return down;
    };
    public static int currentTab(){
        return tabNumber;
    }
}