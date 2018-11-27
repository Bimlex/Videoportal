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

import de.awk.benutzerverwaltung.facade.IUserFacade;
import de.awk.benutzerverwaltung.model.User;

@ManagedBean(name = "userDataMB")
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

	private String username;
	private String password;
	private String vorname;
	private String nachname;
	private String rolename;

	private List<User> userList = null;
	private HtmlDataTable dataTableUser;
	private String searchField;
	private String searchOption;

	private List<String> roleSelection = new ArrayList<String>();

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	// public void searchUser() {
	// this.userList = this.userFacade.getUserByName(this.searchField);
	// }

	@PostConstruct
	public void init() {
		// Fuellt Dropdown-Auswahl-Menue (Benutzer, Administrator)
		roleSelection = this.userFacade.getRoleSelection();

		// Standardwert fuer Suchoption setzen
		this.searchOption = "Username";

		// initialisiert die Usertabelle
		initialiseUserList();

	}

	public void initialiseUserList() {
		this.userList = null;

		if (this.searchField == null) {
			userList = userFacade.getAllUser();
		} else if (this.searchField.equals("")) {
			userList = userFacade.getAllUser();
		} else {
			switch (searchOption) {
			case "Username":
				userList = userFacade.getUsersByUsername(this.searchField);
				break;
			case "Vorname":
				userList = userFacade.getUsersByPrename(this.searchField);
				break;
			case "Nachname":
				userList = userFacade.getUsersBySurname(this.searchField);
				break;
			}

		}

		for (User aUser : userList) {
			aUser.setPassword("*********");
		}
	}

	public HtmlDataTable getDataTableUser() {
		return dataTableUser;
	}

	public void setDataTableUser(HtmlDataTable dataTableUser) {
		this.dataTableUser = dataTableUser;
	}

	public void getUserInfo() throws IOException {
		int index = dataTableUser.getRowIndex(); // Actually not interesting info.
		User user = (User) dataTableUser.getRowData(); // This is what you want.

		System.out.println(user.getUsername());
		System.out.println(user.getVorname());
	}

	public String editUser(User aUser) {
		// Wechsel zur User aendern Sicht
		this.username = aUser.getUsername();
		this.password = "";
		this.vorname = aUser.getVorname();
		this.nachname = aUser.getNachname();
		this.rolename = aUser.getRolename();

		return "bestehendenUserAendern";
	}

	public String createUser() {
		// Wechsel zur User anlegen Sicht
		this.username = "";
		this.password = "";
		this.vorname = "";
		this.nachname = "";
		this.rolename = "";

		return "neuenUserAnlegen";
	}

	public void deleteUser(User aUser) {
		this.userFacade.deleteUser(aUser.getUsername());
	}

	// public List<User> getAlleUser() {
	// // Eventuell umschreiben umschieben in UserFacadeImpl
	// userList = userFacade.getAllUser();
	// for (User aUser : userList) {
	// aUser.setPassword("*********");
	// }
	// return userList;
	// }

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
			this.userFacade.saveUser(this.username, aPassword, this.vorname, this.nachname, this.rolename);

			initialiseUserList();

			return "zurueckZumUserMenue";
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

		userFacade.updateUser(this.username, aPassword, this.vorname, this.nachname, this.rolename);

		initialiseUserList();

		return "zurueckZumUserMenue";

	}

	private void sendInfoMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
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

	public List<String> getRoleSelection() {
		return roleSelection;
	}

	public void setRoleSelection(List<String> roleSelection) {
		this.roleSelection = roleSelection;
	}

	public static String get_SHA_512_SecurePassword(String passwordToHash, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

}
