package persistence.dao;

import persistence.entities.Turno;

import java.util.List;

public interface ITurnoDAO
{
    public Turno findOne(long id_turno);

    public List<Turno> findAll();
}
