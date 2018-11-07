package de.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.awk.projektverwaltung.facade.IProjResBuchFacade;
import de.awk.ressourcenverwaltung.facade.IMaschineFacade;
import de.awk.ressourcenverwaltung.model.Maschine;

@ManagedBean(name="maschineMB")
@RequestScoped
public class MaschineMB {
	
	@EJB
	IMaschineFacade maschineFacade;
	
	@EJB
	IProjResBuchFacade prbFacade;
	
	@NotNull
	@Digits(fraction = 0, integer = 6)	
	private int ressourcenId;
	
	@NotNull
	@Size(min=1, max=25)
	@Pattern(regexp = "[A-Za-z ]*", message= "Namen bitte mit max. 25 Buchstaben und Leerzeihchen!")
	private String name;
	
	@Digits(fraction = 2, integer = 7)	
	private double kostensatzProStunde;
	
	@Max(24)
	@Min(1)
	private int stundenkapazitaetProTag;
	
	@NotNull
	@Size(min=1, max=25)
	@Pattern(regexp = "[A-Za-z ]*", message= "Standort bitte mit max. 25 Buchstaben und Leerzeichen!")
	private String standort;
	
	
	
	public List<Maschine> getAlleMaschinen(){
		return maschineFacade.getAlleMaschinen();
	}
	
	public String neueMaschineAnlegen(){
		maschineFacade.saveMaschine(this.name, this.kostensatzProStunde, 
				this.stundenkapazitaetProTag, this.standort);

		return "maschineMenue";
	}
	
	public void maschineAendern(){
		maschineFacade.updateMaschine(this.ressourcenId, this.name, this.kostensatzProStunde, 
				this.stundenkapazitaetProTag, this.standort);
		sendInfoMessageToUser("Maschine " + this.ressourcenId + " wurde ge\u00e4ndert.");
	}
	
	public void maschinesLoeschen(){
		if (!prbFacade.checkIfRessourceInUse(this.ressourcenId)){
			sendInfoMessageToUser("Maschine " + this.ressourcenId + " konnte gel\u00f6scht werden.");
			maschineFacade.deleteMaschine(this.ressourcenId);
		} else {
			sendErrorMessageToUser("Maschine " + this.ressourcenId + " ist f\u00fcr Projekte gebucht.\n" + 
					"Maschine " + this.ressourcenId + " konnte nicht gel\u00f6scht werden.");
		}
	}
	
	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}
	
	private void sendInfoMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	private void sendErrorMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	public String getMaschineById(){
		// siehe Kommentar aus ProjektMB public String getProjektById()
		Maschine maschine = maschineFacade.getMaschineById(this.ressourcenId);
		if(maschine != null){
			this.ressourcenId = maschine.getRessourcenId();
			this.name = maschine.getName();
			this.kostensatzProStunde = maschine.getKostensatzProStunde();
			this.stundenkapazitaetProTag = maschine.getStundenkapazitaetProTag();
			this.standort = maschine.getStandort();
			return "maschineAendern";
		} else {
			return "maschineAendernUebersicht";
		}
	}	
	
	public Maschine getMaschineById(Integer id) {
		return maschineFacade.getMaschineById(id);
	}
	

	public int getRessourcenId() {
		return ressourcenId;
	}

	public void setRessourcenId(int ressourcenId) {
		// set Id Funktion auch in KundeMB aus dem Beispiel _v2
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

	public String getStandort() {
		return standort;
	}

	public void setStandort(String standort) {
		this.standort = standort;
	}
	
	public static MaschineMB getCurrentInstance(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (MaschineMB) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "MaschineMB");
	}

}
