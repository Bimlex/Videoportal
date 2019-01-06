package de.awk.videoverwaltung.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import de.awk.videoverwaltung.dao.GenericDAO;
import de.awk.videoverwaltung.model.Configuration;

@Stateless
public class ConfigDAO extends GenericDAO<Configuration> {

	public ConfigDAO() {
		super(Configuration.class);
	}

	public Configuration getConfigurationOutput() {

		System.out.println("We get a folder for output");

		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", 1);
		return super.findOneResult(Configuration.FIND_OUTPUT_BY_ID, parameters);
	}
}
