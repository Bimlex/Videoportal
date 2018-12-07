package de.awk.userManagement.model;

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
	@NamedQuery(name="User.findUsersByPrename", query="SELECT u FROM User u WHERE u.prename LIKE :prename"),
	@NamedQuery(name="User.findUsersBySurname", query="SELECT u FROM User u WHERE u.surname LIKE :surname")
})
public class User {

	public static final String FIND_BY_USERNAME = "User.findUserByUsername";
	public static final String FIND_LIST_BY_USERNAME = "User.findUsersByUsername";
	public static final String FIND_LIST_BY_PRENAME = "User.findUsersByPrename";
	public static final String FIND_LIST_BY_SURNAME = "User.findUsersBySurname";
	
	

	@Id
	private String username;
	private String password;
	private String prename;
	private String surname;
	private int timesClicked;
	private int minutesLoggedIn;
	private String rolename;


	public User(String aUsername, String aPassword, String aPrename, 
			String aSurname, String aRolename){
		this.username = aUsername;
		this.password = aPassword;
		this.prename = aPrename;
		this.surname = aSurname;
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

	

