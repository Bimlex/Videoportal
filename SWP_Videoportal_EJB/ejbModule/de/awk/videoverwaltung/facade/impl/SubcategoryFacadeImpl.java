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
	
	public void saveSubcategory(int topicId, String name, String beschreibung) {
		Subcategory aSubcategory = new Subcategory(topicId, name, beschreibung);
		subcategoryDAO.save(aSubcategory);
	}
	
	public void updateSubcategory(int subcategoryId, int topicId, String name, String beschreibung) {
		Subcategory aSubcategory = this.getSubcategoryById(subcategoryId);
		aSubcategory.setTopicId(topicId);
		aSubcategory.setName(name);
		aSubcategory.setBeschreibung(beschreibung);
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
	public List<Subcategory> findSubcategoriesByBeschreibung(String beschreibung) {
		return subcategoryDAO.findSubcategoriesByBeschreibung(beschreibung);
	}

	@Override
	public Subcategory findSubcategoryByName(String name) {
		return subcategoryDAO.findSubcategoryByName(name);
	}
	
	
}