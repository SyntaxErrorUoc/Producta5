package com.onlinestore.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GestionOS extends Application {

    public Stage currentStage;
    public Scene scene;
    public Parent root;
    private List<Stage> openStages = new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException {
        // set up the scene
        try{
            this.root = FXMLLoader.load(getClass().getResource("/login.fxml"));
            Scene scene = new Scene(this.root);
            Image icon = new Image("logo.jpeg");
            openStages.add(stage);
            stage.getIcons().add(icon);
            stage.resizableProperty().setValue(true);
            stage.setMaximized(false);
            stage.setTitle("OnlineStore");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void openNewForm() {
        // Cierra todas las ventanas abiertas
        for (Stage stage : openStages) {
            stage.close();
        }
        openStages.clear();

        // Abre el nuevo formulario
        Stage newStage = new Stage();
        // Configura y muestra tu nuevo formulario
        // ...

        // Añade el nuevo formulario a la lista de ventanas abiertas
        openStages.add(newStage);
    }
    public void cerrarventana(Stage stage){
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}