package de.awk.projektverwaltung.dao;

import javax.ejb.Stateless;

import de.awk.benutzerverwaltung.dao.GenericDAO;
import de.awk.projektverwaltung.model.Projekt;

@Stateless
public class ProjektDAO extends GenericDAO<Projekt> {

	public ProjektDAO(){
		super(Projekt.class);
	}
	
	public void delete(Projekt aProjekt){
		super.delete(aProjekt.getProjektId(), Projekt.class);
	}
	
}
