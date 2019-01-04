package de.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import de.awk.videoverwaltung.facade.IConfigurationFacade;
import de.awk.videoverwaltung.model.Configuration;

@ManagedBean(name = "configurationMB")
@RequestScoped
public class ConfigurationMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3593854984271153855L;

	private Configuration configuration;

	@EJB
	IConfigurationFacade configurationFacade;

	public Configuration getConfigurationOutput() {
		return this.setConfiguration(this.configurationFacade.getConfigurationbyOutput());
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public Configuration setConfiguration(Configuration configuration) {
		this.configuration = configuration;
		return configuration;
	}

}
