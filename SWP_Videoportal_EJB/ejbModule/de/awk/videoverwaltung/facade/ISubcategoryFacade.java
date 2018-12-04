package de.awk.videoverwaltung.facade;

import java.util.List;

import javax.ejb.Local;
import de.awk.videoverwaltung.model.Subcategory;

@Local
public interface ISubcategoryFacade {
	

	public abstract Subcategory getSubcategoryById(int subcategoryId);
	public abstract void saveSubcategory(int topicId, String name, String beschreibung);
	public abstract void deleteSubcategory(int subcategoryId);
	
	
	public abstract List<Subcategory> getAllSubcategories();
	public abstract List<Subcategory> findSubcategoriesByTopicId(int topicId);
	public abstract List<Subcategory> findSubcategoriesByName(String searchField);
	public abstract List<Subcategory> findSubcategoriesByBeschreibung(String searchField);
	
//	public abstract Subcategory findSubcategoryById(int subcategoryId);
//	public abstract Subcategory findSubcategoryByBeschreibung(String beschreibung);
	
	public abstract Subcategory findSubcategoryByName(String name);
	public abstract void updateSubcategory(int subcategoryId, int topicId, String name, String beschreibung);
	
	
}
