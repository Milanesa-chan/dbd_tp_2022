package persistence.dao;

import persistence.entities.GrupoFamiliar;

import java.util.List;

public interface IGrupoFamiliarDAO {
    public GrupoFamiliar findOne(long cod_base);
    public int save(GrupoFamiliar grupoFamiliar);

    List<GrupoFamiliar> getGrupoFamiliarList();
}
