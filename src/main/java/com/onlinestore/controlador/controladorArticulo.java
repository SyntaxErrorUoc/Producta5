package com.onlinestore.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import com.onlinestore.modelo.Articulo;
import com.onlinestore.modelo.Datos;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class controladorArticulo implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Datos datos;

    // Botones

    @FXML
    private Button btn_creararticulo;

    @FXML
    private Button btn_buscararticulo1;

    @FXML
    private Button btn_buscararticulo2;

    // Campos

    @FXML
    private TextField cpArticulo;

    @FXML
    private TextField descripcionArticulo;

    @FXML
    private TextField precioArticulo;

    @FXML
    private TextField tiempopreparacionArticulo;

    // Tabla

    @FXML
    private TableColumn<Articulo, String> clm_cp;

    @FXML
    private TableColumn<Articulo, String> clm_descripcion;

    @FXML
    private TableColumn<Articulo, Double> clm_precio;

    @FXML
    private TableColumn<Articulo, Duration> clm_tiempoPreparacion;

    @FXML
    private TableView<Articulo> table_articulos;

    @FXML
    private ComboBox<String> cmb_cpArticulo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            datos = new Datos();
            cargarComboBoxArticulos();
        } catch (Exception e) {
            // Manejar excepción, como mostrar un mensaje de error
            e.printStackTrace();
        }

    }

    /*
     * Adaptamos los métodos existentes en "Controlador.java" al controlador de cada evento
     */

    @FXML
    void clk_sort(ActionEvent event) {
        // TO DO
    }

    @FXML
    void clk_buscararticulo(ActionEvent event) {
        // Detectar si el artículo es válido en la modificación / eliminación
        String cp="";
        Articulo a;

        cp = cmb_cpArticulo.getValue();
        a = datos.obtenerArticulo(cp);

        if (articuloExiste(cp)){
            informacion("Articulo existente");
            descripcionArticulo.setText(a.getDescripcion());
            precioArticulo.setText(String.valueOf((a.getPrecio())));
            tiempopreparacionArticulo.setText(String.valueOf(a.getTiempoPreparacion()));
        }else{
            alerta("Código de producto no existente.");
        }
    }

    @FXML
    void clk_buscararticulo2(ActionEvent event) {
        // Limpiamos la tabla
        table_articulos.getItems().clear();
        // Mostrar un artículo
        String cp;
        Articulo art;
        cp = cmb_cpArticulo.getValue();

        // Asociamos las columnas
        clm_cp.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        clm_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        clm_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        clm_tiempoPreparacion.setCellValueFactory(new PropertyValueFactory<>("tiempoPreparacion"));

        if(articuloExiste(cp)){
            Articulo articulosList = datos.obtenerArticulo(cp);
            ObservableList<Articulo> articulosObservableList = FXCollections.observableArrayList(articulosList);
            table_articulos.setItems(articulosObservableList);
        }else{
            alerta("Código de producto no existente.");
        }
    }

    @FXML
    void clk_buscararticulo1(ActionEvent event) {
        // Limpiamos la tabla
        table_articulos.getItems().clear();
        // Ver todos los artículos
        ArrayList<Articulo> articulosList = datos.obtenerArticulos();
        ObservableList<Articulo> articulosObservableList = FXCollections.observableArrayList(articulosList);
        table_articulos.setItems(articulosObservableList);
    }

    @FXML
    void clk_eliminarArticulo(ActionEvent event) {

        String cp="";
        Articulo a;

        cp = cpArticulo.getText();
        a = datos.obtenerArticulo(cp);

        if (articuloExiste(cp)){
            datos.eliminarArticulo(cp);
            informacion("Articulo eliminado");
            stage.close();
        }else{
            alerta("Código de producto no existente.");
        }
    }

    @FXML
    void clk_modificararticulo(ActionEvent event) {
        String cp="";
        String desc="";
        double precio=0.0;
        String tiempo="";
        Duration tiempoPrep = null;

        cp = cpArticulo.getText();

        Articulo a;
        a = datos.obtenerArticulo(cp);
        if (articuloExiste(cp)){
            // Mostramos los datos
            descripcionArticulo.setText(a.getDescripcion());
            precioArticulo.setText(String.valueOf(a.getPrecio()));
            tiempopreparacionArticulo.setText(String.valueOf(a.getTiempoPreparacion()));

            // cargamos lo que hay escrito
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
                    Articulo nuevoA = new Articulo(cp,desc,precio,tiempoPrep);
                    datos.agregarArticulo(nuevoA);
                    // -------------------------------------------------------------
                }catch(NumberFormatException e){
                    alerta("La hora no es valida");
                }
            }
        }else {

            // TODO

        }
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
                alerta("La hora no es valida");
            }
        }
    }

    /**
     * Método para comprobar si el artículo existe
     * @param cp Código del Producto que es el identificador único en la BBDD
     * @return
     */
    public boolean articuloExiste(String cp) {

        boolean existe = false;
        if (datos.obtenerArticulo(cp) != null){

            existe = true;
            return existe;
        }
        return existe;
    }

    boolean Confirmación (){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() ==  ButtonType.OK){
            return true;
        }else{
            return false;
        }
    }

    void alerta(String textoalerta){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Alerta");
        alert.setContentText(textoalerta);
        alert.showAndWait();
    }

    void informacion(String textoinfo){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText(textoinfo);
        alert.showAndWait();
    }

    private void cargarComboBoxArticulos() {
        ArrayList<Articulo> art = datos.obtenerArticulos();
        ObservableList<String> nombresArticulos = FXCollections.observableArrayList();

        for (Articulo articulo : art) {
            nombresArticulos.add(articulo.getCodigo());
        }

        cmb_cpArticulo.setItems(nombresArticulos);
    }

}
