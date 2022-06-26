package services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.ISocioDAO;
import persistence.entities.Socio;
import services.interfaces.ISocioService;

import java.util.List;

@Service
public class SocioServiceImpl implements ISocioService {

    @Autowired
    private ISocioDAO socioDAO;

    @Override
    public Socio findOne(long cod_base, long id_socio) {
        return socioDAO.findOne(cod_base, id_socio);
    }

    @Override
    public int save(Socio socio) {
        return socioDAO.save(socio);
    }

    @Override
    public List<Socio> getSociosTitulares() {
        return socioDAO.getSociosTitulares();
    }

    @Override
    public List<Socio> getSociosByCodBase(long cod_base) {
        return socioDAO.getSociosByCodBase(cod_base);
    }

    @Override
    public int getCantidadSociosFamilia(long cod_base) {
        return socioDAO.getCantidadSociosFamilia(cod_base);
    }

    @Override
    public List<Socio> findAll() {
        return socioDAO.findAll();
    }

}
