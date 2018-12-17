package de.mb;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import de.awk.userManagement.facade.IUserFacade;
import de.awk.userManagement.model.User;

@SessionScoped
@ManagedBean(name="userMB")
public class UserMB {

	private User user;

	@EJB
	private IUserFacade userFacade;
	
	private int topicId = 0;
	private String searchField = "";
	
	public User getUser(){
		if(user == null){
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			String username = context.getUserPrincipal().getName();
			
			user = userFacade.findUserByName(username);
		}
		return user;
	}
	
	public boolean isUserAdmin(){
		return getRequest().isUserInRole("Administrator");
	}
	
	public boolean isUserUser(){
		return getRequest().isUserInRole("Benutzer");
	}
	
	public String logOut(){
		System.out.println("logout");
		getRequest().getSession().invalidate();
		return "logout";
	}
	
	public void reset(){
		getRequest().getSession().invalidate();
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	
	
}
