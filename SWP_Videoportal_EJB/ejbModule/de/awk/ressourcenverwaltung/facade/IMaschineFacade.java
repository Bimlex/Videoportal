package de.awk.ressourcenverwaltung.facade;

import java.util.List;

import de.awk.ressourcenverwaltung.model.Maschine;

public interface IMaschineFacade {
	
	public abstract void saveMaschine(String aName, double aKostensatzProStunde, 
			int aStrundenProTag,String aStandort);
	
	public abstract List<Maschine> getAlleMaschinen();
	
	public abstract Maschine getMaschineById(int id);
	
	public abstract void updateMaschine(int aId, String aName, double aKostensatzProStunde, int aStrundenProTag,
			String aStandort);
	
	public abstract void deleteMaschine(int aId);
}
