package services.interfaces;

import persistence.entities.Turno;

import java.util.List;

public interface ITurnoService {

    public Turno findOne(long cod_base, long id_turno);

    public List<Turno> findAll();
}
