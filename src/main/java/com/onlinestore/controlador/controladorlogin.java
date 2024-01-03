package com.onlinestore.controlador;

import com.onlinestore.modelo.Datos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.onlinestore.modelo.Usuario;

public class controladorlogin implements Initializable {

    @FXML
    private Button btnGit;

    @FXML
    private Button btnIniciar;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsuario;

    private Datos datos;
    public Stage stage = new Stage();
    public Scene scene;
    public Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            datos = new Datos();
        } catch (Exception e) {
            // Manejar excepción, como mostrar un mensaje de error
            e.printStackTrace();
        }
    }

    @FXML
    void clkIniciar(ActionEvent event) {
        String user;
        String pass;
        Usuario usuario = null;
        Usuario check = null;
        user = txtUsuario.getText();
        pass = txtPassword.getText();
        if (datos.verificarusuario(user)==false){
            alerta("Datos no válidos");
        }else{
            usuario = datos.obtenerUnUsuario(user);
        }

        if (user.equals(usuario.getUsuario()) && pass.equals(usuario.getContrasenya())){
            // set up the scene
            try{
                this.root = FXMLLoader.load(getClass().getResource("/menuinicial.fxml"));
                Scene scene = new Scene(this.root);
                Image icon = new Image("logo.jpeg");
                stage.getIcons().add(icon);
                stage.resizableProperty().setValue(true);
                stage.setMaximized(false);
                stage.setTitle("OnlineStore");
                stage.setScene(scene);
                stage.show();
                Stage stage = (Stage) btnIniciar.getScene().getWindow();
                stage.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            alerta("Datos incorrectos.");
        }
    }

    // ------------------------------------------------------------------

    void alerta(String textoalerta){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Alerta");
        alert.setContentText(textoalerta);
        alert.showAndWait();
    }
}
