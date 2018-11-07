package de.awk.projektverwaltung.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import de.awk.ressourcenverwaltung.model.Ressource;

@Entity
@Access(AccessType.FIELD)
@Table(name="H2_PRB")
public class ProjektRessourcenBuchung implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5332477846495601169L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PROJRESBUCH_ID")
	@SequenceGenerator(name="SEQ_PROJRESBUCH_ID", sequenceName="SEQ_PROJRESBUCH_ID", allocationSize = 1)
	private Integer projResBuchId;
	
	private int nrImProjekt;
//	private Integer projektId;
	private Integer ressourcenId;
	private Date buchungsdatum;
	private int gebuchteStunden;
	
	public ProjektRessourcenBuchung(){}
	
	public ProjektRessourcenBuchung(int aNrImProjekt, Integer aRessourcenId,
			Date aBuchungsdatum, int aGebuchteStunden){
		
		this.nrImProjekt = aNrImProjekt;
//		this.projektId = aProjektId;
		this.ressourcenId = aRessourcenId;
		this.buchungsdatum = aBuchungsdatum;
		this.gebuchteStunden = aGebuchteStunden;
		
	}

	public int getNrImProjekt() {
		return nrImProjekt;
	}

	public void setNrImProjekt(int nrImProjekt) {
		this.nrImProjekt = nrImProjekt;
	}

	public Integer getProjResBuchId() {
		return projResBuchId;
	}

//	public Integer getProjektId() {
//		return projektId;
//	}
//
//	public void setProjektId(Integer projektId) {
//		this.projektId = projektId;
//	}

	public Integer getRessourcenId() {
		return ressourcenId;
	}

	public void setRessourcenId(Integer ressourcenId) {
		this.ressourcenId = ressourcenId;
	}

	public Date getBuchungsdatum() {
		return buchungsdatum;
	}

	public void setBuchungsdatum(Date buchungsdatum) {
		this.buchungsdatum = buchungsdatum;
	}

	public int getGebuchteStunden() {
		return gebuchteStunden;
	}

	public void setGebuchteStunden(int gebuchteStunden) {
		this.gebuchteStunden = gebuchteStunden;
	}
	
	
	@Override
	public int hashCode(){
		return this.getProjResBuchId();
	}
		
	@Override
	public boolean equals(Object obj){
		if (obj instanceof ProjektRessourcenBuchung){
			ProjektRessourcenBuchung aProjResBuch = (ProjektRessourcenBuchung) obj;
			boolean ret = false;
			if(
					aProjResBuch.getNrImProjekt() == this.getNrImProjekt() &&
//					aProjResBuch.getProjektId() == this.getProjektId() &&
					aProjResBuch.getRessourcenId() == this.getRessourcenId() &&
					aProjResBuch.getBuchungsdatum().compareTo(this.getBuchungsdatum()) == 0 &&
					aProjResBuch.getGebuchteStunden() == this.getGebuchteStunden()
					) ret = true;
			
			return ret;
		} 
		
		return false;
	}
	

}
