package com.onlinestore.controlador;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class controladorMenu implements Initializable  {

    private Stage stage = new Stage();
    @FXML
    private BorderPane mainContainer;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane content;


    @FXML
    void clk_acercade(ActionEvent event) throws IOException  {
        switchscene(event, "/acercade.fxml");
    }

    @FXML
    void clk_buscararticulo(ActionEvent event) throws IOException  {
        switchscene(event, "/frmlistararticulos.fxml");
    }

    @FXML
    void clk_buscarcliente(ActionEvent event) throws IOException  {
        cargarFormulario("/frmlistarclientes.fxml");
        //switchscene(event, "/frmlistarclientes.fxml");
    }

    @FXML
    void clk_buscarpedido(ActionEvent event) throws IOException  {
        switchscene(event, "/frmlistarpedidos.fxml");
    }

    @FXML
    void clk_editararticulo(ActionEvent event) throws IOException  {
        cargarFormulario("/frmmodificararticulo.fxml");
        //switchscene(event, "/frmmodificararticulo.fxml");
    }

    @FXML
    void clk_editarcliente(ActionEvent event)  throws IOException {
        cargarFormulario("/frmmodificarcliente.fxml");
        //switchscene(event, "/frmmodificarcliente.fxml");
    }

    @FXML
    void clk_eliminararticulo(ActionEvent event) throws IOException {
        switchscene(event, "/frmeliminararticulo.fxml");
    }

    @FXML
    void clk_eliminarcliente(ActionEvent event) throws IOException {
        cargarFormulario("/frmeliminarcliente.fxml");
        //switchscene(event, "/frmeliminarcliente.fxml");
    }

    @FXML
    void clk_eliminarpedido(ActionEvent event) throws IOException  {
        switchscene(event, "/frmeliminarpedido.fxml");
    }

    @FXML
    void clk_insertararticulo(ActionEvent event) throws IOException {
        switchscene(event, "/frmaltaarticulo.fxml");
    }

    @FXML
    void clk_insertarcliente(ActionEvent event) throws IOException {
        cargarFormulario("/frmaltacliente.fxml");
        //switchscene(event, "/frmaltacliente.fxml");
    }

    @FXML
    void clk_insertarpedido(ActionEvent event) throws IOException {
        switchscene(event, "/frmaltapedido.fxml");
    }

    @FXML
    void clk_listaestandar(ActionEvent event)  throws IOException {
        // TODO
    }

    @FXML
    void clk_listapremium(ActionEvent event)  throws IOException {
        // TODO
    }

    @FXML
    void clk_listarenviados(ActionEvent event)  throws IOException {
        // TODO
    }

    @FXML
    void clk_listarpendientes(ActionEvent event) throws IOException  {
        // TODO
    }

    @FXML
    void clk_mostrartodosarticulos(ActionEvent event) throws IOException {
        switchscene(event, "/frmlistararticulos.fxml");
    }

    @FXML
    void clk_mostrartodosclientes(ActionEvent event) throws IOException {
        switchscene(event, "/frmlistarclientes.fxml");
    }

    @FXML
    void clk_mostrartodospedidos(ActionEvent event) throws IOException {
        switchscene(event, "/frmlistarpedidos.fxml");
    }

    @FXML
    void clk_salir(ActionEvent event)  throws IOException {
        Platform.exit();
        System.exit(0);
    }


    // -----------------------------
    public void initialize(URL location, ResourceBundle resources){
        // Podemos crear los eventos aquí dentro.

    }

    public void switchscene_old(ActionEvent event, String formulario) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            VBox vbox = loader.load();
            scene = new Scene(vbox);
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.showAndWait();

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void switchscene(ActionEvent event, String formulario) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            root = loader.load();
            scene = new Scene(root);
            Image icon = new Image("logo.jpeg");
            stage.getIcons().add(icon);
            stage.resizableProperty().setValue(false);  // Para hacer que se haga grande.
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void cargarFormulario(String fxml){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent formulario = loader.load();
            mainContainer.setCenter(formulario);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}