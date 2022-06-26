package persistence.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Se_Inscribe_En")
public class SeInscribeEn implements Serializable {
    @Id
    @JoinTable(name="Grupo_Familiar")
    private int cod_base;

    @Id
    @JoinTable(name="Socio")
    private int id_socio;

    @Id
    @JoinTable(name="Turno")
    private int id_turno;

    @Id
    @Column
    private Date fecha;

    @Column
    private boolean activo;

    public SeInscribeEn() {
    }

    public SeInscribeEn(int cod_base, int id_socio, int id_turno, Date fecha, boolean activo) {
        this.cod_base = cod_base;
        this.id_socio = id_socio;
        this.id_turno = id_turno;
        this.fecha = fecha;
        this.activo = activo;
    }

    public int getCod_base() {
        return cod_base;
    }

    public void setCod_base(int cod_base) {
        this.cod_base = cod_base;
    }

    public int getId_socio() {
        return id_socio;
    }

    public void setId_socio(int id_socio) {
        this.id_socio = id_socio;
    }

    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
