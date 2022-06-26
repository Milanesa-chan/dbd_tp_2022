package services.interfaces;

import persistence.entities.Socio;

import java.math.BigInteger;
import java.util.List;

public interface ISocioService
{
    public Socio findOne(long cod_base, long id_socio);

    public int save(Socio socio);

    List<Socio> getSociosTitulares();

    public int getCantidadSociosFamilia(long cod_base);
}
