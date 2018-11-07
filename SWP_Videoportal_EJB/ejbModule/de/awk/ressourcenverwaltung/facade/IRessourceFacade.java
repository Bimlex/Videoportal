package de.awk.ressourcenverwaltung.facade;

import java.util.List;

import javax.ejb.Local;

import de.awk.ressourcenverwaltung.model.Ressource;

@Local
public interface IRessourceFacade {
	
	public abstract List<Ressource> getAlleRessourcen();
	public abstract Ressource getRessourceById(int id);
	public abstract int checkCapacity(int id, int usage);
	public abstract int getCapacity(int id);
}
