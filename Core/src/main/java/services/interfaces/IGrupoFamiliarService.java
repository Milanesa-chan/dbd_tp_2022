package services.interfaces;

import org.springframework.stereotype.Repository;
import persistence.entities.GrupoFamiliar;

import java.util.List;


public interface IGrupoFamiliarService
{
    public GrupoFamiliar findOne(long cod_base);

    public int save(GrupoFamiliar grupoFamiliar);

    public List<GrupoFamiliar> getGrupoFamiliarList();
}
