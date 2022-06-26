package persistence.dao;

import org.springframework.stereotype.Repository;
import persistence.entities.GrupoFamiliar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


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


    @Transactional
    @Override
    public int save(GrupoFamiliar grupoFamiliar) {
        Query q = entityManager.createNativeQuery("INSERT INTO \"Grupo_Familiar\" (domicilio, telefono) VALUES (:domicilio, :telefono) RETURNING cod_base");
        q.setParameter("domicilio", grupoFamiliar.getDomicilio());
        q.setParameter("telefono", grupoFamiliar.getTelefono());
//      q.executeUpdate();
        return (int) q.getSingleResult();
    }

    @Override
    public List<GrupoFamiliar> getGrupoFamiliarList() {
        Query q = entityManager.createNativeQuery("SELECT * FROM \"Grupo_Familiar\"");
        return q.getResultList();
    }
}
