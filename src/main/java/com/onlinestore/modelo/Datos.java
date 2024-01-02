package com.onlinestore.modelo;

import com.onlinestore.ConexionMySQL.DatabaseConnectionException;
import com.onlinestore.Factory.FactoryDAO;

import java.util.ArrayList;

public class Datos {

    private FactoryDAO factory;

    //Connection conn;

    /**
     * Conexión con el FactoryDAO
     * @throws DatabaseConnectionException
     */
    public Datos() throws DatabaseConnectionException {

        this.factory = new FactoryDAO();

    }

    // Métodos para gestionar Artículos

    /**
     * Método para agregar un artículo en la BBDD mediante Hibernate.
     * @param a Objeto de la clase Articulo
     */
    public void agregarArticulo(Articulo a)  {

        this.factory.articulo.insertar(a);
    }

    /**
     * Método para modificar un artículo en la BBDD mediante Hibernate.
     * @param a Objeto de la clase Articulo
     */
    public void modificarArticulo(Articulo a ){
        this.factory.articulo.modificar(a);

    }

    /**
     * Método para eliminar un artículo en la BBDD mediante Hibernate.
     * @param cp Código del Producto a eliminar
     */
    public void eliminarArticulo(String cp) {
        this.factory.articulo.eliminar(cp);

    }

    /**
     * Método para obtener un articulo de la BBDD
     * @param id Codigo del Producto de la BBDD
     * @return
     */
    public Articulo obtenerArticulo(String id) {
        Articulo a = this.factory.articulo.obtenerUno(id);
        return  a;
    }

    /**
     * Método para obtener todos los artículos en la BBDD
     * @return ArrayList con los artículos
     */
    public ArrayList<Articulo> obtenerArticulos() {
        ArrayList<Articulo> articulos;
        articulos = this.factory.articulo.obtenerTodos();
        return articulos;
    }


    // Métodos para gestionar Clientes

    /**
     * Método para agregar un cliente en la BBDD mediante Hibernate
     * @param c Objeto tipo Cliente que contiene la información del mismo.
     */
    public void agregarCliente(Cliente c)  {
        this.factory.cliente.insertar(c);

    }

    /**
     * Método para eliminar un cliente en la BBDD mediante Hibernate
     * @param mail mail del cliente que es el identificador único en la BBDD
     */
    public void eliminarCliente(String mail) {

        this.factory.cliente.eliminar(mail);

    }

    /**
     * Método para modificar los datos del cliente
     * @param cliente Objeto de la Clase Cliente que contiene la información del mismo.
     */
    public void modificarCliente(Cliente cliente) {
        this.factory.cliente.modificar(cliente);

    }

    /**
     * Método para mostrar todos los clientes de la BBDD
     * @return ArrayList con la lista de clientes
     */
    public ArrayList<Cliente> mostrarTodosLosClientes() {
        ArrayList<Cliente> listaClientes;
        listaClientes = this.factory.cliente.obtenerTodos();
        return listaClientes;

    }

    /**
     * Método para mostrar un listado de cliente según unos criterios
     * @param c Campo de la BBDD para filtrar
     * @param d Valor por el que filtrar.
     * @return ArrayList con las coincidencias.
     */
    public ArrayList<Cliente> mostrarPorTipo(String c,String d){

        ArrayList<Cliente> listado;
        listado = this.factory.cliente.obtenerPorCriterio(c,d);
        return listado;
    }

    /**
     * Método para mostrar la información de un cliente
     * @param mail idetificador del cliente que corresponde al mail.
     * @return Objeto de la Clase Cliente con la información del mismo
     */
    public Cliente obtenerCliente (String mail) {

        return this.factory.cliente.obtenerUno(mail);
    }


    // Métodos para gestionar Pedidos

    /**
     * Método para agregar un pedido a la BBDD en base a Hibernate.
     * @param pedido Objeto de la Clase Pedido con la información del mismo.
     */
    public void agregarPedido(Pedido pedido) {
        this.factory.pedido.insertar(pedido);

    }

    /**
     * Método para eliminar un pedido de la BBDD
     * @param id número de pedido que es un identificador único en la BBDD
     */
    public void eliminarPedido(int id) {
        this.factory.pedido.eliminar(id);
    }

    /**
     * Método que obtiene todos los pedidos de la BBDD
     * @return ArrayList con todos los pedidos de la BBDD
     */
    public ArrayList<Pedido> obtenerPedidos() {
        ArrayList<Pedido> lista = new ArrayList<>();
        lista = this.factory.pedido.obtenerTodos();
        return lista;
    }

    /**
     * Método que filtra los pedidos por el mail del cliente
     * @param idMail mail del cliente que es un identificador único en la tabla cliente
     * @return ArrayList con todas las coincidencias del filtro
     */
    public ArrayList<Pedido> obtenerPedidosfiltro(String idMail) {
        ArrayList<Pedido> lista = new ArrayList<>();
        lista = this.factory.pedido.obtenerPorCriterio("mail", idMail);
        return lista;
    }

    /**
     * Método para obtener la información de un pedido.
     * @param cp Indetificador único que corresponde al Código del Producto
     * @return Objeto de la Clase Pedido con toda la información.
     */
    public Pedido obtenerUnPedido(int cp){
        return this.factory.pedido.obtenerUno(cp);
    }

    // USUARIOS ------------------------------------------------------------------------

    public boolean verificarusuario(String user){
        boolean encontrado=false;
        Usuarios check = this.factory.usuario.obtenerUno(user);
        if (check==null){
            return false;
        }else{
            return true;
        }
    }

    public Usuarios obtenerUnUsuario(String id){
        return this.factory.usuario.obtenerUno(id);
    }

}