package persistence.dao;

import org.springframework.stereotype.Repository;
import persistence.entities.GrupoFamiliar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
public class GrupoFamiliarDAOImplementation implements IGrupoFamiliarDAO {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public GrupoFamiliar findOne(long cod_base) {
        Query q = entityManager.createNativeQuery("SELECT * FROM \"Grupo_Familiar\" WHERE cod_base = :cod_base", GrupoFamiliar.class);
        q.setParameter("cod_base", cod_base);
        GrupoFamiliar grupoFamiliar = (GrupoFamiliar) q.getSingleResult();
        return grupoFamiliar;
    }
}
