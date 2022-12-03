package com.example.seniorproject1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;

import java.io.IOException;

public class Main extends Application {


    private static Scene scene;
    public static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login1"), 830, 593);
        stage.setScene(scene);
        stage.show();
        mainStage = stage;
    }

    static void     setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }


}