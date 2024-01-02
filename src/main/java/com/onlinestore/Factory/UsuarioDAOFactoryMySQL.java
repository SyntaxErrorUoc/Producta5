package com.onlinestore.Factory;

import com.onlinestore.DAO.UsuarioDAO;
import com.onlinestore.modelo.Usuarios;
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
    public String insertar(Usuarios user) {
        // Creamos el enlace con la BBDD
        SessionFactory sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuarios.class)
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
    public void modificar(Usuarios user) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuarios.class)
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
                .addAnnotatedClass(Usuarios.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Usuarios u = session.get(Usuarios.class,campo);
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
    public ArrayList<Usuarios> obtenerTodos() {

        SessionFactory sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuarios.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String filtro = "from Usuarios";
            Query<Usuarios> query = session.createQuery(filtro, Usuarios.class);
            ArrayList<Usuarios> users = (ArrayList<Usuarios>) query.list();
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
    public Usuarios obtenerUno(String id) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuarios.class).buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Usuarios u = session.get(Usuarios.class,id);
            session.close();
            sessionFactory.close();
            return u;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Usuarios> obtenerPorCriterio(String columna, String criterio) {
        SessionFactory sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuarios.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            ArrayList<Usuarios> listausuarios = new ArrayList<>();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Usuarios> criteriaQuery = builder.createQuery(Usuarios.class);
            Root<Usuarios> root = criteriaQuery.from(Usuarios.class);
            // Agregar la cláusula WHERE
            criteriaQuery.where(builder.equal(root.get(columna), criterio));
            Query<Usuarios> query = session.createQuery(criteriaQuery);
            listausuarios = (ArrayList<Usuarios>) query.getResultList();
            session.getTransaction().commit();
            return listausuarios;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
