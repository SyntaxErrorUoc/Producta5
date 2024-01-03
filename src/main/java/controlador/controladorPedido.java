package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelo.Articulo;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class controladorPedido implements Initializable  {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btn_crearPedido;
    @FXML
    private ComboBox cmbProducto;
    @FXML
    private ComboBox cmbCliente;
    @FXML
    private TextField txtNumPedido;
    @FXML
    private TextField txtHoraPedido;
    @FXML
    private TextField cantidad;
    @FXML
    private CheckBox chkenviado;
    @FXML
    private DatePicker dtpFechaPedido;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    @FXML
    void clk_listarProducto(ActionEvent event){

        ArrayList <Articulo> a = new ArrayList<Articulo>();
        

    }
}
