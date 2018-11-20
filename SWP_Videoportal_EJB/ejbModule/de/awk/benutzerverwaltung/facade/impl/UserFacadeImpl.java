 package de.awk.benutzerverwaltung.facade.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.awk.benutzerverwaltung.dao.UserDAO;
import de.awk.benutzerverwaltung.facade.IUserFacade;
import de.awk.benutzerverwaltung.model.User;

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
}