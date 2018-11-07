package de.awk.ressourcenverwaltung.model;

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
@Table(name="H2_Ressource")
public abstract class Ressource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8604946363768626962L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_RESSOURCE_ID")
	@SequenceGenerator(name="SEQ_RESSOURCE_ID", sequenceName="SEQ_RESSOURCE_ID", allocationSize = 1)
	private Integer ressourcenId;
	
	private String art;
	
	private String name;
	private double kostensatzProStunde;
	private int stundenkapazitaetProTag;
	
	public Ressource(){}
	
	public Ressource(String aArt, String aName, double aKostensatzProStunde, int aStundenProTag){
		this.art = aArt;
		this.name = aName;
		this.kostensatzProStunde = aKostensatzProStunde;
		this.stundenkapazitaetProTag = aStundenProTag;
	}
	
	

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getKostensatzProStunde() {
		return kostensatzProStunde;
	}

	public void setKostensatzProStunde(double kostensatzProStunde) {
		this.kostensatzProStunde = kostensatzProStunde;
	}

	public int getStundenkapazitaetProTag() {
		return stundenkapazitaetProTag;
	}

	public void setStundenkapazitaetProTag(int stundenProTag) {
		this.stundenkapazitaetProTag = stundenProTag;
	}
	
	public Integer getRessourcenId() {
		return ressourcenId;
	}

	@Override
	public int hashCode(){
		return this.getRessourcenId();
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj instanceof Ressource){
			Ressource aRessource = (Ressource) obj;
			return aRessource.getRessourcenId().equals(this.getRessourcenId());
		} else return false;
	}
	

}
