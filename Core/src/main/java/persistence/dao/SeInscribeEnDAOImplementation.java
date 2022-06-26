package persistence.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import persistence.entities.SeInscribeEn;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SeInscribeEnDAOImplementation implements ISeInscribeEnDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(SeInscribeEn seInscribeEn) {
        entityManager.createNativeQuery("INSERT INTO \"Se_Inscribe_En\" (cod_base, id_socio,id_turno,fecha_inscr,activo) VALUES (:cod_base,:id_socio,:id_turno,:fecha_inscr,:activo)")
                .setParameter("cod_base", seInscribeEn.getCod_base())
                .setParameter("id_socio", seInscribeEn.getId_socio())
                .setParameter("id_turno", seInscribeEn.getId_turno())
                .setParameter("fecha_inscr", seInscribeEn.getFecha())
                .setParameter("activo", seInscribeEn.isActivo())
                .executeUpdate();
    }
}
