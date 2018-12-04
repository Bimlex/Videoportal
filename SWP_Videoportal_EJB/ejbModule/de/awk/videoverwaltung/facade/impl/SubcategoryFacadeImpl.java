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
	
	@Override
	public List<Subcategory> getAllSubcategories() {
		return subcategoryDAO.findAll();
	}
	
	public Subcategory getSubcategoryById(int subcategoryId) {
		return subcategoryDAO.find(subcategoryId);
	}
	
	public void saveSubcategory(int topicId, String name, String description) {
		Subcategory aSubcategory = new Subcategory(topicId, name, description);
		subcategoryDAO.save(aSubcategory);
	}
	
	public void updateSubcategory(int subcategoryId, int topicId, String name, String description) {
		Subcategory aSubcategory = this.getSubcategoryById(subcategoryId);
		aSubcategory.setTopicId(topicId);
		aSubcategory.setName(name);
		aSubcategory.setDescription(description);
		subcategoryDAO.save(aSubcategory);
	}
	
	public void deleteSubcategory(int subcategoryId) {
		Subcategory aSubcategory = this.getSubcategoryById(subcategoryId);
		try {
			subcategoryDAO.delete(aSubcategory);
		} catch (Exception e) {
			
		}
	}

	@Override
	public List<Subcategory> findSubcategoriesByTopicId(int subcategoryId) {
		return subcategoryDAO.findSubcategoriesByTopicId(subcategoryId);
	}

	@Override
	public List<Subcategory> findSubcategoriesByName(String name) {
		return subcategoryDAO.findSubcategoriesByName(name);
	}

	@Override
	public List<Subcategory> findSubcategoriesByDescription(String description) {
		return subcategoryDAO.findSubcategoriesByDescription(description);
	}

	@Override
	public Subcategory findSubcategoryByName(String name) {
		return subcategoryDAO.findSubcategoryByName(name);
	}
	
	
}