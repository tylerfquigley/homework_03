package com.example.homework_03;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyCode.W;

public interface inputHandler {

    void setInputs(KeyEvent e);
    void unSetInputs(KeyEvent e);

    void bindToScene(Scene scene);
}
