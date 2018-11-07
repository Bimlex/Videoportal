package de.awk.projektverwaltung.facade.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.awk.projektverwaltung.dao.ProjektDAO;
import de.awk.projektverwaltung.facade.IProjektFacade;
import de.awk.projektverwaltung.model.Projekt;
import de.awk.projektverwaltung.model.ProjektRessourcenBuchung;
import de.awk.ressourcenverwaltung.facade.IMaschineFacade;
import de.awk.ressourcenverwaltung.facade.IMitarbeiterInFacade;
import de.awk.ressourcenverwaltung.facade.IRessourceFacade;
import de.awk.ressourcenverwaltung.facade.impl.MaschineFacadeImpl;
import de.awk.ressourcenverwaltung.facade.impl.MitarbeiterInFacadeImpl;
import de.awk.ressourcenverwaltung.facade.impl.RessourceFacadeImpl;
import de.awk.ressourcenverwaltung.model.Maschine;
import de.awk.ressourcenverwaltung.model.MitarbeiterIn;
import de.awk.ressourcenverwaltung.model.Ressource;

@Stateless
public class ProjektFacadeImpl implements IProjektFacade{

	@EJB
	private ProjektDAO projektDAO;
	
	@Override
	public void saveProjekt(String aTitel, String aBeschreibung, Date aProjektStart,
			Date aProjektEnde, String aProjektleiter, String aProjektauftraggeber) {
		Projekt projekt = new Projekt(aTitel, aBeschreibung, aProjektStart,
				aProjektEnde, aProjektleiter, aProjektauftraggeber);
		projektDAO.save(projekt);
	}
	
	@Override
	public void saveProjResBuch(Projekt aProjekt){
		isProjektDataKomplett(aProjekt);
		projektDAO.save(aProjekt);
	}
	
	private void isProjektDataKomplett(Projekt aProjekt){
		boolean hasError = false;
		
		if(aProjekt == null) hasError = true;
		if(aProjekt.getProjResBuchungen().size() == 0) hasError = true;
		if(hasError) throw new IllegalArgumentException("Projekt muss mindestens eine Ressourcenbuchung besitzen!");
	}
	
	@Override
	public void updateProjekt(int aId, String aTitel, String aBeschreibung, Date aProjektStart,
			Date aProjektEnde, String aProjektleiter, String aProjektauftraggeber) {
		Projekt projekt = this.getProjektById(aId);
		projekt.setTitel(aTitel);
		projekt.setBeschreibung(aBeschreibung);
		projekt.setProjektStart(aProjektStart);
		projekt.setProjektEnde(aProjektEnde);
		projekt.setProjektleiter(aProjektleiter);
		projekt.setProjektauftraggeber(aProjektauftraggeber);
		projektDAO.save(projekt);
	}
	
	@Override
	public void updateProjekt(Projekt aProjekt){
		projektDAO.update(aProjekt);
	}


	@Override
	public List<Projekt> getAlleProjekte() {
		return projektDAO.findAll();
	}

	@Override
	public Projekt getProjektById(int id) {
		Projekt aProjekt = null;
		try{
			aProjekt = projektDAO.find(id);
		} catch (Exception e) {
			
		}
		return aProjekt;
	}
	
	@Override
	public void deleteProjekt(int aId){
		Projekt aProjekt = this.getProjektById(aId);
		try{
			projektDAO.delete(aProjekt);
		} catch (Exception e) {
			
		}
		
	}
	
	@Override
	public double getProjektKosten(int aId){
		Projekt aProjekt = this.getProjektById(aId);
//		RessourceFacadeImpl resFacImpl = new RessourceFacadeImpl();
//		IRessourceFacade resFac = resFacImpl;
		
		MaschineFacadeImpl maschFacImpl = new MaschineFacadeImpl();
		MitarbeiterInFacadeImpl mitarbFacImpl = new MitarbeiterInFacadeImpl();
//		IMaschineFacade maschFac;
//		IMitarbeiterInFacade mitarbFac = mitarbFacImpl;
		Maschine aMachine = null;
		MitarbeiterIn aMitarbeiterIn = null;
		double summeKosten = 0;
//		try{
			for(ProjektRessourcenBuchung aPRB : aProjekt.getProjResBuchungen()){
//				Ressource aRessource = resFac.getRessourceById(aPRB.getRessourcenId());
				aMachine = null;
				aMitarbeiterIn = null;
				try{
					aMachine = maschFacImpl.getMaschineById(aPRB.getRessourcenId());
				} catch(Exception e){
					
				}
				
				try{
					aMitarbeiterIn = mitarbFacImpl.getMitarbeiterInById(aPRB.getRessourcenId());
				} catch(Exception e){
					
				}
				
				if(aMachine != null){
					summeKosten += aPRB.getGebuchteStunden() * aMachine.getKostensatzProStunde();
				}
				
				if(aMitarbeiterIn != null){
					summeKosten += aPRB.getGebuchteStunden() * aMitarbeiterIn.getKostensatzProStunde();
				}
				
				
				
				
//				summeKosten += aPRB.getGebuchteStunden() * aRessource.getKostensatzProStunde();
			}
//		} catch(Exception e) {
//			
//		}
		return summeKosten;
	}
	
	
	
	
	

}
