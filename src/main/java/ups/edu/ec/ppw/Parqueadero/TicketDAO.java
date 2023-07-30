
package ups.edu.ec.ppw.Parqueadero;

import java.io.Serializable;
import java.util.List;

import ec.edu.ups.modelo.Ticket;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;



@Stateless
public class TicketDAO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Ticket ticket) {
		em.persist(ticket);
	}
	
	public void update(Ticket ticket) {
		em.merge(ticket);
	}
	
	public Ticket read(int id_ticket) {
		Ticket t = em.find(Ticket.class, id_ticket);
		return t;
	}
	
	public void delete(int id_ticket) {
		Ticket t = em.find(Ticket.class, id_ticket);
		em.remove(t);
	}
	
	public List<Ticket> getAll(){
		String jpql = "SELECT t FROM Ticket t";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}
}