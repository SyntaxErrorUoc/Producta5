package com.onlinestore.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 3124131 (update to juan)


public class GestionOS extends Application {

    public Stage currentStage;
    public Scene scene;
    public Parent root;
<<<<<<< HEAD

=======
    private List<Stage> openStages = new ArrayList<>();
>>>>>>> 3124131 (update to juan)
    @Override
    public void start(Stage stage) throws IOException {
        // set up the scene
        try{
            this.root = FXMLLoader.load(getClass().getResource("/menuinicial.fxml"));
            Scene scene = new Scene(this.root);
            Image icon = new Image("logo.jpeg");
<<<<<<< HEAD
=======
            openStages.add(stage);
>>>>>>> 3124131 (update to juan)
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
<<<<<<< HEAD

=======
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
>>>>>>> 3124131 (update to juan)
    public void cerrarventana(Stage stage){
        stage.close();
    }

    public static void main(String[] args) {
<<<<<<< HEAD
        launch();
=======
        launch(args);
>>>>>>> 3124131 (update to juan)
    }
}