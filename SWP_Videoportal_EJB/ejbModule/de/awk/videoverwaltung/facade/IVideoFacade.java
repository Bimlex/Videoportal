package de.awk.videoverwaltung.facade;

import java.io.File;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.Part;

import de.awk.videoverwaltung.model.Video;

@Local
public interface IVideoFacade {

	public abstract List<Video> getAllVideos();

	public abstract Video getVideoById(int videoId);
	
	public abstract Video findVideoById(int aVideoId);

	public abstract void updateVideo(Integer aVideoId, String aVideoname, String topic, String subcategory, String description);

	// Paul
	public abstract boolean uploadVideo(File file,Part fileToUpload, String name, String description,String subcategory);

	public abstract void deleteVideo(int id);

	public abstract Video findVideoByName(String videoname);


}
