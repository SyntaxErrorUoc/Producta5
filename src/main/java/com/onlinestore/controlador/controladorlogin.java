package com.onlinestore.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class controladorlogin implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button btnGit;

    @FXML
    private Button btnIniciar;

    @FXML
    private PasswordField txtPasswword;

    @FXML
    private TextField txtUsuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void clk_iniciar(ActionEvent event) {

    }


}
