package co.edu.uan.app.siatur.model.service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import co.edu.uan.app.siatur.model.entity.Req;


@Remote
@Stateless
public class ReqService {

	@PersistenceContext(unitName = "siatur-unit")
	private EntityManager em;

	public List<Req> getAll() {
		CriteriaQuery<Req> criteria = this.em.getCriteriaBuilder().createQuery(Req.class);
		return this.em.createQuery(criteria.select(criteria.from(Req.class))).getResultList();

	}
/*
	public Req save(Req req) throws IllegalArgumentException, Exception{

		Req newReq = null;
		
		if(req == null){
			throw new IllegalArgumentException("No hay objeto Requisito para guardar");
		}			
//		}else if(req.getId() == null){
//			this.em.persist(req);
//		}else if(req.getId() != null){
			newRol = this.em.merge(req);
//		}
		
		return newReq;
	}
	*/
}
