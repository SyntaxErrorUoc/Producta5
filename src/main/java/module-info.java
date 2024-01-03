module com.onlinestore.onlinestore_producto {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires javaee.api;

    opens com.onlinestore to javafx.fxml;
    exports com.onlinestore;
    exports com.onlinestore.vista;
    opens com.onlinestore.vista to javafx.fxml;
    exports com.onlinestore.controlador;
    opens com.onlinestore.controlador to javafx.fxml;
    exports com.onlinestore.modelo;
    opens com.onlinestore.modelo to org.hibernate.orm.core;

}