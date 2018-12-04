package de.awk.videoverwaltung.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="swp_subcategory")
@NamedQueries({
	@NamedQuery(name="Subcategory.findListSubcategoriesByTopicId", query="SELECT s FROM Subcategory s WHERE s.topicId LIKE :topicId"),
	@NamedQuery(name="Subcategory.findListSubcategoriesByName", query="SELECT s FROM Subcategory s WHERE s.name LIKE :name"),
	@NamedQuery(name="Subcategory.findListSubcategoriesByBeschreibung", query="SELECT s FROM Subcategory s WHERE s.beschreibung LIKE :beschreibung"),
	@NamedQuery(name="Subcategory.findSubcategoryByName", query="SELECT s FROM Subcategory s WHERE s.name = :name")	
})
public class Subcategory {

	public static final String FIND_LIST_SUBCATEGORIES_BY_TOPICID = "Subcategory.findListSubcategoriesByTopicId";
	public static final String FIND_LIST_SUBCATEGORIES_BY_NAME = "Subcategory.findListSubcategoriesByName";
	public static final String FIND_LIST_SUBCATEGORIES_BY_BESCHREIBUNG = "Subcategory.findListSubcategoriesByBeschreibung";
	public static final String FIND_SUBCATEGORY_BY_NAME = "Subcategory.findSubcategoryByName";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SUBCATEGORY_ID")
	@SequenceGenerator(name="SEQ_SUBCATEGORY_ID", sequenceName="SEQ_SUBCATEGORY_ID", allocationSize = 1)
	private Integer subcategoryId;
	
	private Integer topicId;
	private String name;
	private String beschreibung;
	
	public Subcategory() {}
	
	public Subcategory(int topicId, String name, String beschreibung) {
		this.topicId = topicId;
		this.name = name;
		this.beschreibung = beschreibung;
	}

	//Getter & Setter
	public Integer getSubcategoryId() {
		return subcategoryId;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	

}
