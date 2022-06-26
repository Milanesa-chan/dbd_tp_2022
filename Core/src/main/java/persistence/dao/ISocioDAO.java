package persistence.dao;

import persistence.entities.Socio;

import java.util.List;

public interface ISocioDAO
{
    public Socio findOne(long cod_base,long id_socio);

    public int save(Socio socio);

    List<Socio> getSocios();

    public int cantidadSociosFamilia(long cod_base);
}

