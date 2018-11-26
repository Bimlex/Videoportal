 package de.awk.videoverwaltung.facade;

import java.util.List;

import javax.ejb.Local;

import de.awk.videoverwaltung.model.Topic;

@Local
public interface ITopicFacade {

	public abstract List<Topic> getAlleKategorien();
	public abstract Topic getTopicById(int id);
	public abstract void saveTopic(String name, String beschreibung);
	public abstract void updateTopic(int id, String name, String beschreibung);
	public abstract void deleteTopic(int id);
	
	
}
