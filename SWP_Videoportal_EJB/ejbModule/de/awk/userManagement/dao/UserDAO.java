package de.awk.userManagement.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.awk.userManagement.model.User;

@Stateless
public class UserDAO extends GenericDAO<User> {

    public UserDAO() {
    	super(User.class);
    }

    public User findUserByUsername(String name) {
    	this.getEm().clear();
    	// Wird u.a. benutzt um User zu loeschen
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("username", name);
	return super.findOneResult(User.FIND_BY_USERNAME, parameters);
    }
   
    
    public List<User> findUsersByUsername(String name) {
    	this.getEm().clear();
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("username", name + "%");
    	return (List<User>) this.findAllResult(User.FIND_LIST_BY_USERNAME, parameters);
    	
    }
    
    public List<User> findUsersByPrename(String name) {
    	this.getEm().clear();
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("prename", name + "%");
    	return (List<User>) this.findAllResult(User.FIND_LIST_BY_PRENAME, parameters);
    	
    }
    
    public List<User> findUsersBySurname(String name) {
    	this.getEm().clear();
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("surname", name + "%");
    	return (List<User>) this.findAllResult(User.FIND_LIST_BY_SURNAME, parameters);
    	
    }
        
	public void delete(User aUser){
		super.delete(aUser.getUsername(), User.class);
	}
}