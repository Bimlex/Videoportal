package de.awk.ressourcenverwaltung.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
public class Maschine extends Ressource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4918395727530560727L;

	private String standort;
	
	public Maschine(){}
	
	public Maschine(String aName, double aKostensatzProStunde, int aStundenProTag,
			String aStandort){
		super("Maschine",aName,aKostensatzProStunde,aStundenProTag);
		this.standort = aStandort;
	}

	public String getStandort() {
		return standort;
	}

	public void setStandort(String standort) {
		this.standort = standort;
	}
	
	@Override
	public int hashCode(){
		return this.getRessourcenId();
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj instanceof Maschine){
			Maschine aMaschine = (Maschine) obj;
			return aMaschine.getRessourcenId().equals(this.getRessourcenId());
		} else return false;
	}
	
}
