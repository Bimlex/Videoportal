package de.mb;

import java.util.Date;
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
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.awk.projektverwaltung.facade.IProjResBuchFacade;
import de.awk.ressourcenverwaltung.facade.IMitarbeiterInFacade;
import de.awk.ressourcenverwaltung.model.MitarbeiterIn;

@ManagedBean(name="mitarbeiterInMB")
@RequestScoped
public class MitarbeiterInMB {

	@EJB
	IMitarbeiterInFacade mitarbeiterInFacade;
	
	@EJB
	IProjResBuchFacade prbFacade;
	
	@NotNull
	@Digits(fraction = 0, integer = 6)	
	private int ressourcenId;
	
	@NotNull
	@Size(min=1, max=25)
	@Pattern(regexp = "[A-Za-z ]*", message= "Namen bitte mit max. 25 Buchstaben und Leerzeichen!")
	private String name;
	
	@Digits(fraction = 2, integer = 7)	
	private double kostensatzProStunde;
	
	@Max(24)
	@Min(1)
	private int stundenkapazitaetProTag;
	
	@NotNull
	@Size(min=1, max=25)
	@Pattern(regexp = "[A-Za-z ]*", message= "Vornamen bitte mit max. 25 Buchstaben und Leerzeichen!")
	private String vorname;
	
	@Past
	private Date geburtstag;
	
	@NotNull
	@Size(min=1, max=25)
	@Pattern(regexp = "[A-Za-z ]*", message= "Fachgebiete bitte mit max. 25 Buchstaben und Leerzeichen!")
	private String fachgebiet;
	
	public List<MitarbeiterIn> getAlleMitarbeiterInnen(){
		return mitarbeiterInFacade.getAlleMitarbeiterInnen();
	}
	
	public String neueMitarbeiterInAnlegen(){
		mitarbeiterInFacade.saveMitarbeiterIn(this.name, this.kostensatzProStunde, 
				this.vorname, this.geburtstag, this.fachgebiet);

		return "mitarbeiterInMenue";
	}
	
	public void mitarbeiterInAendern(){
		mitarbeiterInFacade.updateMitarbeiterIn(this.ressourcenId, this.name, 
				this.kostensatzProStunde, this.vorname, this.geburtstag, this.fachgebiet);
		sendInfoMessageToUser("MitarbeiterIn " + this.ressourcenId + " wurde ge\u00e4ndert.");
	}
	
	public void mitarbeiterInLoeschen(){
		if (!prbFacade.checkIfRessourceInUse(this.ressourcenId)){
			sendInfoMessageToUser("MitarbeiterIn " + this.ressourcenId + " konnte gel\u00f6scht werden.");
			mitarbeiterInFacade.deleteMitarbeiterIn(this.ressourcenId);
		} else {
			sendErrorMessageToUser("MitarbeiterIn " + this.ressourcenId + " ist f\u00fcr Projekte gebucht.\n" + 
					"MitarbeiterIn " + this.ressourcenId + " konnte nicht gel\u00f6scht werden.");
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
	
	public String getMitarbeiterInById(){
		// siehe Kommentar aus ProjektMB public String getProjektById()
		MitarbeiterIn mitarbeiterIn = mitarbeiterInFacade.getMitarbeiterInById(this.ressourcenId);
		if(mitarbeiterIn != null){
			this.ressourcenId = mitarbeiterIn.getRessourcenId();
			this.name = mitarbeiterIn.getName();
			this.kostensatzProStunde = mitarbeiterIn.getKostensatzProStunde();
			this.vorname = mitarbeiterIn.getVorname();
			this.geburtstag = mitarbeiterIn.getGeburtstag();
			this.fachgebiet = mitarbeiterIn.getFachgebiet();
			return "mitarbeiterInAendern";
		} else {
			return "mitarbeiterInAendernUebersicht";
		}
	}
	
	public MitarbeiterIn getMitarbeiterInById(Integer id) {
		return mitarbeiterInFacade.getMitarbeiterInById(id);
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

	public int getRessourcenId() {
		return ressourcenId;
	}
	
//	public void setKunde_id(int kunde_id) {
//	this.kunde_id = kunde_id;
//	}

	public void setRessourcenId(int ressourcenId) {
		// set Id Funktion auch in KundeMB aus dem Beispiel _v2 (siehe oben auskommentiert)
		this.ressourcenId = ressourcenId;
	}
	
	public static MitarbeiterInMB getCurrentInstance(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (MitarbeiterInMB) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "MitarbeiterInMB");
	}
	
}
