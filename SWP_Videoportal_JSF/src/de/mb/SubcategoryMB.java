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
import de.awk.videoverwaltung.model.Subcategory;

@ManagedBean(name="subcategoryMB")
@RequestScoped
public class SubcategoryMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3988036423442408897L;

	private Subcategory subcategory;
	
	@EJB
	ISubcategoryFacade subcategoryFacade;
	
	@NotNull
	@Digits(fraction = 0, integer = 6)
	private int subcategoryId;
	
	@NotNull
	@Digits(fraction = 0, integer = 6)
	private int topicId;
	
	@NotNull
	@Size(min=1, max=50, message = "Max. 50 Zeichen!")	
	@Pattern(regexp = "[a-zA-Z0-9_�������.,!? ]+", message = "Ung�ltige Zeichen! Nur folgende Zeichen: a-z A-Z 0-9 und Leerzeichen erlaubt.")
	private String name;

	@NotNull
	@Size(min=1, max=250, message = "Max. 250 Zeichen!")
	@Pattern(regexp = "[a-zA-Z0-9_�������.,!? ]+", message = "Ung�ltige Zeichen! Nur folgende Zeichen: a-z A-Z 0-9 und Leerzeichen erlaubt.")
	private String description;
	
	private List<Subcategory> subcategoryList = null;
	private HtmlDataTable dataTableTopic;
	private String searchField;
	private String searchOption;
	
	public List<Subcategory> initialiseSubcategoryList(){
		this.subcategoryList = null;
		
		if (this.searchField == null) {
			subcategoryList = subcategoryFacade.getAllSubcategories();
		} else if (this.searchField.equals("")) {
			subcategoryList = subcategoryFacade.getAllSubcategories();
		} else {
			if (searchOption == null) {
				searchOption = "ThemenbereichsID";
			}
			
			if (searchOption.equals("")) {
				searchOption = "ThemenbereichsID";
			}
			
			switch (searchOption) {
			case "ThemenbereichsID":
				subcategoryList = subcategoryFacade.findSubcategoriesByTopicId(Integer.parseInt(this.searchField));
				break;
			case "Name":
				subcategoryList = subcategoryFacade.findSubcategoriesByName(this.searchField);
				break;
			case "Description":
				subcategoryList = subcategoryFacade.findSubcategoriesByDescription(this.searchField);
				break;
			}
		}
		return subcategoryList;
	}
	
	public String editSubcategory(String name) {
		Subcategory aSubcategory = this.subcategoryFacade.findSubcategoryByName(name);
		
		this.subcategoryId = aSubcategory.getSubcategoryId();
		this.topicId = aSubcategory.getTopicId();
		this.name = aSubcategory.getName();
		this.description = aSubcategory.getDescription();
		
		return "changeExistingSubcategory";
	}
	
	public String createSubcategory() {
		this.subcategoryId = this.getSubcategoryId();
		this.topicId = 0;
		this.name = "";
		this.description = "";
		
		return "createNewSubcategory";
	}
	
	public void deleteSubcategory(Subcategory aSubcategory) {
		this.subcategoryFacade.deleteSubcategory(aSubcategory.getSubcategoryId());
	}
	
	public String saveSubcategory() {
		
		if (this.topicId == 0) {
			sendInfoMessageToUser("Es wurde keine ThemenbereichsID zugewiesen");
			return "";
		}
		
		if (this.name.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Name vergeben");
			return "";
		}
		
		if (this.description.isEmpty()) {
			sendInfoMessageToUser("Es wurde keine Beschreibung vergeben");
			return "";
		}
		
		Subcategory aSubcategory = this.subcategoryFacade.findSubcategoryByName(this.name);
		if (aSubcategory == null) {
			this.subcategoryFacade.saveSubcategory(this.topicId, this.name, this.description);
			
			initialiseSubcategoryList();
			
			return "backToSubcategoryMenue";
		} else {
			sendInfoMessageToUser("Unterkategorie mit dem Namen '" + this.name + " existiert bereits.");
			return "";
		}
	}
			
	public String updateSubcategory() {
		if (this.topicId == 0) {
			sendInfoMessageToUser("Es wurde keine ThemenbereichsID mit angebeben");
			return "";
		}
		
		if (this.name.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Name vergeben");
			return "";
		}
		
		if (this.description.isEmpty()) {
			sendInfoMessageToUser("Es wurde keine Beschreibung vergeben");
			return "";
		}
		
		subcategoryFacade.updateSubcategory(this.subcategoryId, this.topicId,  this.name, this.description);
		
		initialiseSubcategoryList();
		
		return "backToSubcategoryMenue";
		
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
	public Subcategory getSubcategory() {
		return subcategory;
	}	
	public void setSubcategory(Subcategory aSubcategory) {
		subcategory = aSubcategory;
	}	
	public ISubcategoryFacade getSubcategoryFacade() {
		return subcategoryFacade;
	}
	public void setSubcategoryFacade(ISubcategoryFacade subcategoryFacade) {
		this.subcategoryFacade = subcategoryFacade;
	}	
	public int getSubcategoryId() {
		return subcategoryId;
	}
	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
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
	public List<Subcategory> getSubcategoryList() {
		return subcategoryList;
	}
	public void setSubcategoryList(List<Subcategory> subcategoryList) {
		this.subcategoryList = subcategoryList;
	}
	
	
}