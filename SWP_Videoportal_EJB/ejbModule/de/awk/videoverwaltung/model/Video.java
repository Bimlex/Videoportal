package de.awk.videoverwaltung.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name="swp_video")
@NamedQueries({
	@NamedQuery(name="Video.findVideoByName", query="SELECT v FROM Video v WHERE v.name = :videoname"),
	@NamedQuery(name="Video.findVideoById", query="SELECT v FROM Video v WHERE v.videoId = :videoId"),
	@NamedQuery(name="Video.findListVideosBySubcategoryId", query="SELECT v FROM Video v WHERE v.subcategoryId = :subcategoryId"),
	@NamedQuery(name="Video.findListVideosbySubcategoryIdAndName", query="SELECT v FROM Video v WHERE v.subcategoryId = :subcategoryId AND v.name= :name"),
	@NamedQuery(name="Video.findListVideosBySubcategoryIdAndDescription", query="SELECT v FROM Video v WHERE v.subcategoryId = :subcategoryId AND v.description= :description"),
	
	@NamedQuery(name="Video.findListVideosBySearchfieldInput", query="SELECT v FROM Video v, Subcategory s, Topic t WHERE v.subcategoryId=s.subcategoryId AND s.topicId= t.topicId AND (v.name LIKE :name OR v.description LIKE :description OR s.name LIKE :name OR s.description LIKE :description OR t.name LIKE :name OR t.description LIKE :description) group by v.videoId")
	

	
})
public class Video implements Serializable {

	private static final long serialVersionUID = 116145947726388767L;
	/**
	 * 
	 */

	public static final String FIND_BY_VIDEONAME = "Video.findVideoByName";
	public static final String FIND_VIDEO_BY_ID = "Video.findVideoById";
	public static final String FIND_LIST_VIDEOS_BY_SUBCATEGORYID = "Video.findListVideosBySubcategoryId";
	public static final String FIND_LIST_VIDEOS_BY_SUBCATEGORYID_AND_NAME = "Video.findListVideosbySubcategoryIdAndName";
	public static final String FIND_LIST_VIDEOS_BY_SUBCATEGORYID_AND_DESCRIPTION = "Video.findListVideosBySubcategoryIdAndDescription";
	public static final String FIND_LIST_VIDEOS_BY_SEARCHFIELDINPUT = "Video.findListVideosBySearchfieldInput";
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_VIDEO_ID")
	@SequenceGenerator(name="SEQ_VIDEO_ID", sequenceName="SEQ_VIDEO_ID", allocationSize = 1)
	private Integer videoId;
	private String name;
//	private String topic;
	private Integer subcategoryId;
	private String description;
	private String path;
	
	public Video () {}

	public Video(String name, /*String topic,*/ Integer subcategoryId, String description, String path) {
		this.setName(name);
	//	this.setTopic(topic);
		this.setSubcategoryId(subcategoryId);
		this.setDescription(description);
		this.setPath(path);
	}


	public Integer getVideoId() {
		return videoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

//	public String getTopic() {
//		return topic;
//	}
//
//	public void setTopic(String topic) {
//		this.topic = topic;
//	}
//
//	public String getSubcategory() {
//		return subcategory;
//	}
//
//	public void setSubcategory(String subcategory) {
//		this.subcategory = subcategory;
//	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public Integer getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Integer subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	
	


}
