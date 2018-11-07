package de.awk.ressourcenverwaltung.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.awk.ressourcenverwaltung.dao.MaschineDAO;
import de.awk.ressourcenverwaltung.facade.IMaschineFacade;
import de.awk.ressourcenverwaltung.model.Maschine;
import de.awk.ressourcenverwaltung.model.MitarbeiterIn;

@Stateless
public class MaschineFacadeImpl implements IMaschineFacade{

	@EJB
	private MaschineDAO maschineDAO;
	
	@Override
	public void saveMaschine(String name, double kostensatzProStunde, int strundenProTag, String standort) {
		Maschine maschine = new Maschine(name,kostensatzProStunde,strundenProTag,standort);
		maschineDAO.save(maschine);
	}

	@Override
	public List<Maschine> getAlleMaschinen() {
		return maschineDAO.findAll();
	}

	@Override
	public Maschine getMaschineById(int id) {	
		Maschine aMaschine = null;
		try{
			aMaschine = maschineDAO.find(id);
		} catch(Exception e){
			
		}
		return aMaschine;
	}
	
	public Maschine getMaschineByIdEJB(int id) {	
		MaschineDAO mDAO = new MaschineDAO();
		Maschine aMaschine = null;
		try{
			aMaschine = mDAO.find(id);
		} catch(Exception e){
			
		}
		return aMaschine;
	}

	@Override
	public void updateMaschine(int aId, String aName, double aKostensatzProStunde, int aStundenProTag,
			String aStandort) {
		Maschine aMaschine = this.getMaschineById(aId);
		aMaschine.setName(aName);
		aMaschine.setKostensatzProStunde(aKostensatzProStunde);
		aMaschine.setStundenkapazitaetProTag(aStundenProTag);
		aMaschine.setStandort(aStandort);
		maschineDAO.save(aMaschine);
		
	}

	@Override
	public void deleteMaschine(int aId) {
		Maschine aMaschine = this.getMaschineById(aId);
		try{
			maschineDAO.delete(aMaschine);
		} catch (Exception e) {

		}
		
	}

}
