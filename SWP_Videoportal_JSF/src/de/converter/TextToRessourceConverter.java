package de.converter;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import de.awk.ressourcenverwaltung.model.Maschine;
import de.awk.ressourcenverwaltung.model.MitarbeiterIn;
import de.awk.ressourcenverwaltung.model.Ressource;
import de.mb.MaschineMB;
import de.mb.MitarbeiterInMB;
import de.mb.RessourceMB;




@SessionScoped
@FacesConverter("de.converter.TextToRessourceConverter")
public class TextToRessourceConverter implements Converter {
	
	@Override
	public Ressource getAsObject(FacesContext facesContext, UIComponent uiComonent, String uiValue) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		RessourceMB myBean = context.getApplication().evaluateExpressionGet(context, "#{ressourceMB}", RessourceMB.class);
		Ressource aRessource = myBean.getRessourceById(Integer.valueOf(uiValue));
		return aRessource;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComonent, Object obj) {
		return (obj == null) ? null : Long.toString(((Ressource) obj).getRessourcenId());		
	}

}
