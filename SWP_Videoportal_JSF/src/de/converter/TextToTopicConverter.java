package de.converter;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import de.awk.videoverwaltung.model.Topic;
import de.mb.TopicMB;

@SessionScoped
@FacesConverter("de.converter.TextToTopicConverter")
public class TextToTopicConverter implements Converter{

	@Override
	public Topic getAsObject(FacesContext facesContext, UIComponent uiComponent, String uiValue) {
		FacesContext context = FacesContext.getCurrentInstance();
		TopicMB myBean = context.getApplication().evaluateExpressionGet(context, "#{topicMB}", TopicMB.class);
		Topic aTopic = myBean.getTopicById(Integer.valueOf(uiValue));
		return aTopic;
	}


	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComonent, Object obj) {
		return (obj == null) ? null : Long.toString(((Topic) obj).getTopicId());		
	}


}
