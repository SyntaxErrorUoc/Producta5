package com.onlinestore.controlador;

import com.onlinestore.ConexionMySQL.DatabaseConnectionException;
import com.onlinestore.modelo.Articulo;
import com.onlinestore.modelo.Cliente;
import com.onlinestore.modelo.Datos;
import com.onlinestore.modelo.Pedido;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class controladorPedido implements Initializable  {

    private Datos datos;
    private Pedido pedidos;

    @FXML
    private TextField txtNumPedido;
    @FXML
    private TextField txtcodigoProducto;
    @FXML
    private TextField txtClienteEliminar;
    @FXML
    private TextField txtCantidadEliminar;
    @FXML
    private TextField txtCosteEnvioEliminar;
    @FXML
    private CheckBox chkEnviadoEliminar;
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
    @FXML
    private ComboBox<String> cmbNumPedido;
    @FXML
    private ComboBox<String> cmbPedidoListar;

    @FXML
    private TableColumn<Pedido, Integer> grid_NumPedido ;

    @FXML
    private TableColumn<Pedido, Date> grid_FechaHora;
    @FXML
    private TableColumn<Pedido, Double> grid_costeEnvio;

    @FXML
    private TableColumn<Pedido, Integer> grid_cantidad;

    @FXML
    private TableColumn<Pedido, Boolean> grid_enviado;
    @FXML
    private TableColumn<Pedido, String> grid_codArticulo;

    @FXML
    private TableColumn<Pedido, String> grid_cliente= new TableColumn<>("Cliente");
    @FXML
    private TableView table_Pedidos;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            datos = new Datos();
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }
        if (cmbProducto != null && cmbNumPedido == null) {
            // Se está cargando el formulario de crear pedido
            cargarComboBoxesParaCrearPedido();
        } else if (cmbNumPedido != null && cmbProducto == null) {
            // Se está cargando el formulario de eliminar pedido
            cargarComboBoxesParaEliminarPedido();
        } else if (cmbNumPedido == null && cmbProducto == null){
            //Se está cargando el formulario mostrar
            cargarComboBoxMostrarPedidos();
        }
    }

    private void cargarComboBoxesParaCrearPedido() {
        // Carga los ComboBoxes para crear pedido
        // Tu código existente para cargar los ComboBoxes de artículos y clientes
        cargarComboBoxArticulos();
        cargarComboBoxClientes();
    }
    private void cargarComboBoxesParaEliminarPedido() {
        // Carga el ComboBox para eliminar pedido
        // Tu código existente para cargar los ComboBoxes de pedidos
        cargarComboBoxPedidos();
    }
    private void cargarComboBoxArticulos() {
        ArrayList<Articulo> art = datos.obtenerArticulos();
        ObservableList<String> nombresArticulos = FXCollections.observableArrayList();

        for (Articulo articulo : art) {
            nombresArticulos.add(articulo.getCodigo());
        }

        cmbProducto.setItems(nombresArticulos);
    }

    private void cargarComboBoxClientes() {
        ArrayList<Cliente> cl = datos.mostrarTodosLosClientes();
        ObservableList<String> mailClientes = FXCollections.observableArrayList();

        for (Cliente cliente : cl) {
            mailClientes.add(cliente.getCorreoElectronico());
        }

        cmbCliente.setItems(mailClientes);
    }

    private void cargarComboBoxPedidos() {
        ArrayList<Pedido> ped = datos.obtenerPedidos();
        ObservableList<String> numerPedido = FXCollections.observableArrayList();

        for (Pedido pedido : ped) {
            numerPedido.add(String.valueOf(pedido.getNumeroPedido()));
        }

        cmbNumPedido.setItems(numerPedido);

    }

    private void cargarComboBoxMostrarPedidos(){
        ArrayList<Pedido> ped = datos.obtenerPedidos();
        ObservableList<String> numerPedido = FXCollections.observableArrayList();

        for (Pedido pedido : ped) {
            numerPedido.add(String.valueOf(pedido.getNumeroPedido()));
        }

        cmbPedidoListar.setItems(numerPedido);
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



    // Eliminar Pedido

    @FXML
    private  void clk_seleccionEliminar(ActionEvent event){
        String numeroPedidoSeleccionado = cmbNumPedido.getSelectionModel().getSelectedItem();
        if (numeroPedidoSeleccionado != null && !numeroPedidoSeleccionado.isEmpty()) {
            try {
                int numPedido = Integer.parseInt(numeroPedidoSeleccionado);
                Pedido pedidoSeleccionado = datos.obtenerUnPedido(numPedido);
                if (pedidoSeleccionado != null) {
                    // Actualiza los campos del formulario con los datos del pedido seleccionado
                    txtcodigoProducto.setText(pedidoSeleccionado.getArticulo().getCodigo());
                    txtClienteEliminar.setText(pedidoSeleccionado.getMailCliente());
                    txtCantidadEliminar.setText(String.valueOf(pedidoSeleccionado.getCantidad()));
                    txtCosteEnvioEliminar.setText(String.valueOf(pedidoSeleccionado.getCosteEnvio()));

                    txtcodigoProducto.setEditable(false);
                    txtClienteEliminar.setEditable(false);
                    txtCantidadEliminar.setEditable(false);
                    txtCosteEnvioEliminar.setEditable(false);


                    boolean esEnviado = pedidoSeleccionado.isEnviado(); // isEnviado() debería devolver un booleano
                    chkEnviadoEliminar.setSelected(esEnviado);
                    chkEnviadoEliminar.setDisable(true);
                }
            } catch (NumberFormatException e) {
                // Manejar el error en caso de que el número de pedido no sea un número válido
                e.printStackTrace();
            }
        }
    }

    private void eliminarPedidoSeleccionado() {
        String numeroPedidoSeleccionado = cmbNumPedido.getSelectionModel().getSelectedItem();
        if (numeroPedidoSeleccionado == null || numeroPedidoSeleccionado.isEmpty()) {
            // Mostrar una alerta indicando que no se ha seleccionado ningún pedido
            mostrarAlertaEliminar("Eliminar Pedido", "No se ha seleccionado ningún pedido para eliminar.");
            return;
        }

        try {
            int numPedido = Integer.parseInt(numeroPedidoSeleccionado);

            // Llamar al método para eliminar el pedido de la base de datos
            datos.eliminarPedido(numPedido);

            // Actualizar el ComboBox eliminando el pedido
            cmbNumPedido.getItems().remove(numeroPedidoSeleccionado);

            // Mostrar una alerta de éxito
            mostrarAlertaEliminar("Eliminar Pedido", "Pedido eliminado con éxito.");

        } catch (NumberFormatException e) {
            // Manejar excepción si el número de pedido no es un número válido
            mostrarAlertaEliminar("Error", "Formato de número de pedido inválido.");
        } catch (Exception e) {
            // Manejar otras posibles excepciones
            mostrarAlertaEliminar("Error", "Error al eliminar el pedido: " + e.getMessage());
        }
    }
    @FXML
    private void clk_eliminarPedido(ActionEvent event){

        eliminarPedidoSeleccionado();
        limpiarFormularioEliminar();
    }
    private void limpiarFormularioEliminar(){
        txtCantidadEliminar.clear();
        txtCosteEnvioEliminar.clear();
        txtcodigoProducto.clear();
        txtClienteEliminar.clear();
        cmbNumPedido.getSelectionModel().clearSelection();
        chkEnviadoEliminar.setSelected(false);

    }
    private void mostrarAlertaEliminar(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }



    // Mostrar Todos
    @FXML
    private void clk_pedidoListarTodos(){
        table_Pedidos.getItems().clear();
        table_Pedidos.setEditable(true);

        ArrayList<Pedido> ped = datos.obtenerPedidos();


        grid_NumPedido.setCellValueFactory(new PropertyValueFactory<>("numeroPedido"));
        grid_FechaHora.setCellValueFactory(new PropertyValueFactory<>("fechaHoraPedido"));
        grid_costeEnvio.setCellValueFactory(new PropertyValueFactory<>("costeEnvio"));
        grid_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        grid_enviado.setCellValueFactory((new PropertyValueFactory<>("enviado")));
        grid_codArticulo.setCellValueFactory(cellData ->{
            String codigoArticulo = cellData.getValue().getArticulo().getCodigo();
            return new SimpleStringProperty(codigoArticulo);
        });

        grid_cliente.setCellValueFactory(cellData ->{
            String ClienteStandard_mail = cellData.getValue().getCliente().getCorreoElectronico();
            return new SimpleStringProperty(ClienteStandard_mail);
        });


        for (Pedido pedidos : ped) {

            table_Pedidos.getItems().add(pedidos);

        }
    }
    @FXML
    private void clk_opt_pedidoListarEnviado(){
        table_Pedidos.getItems().clear();
        table_Pedidos.setEditable(true);

        ArrayList<Pedido> ped = datos.obtenerPedidos();


        grid_NumPedido.setCellValueFactory(new PropertyValueFactory<>("numeroPedido"));
        grid_FechaHora.setCellValueFactory(new PropertyValueFactory<>("fechaHoraPedido"));
        grid_costeEnvio.setCellValueFactory(new PropertyValueFactory<>("costeEnvio"));
        grid_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        grid_enviado.setCellValueFactory((new PropertyValueFactory<>("enviado")));
        grid_codArticulo.setCellValueFactory(cellData ->{
            String codigoArticulo = cellData.getValue().getArticulo().getCodigo();
            return new SimpleStringProperty(codigoArticulo);
        });
        grid_cliente.setCellValueFactory(cellData ->{
            String ClienteStandard_mail = cellData.getValue().getCliente().getCorreoElectronico();
            return new SimpleStringProperty(ClienteStandard_mail);
        });



        for (Pedido pedidos : ped) {
            if(pedidos.isEnviado()) {
                table_Pedidos.getItems().add(pedidos);
            }
        }
    }
    @FXML
    private void clk_pedidoListarPendiente(){
        table_Pedidos.getItems().clear();
        table_Pedidos.setEditable(true);

        ArrayList<Pedido> ped = datos.obtenerPedidos();


        grid_NumPedido.setCellValueFactory(new PropertyValueFactory<>("numeroPedido"));
        grid_FechaHora.setCellValueFactory(new PropertyValueFactory<>("fechaHoraPedido"));
        grid_costeEnvio.setCellValueFactory(new PropertyValueFactory<>("costeEnvio"));
        grid_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        grid_enviado.setCellValueFactory((new PropertyValueFactory<>("enviado")));
        grid_codArticulo.setCellValueFactory(cellData ->{
            String codigoArticulo = cellData.getValue().getArticulo().getCodigo();
            return new SimpleStringProperty(codigoArticulo);
        });

        grid_cliente.setCellValueFactory(cellData ->{
            String ClienteStandard_mail = cellData.getValue().getCliente().getCorreoElectronico();
            return new SimpleStringProperty(ClienteStandard_mail);
        });


        for (Pedido pedidos : ped) {
            if(!pedidos.isEnviado()) {
                table_Pedidos.getItems().add(pedidos);
            }
        }
    }
    @FXML
    private void clk_listarPedidoCombo(){
        table_Pedidos.getItems().clear();
        table_Pedidos.setEditable(true);

        Pedido ped = datos.obtenerUnPedido(Integer.parseInt(cmbPedidoListar.getValue()));


        grid_NumPedido.setCellValueFactory(new PropertyValueFactory<>("numeroPedido"));
        grid_FechaHora.setCellValueFactory(new PropertyValueFactory<>("fechaHoraPedido"));
        grid_costeEnvio.setCellValueFactory(new PropertyValueFactory<>("costeEnvio"));
        grid_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        grid_enviado.setCellValueFactory((new PropertyValueFactory<>("enviado")));
        grid_codArticulo.setCellValueFactory(cellData ->{
            String codigoArticulo = cellData.getValue().getArticulo().getCodigo();
            return new SimpleStringProperty(codigoArticulo);
        });

        grid_cliente.setCellValueFactory(cellData ->{
            String ClienteStandard_mail = cellData.getValue().getCliente().getCorreoElectronico();
            return new SimpleStringProperty(ClienteStandard_mail);
        });



        table_Pedidos.getItems().add(ped);


    }



}
