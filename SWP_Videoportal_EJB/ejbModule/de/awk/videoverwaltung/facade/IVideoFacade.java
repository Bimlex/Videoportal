package de.awk.videoverwaltung.facade;

import java.util.List;

import javax.ejb.Local;

import de.awk.videoverwaltung.model.Video;

@Local
public interface IVideoFacade {
	
	public abstract List<Video> getAllVideos();
	public abstract Video getVideoById(int videoId);
	public abstract void updateVideo();
	public abstract void uploadVideo();
	public abstract void deleteVideo(int id);
	public abstract Video findVideoByName(String videoname);
}
