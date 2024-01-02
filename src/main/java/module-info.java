module com.onlinestore.onlinestore_producto {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires javaee.api;

    opens com.onlinestore.onlinestore_producto5 to javafx.fxml;
    exports com.onlinestore.onlinestore_producto5;
    exports vista;
    opens vista to javafx.fxml;
    exports controlador;
    opens controlador to javafx.fxml;
    exports modelo;
    opens modelo to org.hibernate.orm.core;

}