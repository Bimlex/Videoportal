package de.awk.ressourcenverwaltung.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import de.awk.ressourcenverwaltung.model.MitarbeiterIn;


@Local
public interface IMitarbeiterInFacade {
	
	public abstract void saveMitarbeiterIn(String name, double kostensatzProStunde, 
			String vorname, Date geburtstag, String fachgebiet);
	
	public abstract List<MitarbeiterIn> getAlleMitarbeiterInnen();
	
	public abstract MitarbeiterIn getMitarbeiterInById(int id);
	
	public abstract void updateMitarbeiterIn(int aId, String aName, double aKostensatzProStunde, String aVorname, Date aGeburtstag,
			String aFachgebiet);
	
	public abstract void deleteMitarbeiterIn(int aId);
}
