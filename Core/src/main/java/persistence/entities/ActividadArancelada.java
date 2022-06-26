package persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Actividad_Arancelada")
public class ActividadArancelada extends Actividad{
    @Column
    private int costo;

    @Column
    private int priodo_pago;

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getPriodo_pago() {
        return priodo_pago;
    }

    public void setPriodo_pago(int priodo_pago) {
        this.priodo_pago = priodo_pago;
    }
}
