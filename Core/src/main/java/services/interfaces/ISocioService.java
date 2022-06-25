package services.interfaces;

import persistence.entities.Socio;

public interface ISocioService
{
    public Socio findOne(long cod_base, long id_socio);
    public int save(Socio socio);
}
