package controlador;

import ConexionMySQL.DatabaseConnectionException;
import jakarta.persistence.Table;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import modelo.Cliente;
import modelo.ClienteEstandar;
import modelo.ClientePremium;
import modelo.Datos;


public class controladorCliente implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Datos datos;
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

    @FXML
    private TableColumn<Cliente, String> grid_mail;
    @FXML
    private TableColumn<Cliente, Boolean> grid_vip;
    @FXML
    private TableColumn<Cliente, String> grid_name;
    @FXML
    private TableColumn<Cliente, String> grid_direccion;
    @FXML
    private TableColumn<Cliente, Double> grid_descuento;
    @FXML
    private TableColumn<Cliente, Double> grid_cuota;
    @FXML
    private TableView<Cliente> table_clientes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public controladorCliente() throws DatabaseConnectionException {

        this.datos = new Datos();
    }

    @FXML
    void clk_crearArticulo(ActionEvent event){

        if (vip.isSelected()){


            ClienteEstandar stand = new ClienteEstandar(mail.getText(),nombre.getText(),direccion.getText());
            datos.agregarCliente(stand);
        }else{
            ClientePremium prem = new ClientePremium(mail.getText(),nombre.getText(),direccion.getText(), Float.parseFloat(descuento.getText()));
            datos.agregarCliente(prem);
        }


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

        table_clientes.getItems().clear();
        table_clientes.setEditable(true);
        ArrayList<Cliente> cl = datos.mostrarTodosLosClientes();

        grid_mail.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        grid_vip.setCellValueFactory(new PropertyValueFactory<>("vip"));
        grid_name.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        grid_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        grid_descuento.setCellValueFactory(cellData ->
                cellData.getValue() instanceof ClientePremium ?
                        new SimpleDoubleProperty(((ClientePremium) cellData.getValue()).getDescuento()).asObject() :
                        new SimpleDoubleProperty(0.0).asObject());
        grid_cuota.setCellValueFactory(cellData ->
                cellData.getValue() instanceof ClientePremium ?
                        new SimpleDoubleProperty(((ClientePremium) cellData.getValue()).getCuotaAnual()).asObject() :
                        new SimpleDoubleProperty(0.0).asObject());



        for (Cliente cli : cl){

            table_clientes.getItems().add(cli);

        }

    }
    @FXML
    void clk_buscar(ActionEvent event) {

    }

    @FXML
    void clk_modificar(ActionEvent event) {

    }


    @FXML
    void clk_eliminar(ActionEvent event) {
        if (emailExiste(mail.getText())){
            datos.eliminarCliente(mail.getText());
        }
    }
    public boolean emailExiste(String email) {
        boolean existe = false;
        if (datos.obtenerCliente(email) != null){
            existe = true;
        }
        return existe;
    }



}
