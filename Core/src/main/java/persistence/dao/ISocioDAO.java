package persistence.dao;

import persistence.entities.Socio;

public interface ISocioDAO
{
    public Socio findOne(long cod_base,long id_socio);
}

