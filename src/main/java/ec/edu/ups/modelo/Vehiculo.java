package ec.edu.ups.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Vehiculo {

	@Id
	@GeneratedValue
	@Column(name="id_vehiculo")
	int id_vehiculo;
    String placa;
    String tipo_vehiculo;
    String color;

    	
    @OneToMany(mappedBy = "vehiculo")
    private List<Ticket> tickets;

    
    public Vehiculo() {
    }

    
	public Vehiculo(int id_vehiculo, String placa, String tipo_vehiculo, String color) {
		super();
		this.id_vehiculo = id_vehiculo;
		this.placa = placa;
		this.tipo_vehiculo = tipo_vehiculo;
		this.color = color;
	}

	
	public int getId_vehiculo() {
		return id_vehiculo;
	}

	public void setId_vehiculo(int id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo_vehiculo() {
		return tipo_vehiculo;
	}

	public void setTipo_vehiculo(String tipo_vehiculo) {
		this.tipo_vehiculo = tipo_vehiculo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Vehiculo [id_vehiculo=" + id_vehiculo + ", placa=" + placa + ", tipo_vehiculo=" + tipo_vehiculo
				+ ", color=" + color + "]";
	}
}
