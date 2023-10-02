package com.example.homework_03;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUp{




    public void popUp() {
        Stage s = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MazeApp.class.getResource("popup.fxml"));
        fxmlLoader.setController(this);
        try {
            Scene scene = new Scene(fxmlLoader.load());
            s.setScene(scene);
            s.show();
        }
        catch (IOException ioException){
            System.out.println("could not load scene");
        }
    }
}
