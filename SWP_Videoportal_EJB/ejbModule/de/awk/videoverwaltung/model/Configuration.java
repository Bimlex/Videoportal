package de.awk.videoverwaltung.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "swp_configuration")
@NamedQueries({
		@NamedQuery(name = "Configuration.findConfigOutputByID", query = "SELECT c FROM Configuration c WHERE c.id = :id") })
public class Configuration {

	public static final String FIND_OUTPUT_BY_ID = "Configuration.findConfigOutputByID";

	@Id
	private int id;
	private String output;

	public Configuration() {
	}

	public Configuration(String output) {
		this.output = output;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
