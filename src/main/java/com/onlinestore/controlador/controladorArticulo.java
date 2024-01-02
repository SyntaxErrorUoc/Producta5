package com.onlinestore.controlador;

import com.onlinestore.ConexionMySQL.DatabaseConnectionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.onlinestore.modelo.Articulo;
import com.onlinestore.modelo.Datos;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

public class controladorArticulo implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Datos datos;

    @FXML
    private Button btn_creararticulo;

    @FXML
    private TextField cpArticulo;

    @FXML
    private TextField descripcionArticulo;

    @FXML
    private TextField precioArticulo;

    @FXML
    private TextField tiempopreparacionArticulo;


    public controladorArticulo() throws DatabaseConnectionException {

        this.datos = new Datos();

    }

    @FXML
    void clk_buscararticulo(ActionEvent event) {

    }

    @FXML
    void clk_eliminarArticulo(ActionEvent event) {
        String cp;
        cp = cpArticulo.getText();
        datos.eliminarArticulo(cp);

    }

    @FXML
    void clk_modificararticulo(ActionEvent event) {

    }


    @FXML
    void clk_crearArticulo(ActionEvent event) {
        String cp="";
        String desc="";
        double precio=0.0;
        String tiempo="";
        Duration tiempoPrep = null;

        cp = cpArticulo.getText();
        desc = descripcionArticulo.getText();
        precio = Double.parseDouble(precioArticulo.getText());
        tiempo = tiempopreparacionArticulo.getText();
        String[] partes = tiempo.split(":");
        if (partes.length == 2){
            try{
                int horas = Integer.parseInt(partes[0]);
                int minutos = Integer.parseInt(partes[1]);
                tiempoPrep = Duration.ofHours(horas).plusMinutes(minutos);

                // -------------------------------------------------------------
                Articulo art = new Articulo(cp, desc, precio, tiempoPrep);
                datos.agregarArticulo(art);
                // -------------------------------------------------------------

            }catch(NumberFormatException e){
                System.out.println("La hora no es valida");
            }

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
