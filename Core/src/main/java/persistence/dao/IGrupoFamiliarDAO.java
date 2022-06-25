package persistence.dao;

import persistence.entities.GrupoFamiliar;

public interface IGrupoFamiliarDAO {
    public GrupoFamiliar findOne(long cod_base);
    public int save(GrupoFamiliar grupoFamiliar);
}
