package de.mb;

import java.io.IOException;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.awk.benutzerverwaltung.facade.IUserFacade;
import de.awk.benutzerverwaltung.model.User;


@ManagedBean(name="userDataMB")
@ViewScoped
public class UserDataMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7502137075266135502L;

	private User user;
	
	LocalDateTime startTime = LocalDateTime.now();
	int clicks = 0;
	

	@EJB
	private IUserFacade userFacade;
	
	@NotNull
	@Size(min=1, max=250)
	private String username;
	
	@NotNull
	@Size(min=1, max=250)
	private String password;
	
	@NotNull
	@Size(min=1, max=250)
	private String vorname;
	
	@NotNull
	@Size(min=1, max=250)
	private String nachname;			
	
	@NotNull
	@Size(min=1, max=250)
	private String rolename;
	
	private List<User> userList = null;	
	private HtmlDataTable dataTableUser;
	
	
	
	public HtmlDataTable getDataTableUser() {
		return dataTableUser;
	}

	public void setDataTableUser(HtmlDataTable dataTableUser) {
		this.dataTableUser = dataTableUser;
	}

	public void getUserInfo() throws IOException{
	    int index = dataTableUser.getRowIndex(); // Actually not interesting info.
	    User user = (User) dataTableUser.getRowData(); // This is what you want.
	    
	    System.out.println(user.getUsername());	  
	    System.out.println(user.getVorname());
	}
	
	
	
	public String editUser(User user){
		System.out.println(user.getNachname());
		System.out.println(user.isCanEdit());
		user.setCanEdit(true);
		System.out.println(user.isCanEdit());
		return null;
	}	
	
//	public String saveUsers() {
//		for
//	}
	
	public List<User> getAlleUser(){
		userList = userFacade.getAllUser();
		return userList;
	}
	
	

	public User getUser() {
		return user;
	}

//	public List<User> getUserList() {
//		return userList;
//	}

	public void setUser(User user) {
		this.user = user;
	}

	public IUserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(IUserFacade userFacade) {
		this.userFacade = userFacade;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getVorname() {
		return vorname;
	}


	public void setVorname(String vorname) {
		this.vorname = vorname;
	}


	public String getNachname() {
		return nachname;
	}


	public void setNachname(String nachname) {
		this.nachname = nachname;
	}


	public String getRolename() {
		return rolename;
	}


	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
	
}
