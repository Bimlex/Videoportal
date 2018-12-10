package de.awk.videoverwaltung.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.awk.videoverwaltung.dao.SubcategoryDAO;
import de.awk.videoverwaltung.facade.ISubcategoryFacade;
import de.awk.videoverwaltung.model.Subcategory;

@Stateless
public class SubcategoryFacadeImpl implements ISubcategoryFacade{

	@EJB
	private SubcategoryDAO subcategoryDAO;
	
	public List<Subcategory> getAllSubcategories() {
		return subcategoryDAO.findAll();
	}
	
	public List<Subcategory> findSubcategoriesByTopicId(int topicId) {
		return subcategoryDAO.findSubcategoriesByTopicId(topicId);
	}
	
	public List<Subcategory> findSubcategoriesByName(String name) {
		return subcategoryDAO.findSubcategoriesByName(name);
	}

	public List<Subcategory> findSubcategoriesByDescription(String description) {
		return subcategoryDAO.findSubcategoriesByDescription(description);
	}
	
	
	
	
	public List<Subcategory> findSubcategoriesByNameAndTopicId(int topicId, String name) {
		return subcategoryDAO.findSubcategoriesByNameAndTopicId(topicId, name);
	}

	public List<Subcategory> findSubcategoriesByDescriptionAndTopicId(int topicId, String description) {
		return subcategoryDAO.findSubcategoriesByDescriptionAndTopicId(topicId, description);
	}
	
	
	
	
	public Subcategory findSubcategoryBySubcategoryId(int subcategoryId) {
		return subcategoryDAO.findSubcategoryBySubcategoryId(subcategoryId);
	}
	
	public Subcategory findSubcategoryByName(String name) {
		return subcategoryDAO.findSubcategoryByName(name);
	}
	
	
	
	
	public void saveSubcategory(int topicId, String name, String description) {
		Subcategory aSubcategory = new Subcategory(topicId, name, description);
		subcategoryDAO.save(aSubcategory);
	}
	
	public void updateSubcategory(int subcategoryId, int topicId, String name, String description) {
		Subcategory aSubcategory = this.findSubcategoryBySubcategoryId(subcategoryId);
		aSubcategory.setTopicId(topicId);
		aSubcategory.setName(name);
		aSubcategory.setDescription(description);
		subcategoryDAO.save(aSubcategory);
	}
	
	public void deleteSubcategory(int subcategoryId) {
		Subcategory aSubcategory = this.findSubcategoryBySubcategoryId(subcategoryId);
		try {
			subcategoryDAO.delete(aSubcategory);
		} catch (Exception e) {
			
		}
	}
	
	
}