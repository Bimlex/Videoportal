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
    	// Wird u.a. benutzt um User zu loeschen
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	System.out.println("Username: "+name);
    	parameters.put("username", name);
	return super.findOneResult(User.FIND_BY_USERNAME, parameters);
    }
    
	public List<User> getUsersByUsername(String name) {
		// wird u.a. benutzt um die Uebersichtsliste der User zu filtern
		TypedQuery<User> query = this.getEm().createQuery(
				"SELECT u FROM User u WHERE u.username like :name", User.class);
		name = name + "%";
		return query.setParameter("name", name).getResultList();
	}    
	
	public List<User> getUsersByPrename(String name) {
		// wird u.a. benutzt um die Uebersichtsliste der User zu filtern
		TypedQuery<User> query = this.getEm().createQuery(
				"SELECT u FROM User u WHERE u.vorname like :name", User.class);
		name = name + "%";
		return query.setParameter("name", name).getResultList();
	}    
	
	public List<User> getUsersBySurname(String name) {
		// wird u.a. benutzt um die Uebersichtsliste der User zu filtern
		TypedQuery<User> query = this.getEm().createQuery(
				"SELECT u FROM User u WHERE u.nachname like :name", User.class);
		name = name + "%";
		return query.setParameter("name", name).getResultList();
	}    
    
	public void delete(User aUser){
		super.delete(aUser.getUsername(), User.class);
	}
}