package services.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import persistence.dao.GrupoFamiliarDAO;
import services.interfaces.IGrupoFamiliarService;
import persistence.entities.GrupoFamiliar;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class GrupoFamiliarServiceImpl implements IGrupoFamiliarService
{

    @Autowired
    private GrupoFamiliarDAO grupoFamiliarDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public GrupoFamiliar findOne(long cod_base) {
        return grupoFamiliarDAO.findOne(cod_base);
    }

}
