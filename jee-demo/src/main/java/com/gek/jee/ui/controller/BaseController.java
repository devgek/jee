package com.gek.jee.ui.controller;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

@UIController
public class BaseController {
	public void setMessage(Severity severity, String text) {
		FacesMessage message = new FacesMessage(severity, text, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
