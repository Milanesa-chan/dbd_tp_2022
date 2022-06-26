package services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.IActividadDAO;
import persistence.entities.Actividad;
import services.interfaces.IActividadService;

import java.util.List;

@Service
public class ActividadServiceImpl implements IActividadService {

    @Autowired
    private IActividadDAO actividadDAO;

    @Override
    public Actividad findOne(long id_actividad) {
        return actividadDAO.findOne(id_actividad);
    }

    @Override
    public List<Actividad> findAll() {
        return actividadDAO.findAll();
    }

    @Override
    public List<Actividad> findAllAranceladas() {
        return actividadDAO.findAllAranceladas();
    }

    @Override
    public List<Actividad> findAllBySocio(int cod_base, int id_socio) {
        return actividadDAO.findAllBySocio(cod_base, id_socio);
    }
}
