package de.mb;

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

import de.awk.videoverwaltung.facade.ISubcategoryFacade;
import de.awk.videoverwaltung.facade.ITopicFacade;
import de.awk.videoverwaltung.model.Subcategory;
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
	
	@EJB
	ISubcategoryFacade subcategoryFacade;
	
	@NotNull 
	@Digits(fraction = 0, integer = 6) 
	private int topicId;
	
	@NotNull
	@Size(min=1, max=50, message = "Max. 50 Zeichen!")	
	@Pattern(regexp = "[a-zA-Z0-9_äÄöÖüÜß.,!? ]+", message = "Ungültiges Zeichen! Nur folgende Zeichen: a-z A-Z 0-9 und Leerzeichen erlaubt.")
	private String name;

	@NotNull
	@Size(min=1, max=250, message = "Max. 250 Zeichen!")
	@Pattern(regexp = "[a-zA-Z0-9_äÄöÖüÜß.,!? ]+", message = "Ungültiges Zeichen! Nur folgende Zeichen: a-z A-Z 0-9 und Leerzeichen erlaubt.")
	private String description;
	
	private List<Topic> topicList = null;
	private HtmlDataTable dataTableTopic;
	private String searchField;
	private String searchOption;
	
	
	public String selectTopicId() {
		return "showSubcategories";
	}
	
	public List<Topic> initialiseTopicList() {
		
		if (this.searchField == null || this.searchField.equals("")) {
			topicList = topicFacade.getAllTopics();
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
			case "Description":
				topicList = topicFacade.findTopicsByDescription(this.searchField);
				break;
			}
		}
		
		return topicList;		
	}			
	
	public String editTopic(String name) {
		Topic aTopic = this.topicFacade.findTopicByName(name);
	
		this.topicId = aTopic.getTopicId();
		this.name = aTopic.getName();
		this.description = aTopic.getDescription();
		
		return "changeExistingTopic";	
	}
		
	public String createTopic() {
		this.topicId = this.getTopicId();
		this.name = "";
		this.description = "";
		
		return "createNewTopic";
	}	
	
	public void deleteTopic(Topic aTopic) {
		
		/*Variante 1 (funktioniert)*/
		List<Subcategory> list = subcategoryFacade.findSubcategoriesByTopicId(aTopic.getTopicId());

		if (list.isEmpty() == true) {
			this.topicFacade.deleteTopic(aTopic.getTopicId());
		} else {
			sendInfoMessageToUser("Themenbereich '" + aTopic.getName() + "' hat Unterkategorien!" + "\n" + 
								  "Zuvor alle zugehörigen Unterkategorien löschen!");
		}		
		
		/*Variante 2 (funktioniert nicht) Fehler = Delete-Query kann nicht ausgeführt werden*/		 
//		List<Subcategory> list = subcategoryFacade.findSubcategoriesByTopicId(aTopic.getTopicId());
//		
//		if (list.isEmpty() == true) {
//			this.topicFacade.deleteTopic(aTopic.getTopicId());
//		} else {
//			this.topicFacade.deleteTopic(aTopic.getTopicId());
//			subcategoryFacade.deleteAllSubcategoriesByTopicId(aTopic.getTopicId());
//		}

	}
	
	public String saveTopic() {
		
		if (this.name.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Name vergeben");
			return "";
		}
		
		if (this.description.isEmpty()) {
			sendInfoMessageToUser("Es wurde keine Beschreibung vergeben");
			return "";
		}
		
		Topic aTopic = this.topicFacade.findTopicByName(this.name);
		if (aTopic == null) {
			this.topicFacade.saveTopic(this.name, this.description);
			
			initialiseTopicList();
			
			return "backToTopicMenu";
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
		
		if (this.description.isEmpty()) {
			sendInfoMessageToUser("Es wurde keine Beschreibung vergeben");
			return "";
		}
		
		topicFacade.updateTopic(this.topicId, this.name, this.description);
	
		initialiseTopicList();
		
		return "backToTopicMenu";
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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