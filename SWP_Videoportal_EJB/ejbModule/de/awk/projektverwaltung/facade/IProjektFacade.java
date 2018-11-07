package de.awk.projektverwaltung.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import de.awk.projektverwaltung.model.Projekt;

@Local
public interface IProjektFacade {
	public abstract void saveProjekt(String aTitel, String aBeschreibung, Date aProjektStart,
			Date aProjektEnde, String aProjektleiter, String aProjektauftraggeber);
	public abstract List<Projekt> getAlleProjekte();
	public abstract Projekt getProjektById(int id);
	public abstract void updateProjekt(int aId, String aTitel, String aBeschreibung, Date aProjektStart, Date aProjektEnde,
			String aProjektleiter, String aProjektauftraggeber);
	public abstract void deleteProjekt(int aId);
	public abstract void saveProjResBuch(Projekt aProjekt);
	public abstract void updateProjekt(Projekt aProjekt);
	double getProjektKosten(int aId);
}
