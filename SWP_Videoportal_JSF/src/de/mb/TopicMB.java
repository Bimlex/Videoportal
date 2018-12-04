package de.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.awk.benutzerverwaltung.model.User;
import de.awk.videoverwaltung.facade.ITopicFacade;
import de.awk.videoverwaltung.model.Topic;

@ManagedBean(name="topicMB")
@RequestScoped
public class TopicMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1074261552193431698L;

	private Topic topic;
	
	@EJB
	ITopicFacade topicFacade;
	
	@NotNull 
	@Digits(fraction = 0, integer = 6) 
	private int topicId;
	
	@NotNull
	@Size(min=1, max=50, message = "Max. 50 Zeichen!")	
	@Pattern(regexp = "[a-zA-Z0-9_�������.,!? ]+", message = "Ung�ltiges Zeichen! Nur folgende Zeichen: a-z A-Z 0-9 und Leerzeichen erlaubt.")
	private String name;

	@NotNull
	@Size(min=1, max=250, message = "Max. 250 Zeichen!")
	//Umlaute m�ssen noch in regexp und Klammern und eventuell noch andere Sonderzeichen
	@Pattern(regexp = "[a-zA-Z0-9_�������.,!? ]+", message = "Ung�ltiges Zeichen! Nur folgende Zeichen: a-z A-Z 0-9 und Leerzeichen erlaubt.")
	private String beschreibung;
	
	private List<Topic> topicList = null;
	private HtmlDataTable dataTableTopic;
	private String searchField;
	private String searchOption;
		
	public List<Topic> initialiseTopicList() {
//		this.topicList = null;
		
		if (this.searchField == null) {
			topicList = topicFacade.getAlleKategorien();
		} else if (this.searchField.equals("")) {
			topicList = topicFacade.getAlleKategorien();
		} else {
			if(searchOption == null) {
				searchOption = "Name";
			}
			
			if(searchOption.equals("")) {
				searchOption = "Name";
			}
			
			switch (searchOption) {
			case "Name":
				topicList = topicFacade.findTopicsByName(this.searchField);
				break;
			case "Beschreibung":
				topicList = topicFacade.findTopicsByBeschreibung(this.searchField);
				break;
			}
		}
		
		return topicList;		
	}	
	
	public String editTopic(String name) {
		Topic aTopic = this.topicFacade.findTopicByName(name);
	
		this.topicId = aTopic.getTopicId();
		this.name = aTopic.getName();
		this.beschreibung = aTopic.getBeschreibung();
		
		return "bestehendeKategorieAendern";	
	}
		
	public String createTopic() {
		this.topicId = this.getTopicId();
		this.name = "";
		this.beschreibung = "";
		
		return "neueKategorieAnlegen";
	}	
	
	public void deleteTopic(Topic aTopic) {
		this.topicFacade.deleteTopic(aTopic.getTopicId());
	}
	
	public String saveTopic() {
		
		if (this.name.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Name vergeben");
			return "";
		}
		
		if (this.beschreibung.isEmpty()) {
			sendInfoMessageToUser("Es wurde keine Beschreibung vergeben");
			return "";
		}
		
		Topic aTopic = this.topicFacade.findTopicByName(this.name);
		if (aTopic == null) {
			this.topicFacade.saveTopic(this.name, this.beschreibung);
			
			initialiseTopicList();
			
			return "zurueckZumTopicMenue";
		} else {
			sendInfoMessageToUser("Themenbereich mit dem Namen  '" + this.name + "  existiert bereits.");
			return "";
		}
		
	}
	
	public String updateTopic() {
		
		if (this.name.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Name vergeben");
			return "";
		}
		
		if (this.beschreibung.isEmpty()) {
			sendInfoMessageToUser("Es wurde keine Beschreibung vergeben");
			return "";
		}
		
		topicFacade.updateTopic(this.topicId, this.name, this.beschreibung);
	
		initialiseTopicList();
		
		return "zurueckZumTopicMenue";
	}
	
	private void sendInfoMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}


	//Getter ---- Setter	
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic aTopic) {
		topic = aTopic;
	}	
	public ITopicFacade getTopicFacade() {
		return topicFacade;
	}
	public void setTopicFacade(ITopicFacade topicFacade) {
		this.topicFacade = topicFacade;
	}	
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
	public List<Topic> getTopicList() {
		return topicList;
	}
	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}
	public HtmlDataTable getDataTableTopic() {
		return dataTableTopic;
	}	
	public void setDataTableTopic(HtmlDataTable dataTableTopic) {
		this.dataTableTopic = dataTableTopic;
	}
	public String getSearchField() {
		return searchField;
	}	
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchOption() {
		return searchOption;
	}
	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}
		
	
}