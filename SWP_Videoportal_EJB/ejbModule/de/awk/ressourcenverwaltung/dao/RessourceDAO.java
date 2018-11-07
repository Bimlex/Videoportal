package de.awk.ressourcenverwaltung.dao;

import javax.ejb.Stateless;

import de.awk.dao.GenericDAO;
import de.awk.ressourcenverwaltung.model.Ressource;

@Stateless
public class RessourceDAO  extends GenericDAO<Ressource> {
	
	public RessourceDAO(){
		super(Ressource.class);
	}
	
	public void delete(Ressource aRessource){
		super.delete(aRessource.getRessourcenId(), Ressource.class);
	}
}
