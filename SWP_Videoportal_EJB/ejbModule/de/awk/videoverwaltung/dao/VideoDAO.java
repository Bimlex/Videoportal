package de.awk.videoverwaltung.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import de.awk.videoverwaltung.dao.GenericDAO;
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
	
}
