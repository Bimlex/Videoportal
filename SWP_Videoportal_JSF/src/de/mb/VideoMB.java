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
import de.mb.util.Directory;
import de.mb.util.IdGen;

@SessionScoped
@ManagedBean(name="videoMB")
public class VideoMB {

	private Video video;

//	private Integer videoId;
//	private String name;
	private String topic;
	private String subcategory;
//	private String description;
//	private String path;
	
	private List<Video> videoList = null;
	@EJB
	private IVideoFacade videoFacade;

//	public Video getVideo(){
//		if(video == null){
//			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//			String videoname = context.getUserPrincipal().getName();
//			
//			video = videoFacade.findVideoByName(videoname);
//		}
//		return video;
//	}
	
	public String updateVideo() {

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

		
		videoFacade.updateVideo(this.name, this.topic, this.subcategory, this.description);

//		initialiseVideoList();

		return "zurueckZumVideoMenue";

	}
	
//	private void initialiseVideoList() {
//
//		if (this.searchField == null) {
//			videoList = videoFacade.getAllVideos();
//		} else if (this.searchField.equals("")) {
//			videoList = videoFacade.getAllVideos();
//		} else {
//			if(searchOption == null) {
//				searchOption = "Username";
//			}
//			
//			if(searchOption.equals("")) {
//				searchOption = "Username";
//			}
//
//			switch (searchOption) {
//			case "Username":
//				videoList = videoFacade.findVideoByName(this.searchField);
//				break;
//			case "Vorname":
//				videoList = videoFacade.findVideoByName(this.searchField);
//				break;
//			case "Nachname":
//				videoList = videoFacade.findVideoByName(this.searchField);
//				break;
//			}
//
//		}
//		
//		for (User aUser : userList) {
//			aUser.setPassword("*********");
//		}
//		
//		return userList;
//	}

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
		System.out.println("-----------" + videoList.size() + "----------------" );
		Video aVideo = this.videoFacade.findVideoByName(aVideoname);
		
		this.videoId= aVideo.getVideoId();
		this.name = aVideo.getName();
		this.description = aVideo.getDescription();
		this.topic = aVideo.getTopic();
		this.subcategory = aVideo.getSubcategory();

		return "bestehendesVideoAendern";
	}
	
	public void deleteVideo(Video aVideo) {
		this.videoFacade.deleteVideo(aVideo.getVideoId());
	}

//	public Integer getVideoId() {
//		return videoId;
//	}


	public String getTopic() {
		return topic;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public String getDescription() {
		return description;
	}

//	public String getPath() {
//		return path;
//	}

	public List<Video> getVideoList() {
		return videoList;
	}

	 public IVideoFacade getVideoFacade() {
	 return videoFacade;
	 }
	
	
	 
	
	
	
	//Paul


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
	private Directory directory = new Directory();

	/**
	 * 0. @video_typ that can be any typ of videos 1. @buffering_folder path Buffer
	 * folder 2. @output_folder converter folder 3. @id do not change
	 * 4. @full_path_of_video do not change 5. @full_path_of_output_video do not
	 * change
	 */
	private String video_typ = "." + "mp4";
	private String saveVideoFolder = "Videoportal";

	public String unique_id() {
		IdGen idGen = new IdGen();
		String id = idGen.unique_id();
		return id;
	}

	public String uploadNewVideo() {
		videoFacade.uploadVideo(this.name, this.description, this.videoPath);
		return saveVideoFolder;

	}

	public String upload() throws IOException {
		String buffering_folder = directory.buffering_folder();
		String full_path_of_video = buffering_folder + getFilename(fileToUpload);
		fileToUpload.write(full_path_of_video);
		System.out.println(full_path_of_video);
		if (converter(full_path_of_video)) {
			file = new File(full_path_of_video);
			if (file.delete()) {
				System.out.println("File " + full_path_of_video + " deleted");
			} else
				System.out.println("File " + full_path_of_video + " doesn't exists");
			return "success";
		} else {
			return "unsuccess";
		}
	}

	private Boolean converter(String path) throws IOException {
		String id = unique_id();
		String output_folder = directory.output_folder(saveVideoFolder);
		String full_path_of_output_video = output_folder + id;
		setVideoPath(full_path_of_output_video);
		String command = "cmd /c ffmpeg.exe -i \"" + path + "\" -y \"" + full_path_of_output_video + video_typ
				+ "\" -loglevel quiet";
		System.out.println(command);
		try {
			executeProcess(command);
			uploadNewVideo();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				String filenames = filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1);
				return filenames;
			}
		}
		return null;
	}

	/**
	 * Create and Execute Process for each command
	 */
	static Process executeProcess(String command) throws InterruptedException, IOException {
		Process clipProcess = Runtime.getRuntime().exec(command);
		clipProcess.waitFor();
		return clipProcess;
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

	public Directory getDirectory() {
		return directory;
	}

	public void setDirectory(Directory directory) {
		this.directory = directory;
	}

	public String getVideo_typ() {
		return video_typ;
	}

	public void setVideo_typ(String video_typ) {
		this.video_typ = video_typ;
	}

	public String getSaveVideoFolder() {
		return saveVideoFolder;
	}

	public void setSaveVideoFolder(String saveVideoFolder) {
		this.saveVideoFolder = saveVideoFolder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
