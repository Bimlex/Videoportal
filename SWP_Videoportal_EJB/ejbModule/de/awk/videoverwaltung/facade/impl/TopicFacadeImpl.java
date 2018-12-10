package de.awk.videoverwaltung.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.awk.videoverwaltung.dao.TopicDAO;
import de.awk.videoverwaltung.facade.ITopicFacade;
import de.awk.videoverwaltung.model.Topic;

@Stateless
public class TopicFacadeImpl implements ITopicFacade{
	
	@EJB
	private TopicDAO topicDAO;
	
	public List<Topic> getAllTopics() {
		return topicDAO.findAll();
	}
	
	public List<Topic> findTopicsByName(String name) {
		return topicDAO.findTopicsByName(name);
	}
	
	public List<Topic> findTopicsByDescription(String description) {
		return topicDAO.findTopicsByDescription(description);
	}	
	
	
	
	
	public Topic findTopicById(int topicId) {
		return topicDAO.findTopicByTopicId(topicId);
	}	
	
	public Topic findTopicByName(String name) {
		return topicDAO.findTopicByName(name);
	}
	
	
	
	
	public void saveTopic(String name, String description) {
		Topic aTopic = new Topic(name, description);
		topicDAO.save(aTopic);
	}
	
	public void deleteTopic(int topicId) {
		Topic aTopic = this.findTopicById(topicId);
		try {
			topicDAO.delete(aTopic);
		} catch (Exception e) {}
	}
	
	public void updateTopic(int topicId, String name, String description) {
		Topic aTopic = this.findTopicById(topicId);
		aTopic.setName(name);
		aTopic.setDescription(description);
		topicDAO.save(aTopic);
	}
	
	
}