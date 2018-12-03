 package de.awk.videoverwaltung.facade;

import java.util.List;

import javax.ejb.Local;
import de.awk.videoverwaltung.model.Topic;

@Local
public interface ITopicFacade {

	public abstract List<Topic> getAlleKategorien();
	public abstract Topic getTopicById(int topicId);
	
	public abstract void updateTopic(int topicId, String name, String beschreibung);
	

	

	
	public abstract List<Topic> findTopicsById(int searchfield);
	public abstract List<Topic> findTopicsByName(String searchField);	
	
	public abstract List<Topic> findTopicsByBeschreibung(String searchField);
	
	public abstract Topic findTopicById(int topicId);
	public abstract Topic findTopicByName(String name);
	public abstract void deleteTopic(int topicId);
	public abstract void saveTopic(String name, String beschreibung);
	
	
	
	
	
	
}
