package com.gek.jee.ui.controller;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import com.gek.jee.ui.model.LoginUIModel;

@UIController
public class LoginController extends BaseController{
	@Inject
	LoginUIModel uiModel;
	
	public String login() {
		if (uiModel.getUser() != null && uiModel.getUser().equals("demo") && uiModel.getPass() != null && uiModel.getPass().equals("demo")) {
			return "start.xhtml";
		}
		
		setMessage(FacesMessage.SEVERITY_ERROR, "Illegal Login!");
		return "";
	}
}
