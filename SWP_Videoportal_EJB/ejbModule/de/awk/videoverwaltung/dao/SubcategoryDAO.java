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
		return (List<Subcategory>) this.findAllResult(Subcategory.FIND_LIST_SUBCATEGORIES_BY_TOPICID, parameters);
	}

	public List<Subcategory> findSubcategoriesByName(String name) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", name + "%");
		return (List<Subcategory>) this.findAllResult(Subcategory.FIND_LIST_SUBCATEGORIES_BY_NAME, parameters);
	}

	public List<Subcategory> findSubcategoriesByBeschreibung(String beschreibung) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("beschreibung", "%" + beschreibung + "%");
		return (List<Subcategory>) this.findAllResult(Subcategory.FIND_LIST_SUBCATEGORIES_BY_BESCHREIBUNG, parameters);
	}

	public Subcategory findSubcategoryByName(String name) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", name);
		return super.findOneResult(Subcategory.FIND_SUBCATEGORY_BY_NAME, parameters);
	}
	
	
}
