 package de.awk.benutzerverwaltung.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
			String aNachname, String aRolename) {
		
		User aUser = new User(
				aUsername, 
				aPassword, 
				aVorname,
				aNachname, 
				aRolename);
		
		userDAO.save(aUser);
	}
	
	@Override
	public void updateUser(String aUsername, String aPassword, String aVorname, 
			String aNachname, String aRolename) {
		
		User aUser = this.findUserByName(aUsername);
		
		aUser.setUsername(aUsername);
		if(!aPassword.isEmpty()) {
			aUser.setPassword(aPassword);
		} 
//		else {
//			aUser.setPassword(aUser.getPassword());
//		}
		aUser.setVorname(aVorname);
		aUser.setNachname(aNachname);
		aUser.setRolename(aRolename);
		userDAO.save(aUser);
	}
	
	@Override
	public List<User> getAllUser() {
		return userDAO.findAll();
	}
	
	@Override
	public List<User> getUserByName(String name) {
		return userDAO.getUserByName(name);
	}
		
	@Override
	public void deleteUser(String aUsername){
		User aUser = this.findUserByName(aUsername);
		try{
			userDAO.delete(aUser);
		} catch (Exception e) {
			
		}
		
	}
	
	@Override
	public List<String> getRoleSelection(){
		List<String> roleSelection = new ArrayList<String>();
		roleSelection.add("Benutzer");
		roleSelection.add("Administrator");
		return roleSelection;
	}
	

	
}