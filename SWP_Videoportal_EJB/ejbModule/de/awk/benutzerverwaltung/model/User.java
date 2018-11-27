package de.awk.benutzerverwaltung.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.el.ArrayELResolver;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="swp_user")
@NamedQueries({
	@NamedQuery(name="User.findUserByUsername", query="SELECT u FROM User u WHERE u.username = :username"),
	@NamedQuery(name="User.findUsersByUsername", query="SELECT u FROM User u WHERE u.username LIKE :username"),
	@NamedQuery(name="User.findUsersByPrename", query="SELECT u FROM User u WHERE u.vorname LIKE :vorname"),
	@NamedQuery(name="User.findUsersBySurname", query="SELECT u FROM User u WHERE u.nachname LIKE :nachname")
})
public class User {

	public static final String FIND_BY_USERNAME = "User.findUserByUsername";
	public static final String FIND_LIST_BY_USERNAME = "User.findUsersByUsername";
	public static final String FIND_LIST_BY_PRENAME = "User.findUsersByPrename";
	public static final String FIND_LIST_BY_SURNAME = "User.findUsersBySurname";
	
	

	@Id
	private String username;
	private String password;
	private String vorname;
	private String nachname;
	private int timesClicked;
	private int minutesLoggedIn;
	private String rolename;


	public User(String aUsername, String aPassword, String aVorname, 
			String aNachname, String aRolename){
		this.username = aUsername;
		this.password = aPassword;
		this.vorname = aVorname;
		this.nachname = aNachname;
		this.rolename = aRolename;
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

	

