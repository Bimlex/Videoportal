 package de.awk.benutzerverwaltung.facade.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.awk.benutzerverwaltung.dao.UserDAO;
import de.awk.benutzerverwaltung.facade.IUserFacade;
import de.awk.benutzerverwaltung.model.User;
import de.awk.projektverwaltung.model.Projekt;

@Stateless
public class UserFacadeImpl implements IUserFacade {

	@EJB 
	private UserDAO userDAO;
	
	public User findUserByName(String name) {
		return userDAO.findUserByUsername(name);
	}

	@Override
	public void setTimesClickedForLastLogin(String aUsername, int aTimesClicked) {
		User aUser = this.findUserByName(aUsername);
		aUser.setTimesClicked(aTimesClicked);
		userDAO.save(aUser);
	}

	@Override
	public void setMinutesLoggedIn(String aUsername, int aMinutesLoggedIn) {
		User aUser = this.findUserByName(aUsername);
		aUser.setMinutesLoggedIn(aMinutesLoggedIn);
		userDAO.save(aUser);
		
	}
	
	@Override
	public void saveUser(String aUsername, String aPassword, String aVorname, 
			String aNachname) {
		User aUser = new User(aUsername, aPassword, aVorname, 
			aNachname);
		userDAO.save(aUser);
	}
	
	@Override
	public void updateUser(int aId, String aUsername, String aPassword, String aVorname, 
			String aNachname) {
		User aUser = this.getUserById(aId);
		aUser.setUsername(aUsername);
		aUser.setPassword(aPassword);
		aUser.setVorname(aVorname);
		aUser.setNachname(aNachname);
		userDAO.save(aUser);
	}
	
	@Override
	public List<User> getAllUser() {
		return userDAO.findAll();
	}
	
	@Override
	public User getUserById(int id) {
		User aUser = null;
		try{
			aUser = userDAO.find(id);
		} catch (Exception e) {
			
		}
		return aUser;
	}
	
	@Override
	public void deleteUser(int aId){
		User aUser = this.getUserById(aId);
		try{
			userDAO.delete(aUser);
		} catch (Exception e) {
			
		}
		
	}
	

	
}