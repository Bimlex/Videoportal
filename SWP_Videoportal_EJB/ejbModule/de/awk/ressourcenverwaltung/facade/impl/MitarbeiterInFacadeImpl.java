package de.awk.ressourcenverwaltung.facade.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.awk.projektverwaltung.model.Projekt;
import de.awk.ressourcenverwaltung.dao.MitarbeiterInDAO;
import de.awk.ressourcenverwaltung.facade.IMitarbeiterInFacade;
import de.awk.ressourcenverwaltung.model.MitarbeiterIn;

@Stateless
public class MitarbeiterInFacadeImpl implements IMitarbeiterInFacade {

	@EJB
	private MitarbeiterInDAO mitarbeiterInDAO;
	
	@Override
	public void saveMitarbeiterIn(String name, double kostensatzProStunde,
			String vorname, Date geburtstag, String fachgebiet) {
		MitarbeiterIn mitarbeiterIn = new MitarbeiterIn(name, kostensatzProStunde, vorname,geburtstag,fachgebiet);
		mitarbeiterInDAO.save(mitarbeiterIn);
	}
	
	@Override
	public void updateMitarbeiterIn(int aId, String aName, double aKostensatzProStunde,
			String aVorname, Date aGeburtstag, String aFachgebiet) {
		MitarbeiterIn mitarbeiterIn = this.getMitarbeiterInById(aId);
		mitarbeiterIn.setName(aName);
		mitarbeiterIn.setKostensatzProStunde(aKostensatzProStunde);
		mitarbeiterIn.setVorname(aVorname);
		mitarbeiterIn.setGeburtstag(aGeburtstag);
		mitarbeiterIn.setFachgebiet(aFachgebiet);
		mitarbeiterInDAO.save(mitarbeiterIn);
	}

	@Override
	public List<MitarbeiterIn> getAlleMitarbeiterInnen() {
		return mitarbeiterInDAO.findAll();
	}

	@Override
	public MitarbeiterIn getMitarbeiterInById(int id) {
		MitarbeiterIn aMitarbeiterIn = null;
		try{
			aMitarbeiterIn = mitarbeiterInDAO.find(id);
		} catch(Exception e){
			
		}
		return aMitarbeiterIn;
	}

	public MitarbeiterIn getMitarbeiterInByIdEJB(int id) {
		MitarbeiterInDAO mDAO = new MitarbeiterInDAO();
		MitarbeiterIn aMitarbeiterIn = null;
		try{
			aMitarbeiterIn = mDAO.find(id);
		} catch(Exception e){
			
		}
		return aMitarbeiterIn;
	}
	
	@Override
	public void deleteMitarbeiterIn(int aId) {	
		MitarbeiterIn aMitarbeiterIn = this.getMitarbeiterInById(aId);
		try{
			mitarbeiterInDAO.delete(aMitarbeiterIn);
		} catch (Exception e) {

		}
		
	}

}
