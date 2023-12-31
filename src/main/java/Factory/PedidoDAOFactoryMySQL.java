package Factory;

import DAO.PedidoDAO;
import modelo.Articulo;
import modelo.ClienteEstandar;
import modelo.ClientePremium;
import modelo.Pedido;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class PedidoDAOFactoryMySQL implements PedidoDAO {

    private SessionFactory conn;

    /**
     * Conexión a la BBDD
     * @param Conn Conexión de Hibernate.
     */
    public PedidoDAOFactoryMySQL(SessionFactory Conn){
        this.conn = Conn;
    }

    /**
     * Método para la inserción de un pedido en la BBDD
     * @param p Objeto clase Pedido que contiene la información a insertar.
     * @return String con el resultado de la inserción.
     */
    @Override
    public String insertar(Pedido p) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pedido.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            session.save(p);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();


        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Método para modificar la información de un pedido
     * @param p Objeto que contiene la clase pedido con la información necesaria.
     */
    @Override
    public void modificar(Pedido p) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pedido.class)
                .addAnnotatedClass(Articulo.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(p);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            // -------------------------------------------------------------

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Método para eliminar un pedido de la BBDD
     * @param a Número de pedido a eliminar.
     */
    @Override
    public void eliminar(Integer a) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class)
                .addAnnotatedClass(ClientePremium.class)
                .addAnnotatedClass(Articulo.class)
                .addAnnotatedClass(ClienteEstandar.class)
                .buildSessionFactory();


        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Pedido p = session.get(Pedido.class,a);
            session.delete(p);
            session.getTransaction().commit();
            session.close();

            sessionFactory.close();
            // -------------------------------------------------------------

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Método que obtiene una lista de todos los pedidos de la BBDD
     * @return ArrayList con todos los pedidos
     */
    @Override
    public ArrayList<Pedido> obtenerTodos() {

        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class)
                .addAnnotatedClass(ClientePremium.class)
                .addAnnotatedClass(Articulo.class)
                .addAnnotatedClass(ClienteEstandar.class)
                .buildSessionFactory();


        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            String filtro = "from Pedido";
            Query<Pedido> query = session.createQuery(filtro, Pedido.class);
            ArrayList<Pedido> pedidios = (ArrayList<Pedido>) query.list();
            session.close();

            sessionFactory.close();
            // -------------------------------------------------------------
            return pedidios;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método para obtener un pedido.
     * @param id Número de pedido
     * @return Objeto de la clase cliente con la información relativa al pedido.
     */
    @Override
    public Pedido obtenerUno(Integer id) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pedido.class)
                .addAnnotatedClass(Articulo.class)
                .addAnnotatedClass(ClientePremium.class)
                .addAnnotatedClass(ClienteEstandar.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Pedido c = session.get(Pedido.class,id);

            session.close();

            sessionFactory.close();
            // -------------------------------------------------------------
            return c;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Método para obtener un listado de pedidos según los criterios indicados.
     * @param columna Indica el número de la columna de la BBDD a filtrar
     * @param criterio Indica el valor por el que filtrar la BBDD.
     * @return
     */
    @Override
    public ArrayList<Pedido> obtenerPorCriterio(Integer columna, Integer criterio) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pedido.class)
                .addAnnotatedClass(ClientePremium.class)
                .addAnnotatedClass(Articulo.class)
                .addAnnotatedClass(ClienteEstandar.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            ArrayList<Pedido> devolucion = new ArrayList<>();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Pedido> criteriaQuery = builder.createQuery(Pedido.class);
            Root<Pedido> root = criteriaQuery.from(Pedido.class);


            criteriaQuery.where(builder.equal(root.get(String.valueOf(columna)), criterio));

            Query<Pedido> query = session.createQuery(criteriaQuery);
            devolucion = (ArrayList<Pedido>) query.getResultList();

            session.getTransaction().commit();
            return devolucion;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *
     * @param columna
     * @param criterio
     * @return
     */
    @Override
    public ArrayList<Pedido> obtenerPorCriterio(Integer columna, String criterio) {
        return null;
    }

    /**
     * Método para obtener un listado de pedidos según los criterios indicados.
     * @param columna Nombre de la columna por la que filtrar la BBDD
     * @param criterio Valor para filtrar la columna
     * @return ArrayList con los datos que coinciden con el filtro.
     */
    @Override
    public ArrayList<Pedido> obtenerPorCriterio(String columna, String criterio) {

        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class)
                .addAnnotatedClass(ClientePremium.class)
                .addAnnotatedClass(Articulo.class)
                .addAnnotatedClass(ClienteEstandar.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            ArrayList<Pedido> devolucion = new ArrayList<>();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Pedido> criteriaQuery = builder.createQuery(Pedido.class);
            Root<Pedido> root = criteriaQuery.from(Pedido.class);

            // Agregar la cláusula WHERE
            criteriaQuery.where(builder.equal(root.get(String.valueOf(columna)), criterio));

            Query<Pedido> query = session.createQuery(criteriaQuery);
            devolucion = (ArrayList<Pedido>) query.getResultList();

            session.getTransaction().commit();
            return devolucion;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
