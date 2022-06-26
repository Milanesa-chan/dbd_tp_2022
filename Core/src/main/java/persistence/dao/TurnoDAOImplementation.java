package persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import persistence.entities.Turno;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TurnoDAOImplementation implements ITurnoDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Turno findOne(long id_turno) {
    Query query = entityManager.createQuery("SELECT t FROM Turno t WHERE t.id_turno = :id_turno");
    query.setParameter("id_turno", id_turno);
    return (Turno) query.getSingleResult();
    }

    @Override
    public List<Turno> findAll() {
        Query q = entityManager.createNativeQuery("SELECT * FROM \"Turno\"");
        return q.getResultList();
    }

    @Override
    public List<Turno> findAllByActividad(long id_actividad) {
        List<Turno> turnos = entityManager.createNativeQuery("SELECT * FROM \"Turno\" WHERE id_actividad = :id_actividad" +
                        " ORDER BY \n" +
                        "     CASE\n" +
                        "          WHEN dia = 'Lunes' THEN 1\n" +
                        "          WHEN dia = 'Martes' THEN 2\n" +
                        "          WHEN dia = 'Miercoles' THEN 3\n" +
                        "          WHEN dia = 'Jueves' THEN 4\n" +
                        "          WHEN dia = 'Viernes' THEN 5\n" +
                        "          WHEN dia = 'Sabado' THEN 6\n" +
                        "          WHEN dia = 'Domingo' THEN 7\n" +
                        "     END ASC,hora")
                .setParameter("id_actividad", id_actividad).getResultList();
        return turnos;
    }
}
