package com.onlinestore.controlador;

import com.onlinestore.modelo.Articulo;
import com.onlinestore.modelo.Cliente;
import com.onlinestore.modelo.Datos;
import com.onlinestore.modelo.Pedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class controladorPedido implements Initializable  {

    private Datos datos;

    @FXML
    private TextField txtNumPedido;
    @FXML
    private TextField txtHoraPedido;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtCosteEnvio;

    @FXML
    private CheckBox chkenviado;
    @FXML
    private DatePicker dtpFechaPedido;
    @FXML
    private ComboBox<String> cmbProducto;
    @FXML
    private ComboBox<String> cmbCliente;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            datos = new Datos();
            cargarComboBoxArticulos();
            cargarComboBoxClientes();
        } catch (Exception e) {
            // Manejar excepción, como mostrar un mensaje de error
            e.printStackTrace();
        }


    }
    //Crear Pedido
    @FXML
    private void clk_crearPedido(ActionEvent event) {
        if (camposEstanCompletos()) {
            try {
                // Obtener los valores de los campos de texto y ComboBox
                String numeroPedidoTexto = txtNumPedido.getText();
                LocalDate fecha = dtpFechaPedido.getValue();
                String horaTexto = txtHoraPedido.getText();
                String cantidadTexto = txtCantidad.getText();
                String codigoProducto = cmbProducto.getSelectionModel().getSelectedItem();
                String clienteEmail = cmbCliente.getSelectionModel().getSelectedItem();
                String costeEnvioTexto = txtCosteEnvio.getText();
                boolean esEnviado = chkenviado.isSelected();

                // Validar los datos aquí...

                // Parsear y combinar fecha y hora
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime hora = LocalTime.parse(horaTexto, timeFormatter);
                LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

                // Parsear cantidad y coste de envío a números
                int numeroPedido = Integer.parseInt(numeroPedidoTexto);
                int cantidad = Integer.parseInt(cantidadTexto);
                double costeEnvio = Double.parseDouble(costeEnvioTexto);

                // Obtener el artículo y el cliente usando sus códigos
                Articulo articulo = datos.obtenerArticulo(codigoProducto);
                Cliente cliente = datos.obtenerCliente(clienteEmail);

                // Crear un nuevo pedido
                Pedido nuevoPedido = new Pedido(numeroPedido, fechaHora, cliente, articulo, cantidad, esEnviado, costeEnvio);

                // Agregar el pedido a la base de datos
                datos.agregarPedido(nuevoPedido);
                showInformation("Creado", "Los datos de pedido se han introducido con exito.");
                limpiarFormulario();

            } catch (DateTimeParseException e) {
                showAlert("Error", "La hora proporcionada no es válida. Por favor, use el formato HH:mm.");
            } catch (NumberFormatException e) {
                showAlert("Error", "La cantidad y el coste de envío deben ser números válidos.");
            } catch (Exception e) {
                // Manejar otras posibles excepciones
                showAlert("Error", "Ha ocurrido un error al crear el pedido.");
            }
        }
        else
        {
            showAlert("Validación", "Por favor, llene todos los campos antes de crear el pedido.");
        }
    }
    private void limpiarFormulario() {
        // Limpiar los campos de texto
        txtNumPedido.clear();
        txtHoraPedido.clear();
        txtCantidad.clear();
        txtCosteEnvio.clear();

        // Restablecer los ComboBox
        cmbProducto.getSelectionModel().clearSelection();
        cmbCliente.getSelectionModel().clearSelection();

        // Restablecer el DatePicker a null o a la fecha actual
        dtpFechaPedido.setValue(null);

        // Desmarcar CheckBox
        chkenviado.setSelected(false);

        // Aquí puedes restablecer cualquier otro control que puedas tener
    }
    private boolean camposEstanCompletos() {
        // Verificar que los TextFields no estén vacíos
        boolean camposTextoCompletos = !txtNumPedido.getText().isEmpty() &&
                !txtHoraPedido.getText().isEmpty() &&
                !txtCantidad.getText().isEmpty() &&
                !txtCosteEnvio.getText().isEmpty();

        // Verificar que los ComboBox tengan un elemento seleccionado
        boolean comboBoxCompletos = cmbProducto.getSelectionModel().getSelectedItem() != null &&
                cmbCliente.getSelectionModel().getSelectedItem() != null;

        // Verificar que el DatePicker tenga una fecha seleccionada
        boolean datePickerCompleto = dtpFechaPedido.getValue() != null;

        // Devuelve true si todos los campos están completos
        return camposTextoCompletos && comboBoxCompletos && datePickerCompleto;
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void showInformation(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void cargarComboBoxArticulos() {
        ArrayList<Articulo> art = datos.obtenerArticulos();
        ObservableList<String> nombresArticulos = FXCollections.observableArrayList();

        for (Articulo articulo : art) {
            nombresArticulos.add(articulo.getCodigo());
        }

        cmbProducto.setItems(nombresArticulos);
    }
    private void cargarComboBoxClientes(){
        ArrayList<Cliente> cl = datos.mostrarTodosLosClientes();
        ObservableList<String> mailClientes = FXCollections.observableArrayList();

        for (Cliente cliente : cl) {
            mailClientes.add(cliente.getCorreoElectronico());
        }

        cmbCliente.setItems(mailClientes);
    }


    // Eliminar Pedido
    @FXML
    private void clk_eliminarPedido(ActionEvent event){

    }
   /* String cp;

    cp = cpArticulo.getText();
        datos.eliminarArticulo(cp);*/
    // Mostrar Todos

    // Mostrar Enviados

    // Mostrar Pendientes

}
