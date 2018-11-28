 package de.awk.videoverwaltung.facade.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.awk.videoverwaltung.dao.VideoDAO;
import de.awk.videoverwaltung.facade.IVideoFacade;
import de.awk.videoverwaltung.model.Topic;
import de.awk.videoverwaltung.model.Video;


@Stateless
public class VideoFacadeImpl implements IVideoFacade {

	@EJB 
	private VideoDAO videoDAO;

	@Override
	public List<Video> getAllVideos() {
		System.out.println(videoDAO.findAll().size());
		return videoDAO.findAll();
	}

	@Override
	public Video getVideoById(int videoId) {
		return videoDAO.find(videoId);
	}

	@Override
	public void updateVideo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uploadVideo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVideo(int videoId) {
		Video aVideo = this.getVideoById(videoId);
		try {
			videoDAO.delete(aVideo);
		} catch (Exception e) {
			
		}
	}

	@Override
	public Video findVideoByName(String videoname) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
