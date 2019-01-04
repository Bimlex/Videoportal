package de.awk.videoverwaltung.model;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "swp_configuration")
@NamedQueries({
		@NamedQuery(name = "Configuration.findConfigOutputByID", query = "SELECT c FROM Configuration c WHERE c.video_id = :video_id")		})
public class Configuration {

	public static final String FIND_OUTPUT_BY_ID = "Configuration.findConfigOutputByID";

	@Id
	private int video_id;
	private String video_typ;
	private String output;

	public Configuration() {
	}

	public Configuration(String video_typ, String output) {
		this.video_typ = video_typ;
		this.output = output;
	}

	public String getVideo_typ() {
		return video_typ;
	}

	public void setVideo_typ(String video_typ) {
		this.video_typ = video_typ;
	}




	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public int getVideo_id() {
		return video_id;
	}

	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}
	
	

}
