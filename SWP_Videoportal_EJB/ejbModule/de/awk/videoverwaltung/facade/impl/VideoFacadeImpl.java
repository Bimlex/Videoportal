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
	public void updateVideo(String aVideoname, String topic, String subcategory, String description) {
	
		Video aVideo = this.findVideoByName(aVideoname);
		
		aVideo.setName(aVideoname);
		aVideo.setTopic(topic);
		aVideo.setSubcategory(subcategory);
		aVideo.setDescription(description);
		videoDAO.save(aVideo);
	}
	

	// Paul
	@Override
	public void uploadVideo(String name, String description, String aPath) {
		Video video = new Video(name, "topic", "subcategory", description, aPath);
		videoDAO.save(video);
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
		return videoDAO.findVideoByName(videoname);
	}





}
