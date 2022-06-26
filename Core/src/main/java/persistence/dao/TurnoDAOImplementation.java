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
        Query q = entityManager.createNativeQuery("SELECT * FROM \"Turno\"", Turno.class);
        return q.getResultList();
    }
}
