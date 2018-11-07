package de.awk.projektverwaltung.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import de.awk.projektverwaltung.model.ProjektRessourcenBuchung;

@Local
public interface IProjResBuchFacade {
	public abstract List<ProjektRessourcenBuchung> getAllePRB();

	public abstract int checkRessourceUsage(int resNr, Date buchDatum);

	public abstract boolean checkIfRessourceInUse(int resNr);
}
