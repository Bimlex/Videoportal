package de.awk.ressourcenverwaltung.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.awk.ressourcenverwaltung.dao.RessourceDAO;
import de.awk.ressourcenverwaltung.facade.IRessourceFacade;
import de.awk.ressourcenverwaltung.model.Ressource;

@Stateless
public class RessourceFacadeImpl implements IRessourceFacade {

	@EJB
	private RessourceDAO ressourceDAO;

	@Override
	public List<Ressource> getAlleRessourcen() {
		return ressourceDAO.findAll();
	}

	@Override
	public Ressource getRessourceById(int id) {
		return ressourceDAO.find(id);
	}

	@Override
	public int checkCapacity(int id, int usage){
		try{
			Ressource aRes = this.getRessourceById(id);
			
			return aRes.getStundenkapazitaetProTag() - usage;
		} catch(Exception e){
			
		}
		
		return 0;
	}
	
	@Override
	public int getCapacity(int id){
		try{
			Ressource aRes = this.getRessourceById(id);
			
			return aRes.getStundenkapazitaetProTag();
		} catch(Exception e){
			
		}
		return 0;
	}


	
}
