package de.awk.projektverwaltung.facade.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.awk.projektverwaltung.dao.ProjResBuchDAO;
import de.awk.projektverwaltung.facade.IProjResBuchFacade;
import de.awk.projektverwaltung.model.ProjektRessourcenBuchung;

@Stateless
public class ProjResBuchFacadeImpl implements IProjResBuchFacade{

	
	@EJB
	private ProjResBuchDAO prbDAO;
	
	@Override
	public List<ProjektRessourcenBuchung> getAllePRB() {
		
		return prbDAO.findAll();
	}
	
	@Override
	public int checkRessourceUsage(int resNr, Date buchDatum) {
		int usageForThisDate = 0;
		int aResNr = 0;
		Date aBuchDatum = null;
		List<ProjektRessourcenBuchung> prbList = this.getAllePRB();
		for(ProjektRessourcenBuchung aPRB : prbList){
			aResNr = aPRB.getRessourcenId();
			aBuchDatum = aPRB.getBuchungsdatum();
			if(aResNr == resNr)
			{
				if(aBuchDatum.compareTo(buchDatum) == 0){
					usageForThisDate += aPRB.getGebuchteStunden();
				}
				
			}
		}
		
		return usageForThisDate;
		
	}
	
	@Override
	public boolean checkIfRessourceInUse(int resNr){
		int aResNr = 0;
		List<ProjektRessourcenBuchung> prbList = this.getAllePRB();
		for(ProjektRessourcenBuchung aPRB : prbList){
			aResNr = aPRB.getRessourcenId();
			if(aResNr == resNr)
			{
				return true;
				
			}
		}
		return false;
	}

}
