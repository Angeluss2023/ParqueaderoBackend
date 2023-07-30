package ec.edu.ups.modelo;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Tarifa {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_tarifa") 
    int id_Tarifa;
    String tipo_tarifa;
    int precioActual;

    public Tarifa() {
    	super();
    }

    public Tarifa(int id_Tarifa, String tipo_tarifa, int precioActual) {
        this.id_Tarifa = id_Tarifa;
        this.tipo_tarifa = tipo_tarifa;
        this.precioActual = precioActual;
    }
    
    @OneToOne(mappedBy = "tarifa")
    private Ticket ticket;


    public int getId_Tarifa() {
        return id_Tarifa;
    }

    public void setId_Tarifa(int id_Tarifa) {
        this.id_Tarifa = id_Tarifa;
    }

    public String getTipo_tarifa() {
        return tipo_tarifa;
    }

    public void setTipo_tarifa(String tipo_tarifa) {
        this.tipo_tarifa = tipo_tarifa;
    }

    public int getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(int precioActual) {
        this.precioActual = precioActual;
    }
    

    @Override
    public String toString() {
        return "Tarifa{" + "id_Tarifa=" + id_Tarifa + ", tipo_tarifa=" + tipo_tarifa + ", precioActual=" + precioActual + '}';
    } 
}