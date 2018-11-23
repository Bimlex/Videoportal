package de.awk.benutzerverwaltung.model;

import javax.el.ArrayELResolver;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="swp_user")
@NamedQuery(name="User.findUserByUsername", query="SELECT u from User u where u.username = :username")
public class User {

	public static final String FIND_BY_USERNAME = "User.findUserByUsername";
	
	

	@Id
	private String username;
	private String password;
	private String vorname;
	private String nachname;
	private int timesClicked;
	private int minutesLoggedIn;
	private String rolename;
	private boolean canEdit;


	public User(String aUsername, String aPassword, String aVorname, 
			String aNachname, String aRolename){
		this.username = aUsername;
		this.password = aPassword;
		this.vorname = aVorname;
		this.nachname = aNachname;
		this.rolename = aRolename;
		canEdit = false;
				
	}
	
	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}



	public User() {
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String name) {
		this.username = name;
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

	public int getTimesClicked() {
		return timesClicked;
	}

	public void setTimesClicked(int timesClicked) {
		this.timesClicked = timesClicked;
	}

	public int getMinutesLoggedIn() {
		return minutesLoggedIn;
	}

	public void setMinutesLoggedIn(int minutesLoggedIn) {
		this.minutesLoggedIn = minutesLoggedIn;
	}
	
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User){
			User user = (User) obj;
			return user.getUsername().equals(getUsername());
		}
		
		return false;
	}
}

	

