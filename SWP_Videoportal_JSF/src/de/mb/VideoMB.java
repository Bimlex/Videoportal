package de.mb;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.awk.userManagement.model.User;
import de.awk.videoverwaltung.facade.ISubcategoryFacade;
import de.awk.videoverwaltung.facade.IVideoFacade;
import de.awk.videoverwaltung.model.Video;

@RequestScoped
@ManagedBean(name = "videoMB")
public class VideoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8157369186196474832L;

	private Video video;

	// private Integer videoId;
	// private String name;
	// private String topic;
	// private String subcategory;
	// private String description;
	// private String path;

	private String searchOption;

	@ManagedProperty(value = "#{searchField}")
	private String searchField;

	private List<Video> videoList = null;
	@EJB
	private IVideoFacade videoFacade;

	@EJB
	private ISubcategoryFacade subcategoryFacade;

	@NotNull
	@Digits(fraction = 0, integer = 6)
	private Integer subcategoryId;

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

	// public String setVideoIdForVideoView(int aVideoId) {
	//
	// this.videoId = aVideoId;
	//
	// return "videoWatch";
	// }

	public String setVideoIdForVideoWatch(int aVideoId) {
		System.out.println("die gewählte videoId ist " + aVideoId);

		Video aVideo = this.videoFacade.findVideoById(aVideoId);

		if (aVideo != null) {
			this.videoId = aVideo.getVideoId();
			this.name = aVideo.getName();
			this.description = aVideo.getDescription();
			this.subcategoryId = aVideo.getSubcategoryId();
			this.path = aVideo.getPath();

			System.out.println("folgendes Video wurde gewählt: VideoID ist: " + this.getVideoId() + " beschreibung:  "
					+ this.getDescription() + " name:  " + this.getName() + " path: " + this.getPath());

			return "videoWatch";
		}
		return "";

	}

	public List<Video> initialiseVideoListBySubcategoryList() {
		this.videoList = null;

		if (this.searchField == null || this.searchField.equals("")) {
			videoList = videoFacade.getAllVideos();
		} else {
			if (searchOption == null || searchOption.equals("")) {
				searchOption = "VideosID";
			}

			switch (searchOption) {
			case "VideosID":
				videoList = videoFacade.findVideosBySubcategoryId(Integer.parseInt(this.searchField));
				break;
			}
		}
		System.out.println(subcategoryId);
		return videoList;
	}

	public List<Video> initialiseVideoListBySubcategoryId() {
		this.videoList = null;

		if (this.searchField == null || this.searchField.equals("")) {
			videoList = videoFacade.findVideosBySubcategoryId(this.subcategoryId);
		} else {
			if (searchOption == null || searchOption.equals("")) {
				searchOption = "Name";
			}

			switch (searchOption) {
			case "Name":
				this.videoList = null;
				System.out.println("Dies ist die subId wenn man nach Name filtert = " + subcategoryId);
				System.out.println("subcategory = " + subcategoryId + " --------------------------------- "
						+ " SUCHFELD: " + this.searchField);
				videoList = videoFacade.findVideosByNameAndSubcategoryId(this.subcategoryId, this.searchField);
				break;
			case "Description":
				this.videoList = null;
				System.out.println("Dies ist die TopicId wenn man nach Beschreibung filtert = " + subcategoryId);
				System.out.println("TOPICID = " + subcategoryId + " --------------------------------- " + " SUCHFELD: "
						+ this.searchField);
				videoList = videoFacade.findVideosByDescriptionAndSubcategoryId(this.subcategoryId, this.searchField);
				break;
			}
		}
		System.out.println(subcategoryId);
		return videoList;
	}

	// public List<Video> initialiseVideoListBySubcategoryId(){
	// this.videoList = null;
	//
	//
	//
	//
	// if (this.searchField == null) {
	// videoList = videoFacade.findVideosBySubcategoryId(this.subcategoryId);
	// } else if (this.searchField.equals("")) {
	// videoList = videoFacade.findVideosBySubcategoryId(this.subcategoryId);
	// } else {
	// if (searchOption == null) {
	// searchOption = "Name";
	// }
	//
	// if (searchOption.equals("")) {
	// searchOption = "Name";
	// }
	//
	// switch (searchOption) {
	// case "Name":
	// videoList = videoFacade.findVideosByNameAndSubcategoryId(this.subcategoryId,
	// this.searchField);
	// break;
	// case "Description":
	// videoList =
	// videoFacade.findVideosByDescriptionAndSubcategoryId(this.subcategoryId,
	// this.searchField);
	// break;
	// }
	// }
	// System.out.println("erstes video der liste: "+ videoList.get(0)+"-----
	// zweites: "+ videoList.get(1));
	// return videoList;
	// }
	//

	public void printTestNachricht() {
		System.out.println("xkjsahdfkls--------------------------------------jhadflksajhdflsakjhfdlaksdf");
	}

	public String setSubcategoryIdForVideoSearch(int aTopicId, int aSubcategoryId) {

		System.out.println("++++++++++############ gewaehlte SubcategoryId ist: " + aSubcategoryId + "\n"
				+ "+++++++++########### ausgewaehlte topicID " + aTopicId + "\n");
		this.subcategoryId = aSubcategoryId;
		System.out.println("++++++++++############ gewaehlte SubcategoryId ist: " + aSubcategoryId + "\n");
		return "videoSearchVideo";
	}

	public String updateVideo() {

		// if (this.videoId.isEmpty()) {
		// sendInfoMessageToUser("Es wurde keine ID vergeben");
		// return "";
		// }

		if (this.name.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Videoname vergeben");
			return "";
		}

		// if (this.topic.isEmpty()) {
		// sendInfoMessageToUser("Es wurde kein Themenbereich vergeben");
		// return "";
		// }
		if ((this.subcategoryId == null)) {
			sendInfoMessageToUser("Es wurde kein Kategorie vergeben");
			return "";
		}

		if (this.description.isEmpty()) {
			sendInfoMessageToUser("Es wurde kein Beschreibung vergeben");
			return "";
		}

		videoFacade.updateVideo(this.videoId, this.name, /* this.topic, */ this.subcategoryId, this.description);

		// initialiseVideoList();

		return "backToVideoAdministration";

	}

	public List<Video> initialiseVideoList() {
		this.videoList = null;

		// this.searchField ="apfel";

		if (this.searchField == null || this.searchField.equals("")) {
			videoList = videoFacade.getAllVideos();
		} else {
			System.out.println("Das ist das ********* das SearchValue : " + searchField);
			videoList = videoFacade.findVideosBySearchInput(this.searchField);

		}
		System.out.println("es wurden " + videoList.size() + " videos gefunden");
		System.out.println("*************" + this.searchField);

		return videoList;
	}

	// public void setTopic(String topic) {
	// this.topic = topic;
	// }

	private void sendInfoMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
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
		// this.topic = aVideo.getTopic();
		this.subcategoryId = aVideo.getSubcategoryId();

		return "bestehendesVideoAendern";
	}

	public void deleteVideo(Video aVideo) {
		this.videoFacade.deleteVideo(aVideo.getVideoId());
	}

	// public Integer getVideoId() {
	// return videoId;
	// }

	// public String getTopic() {
	// return topic;
	// }

	// public String getSubcategory() {
	// return subcategory;
	// }

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
	private String path;

	@NotNull
	@Size(min = 1, max = 200)
	@Pattern(regexp = "[A-Za-z0-9_äÄöÖüÜß ]*")
	private String name;

	@NotNull
	@Size(min = 1, max = 300)
	@Pattern(regexp = "[A-Za-z0-9_äÄöÖüÜß ]*")
	private String description;

	private Part fileToUpload;
	private File file;

	private String output;
	private String typ = "mp4";

	public boolean upload() throws IOException {
		System.out.println("methide upload() gestartet");
		// System.out.println(subcategoryFacade.findSubcategoryByName(name).getSubcategoryId());
		if (videoFacade.uploadVideo(this.file, this.fileToUpload, this.name, this.description, this.subcategoryId,
				this.output, this.typ))
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}

	public Integer getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Integer subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}
