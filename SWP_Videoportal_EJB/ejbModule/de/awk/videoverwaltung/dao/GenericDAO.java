package de.awk.videoverwaltung.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;

public abstract class GenericDAO<T> {

	private final String UNIT_NAME = "SWP_Videoportal_EJB";
	
	@PersistenceContext(unitName = UNIT_NAME)
	private EntityManager em;
	
	private Class<T> entityClass;
	
	public GenericDAO(){}
	
	public GenericDAO(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	public void save(T entity){
		this.em.persist(entity);
	}
	
	public T update(T entity){
		return em.merge(entity);
	}
	
	protected boolean delete(Object id, Class<T> classe){
		T entityToBeRemoved = em.getReference(classe, id);
		try {
			em.remove(entityToBeRemoved);
			return true;
		} catch (Exception e){
			System.out.println("Fehler beim Loeschen der Id: "+id+" aus Klasse "+classe.getClass());
			return false;
		}
		
	}
	
	public T find(int entityId){
		return em.find(entityClass, entityId);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public List<T> findAll(){
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}
	
//	@SuppressWarnings({"unchecked", "rawtypes"})
//	public List<T> findFiltered(String columnName, String paramName){
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery cq = cb.createQuery();
//		cq.select(cq.from(entityClass));
//		ParameterExpression<String> p = cb.parameter(String.class,paramName);
//		cq.where(cb.equal(cq.from(entityClass).get(columnName), p));
//		return em.createQuery(cq).getResultList();
//	}
	
	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> parameters){
		T result = null;
		 try {
			 Query query = em.createNamedQuery(namedQuery);
			 if (parameters != null && !parameters.isEmpty()){
				 populateQueryParameters(query, parameters);
			 }
			 result = (T) query.getSingleResult();
			 
		 } catch (Exception e){
			 System.out.println("Fehler bei Query: "+e.getMessage());
		 }
		
		return result;
		
	}

	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
	}
	
}

