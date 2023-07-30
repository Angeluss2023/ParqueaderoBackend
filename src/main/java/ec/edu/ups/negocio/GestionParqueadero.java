package ec.edu.ups.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ec.edu.ups.modelo.Ticket;
import ec.edu.ups.modelo.Vehiculo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.ppw.Parqueadero.TicketDAO;
import ups.edu.ec.ppw.Parqueadero.VehiculoDAO;

@Stateless
public class GestionParqueadero {
	
	@Inject
	private TicketDAO  daoTicket;
	@Inject
	private VehiculoDAO daoVehiculo;
	
	

	public void guardarVehiculo(Vehiculo vehiculo) throws Exception {
	    
	    if(!this.validarPlacaCuenca(vehiculo.getPlaca()))
	        throw new Exception("Placa no válida para Cuenca");
	    
	    if(daoVehiculo.read(vehiculo.getId_vehiculo())==null) {
	        try {
	            daoVehiculo.insert(vehiculo);
	        } catch(Exception e) {
	            throw new Exception("Error al insertar vehículo: " + e.getMessage());
	        }
	    } else {
	        try {
	            daoVehiculo.update(vehiculo);
	        } catch(Exception e) {
	            throw new Exception("Error al actualizar vehículo: " + e.getMessage());
	        }
	    }
	}	
	
	    private boolean validarPlacaCuenca(String placa) {
	        // Patrón de expresión regular para validar placas de Cuenca, Ecuador
	        String patronPlaca = "^[A-Z]{3}-\\d{4}[A-Z]{1}$";

	        // Validar la placa usando el patrón
	        if (placa.matches(patronPlaca)) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	    
	    public void guardarTicket(Ticket ticket, String placa) throws Exception {
	        
	        // 1. Buscar el vehículo por placa.
	        Vehiculo vehiculo = daoVehiculo.findByPlaca(placa);
	        
	        // Si no se encuentra el vehículo, lanzar una excepción.
	        if(vehiculo == null) {
	            throw new Exception("El vehículo con placa " + placa + " no se encuentra registrado.");
	        }
	        
	        // 2. Asociar el vehículo al ticket.
	        ticket.setVehiculo(vehiculo);
	        
	        // 3. Insertar o actualizar el ticket en la base de datos.
	        if(daoTicket.read(ticket.getId_ticket()) == null) {
	            try {
	                daoTicket.insert(ticket);
	            } catch(Exception e) {
	                throw new Exception("Error al insertar ticket: " + e.getMessage());
	            }
	        } else {
	            try {
	                daoTicket.update(ticket);
	            } catch(Exception e) {
	                throw new Exception("Error al actualizar ticket: " + e.getMessage());
	            }
	        }
	    }


	
	private List<Boolean> puestos = new ArrayList<>(Collections.nCopies(10, false)); // Inicialmente todos los puestos están libres.

	

	public int asignarPuesto() {
	    int indicePuesto = puestos.indexOf(false);
	    if (indicePuesto != -1) {
	        puestos.set(indicePuesto, true);
	        return indicePuesto;
	    }
	    return -1;
	}



	public void liberarPuesto(int puesto) {
	    if (puesto >= 0 && puesto < puestos.size()) {
	        puestos.set(puesto, false);  // Marcar el puesto como libre
	    }
	}
	
	public void actualizarVehiculo(Vehiculo vehiculo) throws Exception {
	    if (!validarPlacaCuenca(vehiculo.getPlaca()))
	        throw new Exception("Placa incorrecta");

	    try {
	        if (daoVehiculo.read(vehiculo.getId_vehiculo()) == null) {
	            throw new Exception("El vehículo no existe en la base de datos.");
	        } else {
	            daoVehiculo.update(vehiculo);
	        }
	    } catch (Exception e) {
	        throw new Exception("Error al actualizar el vehículo: " + e.getMessage());
	    }
	}

	
	public void eliminarVehiculo(String placa) {
		daoVehiculo.delete(placa);
	}
	
	
	public List<Vehiculo> getVehiculo(){
	    return daoVehiculo.getAll();
	}

	public List<Ticket> getTicket(){
	    return daoTicket.getAll();
	}

}
