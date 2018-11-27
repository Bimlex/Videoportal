package de.awk.benutzerverwaltung.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
	return super.findOneResult(User.FIND_BY_USERNAME, parameters);
    }
    
	public List<User> getUserByName(String name) {
		TypedQuery<User> query = this.getEm().createQuery(
				"SELECT u FROM User u WHERE u.username = :name", User.class);
		return query.setParameter("name", name).getResultList();
	}    
    
	public void delete(User aUser){
		super.delete(aUser.getUsername(), User.class);
	}
}