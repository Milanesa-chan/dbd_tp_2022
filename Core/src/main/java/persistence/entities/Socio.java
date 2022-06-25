package persistence.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Socio")
public class Socio {

    @Id
    @Column(name="cod_base")
    @JoinTable(name="Grupo_Familiar")
    private int cod_base;

    @Id
    @Column(name="id_socio")
    private int id_socio;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="celular")
    private String celular;

    @Column(name="fecha_nac")
    private Date fechaNac;

    @Column(name="deuda_socio")
    private int deudaSocio;

    @Column(name="fecha_inscripcion_club")
    private Date fechaInscripcionClub;

    @Column(name="tipo")
    private char tipo;


    public Socio() {
    }

    public Socio(int cod_base, String nombre, String apellido, String celular, Date fechaNac, Date fechaInscripcionClub, char tipo) {
        this.cod_base = cod_base;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.fechaNac = fechaNac;
        this.fechaInscripcionClub = fechaInscripcionClub;
        this.tipo = tipo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getDeudaSocio() {
        return deudaSocio;
    }

    public void setDeudaSocio(int deudaSocio) {
        this.deudaSocio = deudaSocio;
    }

    public Date getFechaInscripcionClub() {
        return fechaInscripcionClub;
    }

    public void setFechaInscripcionClub(Date fechaInscripcionClub) {
        this.fechaInscripcionClub = fechaInscripcionClub;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}
