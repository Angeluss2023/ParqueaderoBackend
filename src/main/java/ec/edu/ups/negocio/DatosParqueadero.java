package ec.edu.ups.negocio;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import ec.edu.ups.modelo.Ticket;
import ec.edu.ups.ppw.Parqueadero.DetalleTicketDAO;
import ec.edu.ups.ppw.Parqueadero.TarifaDAO;
import ec.edu.ups.ppw.Parqueadero.TicketDAO;
import ec.edu.ups.ppw.Parqueadero.VehiculoDAO;
import ec.edu.ups.servicios.GTicketService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class DatosParqueadero {
	
	@Inject
	private VehiculoDAO daoVehiculo;
	
	@Inject
	private TarifaDAO daoTarifa;
	
	@Inject
	private TicketDAO daoTicket;
	
	@Inject
	private DetalleTicketDAO daoDetalle;
	
	@Inject 
	private GTicketService gService;
	
	
	@PostConstruct
	public void init() {
		
		System.out.println("Hola UPS");
		System.out.println("------------------------------------------------------");	
		System.out.println("------------------------------------------------------");	
	}
}