package de.awk.ressourcenverwaltung.dao;

import javax.ejb.Stateless;

import de.awk.dao.GenericDAO;
import de.awk.ressourcenverwaltung.model.Maschine;

@Stateless
public class MaschineDAO extends GenericDAO<Maschine> {

	public MaschineDAO(){
		super(Maschine.class);
	}
	
	public void delete(Maschine aMaschine){
		super.delete(aMaschine.getRessourcenId(), Maschine.class);
	}
	
}
