package services.interfaces;

import persistence.entities.Socio;

import java.util.List;

public interface ISocioService
{
    public Socio findOne(long cod_base, long id_socio);

    public int save(Socio socio);

    List<Socio> getSociosTitulares();

    List<Socio> getSociosByCodBase(long cod_base);

    public int getCantidadSociosFamilia(long cod_base);

    public List<Socio> findAll();
}
