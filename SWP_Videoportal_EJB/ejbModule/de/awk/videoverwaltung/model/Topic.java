package de.awk.videoverwaltung.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import de.awk.ressourcenverwaltung.model.MitarbeiterIn;


@Entity
@Access(AccessType.FIELD)
@Table(name="swp_topic")
public class Topic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3604027455613521554L;
	
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

	
	//Getter & Setter-Methoden
	public Integer getTopicId() {
		return topicId;
	}
	
//	public void setTopic(Integer topicId) {
//		this.topicId = topicId;
//	//Wird nicht implementiert, weil die ID generiert wird 
//	}
	
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
	
	@Override
	public int hashCode() {
		return this.getTopicId();
	}
		
		
}
