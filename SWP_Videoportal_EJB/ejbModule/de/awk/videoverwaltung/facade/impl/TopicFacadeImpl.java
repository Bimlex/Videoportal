package de.awk.videoverwaltung.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.awk.ressourcenverwaltung.model.Maschine;
import de.awk.ressourcenverwaltung.model.Ressource;
import de.awk.videoverwaltung.dao.TopicDAO;
import de.awk.videoverwaltung.facade.ITopicFacade;
import de.awk.videoverwaltung.model.Topic;

@Stateless
public class TopicFacadeImpl implements ITopicFacade{

	@EJB
	private TopicDAO topicDAO;
	
	public List<Topic> getAlleKategorien() {
		System.out.println(topicDAO.findAll().size());
		return topicDAO.findAll();
	}
		
	public Topic getTopicById(int topicId) {
		return topicDAO.find(topicId);
	}
	
	public void saveTopic(String name, String beschreibung) {
		Topic aTopic = new Topic(name, beschreibung);
		topicDAO.save(aTopic);
	}
	
	public void updateTopic(int topicId, String name, String beschreibung) {
		Topic aTopic = this.getTopicById(topicId);
		aTopic.setName(name);
		aTopic.setBeschreibung(beschreibung);
	}

	public void deleteTopic(int topicId) {
		Topic aTopic = this.getTopicById(topicId);
		try {
			topicDAO.delete(aTopic);
		} catch (Exception e) {
			
		}
	}
	
	
}
