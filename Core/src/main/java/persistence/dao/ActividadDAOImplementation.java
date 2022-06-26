package persistence.dao;

import persistence.entities.Actividad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ActividadDAOImplementation implements IActividadDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Actividad findOne(long id_actividad) {
        Query q = entityManager.createNativeQuery("SELECT * FROM \"Actividad\" WHERE id_actividad = :id_actividad", Actividad.class);
        q.setParameter("id_actividad", id_actividad);
        Actividad actividad = (Actividad) q.getSingleResult();
        return actividad;
    }

    @Override
    public List<Actividad> findAll() {
        Query q = entityManager.createNativeQuery("SELECT * FROM \"Actividad\"", Actividad.class);
        List<Actividad> actividades = q.getResultList();
        return actividades;
    }

    @Override
    public List<Actividad> findAllAranceladas() {
        Query q = entityManager.createNativeQuery("SELECT * FROM \"Actividad\" WHERE tipo = 'A'", Actividad.class);
        List<Actividad> actividades = q.getResultList();
        return actividades;
    }


}
