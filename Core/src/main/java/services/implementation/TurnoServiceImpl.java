package services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.ITurnoDAO;
import persistence.entities.Turno;
import services.interfaces.ITurnoService;

import java.util.List;

@Service
public class TurnoServiceImpl implements ITurnoService {

    @Autowired
    private ITurnoDAO turnoDAO;

    @Override
    public Turno findOne(long cod_base, long id_turno) {
        return turnoDAO.findOne(id_turno);
    }

    @Override
    public List<Turno> findAllByActividad(long id_actividad) {
        return turnoDAO.findAllByActividad(id_actividad);
    }

    @Override
    public List<Turno> findAll() {
        return turnoDAO.findAll();
    }
}
