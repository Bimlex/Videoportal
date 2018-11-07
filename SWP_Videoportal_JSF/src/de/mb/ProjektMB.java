package de.mb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.awk.projektverwaltung.facade.IProjResBuchFacade;
import de.awk.projektverwaltung.facade.IProjektFacade;
import de.awk.projektverwaltung.model.Projekt;
import de.awk.projektverwaltung.model.ProjektRessourcenBuchung;
import de.awk.ressourcenverwaltung.facade.IMaschineFacade;
import de.awk.ressourcenverwaltung.facade.IMitarbeiterInFacade;
import de.awk.ressourcenverwaltung.facade.IRessourceFacade;
import de.awk.ressourcenverwaltung.model.Maschine;
import de.awk.ressourcenverwaltung.model.MitarbeiterIn;
import de.awk.ressourcenverwaltung.model.Ressource;

@ManagedBean(name="projektMB")
@ViewScoped
public class ProjektMB {

	@EJB
	IProjektFacade projektFacade;
	
	@EJB
	IMaschineFacade maschineFacade;
	
	@EJB
	IMitarbeiterInFacade mitarbeiterInFacade;
	
	@EJB
	IProjResBuchFacade prbFacade;
	
	@EJB 
	IRessourceFacade ressourceFacade;
	
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
	private Integer projektId;
	
	@NotNull
	@Size(min=1, max=50)
	@Pattern(regexp = "[A-Za-z ]*", message= "Titel bitte mit max. 50 Buchstaben und Leerzeihchen!")
	private String titel;
	
	@NotNull
	@Size(min=1, max=250)
	@Pattern(regexp = "[A-Za-z ]*", message= "Beschreibung bitte mit max. 250 Buchstaben und Leerzeihchen!")
	private String beschreibung;
	
	@Future
	private Date projektStart;
	
	@Future
	private Date projektEnde;
	
	@NotNull
	@Size(min=1, max=50)
	@Pattern(regexp = "[A-Za-z ]*", message= "Titel bitte mit max. 50 Buchstaben und Leerzeihchen!")
	private String projektleiter;
	
	@NotNull
	@Size(min=1, max=50)
	@Pattern(regexp = "[A-Za-z ]*", message= "Titel bitte mit max. 50 Buchstaben und Leerzeihchen!")
	private String projektauftraggeber;
	
	// ------------------------------------
	
//	@ManagedProperty(value="#{maschineMB}")
//	private MaschineMB maschineMB;
//	
//	@ManagedProperty(value="#{mitarbeiterInMB}")
//	private MitarbeiterInMB mitarbeiterIn;
//	
//	
//	
//	public MaschineMB getMaschineMB() {
//		return maschineMB;
//	}
//
//	public void setMaschineMB(MaschineMB maschineMB) {
//		this.maschineMB = maschineMB;
//	}
//
//	public MitarbeiterInMB getMitarbeiterIn() {
//		return mitarbeiterIn;
//	}
//
//	public void setMitarbeiterIn(MitarbeiterInMB mitarbeiterIn) {
//		this.mitarbeiterIn = mitarbeiterIn;
//	}
	
	//------------------------------

	private double kosten = 0;
	
	public List<Projekt> getAlleProjekte(){
		return projektFacade.getAlleProjekte();
	}
	
	public String neuesProjektAnlegen(){
		projektFacade.saveProjekt(this.titel, this.beschreibung, 
				this.projektStart, this.projektEnde, 
				this.projektleiter, this.projektauftraggeber);
		return "projektMenue";
	}
	
	public void projektAendern(){
		projektFacade.updateProjekt(this.projektId, this.titel, this.beschreibung, 
				this.projektStart, this.projektEnde, 
				this.projektleiter, this.projektauftraggeber);
		sendInfoMessageToUser("Projekt " + this.projektId + " wurde ge\u00e4ndert.");
	}
	
	public void projektLoeschen(){
		if(this.checkDeleteProjekt(this.projektId)){
			projektFacade.deleteProjekt(this.projektId);
		}
	}
	
	public String getTestBeschreibung(){
		return this.projekt.getBeschreibung();
		
	}
	
	public String testMitBeschreibung(ProjektRessourcenBuchung aProjResBuch){
		return this.projekt.getBeschreibung();
		
	}
	
	public String aProjResBuchEintragen(Projekt aProjekt, ProjektRessourcenBuchung aProjResBuch){
		Projekt pProjekt = projektFacade.getProjektById(aProjekt.getProjektId());
		
		pProjekt.addProjResBuchung(aProjResBuch);
		projektFacade.updateProjekt(pProjekt);
		sendInfoMessageToUser("Neue Ressourcenbuchung " + aProjResBuch.getNrImProjekt() + " eingetragen!");
		return "projResBuch";
	}
	
	public String aProjResBuchEintragen(int aProjektId, ProjektRessourcenBuchung aProjResBuch){
		Projekt pProjekt = projektFacade.getProjektById(aProjektId);
		
		pProjekt.addProjResBuchung(aProjResBuch);
		projektFacade.updateProjekt(pProjekt);
		sendInfoMessageToUser("Neue Ressourcenbuchung " + aProjResBuch.getNrImProjekt() + " eingetragen!");
		return "projResBuchMenue";
	}
	
	// Genutzt
	public String aProjResBuchEintragen(int aProjektId, int aRessourcenId, ProjektRessourcenBuchung aProjResBuch){
		aProjResBuch.setRessourcenId(aRessourcenId);
		int usage = 0;
		
		usage = prbFacade.checkRessourceUsage(aRessourcenId, aProjResBuch.getBuchungsdatum());
		
//		Ressource aRessource = ressourceFacade.getRessourceById(aRessourcenId);
//		
//		
//		if(usage + aProjResBuch.getGebuchteStunden() <= aRessource.getStundenkapazitaetProTag()){
//			Projekt pProjekt = projektFacade.getProjektById(aProjektId);
//			
//			pProjekt.addProjResBuchung(aProjResBuch);
//			projektFacade.updateProjekt(pProjekt);
//			sendInfoMessageToUser("Neue Ressourcenbuchung " + aProjResBuch.getNrImProjekt() + " eingetragen!");
//			return "projResBuchMenue";
//		} else {
//			sendErrorMessageToUser("Ressource " + aRessourcenId + " hat an diesem Datum noch\n" +
//					(aRessource.getStundenkapazitaetProTag() - usage) + " Stunden frei.\n" +
//					"Die zu buchenden " + aProjResBuch.getGebuchteStunden() + " Stunden \u00fcbersteigen " +
//					"die Kapazit\u00e4t der Ressource " + aRessourcenId);
//			return "projResBuchHinzufuegen";
//		}
		
		int capacity = 0;
		capacity = ressourceFacade.checkCapacity(aRessourcenId, usage + aProjResBuch.getGebuchteStunden());
		
		if(capacity >= 0){
			Projekt pProjekt = projektFacade.getProjektById(aProjektId);
			
			pProjekt.addProjResBuchung(aProjResBuch);
			projektFacade.updateProjekt(pProjekt);
			sendInfoMessageToUser("Neue Ressourcenbuchung " + aProjResBuch.getNrImProjekt() + " eingetragen!");
			return "projResBuchMenue";
		}else {
			sendErrorMessageToUser("Ressource " + aRessourcenId + " hat an diesem Datum noch\n" +
			(ressourceFacade.getCapacity(aRessourcenId) - usage) + " Stunden frei.\n" +
			"Die zu buchenden " + aProjResBuch.getGebuchteStunden() + " Stunden \u00fcbersteigen " +
			"die Kapazit\u00e4t der Ressource " + aRessourcenId);
			return "projResBuchHinzufuegen";
		}
		

	}
	
	private void sendInfoMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	private void sendErrorMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	
	
	public String setProjektById(int aProjektId){
		projekt = projektFacade.getProjektById(aProjektId);
		
		return "prbProjekt";
		
	}
	
	public String aProjResBuchEintragen(ProjektRessourcenBuchung aProjResBuch){
		
		
		this.projekt.addProjResBuchung(aProjResBuch);
		projektFacade.updateProjekt(projekt);
		sendInfoMessageToUser("Neue Ressourcenbuchung " + aProjResBuch.getNrImProjekt() + " eingetragen!");
		return "projResBuch";
	}
	
	
	
	
	
	public String aProjResBuchEintragenParam(int nr, int ressourcenId, Date buchungsdateum, int gebuchteStunden){
		ProjektRessourcenBuchung pProjResBuch = new ProjektRessourcenBuchung(nr, ressourcenId, buchungsdateum, gebuchteStunden);
//		Date testDate = new Date();
//		ProjektRessourcenBuchung pProjResBuch = new ProjektRessourcenBuchung(20, 30, testDate, 4);
		this.projekt.addProjResBuchung(pProjResBuch);
//		sendInfoMessageToUser("Neue Ressourcenbuchung " + pProjResBuch.getNrImProjekt() + " eingetragen!");
		
//		projektFacade.updateProjekt(projekt);
		
		return "projResBuch";
	}
	
	
	
	
	
	
	public String aProjResBuchEntfernen(ProjektRessourcenBuchung aProjResBuch){
		this.projekt.removeProjResBuchung(aProjResBuch);
		sendInfoMessageToUser("Auftragsposition " + aProjResBuch.getNrImProjekt() + " entfernt!");

		//return LIST_ALL_AUFTRAEGE;
//		Aus AuftragMB.java:
//		private static final String LIST_ALL_AUFTRAEGE = "listAllAuftraege";
//		"listAllAuftraege" in der faces-Config.xml von dem Projekt "EJB_JPA_Auftrag_JSFClient_V1" nicht zu finden
		
		return "";
	}
	
	//Genutzt
	public String aProjResBuchEntfernen(int aProjektId, int aPrbID){
		try{
			Projekt aProjekt = this.getProjektById(aProjektId);
			ProjektRessourcenBuchung aPRB = null;
			for(ProjektRessourcenBuchung pPRB : aProjekt.getProjResBuchungen()){
				if(pPRB.getProjResBuchId() == aPrbID){
					aPRB = pPRB;
				}
			}
			 
			aProjekt.removeProjResBuchung(aPRB);
			projektFacade.updateProjekt(aProjekt);
			sendInfoMessageToUser("Buchung " + aPRB.getNrImProjekt() + " entfernt!");
		} catch (Exception e){
			
		}

		
		return "projResBuchMenue";
	}
	
	public String aProjResBuchEntfernen(int aProjektId, ProjektRessourcenBuchung aProjResBuch){
		Projekt pProjekt = projektFacade.getProjektById(aProjektId);
		
		pProjekt.removeProjResBuchung(aProjResBuch);
		projektFacade.updateProjekt(pProjekt);
		sendInfoMessageToUser("Auftragsposition " + aProjResBuch.getNrImProjekt() + " entfernt!");
		return "projResBuch";
	}
	
	public String testTest(){
		int aID = this.projekt.getProjektId();
		return String.valueOf(aID);
	}
	

	
	public String resProjBuchEnd(){
		try{
			System.out.println("Anzahl an Ressourcenbuchungen: " + projekt.getProjResBuchungen().size());
			projektFacade.saveProjResBuch(projekt);
			projekt = new Projekt();
		} catch (EJBException e) {
			sendErrorMessageToUser("Fehler beim Speichern vom Auftrag!");
			return "projResBuch";
		}
		return "projektMenue";
	}
	
	
	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}
	
	public String getProjektById(){
		
//		da in der Funktion "public List<Kunde> getAlleKunden()" aus der KundeMB 
//		die Klasse Kunde ebenfalls in der KundeMB genutzt wird, habe ich die Klasse 
//		Projekt ebenfalls fuer die ProjektMB freigegeben und nutze sie auch in dieser Funktion, 
//		sowie der Funktion "public List<Projekt> getAlleProjekte()"
		
		Projekt aProjekt = projektFacade.getProjektById(this.projektId);
		if(aProjekt != null){
//		if(projekt != null){
//			projekt = aProjekt;
			projekt = aProjekt;
			
			this.projektId = aProjekt.getProjektId();
			this.titel = aProjekt.getTitel();
			this.beschreibung = aProjekt.getBeschreibung();
			this.projektStart = aProjekt.getProjektStart();
			this.projektEnde = aProjekt.getProjektEnde();
			this.projektleiter = aProjekt.getProjektleiter();
			this.projektauftraggeber = aProjekt.getProjektauftraggeber();
			
			return "projektAendern";
		} else {
			return "projektAendernUebersicht";
		}

	}
	
	public String getProjektKosten(){
//		Projekt aProjekt = projekt;
//		RessourceMB aRessourceMB = new RessourceMB();
//		double Summe = 0;
//		
//		for(ProjektRessourcenBuchung projResBuch : aProjekt.getProjResBuchungen()){
//			
//			return String.valueOf(aRessourceMB.getRessourcenStundensatzById(
//					Integer.valueOf(projResBuch.getRessourcenId())));
//			
//		}
//		return "nicht funktioniert";
//			
//
////		return String.valueOf(aProjekt.getProjektId());
	
		
// FUNKTIONIERT bis auf die DAO @EJB -----------	
		
//		this.setKosten(projektFacade.getProjektKosten(
//				projekt.getProjektId())
//		);
//		
////		this.setKosten(123);
//		
//		return "";
// ----------------------------------------------
	
		
		// Facade ist null
		Projekt aProjekt = projektFacade.getProjektById(projekt.getProjektId());
		Maschine aMaschine = null;
		MitarbeiterIn aMitarbeiterIn = null;
		
//		MaschineMB maschMB = new MaschineMB();
//		MitarbeiterInMB mitarbMB = new MitarbeiterInMB();
		
//		MaschineMB maschMB = MaschineMB.getCurrentInstance();
//		MitarbeiterInMB mitarbMB = MitarbeiterInMB.getCurrentInstance();
		
		double summeKosten = 0;
		for(ProjektRessourcenBuchung aPRB : aProjekt.getProjResBuchungen()){
			aMaschine = null;
			aMitarbeiterIn = null;
			
			try{
				aMaschine = maschineFacade.getMaschineById(aPRB.getRessourcenId());
			} catch(Exception e){
				
			}
			
			try{
				aMitarbeiterIn = mitarbeiterInFacade.getMitarbeiterInById(aPRB.getRessourcenId());
			} catch(Exception e){
				
			}
			
			if(aMaschine != null){
				summeKosten += aPRB.getGebuchteStunden() * aMaschine.getKostensatzProStunde();
			}
			
			if(aMitarbeiterIn != null){
				summeKosten += aPRB.getGebuchteStunden() * aMitarbeiterIn.getKostensatzProStunde();
			}
		}
		
		this.setKosten(summeKosten);
		return "";
	}
	
	public Projekt getProjektById(Integer id) {
		return projektFacade.getProjektById(id);
	}
	
	public boolean checkDeleteProjekt(Integer id){
		Projekt aProjekt = this.getProjektById(id);
		if(!aProjekt.getProjResBuchungen().isEmpty()){
			sendErrorMessageToUser("F\u00fcr das Projekt " + aProjekt.getProjektId() + 
					" liegen Buchungen vor. Es kann nicht gel\u00f6scht werden.");	
			return false;
		} else {
			sendInfoMessageToUser("Das Projekt " + aProjekt.getProjektId() + 
					" wurde gel\u00f6scht.");	
			return true;
		}
	}
	
	public void initProjektId(){
		this.projektId = 0;
	}

	public Integer getProjektId() {
		return projektId;
	}
	
//	public void setKunde_id(int kunde_id) {
//		this.kunde_id = kunde_id;
//	}

	public void setProjektId(Integer projektId) {
		// set Id Funktion auch in KundeMB aus dem Beispiel _v2 (siehe oben auskommentiert)
		this.projektId = projektId;
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

	public double getKosten() {
		return kosten;
	}

	public void setKosten(double kosten) {
		this.kosten = kosten;
	}
	
	
}
