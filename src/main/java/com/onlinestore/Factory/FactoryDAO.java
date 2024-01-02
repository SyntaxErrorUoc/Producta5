package com.onlinestore.Factory;

import com.onlinestore.ConexionMySQL.ConexionMySQL;
import com.onlinestore.ConexionMySQL.DatabaseConnectionException;
import com.onlinestore.DAO.ArticuloDAO;
import com.onlinestore.DAO.ClienteDAO;
import com.onlinestore.DAO.PedidoDAO;
import com.onlinestore.DAO.UsuarioDAO;
import com.onlinestore.modelo.Articulo;
import com.onlinestore.modelo.Cliente;
import com.onlinestore.modelo.Pedido;
import com.onlinestore.modelo.Usuarios;
import org.hibernate.SessionFactory;

import java.sql.Connection;

public class FactoryDAO {


    public ArticuloDAO articulo;
    public PedidoDAO pedido;
    public ClienteDAO cliente;
    public UsuarioDAO usuario;

    /**
     * Método que activa las conexiones en la BBDD dependiendo del tipo de BBDD. En nuestro caso MySQL
     * @throws DatabaseConnectionException
     */
    public FactoryDAO() throws DatabaseConnectionException {

        try{
            SessionFactory articulo = MySQLArticulo();
            this.articulo = new ArticuloDAOFactoryMySQL(articulo);

            SessionFactory cliente = MySQLCliente();
            this.cliente = new ClienteDAOFactoryMySQL(cliente);

            SessionFactory pedido = MySQLPedido();
            this.pedido = new PedidoDAOFactoryMySQL(pedido);

            SessionFactory usuario = MySQLUsuarios();
            this.usuario = new UsuarioDAOFactoryMySQL(usuario);

        }catch(Exception e){
            System.out.println(e);
        }
        if (this.articulo != null|| this.cliente != null || this.pedido != null) {
            System.out.println("Es una base de datos Mysql");

        }
        else {
            Connection conn = null;

            throw new DatabaseConnectionException(("No es una base de datos MySQL"));

        }
    }

    /**
     * Método que conecta con la tabla articulo de la BBDD
     * @return Conexión a la BBDD
     */
    public SessionFactory MySQLArticulo()  {
        SessionFactory ConexionArticulo = null;
        ConexionArticulo = ConexionMySQL.conectarMySQL(Articulo.class);

        return ConexionArticulo;
    }

    /**
     * Método que conecta con la tabla cliente de la BBDD
     * @return Conexión a la BBDD
     */
    public SessionFactory MySQLCliente()  {
        SessionFactory ConexionCliente = null;
        ConexionCliente = ConexionMySQL.conectarMySQL(Cliente.class);

        return ConexionCliente;
    }

    /**
     * Método que conecta con la tabla pedido de la BBDD
     * @return Conexión a la BBDD
     */
    public SessionFactory MySQLPedido()  {
        SessionFactory ConexionPedido = null;
        ConexionPedido = ConexionMySQL.conectarMySQL(Pedido.class);

        return ConexionPedido;
    }

    /**
     * Método que conecta con la tabla pedido de la BBDD
     * @return Conexión a la BBDD
     */
    public SessionFactory MySQLUsuarios()  {
        SessionFactory ConexionPedido = null;
        ConexionPedido = ConexionMySQL.conectarMySQL(Usuarios.class);

        return ConexionPedido;
    }

    /**
     * Método de conexión a otra BBDD que no sea MySQL
     * @return En este caso null porque no se ha implementado.
     */
    public Connection NoMysql(){
        return null;
    }


}
