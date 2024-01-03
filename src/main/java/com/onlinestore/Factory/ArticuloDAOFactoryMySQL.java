package com.onlinestore.Factory;

import com.onlinestore.DAO.ArticuloDAO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.onlinestore.modelo.Articulo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class ArticuloDAOFactoryMySQL implements ArticuloDAO{

    private SessionFactory conn=null;

    /**
     * Método para conectar con la BBDD
     * @param conn Conexión vía Hibernate.
     */
    public ArticuloDAOFactoryMySQL(SessionFactory conn){
        this.conn = conn;
    }

    /**
     * Método para insertar un artículo en la BBDD
     * @param a Objeto que contiene la clase artículo.
     * @return String con el resultado de la inserción
     */
    @Override
    public String insertar(Articulo a) {
        // Creamos el enlace con la BBDD
        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Articulo.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.save(a);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Método para modificar el artículo en la BBDD
     * @param a Objeto que contiene la clase Articulo
     */
    @Override
    public void modificar(Articulo a) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Articulo.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(a);
            session.getTransaction().commit();
            session.flush();
            session.close();

            sessionFactory.close();
            // -------------------------------------------------------------

        }catch (Exception e){

        }

    }

    /**
     * Método para eliminar un artículo en la BBDD
     * @param cp Es el código de producto.
     */
    @Override
    public void eliminar(String cp) {

        SessionFactory sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Articulo.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Articulo a = session.get(Articulo.class,cp);

            session.delete(a);
            session.getTransaction().commit();
            session.close();

            sessionFactory.close();
            // -------------------------------------------------------------

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Método para obtener una lista con todos los artículos de la BBDD
     * @return ArrayList con todos los artículos.
     */
    @Override
    public ArrayList<Articulo> obtenerTodos() {

        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Articulo.class)
                .buildSessionFactory();


        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            String filtro = "from Articulo";
            Query<Articulo> query = session.createQuery(filtro, Articulo.class);
            ArrayList<Articulo> articulos = (ArrayList<Articulo>) query.list();
            session.close();

            sessionFactory.close();
            // -------------------------------------------------------------
            return articulos;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método para obtener un Artículo de la BBDD
     * @param id Es el Codigo de Producto.
     * @return objeto que contiene la Clase Articulo con sus valores.
     */
    @Override
    public Articulo obtenerUno(String id) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Articulo.class).buildSessionFactory();

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Articulo a = session.get(Articulo.class,id);

            session.close();

            sessionFactory.close();
            // -------------------------------------------------------------
            return a;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método que obtiene un listado de artículos serúin un criterio dado
     * @param columna indica el nombre de la columna de la BBDD
     * @param criterio indica el valor a buscar
     * @return ArraList con las coincidencias.
     */
    @Override
    public ArrayList<Articulo> obtenerPorCriterio(String columna, String criterio) {

        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Articulo.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            ArrayList<Articulo> devolucion = new ArrayList<>();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Articulo> criteriaQuery = builder.createQuery(Articulo.class);
            Root<Articulo> root = criteriaQuery.from(Articulo.class);

            // Agregar la cláusula WHERE
            criteriaQuery.where(builder.equal(root.get(columna), criterio));

            Query<Articulo> query = session.createQuery(criteriaQuery);
            devolucion = (ArrayList<Articulo>) query.getResultList();

            session.getTransaction().commit();
            return devolucion;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
