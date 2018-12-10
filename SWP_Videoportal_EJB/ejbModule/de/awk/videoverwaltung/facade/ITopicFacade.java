 package de.awk.videoverwaltung.facade;

import java.util.List;

import javax.ejb.Local;
import de.awk.videoverwaltung.model.Topic;

@Local
public interface ITopicFacade {
	
	public abstract List<Topic> getAllTopics();
	public abstract List<Topic> findTopicsByName(String searchField);
	public abstract List<Topic> findTopicsByDescription(String searchField);
	
	public abstract Topic findTopicById(int topicId);
	public abstract Topic findTopicByName(String name);
	
	public abstract void saveTopic(String name, String description);
	public abstract void deleteTopic(int topicId);
	public abstract void updateTopic(int topicId, String name, String description);
	
	
}
