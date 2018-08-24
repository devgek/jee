package com.gek.jee.ui.controller;

import java.util.List;

import javax.inject.Inject;

import com.gek.jee.persistence.entity.Project;
import com.gek.jee.service.ProjectService;
import com.gek.jee.ui.model.ProjectUIModel;

@UIController
public class ProjectController{
	@Inject
	ProjectUIModel uiModel;
	@Inject
	ProjectService projectService;
	
	public void getProjects() {
		List<Project> projects = projectService.getProjects();
		uiModel.setProjects(projects);
	}
}
