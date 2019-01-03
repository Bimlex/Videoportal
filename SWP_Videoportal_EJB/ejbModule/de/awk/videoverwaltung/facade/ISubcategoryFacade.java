package de.awk.videoverwaltung.facade;

import java.util.List;

import javax.ejb.Local;
import de.awk.videoverwaltung.model.Subcategory;

@Local
public interface ISubcategoryFacade {
	
	public abstract List<Subcategory> getAllSubcategories();
	public abstract List<Subcategory> findSubcategoriesByTopicId(int topicId);
	public abstract List<Subcategory> findSubcategoriesByName(String searchField);
	public abstract List<Subcategory> findSubcategoriesByDescription(String searchField);
	
	public abstract List<Subcategory> findSubcategoriesByNameAndTopicId(int topicId, String searchField);
	public abstract List<Subcategory> findSubcategoriesByDescriptionAndTopicId(int topicId, String searchField);	
	
	public abstract Subcategory findSubcategoryBySubcategoryId(int subcategoryId);
	public abstract Subcategory findSubcategoryByName(String name);
	
	public abstract void saveSubcategory(int topicId, String name, String description);
	public abstract void deleteSubcategory(int subcategoryId);
	public abstract void updateSubcategory(int subcategoryId, int topicId, String name, String description);
	
	public abstract void deleteAllSubcategoriesByTopicId(int topicId);

}
