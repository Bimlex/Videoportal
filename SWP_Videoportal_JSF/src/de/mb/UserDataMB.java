package de.mb;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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

import de.awk.userManagement.facade.IUserFacade;
import de.awk.userManagement.model.User;

@ManagedBean(name = "userDataMB")
@SessionScoped
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

	@Size(min=1, max=255)
	private String username;
	@Size(min=1, max=255)
	private String password;
	@Size(min=1, max=255)
	private String prename;
	@Size(min=1, max=255)
	private String surname;
	@Size(min=1, max=255)
	private String rolename;

	private List<User> userList = null;
	private HtmlDataTable dataTableUser;
	private String searchField;
	private String searchOption;
	private String searchText;

	private List<String> roleSelection = new ArrayList<String>();


	@PostConstruct
	public void init() {
		// Fuellt Dropdown-Auswahl-Menu (Benutzer, Administrator)
		roleSelection = this.userFacade.getRoleSelection();

	}

	public List<User> initialiseUserList() {
		
		if (this.searchField == null) {
			userList = userFacade.getAllUser();
		} else if (this.searchField.equals("")) {
			userList = userFacade.getAllUser();
		} else {
			if(searchOption == null) {
				searchOption = "Username";
			}
			
			if(searchOption.equals("")) {
				searchOption = "Username";
			}

			switch (searchOption) {
			case "Username":
				userList = userFacade.findUsersByUsername(this.searchField);
				break;
			case "Vorname":
				userList = userFacade.findUsersByPrename(this.searchField);
				break;
			case "Nachname":
				userList = userFacade.findUsersBySurname(this.searchField);
				break;
			}

		}
		
		for (User aUser : userList) {
			aUser.setPassword("*********");
		}
		
		return userList;
	}
		

	public HtmlDataTable getDataTableUser() {
		return dataTableUser;
	}

	public void setDataTableUser(HtmlDataTable dataTableUser) {
		this.dataTableUser = dataTableUser;
	}

	public void getUserInfo() throws IOException {
		int index = dataTableUser.getRowIndex(); 
		User user = (User) dataTableUser.getRowData(); 

	}

	public String editUser(String aUsername) {
		// Wechsel zur User aendern Sicht
		
		User aUser = this.userFacade.findUserByName(aUsername);
		
		if(aUser != null) {
			this.username = aUser.getUsername();
			this.password = "";
			this.prename = aUser.getPrename();
			this.surname = aUser.getSurname();
			this.rolename = aUser.getRolename();

			return "changeExistingUser";
		}
		return "";

	}

	public String createUser() {
		// Wechsel zur User anlegen Sicht
		this.username = "";
		this.password = "";
		this.prename = "";
		this.surname = "";
		this.rolename = "";

		return "createNewUser";
	}

	public void deleteUser(User aUser) {
		if(aUser != null) {
			this.userFacade.deleteUser(aUser.getUsername());
		}
	}

	public String saveUser() {
		// Neuen User speichern

		if (this.username.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Username vergeben");
			return "";
		}

		if (this.password.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Passwort vergeben");
			return "";
		}

		if (this.rolename.isEmpty()) {
			sendInfoMessageToUser("Es wurde keine Rolle vergeben");
			return "";
		}

		String aPassword = "";
		if (!this.password.isEmpty()) {
			aPassword = get_SHA_512_SecurePassword(this.password, "");
		}

		User aUser = this.userFacade.findUserByName(this.username);
		if (aUser == null) {
			this.userFacade.saveUser(this.username, aPassword, this.prename, this.surname, this.rolename);

			initialiseUserList();			

			return "backToUserMenu";
		} else {
			sendInfoMessageToUser("User " + this.username + " existiert bereits.");
			return "";
		}

	}

	public String updateUser() {
		// Bestehenden User anpassen

		if (this.username.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Username vergeben");
			return "";
		}

		if (this.rolename.isEmpty()) {
			sendInfoMessageToUser("Es wurde keine Rolle vergeben");
			return "";
		}

		String aPassword = "";
		if (!this.password.isEmpty()) {
			aPassword = get_SHA_512_SecurePassword(this.password, "");
		}

		userFacade.updateUser(this.username, aPassword, this.prename, this.surname, this.rolename);

		initialiseUserList();

		return "backToUserMenu";

	}
	
	public String get_SHA_512_SecurePassword(String passwordToHash, String salt) {
//		String generatedPassword = null;
//		try {
//			MessageDigest md = MessageDigest.getInstance("SHA-512");
//			md.update(salt.getBytes(StandardCharsets.UTF_8));
//			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < bytes.length; i++) {
//				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//			}
//			generatedPassword = sb.toString();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		return generatedPassword;
		
		return this.userFacade.get_SHA_512_SecurePassword(passwordToHash, salt);
	}

	private void sendInfoMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
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

	public User getUser() {
		return user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

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

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<String> getRoleSelection() {
		return roleSelection;
	}

	public void setRoleSelection(List<String> roleSelection) {
		this.roleSelection = roleSelection;
	}

}
