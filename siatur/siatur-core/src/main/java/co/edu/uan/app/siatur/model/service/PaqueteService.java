package co.edu.uan.app.siatur.model.service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import co.edu.uan.app.siatur.model.entity.Paquete;;

@Remote
@Stateless
public class PaqueteService {
	@PersistenceContext(unitName = "siatur-unit")
	private EntityManager em;

	
	public List<Paquete> getAll() {
		CriteriaQuery<Paquete> criteria = this.em.getCriteriaBuilder().createQuery(Paquete.class);
		return this.em.createQuery(criteria.select(criteria.from(Paquete.class))).getResultList();
	}
	

	public Paquete save(Paquete usr) throws IllegalArgumentException, Exception{

		Paquete newPaquete = null;
		
		if(usr == null){
			throw new IllegalArgumentException("No hay objeto paquete para guardar");
		}	
		
		newPaquete = this.em.merge(usr);

		
		return newPaquete;
	}	
	
}
