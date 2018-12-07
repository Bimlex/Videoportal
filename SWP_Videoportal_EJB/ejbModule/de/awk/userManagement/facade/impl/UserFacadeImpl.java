 package de.awk.userManagement.facade.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.awk.userManagement.dao.UserDAO;
import de.awk.userManagement.facade.IUserFacade;
import de.awk.userManagement.model.User;

@Stateless
public class UserFacadeImpl implements IUserFacade {

	@EJB 
	private UserDAO userDAO;
	
	public User findUserByName(String name) {
		return userDAO.findUserByUsername(name);
	}


	@Override
	public void saveUser(String aUsername, String aPassword, String aPrename, 
			String aSurname, String aRolename) {
		
		User aUser = new User(
				aUsername, 
				aPassword, 
				aPrename,
				aSurname, 
				aRolename);
		
		userDAO.save(aUser);
	}
	
	@Override
	public void updateUser(String aUsername, String aPassword, String aPrename, 
			String aSurname, String aRolename) {
		
		User aUser = this.findUserByName(aUsername);
		
		aUser.setUsername(aUsername);
		if(!aPassword.isEmpty()) {
			aUser.setPassword(aPassword);
		} 
		aUser.setPrename(aPrename);
		aUser.setSurname(aSurname);
		aUser.setRolename(aRolename);
		userDAO.save(aUser);
	}
	
	@Override
	public List<User> getAllUser() {
		return userDAO.findAll();
	}
		
	@Override
	public List<User> findUsersByUsername(String name) {
		return userDAO.findUsersByUsername(name);
	}
	
	@Override
	public List<User> findUsersByPrename(String name) {
		return userDAO.findUsersByPrename(name);
	}
	
	@Override
	public List<User> findUsersBySurname(String name) {
		return userDAO.findUsersBySurname(name);
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
	
	@Override
	public String get_SHA_512_SecurePassword(String passwordToHash, String salt) {
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