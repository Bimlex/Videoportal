package de.awk.benutzerverwaltung.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import de.awk.benutzerverwaltung.model.User;
import de.awk.projektverwaltung.model.Projekt;

@Stateless
public class UserDAO extends GenericDAO<User> {

    public UserDAO() {
    	super(User.class);
    }

    public User findUserByUsername(String name) {
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	System.out.println("Username: "+name);
    	parameters.put("username", name);
//    	System.out.println(parameters);
    	

	return super.findOneResult(User.FIND_BY_USERNAME, parameters);
    }
    
	public void delete(User aUser){
		super.delete(aUser.getUserId(), User.class);
	}
}