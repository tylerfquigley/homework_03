package com.example.homework_03;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Player extends GameObject implements inputHandler{
    private boolean right=false;
    private  boolean left=false;
    private  boolean up=false;
    private boolean down=false;
    private int speed=3;
    @Override
    void toDo() {
        setVsp(0);
        setHsp(0);
        if (right&&!left){setHsp(speed);}
        if (!right&&left){setHsp(-speed);}
        if (up&&!down){setVsp(-speed);}
        if (!up&&down){setVsp(speed);}
        setX(getX()+getHsp());
        setY(getY()+getVsp());
    }

    @Override
    public void setInputs(KeyEvent e) {
        switch (e.getCode()) {
            case W -> up=true;
            case D -> right=true;
            case S -> down=true;
            case A -> left=true;

        }
    }

    @Override
    public void unSetInputs(KeyEvent e) {
        switch (e.getCode()){
            case W -> up=false;
            case D -> right=false;
            case S -> down=false;
            case A -> left=false;
        }
    }

    @Override
    public void bindToScene(Scene scene) {
        scene.setOnKeyReleased(this::unSetInputs);
        scene.setOnKeyPressed(this::setInputs);
    }

}
