package ec.edu.ups.servicios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ec.edu.ups.modelo.Ticket;
import ec.edu.ups.modelo.Vehiculo;
import ec.edu.ups.negocio.GestionParqueadero;
import ec.edu.ups.ppw.Parqueadero.TicketDAO;
import ec.edu.ups.ppw.Parqueadero.VehiculoDAO;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("ticket")
public class GTicketService {

   @Inject
   private GestionParqueadero gParqueadero;
   @Inject
   private TicketDAO daoTicket;
   
   @Inject
   private VehiculoDAO daoVehiculo;
   
   @PersistenceContext
   private EntityManager em;
  
	@POST
	@Path("vehiculo")
	@Produces("application/json")
	@Consumes("application/json")
	public Response guardarVehiculo(Vehiculo vehiculo) {
	    try {
	    	gParqueadero.guardarVehiculo(vehiculo); 
	        List<Vehiculo> vehiculos = getVehiculos(); 
	        return Response.status(Response.Status.OK).entity(vehiculos).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        Error error = new Error();
	        error.setCodigo(99);
	        error.setMensaje("Error al guardar el vehículo: " + e.getMessage());
	        return Response.status(Response.Status.OK).entity(error).build();
	    }
	}

	public List<Vehiculo> getVehiculos() {
	    return daoVehiculo.getAll();
	}
    
	@POST
	@Path("ticket1")
	@Produces("application/json")
	@Consumes("application/json")
	public Response guardarTicket(Ticket ticket) {
	    try {
	        // Comprueba que el vehículo asociado al ticket no sea nulo.
	        if (ticket.getVehiculo() == null) {
	            throw new Exception("El ticket no tiene un vehículo asociado.");
	        }

	        // Asignar un puesto al ticket (si hay disponibles).
	        int puesto = gParqueadero.asignarPuesto();
	        if (puesto == -1) {
	            throw new Exception("No hay puestos disponibles.");
	        }
	        ticket.setPuestoAsignado(puesto + 1); // +1 porque las listas comienzan desde 0 y tú quieres puestos del 1 al 10.

	        String placa = ticket.getVehiculo().getPlaca();
	        gParqueadero.guardarTicket(ticket, placa);

	        List<Ticket> tickets = getTickets(); 
	        System.out.println("Placa recibida en guardarTicket: " + placa);
	        return Response.status(Response.Status.OK).entity(tickets).build();

	    } catch (Exception e) {
	        e.printStackTrace();
	        Error error = new Error();
	        error.setCodigo(99);
	        error.setMensaje("Error al guardar el ticket: " + e.getMessage());
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
	    }
	}



	public List<Ticket> getTickets() {
	    return daoTicket.getAll();
	}
	

	@GET
	@Path("allV")
	@Produces("application/json")
	@Consumes("application/json")
	public Response getVehiculo() {
	    List<Vehiculo> listado = gParqueadero.getVehiculo();
	    return Response.status(Response.Status.OK).entity(listado).build();
	}

	
	@GET
	@Path("allT")
	@Produces("application/json")
	@Consumes("application/json")
	public Response getTicket() {
	    List<Ticket> listado = gParqueadero.getTicket();
	    return Response.status(Response.Status.OK).entity(listado).build();
	}
	
	
	// Para Vehiculo:
	@PUT
	@Path("actualizar")
	@Produces("application/json")
	@Consumes("application/json")
	public Response actualizarVehiculo(Vehiculo vehiculo) {
	    try {
	    	gParqueadero.actualizarVehiculo(vehiculo);  
	        List<Vehiculo> vehiculos = getVehiculos();
	        return Response.status(Response.Status.OK).entity(vehiculos).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        Error error = new Error();
	        error.setCodigo(99);
	        error.setMensaje("Error al actualizar vehículo: " + e.getMessage());
	        return Response.status(Response.Status.OK).entity(error).build();
	    }
	}

	@DELETE
	@Path("eliminar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response eliminarVehiculo(Vehiculo vehiculo) {
	    try {
	    	gParqueadero.eliminarVehiculo(vehiculo.getPlaca());  
	        return Response.status(Response.Status.OK).entity(vehiculo).build();

	    } catch (Exception e) {
	        e.printStackTrace();
	        Error error = new Error();
	        error.setCodigo(99);
	        error.setMensaje("Error al eliminar vehículo: " + e.getMessage());
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
	    }
	}
	
	
	@GET
    @Path("fecha")
    @Produces("application/json")
    public Response obtenerTicketsPorFecha(@PathParam("fecha") String fechaStr) {
        try {
            // Convertimos el string de fecha a un tipo de dato Date
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(fechaStr);
            
            // Obtenemos el siguiente día para establecer un rango de búsqueda
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            c.add(Calendar.DATE, 1);
            Date siguienteDia = c.getTime();
            
            List<Ticket> tickets = em.createQuery("SELECT t FROM Ticket t WHERE t.fecha_creacion >= :fecha AND t.fecha_creacion < :siguienteDia", Ticket.class)
                                     .setParameter("fecha", fecha)
                                     .setParameter("siguienteDia", siguienteDia)
                                     .getResultList();
            
            if(tickets.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("No se encontraron tickets para la fecha proporcionada.")
                               .build();
            }
            
            return Response.status(Response.Status.OK)
                           .entity(tickets)
                           .build();
        } catch (NoResultException nre) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("No se encontraron tickets para la fecha proporcionada.")
                           .build();
        } catch (Exception e) {
        	String errorMessage = (e.getMessage() != null) ? e.getMessage() : "Error desconocido al obtener los tickets.";
        	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        	               .entity("Error al obtener los tickets: " + errorMessage)
        	               .build();
            /*/return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al obtener los tickets: " + e.getMessage())
                           .build();/*/
        }
    }



}