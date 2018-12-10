package de.mb;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import de.awk.videoverwaltung.facade.IVideoFacade;
import de.awk.videoverwaltung.model.Video;

@SessionScoped
@ManagedBean(name = "videoMB")
public class VideoMB {

	private Video video;

	// private Integer videoId;
	// private String name;
	private String topic;
	private String subcategory;
	// private String description;
	// private String path;

	private List<Video> videoList = null;
	@EJB
	private IVideoFacade videoFacade;

	// public Video getVideo(){
	// if(video == null){
	// ExternalContext context =
	// FacesContext.getCurrentInstance().getExternalContext();
	// String videoname = context.getUserPrincipal().getName();
	//
	// video = videoFacade.findVideoByName(videoname);
	// }
	// return video;
	// }

	public String updateVideo() {

		// if (this.videoId.isEmpty()) {
		// sendInfoMessageToUser("Es wurde keine ID vergeben");
		// return "";
		// }

		if (this.name.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Videoname vergeben");
			return "";
		}

		if (this.topic.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Themenbereich vergeben");
			return "";
		}
		if (this.subcategory.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Kategorie vergeben");
			return "";
		}

		if (this.description.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Beschreibung vergeben");
			return "";
		}

		videoFacade.updateVideo(this.videoId, this.name, this.topic, this.subcategory, this.description);

		// initialiseVideoList();

		return "zurueckZumVideoMenue";

	}

	// private void initialiseVideoList() {
	//
	// if (this.searchField == null) {
	// videoList = videoFacade.getAllVideos();
	// } else if (this.searchField.equals("")) {
	// videoList = videoFacade.getAllVideos();
	// } else {
	// if(searchOption == null) {
	// searchOption = "Username";
	// }
	//
	// if(searchOption.equals("")) {
	// searchOption = "Username";
	// }
	//
	// switch (searchOption) {
	// case "Username":
	// videoList = videoFacade.findVideoByName(this.searchField);
	// break;
	// case "Vorname":
	// videoList = videoFacade.findVideoByName(this.searchField);
	// break;
	// case "Nachname":
	// videoList = videoFacade.findVideoByName(this.searchField);
	// break;
	// }
	//
	// }
	//
	// for (User aUser : userList) {
	// aUser.setPassword("*********");
	// }
	//
	// return userList;
	// }

	public void setTopic(String topic) {
		this.topic = topic;
	}

	private void sendInfoMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public List<Video> getAllVideos() {
		videoList = videoFacade.getAllVideos();
		return videoList;
	}

	public String editVideo(String aVideoname) {
		System.out.println("---------------------------" + aVideoname);
		System.out.println("-----------" + videoList.size() + "----------------");
		Video aVideo = this.videoFacade.findVideoByName(aVideoname);

		this.videoId = aVideo.getVideoId();
		this.name = aVideo.getName();
		this.description = aVideo.getDescription();
		this.topic = aVideo.getTopic();
		this.subcategory = aVideo.getSubcategory();

		return "bestehendesVideoAendern";
	}

	public void deleteVideo(Video aVideo) {
		this.videoFacade.deleteVideo(aVideo.getVideoId());
	}

	// public Integer getVideoId() {
	// return videoId;
	// }

	public String getTopic() {
		return topic;
	}

	public String getSubcategory() {
		return subcategory;
	}

	// public String getDescription() {
	// return description;
	// }

	// public String getPath() {
	// return path;
	// }

	public List<Video> getVideoList() {
		return videoList;
	}

	public IVideoFacade getVideoFacade() {
		return videoFacade;
	}

	// Paul

	@NotNull
	@Digits(fraction = 0, integer = 6)
	private Integer videoId;

	@NotNull
	@Size(min = 1, max = 200)
	@Pattern(regexp = "[A-Za-z ]*")
	private String videoPath;

	@NotNull
	@Size(min = 1, max = 200)
	@Pattern(regexp = "[A-Za-z ]*")
	private String name;

	@NotNull
	@Size(min = 1, max = 300)
	@Pattern(regexp = "[A-Za-z ]*")
	private String description;

	private Part fileToUpload;
	private File file;

	public boolean upload() throws IOException {
		if (videoFacade.uploadVideo(this.file, this.fileToUpload, this.name, this.description,this.subcategory))
			return true;
		else {
			return false;
		}
	}

	public Part getFileToUpload() {
		return fileToUpload;
	}

	public void setFileToUpload(Part fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setVideoFacade(IVideoFacade videoFacade) {
		this.videoFacade = videoFacade;
	}

	public Video getVideo() {
		if (video == null) {
			video = new Video();
		}
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
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

}
