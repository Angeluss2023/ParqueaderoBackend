package ec.edu.ups.ppw.Parqueadero;

import java.io.Serializable;
import java.util.List;

import ec.edu.ups.modelo.Tarifa;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Stateless
public class TarifaDAO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Tarifa tarifa) {
		em.persist(tarifa);
	}
	
	public void update(Tarifa tarifa) {
		em.merge(tarifa);
	}
	
	public Tarifa read(int id_Tarifa) {
		Tarifa ta = em.find(Tarifa.class, id_Tarifa);
		return ta;
	}
	
	public void delete(int id_Tarifa) {
		Tarifa ta = em.find(Tarifa.class, id_Tarifa);
		em.remove(ta);
	}
	
	public List<Tarifa> getAll(){
		String jpql = "SELECT ta FROM Tarifa ta";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}

}