package de.awk.videoverwaltung.facade.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.awk.videoverwaltung.dao.ConfigDAO;
import de.awk.videoverwaltung.facade.IConfigurationFacade;
import de.awk.videoverwaltung.model.Configuration;

	
	@Stateless
	public class ConfigurationFacadeImpl implements IConfigurationFacade {

		@EJB
		private ConfigDAO configDAO;

		@Override
		public Configuration getConfigurationbyOutput() {
			return this.configDAO.getConfigurationOutput();
		}

		

}
