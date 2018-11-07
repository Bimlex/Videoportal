package de.converter;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


import de.awk.projektverwaltung.model.Projekt;
import de.mb.ProjektMB;


@SessionScoped
@FacesConverter("de.converter.TextToProjektConverter")
public class TextToProjektConverter implements Converter {
	
	@Override
	public Projekt getAsObject(FacesContext facesContext, UIComponent uiComonent, String uiValue) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ProjektMB myBean = context.getApplication().evaluateExpressionGet(context, "#{projektMB}", ProjektMB.class);
		Projekt aProjekt = myBean.getProjektById(Integer.valueOf(uiValue));
		return aProjekt;

	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComonent, Object obj) {
		return (obj == null) ? null : Long.toString(((Projekt) obj).getProjektId());		
	}

}
