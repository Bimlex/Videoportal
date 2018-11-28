package de.awk.videoverwaltung.dao;

import javax.ejb.Stateless;

import de.awk.ressourcenverwaltung.dao.GenericDAO;
import de.awk.videoverwaltung.model.Video;


@Stateless
public class VideoDAO  extends GenericDAO<Video> {
	
	public VideoDAO(){
		super(Video.class);
	}
	
	public void delete(Video aVideo){
		super.delete(aVideo.getVideoId(), Video.class);
	}
}
