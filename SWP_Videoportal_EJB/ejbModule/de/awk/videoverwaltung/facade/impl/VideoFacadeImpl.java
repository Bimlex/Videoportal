package de.awk.videoverwaltung.facade.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.Part;

import com.sun.org.apache.regexp.internal.recompile;

import de.awk.videoverwaltung.dao.VideoDAO;
import de.awk.videoverwaltung.facade.IVideoFacade;
import de.awk.videoverwaltung.facade.impl.util.Converter;
import de.awk.videoverwaltung.facade.impl.util.Directory;
import de.awk.videoverwaltung.facade.impl.util.IdGen;
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

	Converter converter = new Converter();

	@Override
	public void updateVideo(Integer aVideoId, String aVideoname, /*String topic,*/ Integer subcategoryId, String description) {
		Video aVideo = this.findVideoById(aVideoId);
		aVideo.setVideoId(aVideoId);
		aVideo.setName(aVideoname);
//		aVideo.setTopic(topic);
		aVideo.setSubcategoryId(subcategoryId);
		aVideo.setDescription(description);
		videoDAO.save(aVideo);
	}

	// Paul
	@Override
	public boolean uploadVideo(File file, Part fileToUpload, String name, String description, int subcategoryId) {
		boolean ok = false;
		try {
			if (converter.uploadNewVideo(file, fileToUpload)) {
				Video video = new Video(name,/* "topic,"*/ subcategoryId, description, converter.getVideoPath());
				videoDAO.save(video);
				System.out.println("Ein Bier trinken");
				ok = true;
			}
		} catch (IOException e) {
			System.out.println("Ein heftiger Fehler");
			e.printStackTrace();
			ok = false;
		}
		return ok;
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

	@Override
	public Video findVideoById(int aVideoId) {
		return videoDAO.findVideoById(aVideoId);
	}

	public VideoDAO getVideoDAO() {
		return videoDAO;
	}

	public void setVideoDAO(VideoDAO videoDAO) {
		this.videoDAO = videoDAO;
	}

	@Override
	public List<Video> findVideosBySubcategoryId(int subcategoryId) {
		return videoDAO.findVideosBySubcategoryId(subcategoryId);
	}

	@Override
	public List<Video> findVideosByNameAndSubcategoryId(int subcategoryId, String name) {
		return videoDAO.findVideosByNameAndSubcategoryId(subcategoryId,name);
	}

	@Override
	public List<Video> findVideosByDescriptionAndSubcategoryId(int subcategoryId, String description) {
		return videoDAO.findVideosByDescriptionAndSubcategoryId(subcategoryId, description);
	}

	@Override
	public List<Video> findVideosBySearchInput(String searchField) {
		return videoDAO.findVideosBySearchInput(searchField);
	}

}
