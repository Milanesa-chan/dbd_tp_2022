package persistence.dao;

import org.springframework.stereotype.Repository;
import persistence.entities.Actividad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
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

    @Override
    public List<Actividad> findAllBySocio(int cod_base, int id_socio) {
        Query q = entityManager.createNativeQuery("SELECT a.* FROM \"Socio\" s,\"Actividad\" a where ((s.tipo='I' and a.es_infantil = true)\n" +
                "OR (s.tipo='M' and a.es_mayor = true) OR (s.tipo='V' and a.es_vitalicio = true))\n" +
                "AND (s.cod_base,s.id_socio) = (:cod_base,:id_socio)");
        q.setParameter("cod_base", cod_base);
        q.setParameter("id_socio", id_socio);
        List<Actividad> actividades = q.getResultList();
        return actividades;
    }


}
