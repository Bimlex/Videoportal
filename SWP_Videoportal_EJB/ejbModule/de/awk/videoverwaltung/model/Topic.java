package de.awk.videoverwaltung.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="swp_topic")
@NamedQueries({
	@NamedQuery(name="Topic.findTopicsById", query="SELECT t FROM Topic t WHERE t.topicId LIKE :topicId"),
	@NamedQuery(name="Topic.findTopicsByName", query="SELECT t FROM Topic t WHERE t.name LIKE :name"),
	@NamedQuery(name="Topic.findTopicsByBeschreibung", query="SELECT t FROM Topic t WHERE t.beschreibung LIKE :beschreibung"),
	@NamedQuery(name="Topic.findTopicById", query="SELECT t FROM Topic t WHERE t.topicId = :topicId"),
	@NamedQuery(name="Topic.findTopicByName", query="SELECT t FROM Topic t WHERE t.name = :name")
})
public class Topic {

	public static final String FIND_LIST_BY_ID = "Topic.findTopicsById";
	public static final String FIND_LIST_BY_NAME = "Topic.findTopicsByName";
	public static final String FIND_TOPIC_BY_ID = "Topic.findTopicById";
	public static final String FIND_TOPIC_BY_NAME = "Topic.findTopicByName";
	public static final String FIND_LIST_BY_BESCHREIBUNG = "Topic.findTopicsByBeschreibung";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TOPIC_ID")
	@SequenceGenerator(name="SEQ_TOPIC_ID", sequenceName="SEQ_TOPIC_ID", allocationSize = 1)
	private Integer topicId;
	
	private String name;
	private String beschreibung;
	
	public Topic () {}

	public Topic(String name, String beschreibung) {
		this.name = name;
		this.beschreibung = beschreibung;
	}
	
	//Getter & Setter
	public Integer getTopicId() {
		return topicId;
	}		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
		
}
