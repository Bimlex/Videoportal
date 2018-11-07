package de.awk.ressourcenverwaltung.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import de.awk.projektverwaltung.model.Projekt;

@Entity
@Access(AccessType.FIELD)
public class MitarbeiterIn extends Ressource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5470337818915819067L;

	private String vorname;
	private Date geburtstag;
	private String fachgebiet;
	
	public MitarbeiterIn(){}
	
	public MitarbeiterIn(String aName, double aKostensatzProStunde,
			String aVorname, Date aGeburtstag, String aFachgebiet){
		super("MitarbeiterIn",aName,aKostensatzProStunde,8);
		this.vorname = aVorname;
		this.geburtstag = aGeburtstag;
		this.fachgebiet = aFachgebiet;		
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Date getGeburtstag() {
		return geburtstag;
	}

	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag;
	}

	public String getFachgebiet() {
		return fachgebiet;
	}

	public void setFachgebiet(String fachgebiet) {
		this.fachgebiet = fachgebiet;
	}
	
	@Override
	public int hashCode(){
		return this.getRessourcenId();
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj instanceof MitarbeiterIn){
			MitarbeiterIn aMitarbeiterIn = (MitarbeiterIn) obj;
			return aMitarbeiterIn.getRessourcenId().equals(this.getRessourcenId());
		} else return false;
	}
	

}
