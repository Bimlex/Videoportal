package de.awk.benutzerverwaltung.facade;

import java.util.List;

import javax.ejb.Local;

import de.awk.benutzerverwaltung.model.User;

@Local
public interface IUserFacade {
	public User findUserByName(String name);
	public abstract void setTimesClickedForLastLogin(String aUsername,int aTimesClicked);
	public abstract void setMinutesLoggedIn(String aUsername, int aMinutesLoggedIn);
	void saveUser(String aUsername, String aPassword, String aVorname, String aNachname);
	List<User> getAllUser();
	User getUserById(int id);
	void deleteUser(int aId);
	void updateUser(int aId, String aUsername, String aPassword, String aVorname, String aNachname);
}