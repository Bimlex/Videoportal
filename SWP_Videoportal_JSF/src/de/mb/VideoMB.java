package de.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import de.awk.benutzerverwaltung.model.User;
import de.awk.videoverwaltung.facade.IVideoFacade;
import de.awk.videoverwaltung.model.Video;

@SessionScoped
@ManagedBean(name="videoMB")
public class VideoMB {

	private Video video;

	private Integer videoId;
	private String name;
	private String topic;
	private String subcategory;
	private String description;
	private String path;
	
	private List<Video> videoList = null;
	@EJB
	private IVideoFacade videoFacade;

	public Video getVideo(){
		if(video == null){
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			String videoname = context.getUserPrincipal().getName();
			
			video = videoFacade.findVideoByName(videoname);
		}
		return video;
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	} 
	
	public List<Video> getAllVideos() {
		videoList = videoFacade.getAllVideos();
		return videoList;
	}

	public Integer getVideoId() {
		return videoId;
	}

	public String getName() {
		return name;
	}

	public String getTopic() {
		return topic;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public String getDescription() {
		return description;
	}

	public String getPath() {
		return path;
	}

	public List<Video> getVideoList() {
		return videoList;
	}

	public IVideoFacade getVideoFacade() {
		return videoFacade;
	}
	
	
	
}
