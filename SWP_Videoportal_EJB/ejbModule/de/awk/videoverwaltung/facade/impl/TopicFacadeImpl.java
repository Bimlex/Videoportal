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
	
	public List<Topic> getAlleKategorien() {
		return topicDAO.findAll();
	}
		
	
	
	public void saveTopic(String name, String beschreibung) {
		Topic aTopic = new Topic(name, beschreibung);
		topicDAO.save(aTopic);
	}
	
	public void updateTopic(int topicId, String name, String beschreibung) {
		Topic aTopic = this.findTopicById(topicId);
		aTopic.setName(name);
		aTopic.setBeschreibung(beschreibung);
		topicDAO.save(aTopic);
	}

	public void deleteTopic(int topicId) {
		Topic aTopic = this.findTopicById(topicId);
		try {
			topicDAO.delete(aTopic);
		} catch (Exception e) {
			
		}
	}

	
	
	
	@Override
	public List<Topic> findTopicsById(int topicId) {
		return topicDAO.findTopicsById(topicId);
	}

	@Override
	public List<Topic> findTopicsByName(String name) {
		return topicDAO.findTopicsByName(name);
	}

	@Override
	public Topic findTopicById(int topicId) {
		return topicDAO.findTopicByTopicId(topicId);
	}

	@Override
	public Topic findTopicByName(String name) {
		return topicDAO.findTopicByName(name);
	}


	@Override
	public List<Topic> findTopicsByBeschreibung(String beschreibung) {
		return topicDAO.findTopicsByBeschreibung(beschreibung);
	}






	
	
}