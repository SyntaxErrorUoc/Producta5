package ConexionMySQL;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConexionMySQL {

    /**
     * Método para conercar con Hibernate a la BBDD
     * @param claseEntidad
     * @return
     */
    public static SessionFactory conectarMySQL(Class<?> claseEntidad) {
        try {
            //   addAnnotatedClass(Articulo.class).buildSessionFactory();
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            // Agregamos la clase mapeada a la configuración
            configuration.addAnnotatedClass(claseEntidad);

            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
