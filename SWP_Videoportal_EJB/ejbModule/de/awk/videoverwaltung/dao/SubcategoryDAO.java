package de.awk.videoverwaltung.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import de.awk.videoverwaltung.model.Subcategory;

@Stateless
public class SubcategoryDAO extends GenericDAO<Subcategory>{


	public SubcategoryDAO() {
		super(Subcategory.class);
	}
	
	public void delete(Subcategory aSubcategory) {
		super.delete(aSubcategory.getSubcategoryId(), Subcategory.class);
	}

	public List<Subcategory> findSubcategoriesByTopicId(int topicId) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("topicId",topicId);
		return (List<Subcategory>) this.findAllResult(Subcategory.FIND_SUBCATEGORYLIST_BY_TOPICID, parameters);
	}

	public List<Subcategory> findSubcategoriesByName(String name) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", name + "%");
		return (List<Subcategory>) this.findAllResult(Subcategory.FIND_SUBCATEGORYLIST_BY_NAME, parameters);
	}

	public List<Subcategory> findSubcategoriesByDescription(String description) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("description", "%" + description + "%");
		return (List<Subcategory>) this.findAllResult(Subcategory.FIND_SUBCATEGORYLIST_BY_DESCRIPTION, parameters);
	}

	
	
	
	public List<Subcategory> findSubcategoriesByNameAndTopicId (int topicId, String name){
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("topicId", topicId);
		parameters.put("name", "%" + name + "%");
		return (List<Subcategory>) this.findAllResult(Subcategory.FIND_SUBCATEGORYLIST_BY_TOPICID_AND_NAME, parameters);
	}
	
	public List<Subcategory> findSubcategoriesByDescriptionAndTopicId (int topicId, String description){
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("topicId", topicId);
		parameters.put("description", "%" + description + "%");
		return (List<Subcategory>) this.findAllResult(Subcategory.FIND_SUBCATEGORYLIST_BY_TOPICID_AND_DESCRITPION, parameters);
	}
	
	
	
	
	public Subcategory findSubcategoryBySubcategoryId(int subcategoryId) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("subcategoryId", subcategoryId);
		return super.findOneResult(Subcategory.FIND_SUBCATEGORY_BY_SUBCATEGORYID, parameters);
	}	
	
	public Subcategory findSubcategoryByName(String name) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", name);
		return super.findOneResult(Subcategory.FIND_SUBCATEGORY_BY_NAME, parameters);
	}
	
	

	public void deleteAllSubcategoriesByTopicId(int topicId) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("topicId", topicId);
		this.findAllResult(Subcategory.DELETE_ALL_SUBCATEGORIES_BY_TOPICID, parameters);
	}
	

	
	
}
