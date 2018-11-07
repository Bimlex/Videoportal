package de.mb;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import de.awk.projektverwaltung.facade.IProjResBuchFacade;
import de.awk.projektverwaltung.model.Projekt;
import de.awk.projektverwaltung.model.ProjektRessourcenBuchung;

@ManagedBean(name="projResBuchMB")
@RequestScoped
public class ProjResBuchMB {
	
	@EJB
	IProjResBuchFacade prbFacade;
	
	private ProjektRessourcenBuchung aProjektRessourcenBuchung
		= new ProjektRessourcenBuchung();
	
	private Projekt projekt;
	
	public Projekt getProjekt(){
		if (projekt == null){
			projekt = new Projekt();
		}
		return projekt;
	}
	
	public void setProjekt(Projekt aProjekt){
		projekt = aProjekt; 
	}
	
	@NotNull
	@Digits(fraction = 0, integer = 6)
	private Integer projResBuchId = 0;
	
	@NotNull
	@Digits(fraction = 0, integer = 6)
	private int nrImProjekt;
	
	@NotNull
	@Digits(fraction = 0, integer = 6)
	private Integer ressourcenId;
	
	@Future
	private Date buchungsdatum;
	
	@NotNull
	@Digits(fraction = 0, integer = 6)
	private int gebuchteStunden;

	public ProjektRessourcenBuchung getProjektRessourcenBuchung(){
		if(aProjektRessourcenBuchung == null){
			aProjektRessourcenBuchung = new ProjektRessourcenBuchung();
		}
		return aProjektRessourcenBuchung;
	}
	
	public ProjektRessourcenBuchung erstelleProjResBuch(){
		if(aProjektRessourcenBuchung == null){
			aProjektRessourcenBuchung = new ProjektRessourcenBuchung();
		}
		return aProjektRessourcenBuchung;
	}
	
	public void setProjektRessourcenBuchung(ProjektRessourcenBuchung aPRB){
		this.aProjektRessourcenBuchung = aPRB;
	}
	
	public String einfuegenProjResBuchRessourcenId(int aRessourcenId){
		if(aProjektRessourcenBuchung == null){
			aProjektRessourcenBuchung = new ProjektRessourcenBuchung();
		}
		this.aProjektRessourcenBuchung.setRessourcenId(aRessourcenId);
		return "";
	}

	public Integer getProjResBuchId() {
		return projResBuchId;
	}

	public void setProjResBuchId(Integer projResBuchId) {
		this.projResBuchId = projResBuchId;
	}

	public int getNrImProjekt() {
		return nrImProjekt;
	}

	public void setNrImProjekt(int nrImProjekt) {
		this.nrImProjekt = nrImProjekt;
	}

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
	
	
	public int checkRessourceUsage(int resNr, Date buchDatum){
		return this.prbFacade.checkRessourceUsage(resNr, buchDatum);
	}
	
	
}
