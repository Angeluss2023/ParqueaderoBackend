package ec.edu.ups.modelo;


import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class DetalleTicket {
	
	@Id
	@GeneratedValue
	int puesto;
	@Column(name="det_id")
	private int id_detalleTicket;

	@Column(name="det_total_time")
	private Time totalTime;
	
	@Column(name="det_total")
	private double total;
	
	@OneToOne
	@JoinColumn(name="id_vehiculo")
	private Vehiculo vehiculo;

	
	//@ManyToOne
	//@JoinColumn(name = "id_ticket")
	//private Ticket ticket;

	
	public DetalleTicket() {
		super();
	}


	public DetalleTicket(int puesto, int id_detalleTicket, Time totalTime, double total, Vehiculo vehiculo
			) {
		super();
		this.puesto = puesto;
		this.id_detalleTicket = id_detalleTicket;
		this.totalTime = totalTime;
		this.total = total;
		this.vehiculo = vehiculo;
		//this.ticket = ticket;
	}


	public int getPuesto() {
		return puesto;
	}


	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}


	public int getId_detalleTicket() {
		return id_detalleTicket;
	}


	public void setId_detalleTicket(int id_detalleTicket) {
		this.id_detalleTicket = id_detalleTicket;
	}


	public Time getTotalTime() {
		return totalTime;
	}


	public void setTotalTime(Time totalTime) {
		this.totalTime = totalTime;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public Vehiculo getVehiculo() {
		return vehiculo;
	}


	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}




	@Override
	public String toString() {
		return "DetalleTicket [puesto=" + puesto + ", id_detalleTicket=" + id_detalleTicket + ", totalTime=" + totalTime
				+ ", total=" + total + ", vehiculo=" + vehiculo + "]";
	}
	
	
	
}