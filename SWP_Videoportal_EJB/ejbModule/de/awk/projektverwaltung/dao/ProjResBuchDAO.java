package de.awk.projektverwaltung.dao;

import javax.ejb.Stateless;

import de.awk.projektverwaltung.dao.GenericDAO;
import de.awk.projektverwaltung.model.ProjektRessourcenBuchung;

@Stateless
public class ProjResBuchDAO extends GenericDAO<ProjektRessourcenBuchung>{
	
	public ProjResBuchDAO(){
		super(ProjektRessourcenBuchung.class);
	}
	
}
