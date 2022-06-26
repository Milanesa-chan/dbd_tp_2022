package services.interfaces;

import persistence.entities.Actividad;

import java.util.List;

public interface IActividadService {
    public Actividad findOne(long id_actividad);

    public List<Actividad> findAll();

    public List<Actividad> findAllAranceladas();
}
