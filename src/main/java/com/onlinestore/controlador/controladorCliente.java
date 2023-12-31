package com.onlinestore.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class controladorCliente implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField apellidos;

    @FXML
    private Button btn_creararticulo;
    @FXML
    private Button btn_eliminar;

    @FXML
    private Button btn_modificar;

    @FXML
    private TextField cuota;

    @FXML
    private TextField descuento;

    @FXML
    private TextField direccion;

    @FXML
    private TextField mail;

    @FXML
    private TextField nombre;

    @FXML
    private CheckBox vip;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    @FXML
    void clk_clientesTodos(ActionEvent event) {

    }

    @FXML
    void clk_clientesPremium(ActionEvent event) {

    }

    @FXML
    void clk_clienteEstandar(ActionEvent event) {

    }

    @FXML
    void clk_buscarcliente(ActionEvent event) {

    }



    @FXML
    void clk_buscar(ActionEvent event) {

    }

    @FXML
    void clk_modificar(ActionEvent event) {

    }


    @FXML
    void clk_eliminar(ActionEvent event) {

    }


}
