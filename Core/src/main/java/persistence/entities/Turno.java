package persistence.entities;


import javax.persistence.*;

@Entity
@Table(name="Turno")
public class Turno {

    @Id
    @Column(name="id_turno")
    private int idTurno;

    @JoinTable(name="Actividad")
    @Column(name="id_actividad")
    private int idActividad;

    @JoinTable(name="Zona")
    @Column(name="id_zona")
    private int idZona;

    @Column
    private String hora;
    @Column
    private String dia;
    @Column
    private int periodo;

    public Turno(int idActividad, int idZona, String hora, String dia, int periodo) {
        this.idActividad = idActividad;
        this.idZona = idZona;
        this.hora = hora;
        this.dia = dia;
        this.periodo = periodo;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
}
