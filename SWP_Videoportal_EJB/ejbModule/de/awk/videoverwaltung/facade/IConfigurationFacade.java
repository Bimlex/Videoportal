package de.awk.videoverwaltung.facade;

import javax.ejb.Local;

import de.awk.videoverwaltung.model.Configuration;

@Local
public interface IConfigurationFacade {

//	public void setConfig(String video_typ, String saveVideoFolder);
//
//	public String getConfigVideoTyp(String videoTyp);
//
//	public String getConfigVideoFolder(String videoFolder);
//	public Configuration getConfigurationbyTyp();

	public Configuration getConfigurationbyOutput();

}
