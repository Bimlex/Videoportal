package de.awk.ressourcenverwaltung.dao;

import javax.ejb.Stateless;

import de.awk.ressourcenverwaltung.dao.GenericDAO;
import de.awk.ressourcenverwaltung.model.MitarbeiterIn;
@Stateless
public class MitarbeiterInDAO extends GenericDAO<MitarbeiterIn> {
	
	public MitarbeiterInDAO(){
		super(MitarbeiterIn.class);
	}
	
	public void delete(MitarbeiterIn aMitarbeiterIn){
		super.delete(aMitarbeiterIn.getRessourcenId(), MitarbeiterIn.class);
	}

}
