package persistence.entities;


import javax.persistence.*;

@Entity
@Table(name="Grupo_Familiar")
public class GrupoFamiliar {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int cod_base;

    @Column(name="deuda_cuota_social")
    private int deudaCuotaSocial;

    @Column(name="domicilio")
    private String domicilio;

    @Column(name="telefono")
    private String telefono;


    public GrupoFamiliar(int cod_base, int deudaCuotaSocial, String domicilio, String telefono) {
        this.cod_base = cod_base;
        this.deudaCuotaSocial = deudaCuotaSocial;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    public GrupoFamiliar(String domicilio, String telefono) {
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    public GrupoFamiliar(){

    }

    public int getCod_base() {
        return cod_base;
    }

    public void setCod_base(int cod_base) {
        this.cod_base = cod_base;
    }

    public int getDeuda_cuota_social() {
        return deudaCuotaSocial;
    }

    public void setDeuda_cuota_social(int deuda_cuota_social) {
        this.deudaCuotaSocial = deuda_cuota_social;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
