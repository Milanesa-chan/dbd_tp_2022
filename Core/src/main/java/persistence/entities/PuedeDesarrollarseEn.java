package persistence.entities;


import javax.persistence.*;

@Entity
@Table(name="Puede_Desarrolarse_En")
public class PuedeDesarrollarseEn {

    @Id
    @Column(name="id_actividad")
    @JoinTable(name="Actividad")
    private int idActividad;

    @Id
    @Column(name="id_zona")
    @JoinTable(name="Zona")
    private int idZona;

    public PuedeDesarrollarseEn() {
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }
}
