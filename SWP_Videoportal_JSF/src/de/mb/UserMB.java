package de.mb;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import de.awk.facade.IUserFacade;
import de.awk.model.User;

@SessionScoped
@ManagedBean(name="userMB")
public class UserMB {

	private User user;
	
	LocalDateTime startTime = LocalDateTime.now();
	int clicks = 0;
	
	//Änderung  13:32
	//Test für GitHub

	@EJB
	private IUserFacade userFacade;
	

	public User getUser(){
		if(user == null){
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			String username = context.getUserPrincipal().getName();
			
			user = userFacade.findUserByName(username);
		}
		return user;
	}
	
	public boolean isUserAdmin(){
		return getRequest().isUserInRole("ADMIN_ROLE");
	}
	
	public boolean isUserUser(){
		return getRequest().isUserInRole("USER_ROLE");
	}
	
	public int getTimesClicked(){
		return clicks;
//		return user.getTimesClicked();
	}
	
	public void resetTimesClicked(){
		userFacade.setTimesClickedForLastLogin(user.getUsername(), 0);
	}
	
	public void resetMinutesLoggedIn(){
		userFacade.setMinutesLoggedIn(user.getUsername(), 0);
	}
	
	public void userClicked(){
		// Da nur die Klicks des USERS und nicht des ADMINS gezaehlt werden sollen wird diese Funktion auch nur bei Buttons
		// hinterlegt, auf die der USER zugreifen kann. Bei den Buttons des ADMINS wird diese Funktion nicht ausgefuehrt
		clicks += 1;

	}
	
	public int getMinutesLoggedIn(){
		Duration duration = Duration.between(startTime, LocalDateTime.now());
		int diff = (int) Math.abs(duration.toMinutes());
		user.setMinutesLoggedIn(diff);
		
		return user.getMinutesLoggedIn();
	}
	
	public void preLogout(){
		userFacade.setTimesClickedForLastLogin(user.getUsername(), clicks);
		userFacade.setMinutesLoggedIn(user.getUsername(), getMinutesLoggedIn());
	}
	
	public String logOut(){
		System.out.println("logout");
		preLogout();
		getRequest().getSession().invalidate();
		return "logout";
	}
	
	public void reset(){
		getRequest().getSession().invalidate();
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
}
