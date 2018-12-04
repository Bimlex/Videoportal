package de.awk.videoverwaltung.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import de.awk.videoverwaltung.model.Topic;

@Stateless
public class TopicDAO extends GenericDAO<Topic>{

	public TopicDAO () {
		super(Topic.class);
	}
	
	public void delete(Topic aTopic) {
		super.delete(aTopic.getTopicId(), Topic.class);
	}

	public List<Topic> getTopicsById(int id) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id",id);
		return (List<Topic>) this.findAllResult(Topic.FIND_LIST_BY_ID, parameters);
	}


	public Topic findTopicByTopicId(int topicId) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("topicId", topicId);
		return super.findOneResult(Topic.FIND_TOPIC_BY_ID, parameters);
	}

	public Topic findTopicByName(String name) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", name);
		return super.findOneResult(Topic.FIND_TOPIC_BY_NAME, parameters);
	}
	
	public List<Topic> findTopicsById(int topicId) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id" , topicId + "%");
		return(List<Topic>) this.findAllResult(Topic.FIND_LIST_BY_ID, parameters);
	}

	public List<Topic> findTopicsByName(String name) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name" , name + "%");
		return(List<Topic>) this.findAllResult(Topic.FIND_LIST_BY_NAME, parameters);		
	}

	public List<Topic> findTopicsByDescription(String description) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("description" , "%" + description + "%");
		return(List<Topic>) this.findAllResult(Topic.FIND_LIST_BY_DESCRIPTION, parameters);
	}
	
		
}
