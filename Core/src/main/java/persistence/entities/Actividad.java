package persistence.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Actividad")
public class Actividad {
    @Id
    @Column(name="id_actividad")
    private int id_actividad;

    @Column(name="descripcion")
    String descripcion;

    @Column(name="es_infantil")
    Boolean esInfantil;

    @Column(name="es_mayor")
    Boolean esMayor;

    @Column(name="es_vitalicio")
    Boolean esVitalicio;

    public Actividad() {
    }

    public Actividad(String descripcion, Boolean esInfantil, Boolean esMayor, Boolean esVitalicio) {
        this.descripcion = descripcion;
        this.esInfantil = esInfantil;
        this.esMayor = esMayor;
        this.esVitalicio = esVitalicio;
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEsInfantil() {
        return esInfantil;
    }

    public void setEsInfantil(Boolean esInfantil) {
        this.esInfantil = esInfantil;
    }

    public Boolean getEsMayor() {
        return esMayor;
    }

    public void setEsMayor(Boolean esMayor) {
        this.esMayor = esMayor;
    }

    public Boolean getEsVitalicio() {
        return esVitalicio;
    }

    public void setEsVitalicio(Boolean esVitalicio) {
        this.esVitalicio = esVitalicio;
    }
}
