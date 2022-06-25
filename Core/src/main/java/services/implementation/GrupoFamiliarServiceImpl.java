package services.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import persistence.dao.IGrupoFamiliarDAO;
import services.interfaces.IGrupoFamiliarService;
import persistence.entities.GrupoFamiliar;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class GrupoFamiliarServiceImpl implements IGrupoFamiliarService
{

    @Autowired
    private IGrupoFamiliarDAO grupoFamiliarDAO;

    @Override
    public GrupoFamiliar findOne(long cod_base) {
        return grupoFamiliarDAO.findOne(cod_base);
    }

}
