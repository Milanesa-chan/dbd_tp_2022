package services.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import persistence.dao.IGrupoFamiliarDAO;
import services.interfaces.IGrupoFamiliarService;
import persistence.entities.GrupoFamiliar;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class GrupoFamiliarServiceImpl implements IGrupoFamiliarService
{

    @Autowired
    private IGrupoFamiliarDAO grupoFamiliarDAO;

    @Override
    public GrupoFamiliar findOne(long cod_base) {
        return grupoFamiliarDAO.findOne(cod_base);
    }

    @Override
    public int save(GrupoFamiliar grupoFamiliar) {
        return grupoFamiliarDAO.save(grupoFamiliar);
    }

    @Override
    public List<GrupoFamiliar> getGrupoFamiliarList() {
        return grupoFamiliarDAO.getGrupoFamiliarList();
    }


}
