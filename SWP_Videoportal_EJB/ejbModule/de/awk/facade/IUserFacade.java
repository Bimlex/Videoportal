package de.awk.facade;

import javax.ejb.Local;

import de.awk.model.User;

@Local
public interface IUserFacade {
	public User findUserByName(String name);
	public abstract void setTimesClickedForLastLogin(String aUsername,int aTimesClicked);
	public abstract void setMinutesLoggedIn(String aUsername, int aMinutesLoggedIn);
}