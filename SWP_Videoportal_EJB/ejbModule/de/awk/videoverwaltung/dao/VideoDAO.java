package de.awk.videoverwaltung.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import de.awk.videoverwaltung.dao.GenericDAO;
import de.awk.videoverwaltung.model.Topic;
import de.awk.videoverwaltung.model.Video;


@Stateless
public class VideoDAO  extends GenericDAO<Video> {
	
	public VideoDAO(){
		super(Video.class);
	}
	
	public void delete(Video aVideo){
		super.delete(aVideo.getVideoId(), Video.class);
	}

	public Video findVideoByName(String videoname) {
    	this.getEm().clear();
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("videoname", videoname);
	return super.findOneResult(Video.FIND_BY_VIDEONAME, parameters);
    }

	public Video findVideoById(int videoId) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("videoId", videoId);
		return super.findOneResult(Video.FIND_VIDEO_BY_ID, parameters);
	}
	
	public List<Video> findVideosBySubcategoryId(int subcategoryId) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("subcategoryId", subcategoryId);
		return (List<Video>) this.findAllResult(Video.FIND_LIST_VIDEOS_BY_SUBCATEGORYID, parameters);
	}
	

	public List<Video> findVideosByNameAndSubcategoryId(int subcategoryId, String name) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("subcategoryId", subcategoryId);
		parameters.put("name", name);
		return (List<Video>) this.findAllResult(Video.FIND_LIST_VIDEOS_BY_SUBCATEGORYID_AND_NAME, parameters);
	}

	public List<Video> findVideosByDescriptionAndSubcategoryId(int subcategoryId, String description) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("subcategoryId", subcategoryId);
		parameters.put("description", description);
		return (List<Video>) this.findAllResult(Video.FIND_LIST_VIDEOS_BY_SUBCATEGORYID_AND_DESCRIPTION, parameters);
	}

	public List<Video> findVideosBySearchInput(String searchField) {
		this.getEm().clear();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", "%" + searchField + "%");
		parameters.put("description", "%" + searchField + "%");		

		return (List<Video>) this.findAllResult(Video.FIND_LIST_VIDEOS_BY_SEARCHFIELDINPUT, parameters);
	}
	
}
