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
    	this.getEm().clear();
    	// Wird u.a. benutzt um User zu loeschen
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("username", name);
	return super.findOneResult(User.FIND_BY_USERNAME, parameters);
    }
    
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // TESTEN WIE findUserByUsername FÜR EINE LISTE FUNKTIONIERT
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // https://forum.primefaces.org/viewtopic.php?t=8098
    // Evtl. funktioniert die NamedQuery besser
    
    public List<User> findUsersByUsername(String name) {
    	this.getEm().clear();
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("username", name + "%");
    	return (List<User>) this.findAllResult(User.FIND_LIST_BY_USERNAME, parameters);
    	
    }
    
    public List<User> findUsersByPrename(String name) {
    	this.getEm().clear();
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("vorname", name + "%");
    	return (List<User>) this.findAllResult(User.FIND_LIST_BY_PRENAME, parameters);
    	
    }
    
    public List<User> findUsersBySurname(String name) {
    	this.getEm().clear();
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("nachname", name + "%");
    	return (List<User>) this.findAllResult(User.FIND_LIST_BY_SURNAME, parameters);
    	
    }
    
    
	public List<User> getUsersByUsername(String name) {
		// wird u.a. benutzt um die Uebersichtsliste der User zu filtern
		this.getEm().clear();
		TypedQuery<User> query = this.getEm().createQuery(
				"SELECT u FROM User u WHERE u.username like :name", User.class);
		name = name + "%";
		return query.setParameter("name", name).getResultList();
	}    
	
	public List<User> getUsersByPrename(String name) {
		// wird u.a. benutzt um die Uebersichtsliste der User zu filtern
		this.getEm().clear();
		TypedQuery<User> query = this.getEm().createQuery(
				"SELECT u FROM User u WHERE u.vorname like :name", User.class);
		name = name + "%";
		return query.setParameter("name", name).getResultList();
	}    
	
	public List<User> getUsersBySurname(String name) {
		// wird u.a. benutzt um die Uebersichtsliste der User zu filtern
		this.getEm().clear();
		TypedQuery<User> query = this.getEm().createQuery(
				"SELECT u FROM User u WHERE u.nachname like :name", User.class);
		name = name + "%";
		return query.setParameter("name", name).getResultList();
	}    
    
	public void delete(User aUser){
		super.delete(aUser.getUsername(), User.class);
	}
}