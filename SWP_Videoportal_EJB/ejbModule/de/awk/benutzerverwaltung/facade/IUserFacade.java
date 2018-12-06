package de.awk.benutzerverwaltung.facade;

import java.util.List;

import javax.ejb.Local;

import de.awk.benutzerverwaltung.model.User;

@Local
public interface IUserFacade {
	public User findUserByName(String name);
	public abstract void saveUser(String aUsername, String aPassword, String aPrename, String aSurname, String aRolename);
	public abstract List<User> getAllUser();
	public abstract void deleteUser(String aUsername);
	public abstract void updateUser(String aUsername, String aPassword, String aPrename, String aSurname, String aRolename);
	public abstract List<String> getRoleSelection();
	public abstract List<User> findUsersByUsername(String name);
	public abstract List<User> findUsersByPrename(String name);
	public abstract List<User> findUsersBySurname(String name);	
}