package de.awk.projektverwaltung.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import de.awk.ressourcenverwaltung.model.MitarbeiterIn;
import de.awk.ressourcenverwaltung.model.Ressource;

@Entity
@Access(AccessType.FIELD)
@Table(name="H2_Projekt")
public class Projekt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7195303983947250095L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PROJEKT_ID")
	@SequenceGenerator(name="SEQ_PROJEKT_ID", sequenceName="SEQ_PROJEKT_ID", allocationSize = 1)
	private Integer projektId;
	
	private String titel;
	private String beschreibung;
	private Date projektStart;
	private Date projektEnde;
	private String projektleiter;
	private String projektauftraggeber;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
	private Collection<ProjektRessourcenBuchung> projResBuchungen = new ArrayList<ProjektRessourcenBuchung>(); ;
	public Collection<ProjektRessourcenBuchung> getProjResBuchungen() { return projResBuchungen; }
	public void setProjResBuchungen(Collection<ProjektRessourcenBuchung> projResBuchungen) {
		this.projResBuchungen = projResBuchungen; }
	
	public void addProjResBuchung(ProjektRessourcenBuchung aProjResBuch) {

		aProjResBuch.getNrImProjekt();
		
		this.projResBuchungen.add(aProjResBuch);		
	}
	
	public void removeProjResBuchung(ProjektRessourcenBuchung aProjResBuch){
		System.out.println("Entfernen von Buchung: " + aProjResBuch.getNrImProjekt() + aProjResBuch.getRessourcenId());
		boolean bool = this.projResBuchungen.remove(aProjResBuch);
		System.out.println("Size nach dem Entfernen: "+this.projResBuchungen.size()+" "+bool);
	}
		
	public Projekt(){}
	
	public Projekt(String aTitel, String aBeschreibung, Date aProjektStart,
			Date aProjektEnde, String aProjektleiter, String aProjektauftraggeber){
		this.titel = aTitel;
		this.beschreibung = aBeschreibung;
		this.projektStart = aProjektStart;
		this.projektEnde = aProjektEnde;
		this.projektleiter = aProjektleiter;
		this.projektauftraggeber = aProjektauftraggeber;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Date getProjektStart() {
		return projektStart;
	}

	public void setProjektStart(Date projektStart) {
		this.projektStart = projektStart;
	}

	public Date getProjektEnde() {
		return projektEnde;
	}

	public void setProjektEnde(Date projektEnde) {
		this.projektEnde = projektEnde;
	}

	public String getProjektleiter() {
		return projektleiter;
	}

	public void setProjektleiter(String projektleiter) {
		this.projektleiter = projektleiter;
	}

	public String getProjektauftraggeber() {
		return projektauftraggeber;
	}

	public void setProjektauftraggeber(String projektauftraggeber) {
		this.projektauftraggeber = projektauftraggeber;
	}

	public Integer getProjektId() {
		return projektId;
	}
	
	@Override
	public int hashCode(){
		return this.getProjektId();
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj instanceof Projekt){
			Projekt aProjekt = (Projekt) obj;
			return aProjekt.getProjektId().equals(this.getProjektId());
		} else return false;
	}
	
}
