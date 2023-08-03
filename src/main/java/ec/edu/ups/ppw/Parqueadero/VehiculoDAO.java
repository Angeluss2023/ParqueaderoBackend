package ec.edu.ups.ppw.Parqueadero;

import java.io.Serializable;
import java.util.List;

import ec.edu.ups.modelo.Vehiculo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class VehiculoDAO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert (Vehiculo vehiculo) {
		
		em.persist(vehiculo);
	}
	
	public void update (Vehiculo vehiculo) {
		
		em.merge(vehiculo);
	}
	    
	public Vehiculo read(int  id_vehiculo) {
		Vehiculo v = em.find(Vehiculo.class, id_vehiculo);
		return v;
		
	}
	
	public Vehiculo read(String  id_vehiculo) {
		Vehiculo v = em.find(Vehiculo.class, id_vehiculo);
		return v;
		
	}
	
	public void delete(String placa) {
	    Query query = em.createQuery("DELETE FROM Vehiculo v WHERE v.placa = :placa");
	    query.setParameter("placa", placa);
	    query.executeUpdate();
	}
	
	// metodo de lista
	
	public List<Vehiculo> getAll(){
		String jpql = "SELECT v FROM Vehiculo v";
		Query q = em.createQuery(jpql);
		return q.getResultList();		
	}
	
	public Vehiculo findByPlaca(String placa) {
	    System.out.println("Buscando vehículo con placa: " + placa);
	    try {
	        TypedQuery<Vehiculo> query = em.createQuery("SELECT v FROM Vehiculo v WHERE v.placa = :placa", Vehiculo.class);
	        query.setParameter("placa", placa);
	        Vehiculo vehiculo = query.getSingleResult();
	        System.out.println("Vehículo encontrado: " + vehiculo);
	        return vehiculo;
	    } catch (NoResultException e) {
	        System.out.println("Vehículo no encontrado");
	        return null;
	    }

	}	
}

	



