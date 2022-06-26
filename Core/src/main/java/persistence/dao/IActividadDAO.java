package persistence.dao;

import persistence.entities.Actividad;

import java.util.List;

public interface IActividadDAO {
    public Actividad findOne(long id_actividad);

    public List<Actividad> findAll();

    public List<Actividad> findAllAranceladas();
}
