package com.onlinestore.controlador;

import com.onlinestore.ConexionMySQL.DatabaseConnectionException;
import javafx.beans.property.SimpleDoubleProperty;
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

import com.onlinestore.modelo.Cliente;
import com.onlinestore.modelo.ClienteEstandar;
import com.onlinestore.modelo.ClientePremium;
import com.onlinestore.modelo.Datos;


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
    private TextField descuentoEliminar;

    @FXML
    private TextField direccionEliminar;

    @FXML
    private TextField cuotaEliminar;

    @FXML
    private TextField apellidosEliminar;

    @FXML
    private TextField mailEliminar;

    @FXML
    private TextField nombreEliminar;

    @FXML
    private CheckBox vipEliminar;

    @FXML
    private ComboBox comboCliente;
    @FXML
    private ComboBox cmb_Eliminar;

    @FXML
    private TextField opt_buscarCliente;

    @FXML
    private TextField nombreCliente;

    @FXML
    private TextField apellidosCliente;

    @FXML
    private TextField direccionCliente;

    @FXML
    private TextField cuotaCliente;

    @FXML
    private TextField descuentoCliente;

    @FXML
    private CheckBox vipmodificar;

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


        if (comboCliente!= null){
            cargarComboBoxCliente(comboCliente);
        }
        if (cmb_Eliminar!= null){
            cargarComboBoxCliente(cmb_Eliminar);
        }
    }


    public controladorCliente() throws DatabaseConnectionException {

        this.datos = new Datos();
    }

    @FXML
    void clk_crearCliente(ActionEvent event){

        if (!emailExiste(mail.getText())) {

            if (!vip.isSelected()) {


                ClienteEstandar stand = new ClienteEstandar(mail.getText(), nombre.getText(), direccion.getText());
                datos.agregarCliente(stand);
                mostrarAlertaCliente("Creacion","Se ha creado un cliente Standard");
            } else {
                ClientePremium prem = new ClientePremium(mail.getText(), nombre.getText(), direccion.getText(), Float.parseFloat(descuento.getText()));
                mostrarAlertaCliente("Creacion","Se ha creado un cliente Premium");
                datos.agregarCliente(prem);
            }
        }else{

            mostrarAlertaCliente("Alerta Existe","El cliente ya existe!");
        }

    }
    @FXML
    void clk_clientesTodos(ActionEvent event) {

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

        for (Cliente cli : cl) {

            table_clientes.getItems().add(cli);

        }
    }

    @FXML
    void clk_clientesPremium(ActionEvent event) {

        table_clientes.getItems().clear();
        table_clientes.setEditable(true);
        ArrayList<Cliente> lista;
        lista = datos.mostrarPorTipo("vip","1");
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

        for (Cliente cli : lista) {

            table_clientes.getItems().add(cli);

        }
    }
    @FXML
    void clk_clientesStandard(ActionEvent event) {

        table_clientes.getItems().clear();
        table_clientes.setEditable(true);
        ArrayList<Cliente> lista;
        lista = datos.mostrarPorTipo("vip","0");
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

        for (Cliente cli : lista) {

            table_clientes.getItems().add(cli);

        }
    }

    @FXML
    void clk_buscarcliente(ActionEvent event) {

        String clienteBuscar = opt_buscarCliente.getText();

        if (emailExiste(clienteBuscar)){

            Cliente c;

            c = datos.obtenerCliente(clienteBuscar);
            table_clientes.getItems().clear();
            table_clientes.setEditable(true);
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

            table_clientes.getItems().add(c);


        }else{
            mostrarAlertaCliente("No encontrado","El cliente no se encuentra en la base de datos");
        }
    }
    @FXML
    void cmb_buscarEliminar(ActionEvent event) {

        String mailClinte = cmb_Eliminar.getValue().toString();

        if (mailClinte != null && !mailClinte.isEmpty()) {
            try {
                Cliente cl = datos.obtenerCliente(mailClinte);

                if (cl != null) {

                    nombreEliminar.setText(cl.getNombre().toString());
                    apellidosEliminar.setText("");
                    direccionEliminar.setText(cl.getDireccion());
                    if(cl instanceof ClientePremium){
                        vipEliminar.setSelected(true);
                        vipEliminar.setDisable(true);
                        descuentoEliminar.setText(String.valueOf(((ClientePremium) cl).getDescuento()));
                        cuotaEliminar.setText(String.valueOf(((ClientePremium) cl).getCuotaAnual()));

                    }
                    else{
                        vipEliminar.setSelected(false);
                        vipEliminar.setDisable(true);
                        descuentoEliminar.setText("0.0");
                        cuotaEliminar.setText("0.0");
                    }
                    nombreEliminar.setEditable(false);
                    apellidosEliminar.setEditable(false);
                    direccionEliminar.setEditable(false);
                    descuentoEliminar.setEditable(false);
                    cuotaEliminar.setEditable(false);


                }
            } catch (NumberFormatException e) {
                // Manejar el error en caso de que el número de pedido no sea un número válido
                e.printStackTrace();
            }
        }

    }


    @FXML
    void clk_modificarCliente(ActionEvent event){

        if (vipmodificar.isSelected()){
            Cliente c = new ClientePremium( comboCliente.getValue().toString(),nombreCliente.getText(),direccionCliente.getText(),Float.parseFloat(descuentoCliente.getText()));
            if (emailExiste(comboCliente.getValue().toString())){
                mostrarAlertaCliente("Modificacion","Se ha modificado con éxito");
                datos.modificarCliente(c);
            }
        }else if  (!vipmodificar.isSelected()){
            Cliente c = new ClienteEstandar( comboCliente.getValue().toString(),nombreCliente.getText(),direccionCliente.getText());
            if (emailExiste(comboCliente.getValue().toString())){
                mostrarAlertaCliente("Modificacion","Se ha modificado con éxito");
                datos.modificarCliente(c);
            }
        }else{
            mostrarAlertaCliente("Modificacion","no se ha podido modificar el cliente");
        }

    }
    @FXML
    void clk_comboCliente(ActionEvent event){
        String mailClinte = comboCliente.getValue().toString();
        System.out.println(mailClinte);
        if (mailClinte != null && !mailClinte.isEmpty()) {
            try {
                Cliente cl = datos.obtenerCliente(mailClinte);

                if (cl != null) {

                    nombreCliente.setText(cl.getNombre().toString());
                    apellidosCliente.setText("");
                    direccionCliente.setText(cl.getDireccion());
                    if(cl instanceof ClientePremium){
                        vipmodificar.setSelected(true);
                        descuentoCliente.setText(String.valueOf(((ClientePremium) cl).getDescuento()));
                        cuotaCliente.setText(String.valueOf(((ClientePremium) cl).getCuotaAnual()));

                    }
                    else{
                        vipmodificar.setSelected(false);
                        descuentoCliente.setText("0.0");
                        cuotaCliente.setText("0.0");
                    }

                }
            } catch (NumberFormatException e) {
                // Manejar el error en caso de que el número de pedido no sea un número válido
                e.printStackTrace();
            }
        }
    }

    @FXML
    void clk_eliminar(ActionEvent event) {
        if (emailExiste(cmb_Eliminar.getValue().toString())){
            datos.eliminarCliente(cmb_Eliminar.getValue().toString());
            mostrarAlertaCliente("Eliminacion","Se ha eliminado con éxito");
        }else{
            mostrarAlertaCliente("Eliminacion","No se ha podido eliminar el cliente");
        }
    }
    public boolean emailExiste(String email) {
        boolean existe = false;
        if (datos.obtenerCliente(email) != null){
            existe = true;
        }
        return existe;
    }

    private void mostrarAlertaCliente(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void cargarComboBoxCliente(ComboBox combo) {
        ArrayList<Cliente> cl = datos.mostrarTodosLosClientes();
        ObservableList<String> mailCliente = FXCollections.observableArrayList();

        for (Cliente cliente : cl) {
            mailCliente.add(cliente.getCorreoElectronico());
        }

        combo.setItems(mailCliente);
    }
}
