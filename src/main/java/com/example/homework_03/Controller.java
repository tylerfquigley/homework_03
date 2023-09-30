package com.example.homework_03;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

public class Controller implements Runnable{
    private boolean r =true;
    private Scene s;
    private boolean up=false;
    private boolean down=false;
    private boolean left=false;
    private boolean right=false;
    private int frameRate=60;
    private GraphicsContext gc;
    private Thread t1;
    private Image maze1;
    private AnimationTimer a1;
    private ArrayList<GameObject> world;
    @FXML
    Canvas can;

    @FXML
    public void initialize(){
        gc = can.getGraphicsContext2D();
        maze1 = new Image("maze.png");
        world = new ArrayList<GameObject>();
    }

    public void startRendering(){
        a1 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0,0,can.getWidth(),can.getHeight());
                gc.drawImage(maze1,0,0);
                for(int i=0;i<world.size();i++){world.get(i).draw(gc);}
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
}