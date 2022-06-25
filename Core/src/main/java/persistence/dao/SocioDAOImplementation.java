package persistence.dao;

import org.springframework.stereotype.Repository;
import persistence.entities.Socio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class SocioDAOImplementation implements ISocioDAO{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Socio findOne(long cod_base, long id_socio) {
        Query q = entityManager.createNativeQuery("SELECT * FROM \"Socio\" WHERE cod_base = :cod_base AND id_socio = :id_socio", Socio.class);
        q.setParameter("cod_base", cod_base);
        q.setParameter("id_socio", id_socio);
        return (Socio) q.getSingleResult();
    }
}
