package com.onlinestore.Factory;

import com.onlinestore.DAO.UsuarioDAO;
import com.onlinestore.modelo.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class UsuarioDAOFactoryMySQL implements UsuarioDAO {

    private SessionFactory conn=null;

    public UsuarioDAOFactoryMySQL(SessionFactory usuario) {
        this.conn = conn;
    }

    @Override
    public String insertar(Usuario user) {
        // Creamos el enlace con la BBDD
        SessionFactory sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void modificar(Usuario user) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.flush();
            session.close();
            sessionFactory.close();
        }catch (Exception e){
        }
    }

    @Override
    public void eliminar(String campo) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Usuario u = session.get(Usuario.class,campo);
            session.delete(u);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            // -------------------------------------------------------------
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Usuario> obtenerTodos() {

        SessionFactory sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String filtro = "from Usuarios";
            Query<Usuario> query = session.createQuery(filtro, Usuario.class);
            ArrayList<Usuario> users = (ArrayList<Usuario>) query.list();
            session.close();
            sessionFactory.close();
            // -------------------------------------------------------------
            return users;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Usuario obtenerUno(String nombre) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class).buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Usuario u = session.get(Usuario.class, nombre);
            session.close();
            sessionFactory.close();
            return u;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> obtenerPorCriterio(String columna, String criterio) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            ArrayList<Usuario> listausuarios = new ArrayList<>();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
            Root<Usuario> root = criteriaQuery.from(Usuario.class);
            // Agregar la cláusula WHERE
            criteriaQuery.where(builder.equal(root.get(columna), criterio));
            Query<Usuario> query = session.createQuery(criteriaQuery);
            listausuarios = (ArrayList<Usuario>) query.getResultList();
            session.getTransaction().commit();
            return listausuarios;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
