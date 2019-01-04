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

	public abstract void updateVideo(Integer aVideoId, String aVideoname, /*String topic,*/ Integer subcategoryId, String description);

	// Paul
	public abstract boolean uploadVideo(File file,Part fileToUpload, String name, String description,int subcategoryId,String output, String typ);

	public abstract void deleteVideo(int id);

	public abstract Video findVideoByName(String videoname);

	//Johannes
	public abstract List<Video> findVideosBySubcategoryId(int subcategoryId);

	public abstract List<Video> findVideosByNameAndSubcategoryId(int subcategoryId, String searchField);

	public abstract List<Video> findVideosByDescriptionAndSubcategoryId(int subcategoryId, String searchField);

	public abstract List<Video> findVideosBySearchInput(String searchField);


}
