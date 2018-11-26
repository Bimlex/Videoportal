package de.awk.videoverwaltung.dao;

import javax.ejb.Stateless;

import de.awk.videoverwaltung.dao.GenericDAO;
import de.awk.videoverwaltung.model.Topic;

@Stateless
public class TopicDAO extends GenericDAO<Topic>{

	public TopicDAO () {
		super(Topic.class);
	}
	
	public void delete(Topic aTopic) {
		super.delete(aTopic.getTopicId(), Topic.class);
	}
	
	
}
