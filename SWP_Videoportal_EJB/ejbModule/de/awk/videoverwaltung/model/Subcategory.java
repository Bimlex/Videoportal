package de.awk.videoverwaltung.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name="swp_subcategory")
public class Subcategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4695595447717222379L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SUBCATEGORY_ID")
	@SequenceGenerator(name="SEQ_SUBCATEGORY_ID", sequenceName="SEQ_SUBCATEGORY_ID", allocationSize = 1)
	private Integer subcategoryId;

	private int tid;
	private String name;
	private String beschreibung;
	
	public Subcategory () {}	

	public Subcategory (int tid, String name, String beschreibung) {
		this.tid = tid;
		this.name = name;
		this.beschreibung = beschreibung;
	}
	
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
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
