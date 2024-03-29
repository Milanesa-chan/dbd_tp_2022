package persistence.dao;

import persistence.entities.Socio;

import java.util.List;

public interface ISocioDAO
{
    public Socio findOne(long cod_base,long id_socio);

    public int save(Socio socio);

    List<Socio> getSocios();

    public int getCantidadSociosFamilia(long cod_base);

    List<Socio> getSociosTitulares();

    List<Socio> findAll();

    List<Socio> getSociosByCodBase(long cod_base);
}

