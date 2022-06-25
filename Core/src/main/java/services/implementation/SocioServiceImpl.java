package services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.ISocioDAO;
import persistence.entities.Socio;
import services.interfaces.ISocioService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SocioServiceImpl implements ISocioService {

    @Autowired
    private ISocioDAO socioDAO;

    @Override
    public Socio findOne(long cod_base, long id_socio) {
        return socioDAO.findOne(cod_base, id_socio);
    }
}
