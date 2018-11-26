package de.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.awk.videoverwaltung.facade.ITopicFacade;
import de.awk.videoverwaltung.model.Topic;


@ManagedBean(name="topicMB")
@RequestScoped
public class TopicMB {
	
	@EJB
	ITopicFacade topicFacade;
	
	private Topic topic;
	
	public Topic getTopic() {
		return topic;
	}
	
	public void setTopic(Topic aTopic) {
		topic = aTopic;
	}
	
	@NotNull
	@Digits(fraction = 0, integer = 6)
	private int topicId;
	
	@NotNull
	@Size(min=1, max=50)
	@Pattern(regexp = "[A-Za-z ]*", message = "Name bitte mit max. 50 Buchstaben und Leerzeichen!")
	private String name;

	@NotNull
	@Size(min=1, max=250)
	@Pattern(regexp = "[A-Za-z ]*", message = "Beschreibung bitte mit max. 250 Buchstaben und Leerzeichen!")
	private String beschreibung;

	public List<Topic> getAlleKategorien(){
		System.out.println(topicFacade.getAlleKategorien().size());
		return topicFacade.getAlleKategorien();
	}
	
	public Topic getTopicById(Integer id) {
		return topicFacade.getTopicById(id);
	}
	
	public String neuenTopicAnlegen() {
		topicFacade.saveTopic(this.name, this.beschreibung);
		return "topicMenue";
	}
	
	public void topicAendern() {
		topicFacade.updateTopic(this.topicId, this.name, this.beschreibung);
	}
	
	public void topicLoeschen() {
		topicFacade.deleteTopic(topicId);
	}
	
	
	//Getter & Setter-Methoden
	public int getTopicId() {
		return topicId;
	}
	
	public void setTopicId(int topicId) {
		this.topicId = topicId;
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
