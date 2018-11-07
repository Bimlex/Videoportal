package de.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.awk.projektverwaltung.model.Projekt;
import de.awk.ressourcenverwaltung.facade.IRessourceFacade;
import de.awk.ressourcenverwaltung.model.Maschine;
import de.awk.ressourcenverwaltung.model.MitarbeiterIn;
import de.awk.ressourcenverwaltung.model.Ressource;


@ManagedBean(name="ressourceMB")
@RequestScoped
public class RessourceMB {

	@EJB
	IRessourceFacade ressourceFacade;
	
	private Ressource ressource;
	
	public Ressource getRessource(){
//		if (ressource == null){
//			ressource = new Ressource();
//		}
		return ressource;
	}
	
	public void setRessource(Ressource aRessource){
		ressource = aRessource; 
	}
	
	@NotNull
	@Digits(fraction = 0, integer = 6)	
	private int ressourcenId;
	
	@NotNull
	@Size(min=1, max=25)
	@Pattern(regexp = "[A-Za-z ]*", message= "Art bitte mit max. 25 Buchstaben und Leerzeichen!")
	private String art;
	
	@NotNull
	@Size(min=1, max=25)
	@Pattern(regexp = "[A-Za-z ]*", message= "Namen bitte mit max. 25 Buchstaben und Leerzeichen!")
	private String name;
	
	@Digits(fraction = 2, integer = 7)	
	private double kostensatzProStunde;
	
	@Max(24)
	@Min(1)
	private int stundenkapazitaetProTag;
	
	public List<Ressource> getAlleRessourcen(){
		return ressourceFacade.getAlleRessourcen();
	}
	
	public String getRessourceByIdString(Integer id){
		return String.valueOf(id);
	}
	
	public double getRessourcenStundensatzById(Integer id){
		
//		ID // TODO
		
//		if(ressourceFacade.getRessourceById(id) instanceof Maschine){
//			Maschine aMaschine = (Maschine) ressourceFacade.getRessourceById(id);
//			return aMaschine.getKostensatzProStunde();
//		}
//		
//		if(ressourceFacade.getRessourceById(id) instanceof MitarbeiterIn){
//			MitarbeiterIn aMitarbeiterIn = (MitarbeiterIn) ressourceFacade.getRessourceById(id);
//			return aMitarbeiterIn.getKostensatzProStunde();
//		}
		
		return 777777;
	}
	
	public Ressource getRessourceById(Integer id) {
		return ressourceFacade.getRessourceById(id);
	}

	public int getRessourcenId() {
		return ressourcenId;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public void setRessourcenId(int ressourcenId) {
		this.ressourcenId = ressourcenId;
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

	public void setStundenkapazitaetProTag(int stundenkapazitaetProTag) {
		this.stundenkapazitaetProTag = stundenkapazitaetProTag;
	}
	
	
}
