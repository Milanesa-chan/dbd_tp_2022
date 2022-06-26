package persistence.dao;

import org.springframework.stereotype.Repository;
import persistence.entities.Socio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

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


    @Transactional
    @Override
    public int save(Socio socio) {
        Query q = entityManager.createNativeQuery("INSERT INTO \"Socio\" (cod_base,nombre, apellido, celular, fecha_nac,fecha_inscripcion_club, tipo) " +
                "VALUES (:cod_base,:nombre, :apellido, :celular, :fecha_nac,:fecha_inscripcion_club, :tipo) RETURNING id_socio");
        q.setParameter("cod_base", socio.getCod_base());
        q.setParameter("nombre", socio.getNombre());
        q.setParameter("apellido", socio.getApellido());
        q.setParameter("celular", socio.getCelular());
        q.setParameter("fecha_nac", socio.getFechaNac());
        q.setParameter("fecha_inscripcion_club", socio.getFechaInscripcionClub());
        q.setParameter("tipo", socio.getTipo());
        return (int) q.getSingleResult();
    }

    @Override
    public List<Socio> getSocios() {
        Query q = entityManager.createNativeQuery("SELECT * FROM \"Socio\"", Socio.class);
        return (List<Socio>) q.getResultList();
    }

    @Override
    public int cantidadSociosFamilia(long cod_base) {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM \"Socio\" WHERE cod_base = :cod_base", Integer.class);
        q.setParameter("cod_base", cod_base);
        return (int) q.getSingleResult();
    }
}

