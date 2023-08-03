package ec.edu.ups.modelo;

import java.time.*;
import java.time.format.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Ticket  {
    
    @Id

    @Column(name="id_ticket")
    private int id_ticket;
    private LocalDate fecha;
    private int puestoAsignado;
    private LocalTime  hora_entrada;
    private LocalTime  hora_salida;

    @OneToOne
    @JoinColumn(name="id_tarifa")
    private Tarifa tarifa;
    
    @ManyToOne
    @JoinColumn(name = "id_vehiculo")  
    private Vehiculo vehiculo;
    
	public Ticket() {
		super();
	}

	
	

	public Ticket(int id_ticket, LocalDate fecha, int puestoAsignado, LocalTime hora_entrada, 
			LocalTime hora_salida, Tarifa tarifa, Vehiculo vehiculo) {
		super();
		this.id_ticket = id_ticket;
		this.fecha = fecha;
		this.puestoAsignado = puestoAsignado;
		this.hora_entrada = hora_entrada;
		this.hora_salida = hora_salida;
		this.tarifa = tarifa;
		this.vehiculo = vehiculo;
	
	}




	public int getId_ticket() {
		return id_ticket;
	}


	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getPuestoAsignado() {
		return puestoAsignado;
	}

	public void setPuestoAsignado(int puestoAsignado) {
		this.puestoAsignado = puestoAsignado;
	}

	public LocalTime getHora_entrada() {
		return hora_entrada;
	}

	public void setHora_entrada(LocalTime hora_entrada) {
		this.hora_entrada = hora_entrada;
	}

	public LocalTime getHora_salida() {
		return hora_salida;
	}

	public void setHora_salida(LocalTime hora_salida) {
		this.hora_salida = hora_salida;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	@Override
	public String toString() {
		return "Ticket [id_ticket=" + id_ticket + ", fecha=" + fecha
				+ ", puestoAsignado=" + puestoAsignado + ", hora_entrada=" + hora_entrada + ", hora_salida="
				+ hora_salida + ", tarifa=" + tarifa + ", vehiculo=" + vehiculo + " ]";

	}

	
	
    	    
	
}



